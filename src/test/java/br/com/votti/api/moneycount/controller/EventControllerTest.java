package br.com.votti.api.moneycount.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProcessValidMap() throws Exception{

        MvcResult mvcResult = this.mockMvc.perform(post("/events/process-map")
                .contentType("application/json").content(getValidPayload()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("TST2019"))
                .andExpect(jsonPath("$.description").value("Test 2019"))
                .andExpect(jsonPath("$.currency.code").value("BRL"))
                .andExpect(jsonPath("$.currencyMap.remainingValue").value(new BigDecimal("0.0")))
                .andExpect(jsonPath("$.currencyMap.totalValue").value(new BigDecimal("398.0")))
                .andReturn();

        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }

    @Test
    public void testProcessInvalidMap() throws Exception{

        this.mockMvc.perform(post("/events/process-map").contentType("application/json").content(getInvalidPayload()))
                .andDo(print()).andExpect(status().isNotFound()).andReturn();
    }



    private String getValidPayload(){
        String validPayload = "{\n" +
                "  \"code\": \"TST2019\",\n" +
                "  \"currencyCode\": \"BRL\",\n" +
                "  \"description\": \"Test 2019\",\n" +
                "  \"entries\": [\n" +
                "    {\n" +
                "      \"code\": \"TST20190001\",\n" +
                "      \"description\": \"Test 2019 0001\",\n" +
                "      \"value\": 200.45\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"TST20190002\",\n" +
                "      \"description\": \"Test 2019 0002\",\n" +
                "      \"value\": 197.55\n" +
                "    }\n" +
                "  ],\n" +
                "  \"filter\": {\n" +
                "        \"values\": {\n" +
                "            \"1\": true,\n" +
                "            \"2\": true,\n" +
                "            \"5\": true,\n" +
                "            \"10\": true,\n" +
                "            \"20\": true,\n" +
                "            \"50\": true,\n" +
                "            \"100\": true,\n" +
                "            \"0.10\": true,\n" +
                "            \"0.25\": true,\n" +
                "            \"0.5\": true,\n" +
                "            \"0.05\": true\n" +
                "        }\n" +
                "    }\n" +
                "}";

        return validPayload;
    }

    private String getInvalidPayload(){
        String invalidPayload = "{\n" +
                "  \"code\": \"TST2019\",\n" +
                "  \"currencyCode\": \"FOO\",\n" +
                "  \"description\": \"Test 2019\",\n" +
                "  \"entries\": [\n" +
                "    {\n" +
                "      \"code\": \"TST20190001\",\n" +
                "      \"description\": \"Test 2019 0001\",\n" +
                "      \"value\": 200.45\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"TST20190002\",\n" +
                "      \"description\": \"Test 2019 0002\",\n" +
                "      \"value\": 197.55\n" +
                "    }\n" +
                "  ],\n" +
                "  \"filter\": {\n" +
                "        \"values\": {\n" +
                "            \"1\": true,\n" +
                "            \"2\": true,\n" +
                "            \"5\": true,\n" +
                "            \"10\": true,\n" +
                "            \"20\": true,\n" +
                "            \"50\": true,\n" +
                "            \"100\": true,\n" +
                "            \"0.10\": true,\n" +
                "            \"0.25\": true,\n" +
                "            \"0.5\": true,\n" +
                "            \"0.05\": true\n" +
                "        }\n" +
                "    }\n" +
                "}";

        return invalidPayload;
    }
}
