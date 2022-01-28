package org.example.practice.user.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.handler.data.DataListDto;
import org.example.practice.user.dto.UserDtoForListIn;
import org.example.practice.user.dto.UserDtoForSave;
import org.example.practice.user.dto.UserDtoForUpd;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getOneUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Иван"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("79019209899"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Петр"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.position").value("Менеджер"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}", 5)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Алексей"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.docName").value("Свидетельство о рождении"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}", 666)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/list")
                        .content(asJsonString(new UserDtoForListIn(1L, "Иван")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].position").value("Продавец"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].position").value("Менеджер"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/list")
                        .content(asJsonString(new UserDtoForListIn(1L)))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        DataListDto actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataListDto>() {});
        Assertions.assertEquals(3, actual.getData().size());
    }

    @Test
    void updUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/update")
                        .content(asJsonString(new UserDtoForUpd(2L, "Тестовое имя", "Тестировщик")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Тестовое имя"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.position").value("Тестировщик"));


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/update")
                        .content(asJsonString(new UserDtoForUpd(1L)))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest());


    }

    @Test
    void saveUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/save")
                        .content(asJsonString(new UserDtoForSave(2L, "Save", "Worker")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}", 6)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Save"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.position").value("Worker"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/save")
                        .content(asJsonString(new UserDtoForSave()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}