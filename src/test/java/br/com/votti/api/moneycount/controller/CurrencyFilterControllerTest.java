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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyFilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCurrencyFilterForNonExistingCode() throws Exception{

        this.mockMvc.perform(get("/currency-filters")
                .param("currency-code", "FOO")).andDo(print())
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void testGetCurrencyByCodeForUSD() throws Exception{

        MvcResult mvcResult = this.mockMvc.perform(get("/currency-filters")
                .param("currency-code", "BRL")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }
}
