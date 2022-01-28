package org.example.practice.country.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.handler.data.DataListDto;
import org.example.practice.user.dto.UserDtoForListIn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void countries() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/countries")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[3].name").value("Австралия"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[2].code").value("222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].name").value("США"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/countries")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        DataListDto actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataListDto>() {});
        Assertions.assertEquals(4, actual.getData().size());
    }
}