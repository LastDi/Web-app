package org.example.practice.office.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.handler.data.DataListDto;
import org.example.practice.office.dto.OfficeDtoForListIn;
import org.example.practice.office.dto.OfficeDtoForSave;
import org.example.practice.office.dto.OfficeDtoForUpd;
import org.example.practice.user.dto.UserDtoForListIn;
import org.example.practice.user.dto.UserDtoForSave;
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
class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getOneOffice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/office/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Пятерочка"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("ул. Ленина, 23"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/office/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Realme"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("Highway street, 20"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/office/{id}", 666)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getOffices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/office/list")
                        .content(asJsonString(new OfficeDtoForListIn(1L, "true")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name").value("Пятерочка"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/office/list")
                        .content(asJsonString(new OfficeDtoForListIn(2L, "true")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        DataListDto actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataListDto>() {});
        Assertions.assertEquals(1, actual.getData().size());
    }

    @Test
    void updOffice() throws Exception {
        //Обновляем офис в БД
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/office/update")
                        .content(asJsonString(new OfficeDtoForUpd(1L, "Update Office", "Update Address")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));

        //Проверяем, как отработало обновление
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/office/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Update Office"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("Update Address"));

        //Попытка сохранить офис с невалидными данными
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/office/update")
                        .content(asJsonString(new OfficeDtoForUpd()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveOffice() throws Exception {
        //Сохраняем офис в БД
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/office/save")
                        .content(asJsonString(new OfficeDtoForSave(2L, "New Office", "New Address")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));

        //Проверяем, как отработало сохранение
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/office/{id}", 3)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("New Office"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("New Address"));

        //Попытка сохранить офис с невалидными данными
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/office/save")
                        .content(asJsonString(new OfficeDtoForSave()))
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