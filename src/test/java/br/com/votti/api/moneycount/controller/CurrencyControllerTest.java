package br.com.votti.api.moneycount.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllAvailableCurrencies() throws Exception{

        this.mockMvc.perform(get("/currencies")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/currencies")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(CoreMatchers.allOf(
                    CoreMatchers.containsString("BRL"),
                    CoreMatchers.containsString("USD")
            )));
    }

    @Test
    public void testGetCurrencyByCodeForBRL() throws Exception{

        this.mockMvc.perform(get("/currencies/BRL")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/currencies/BRL")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.allOf(
                        CoreMatchers.containsString("BRL"),
                        CoreMatchers.not(CoreMatchers.containsString("USD"))
                )));
    }

    @Test
    public void testGetCurrencyByCodeForUSD() throws Exception{

        this.mockMvc.perform(get("/currencies/USD")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/currencies/USD")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.allOf(
                        CoreMatchers.containsString("USD"),
                        CoreMatchers.not(CoreMatchers.containsString("BRL"))
                )));
    }
}
