package org.example.practice.typedoc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.handler.data.DataListDto;
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
class TypeDocControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void typeDocs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/docs")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name").value("Свидетельство о рождении"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].code").value("07"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[2].code").value("10"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[3].code").value("21"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[4].name").value("Иные документы"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/docs")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        DataListDto actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataListDto>() {});
        Assertions.assertEquals(5, actual.getData().size());
    }
}