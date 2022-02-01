package org.example.practice.organization.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.handler.data.DataListDto;
import org.example.practice.office.dto.OfficeDtoForListIn;
import org.example.practice.office.dto.OfficeDtoForSave;
import org.example.practice.office.dto.OfficeDtoForUpd;
import org.example.practice.organization.dto.OrganizationDtoForListIn;
import org.example.practice.organization.dto.OrganizationDtoForSave;
import org.example.practice.organization.dto.OrganizationDtoForUpd;
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
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getOneOrganization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/organization/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("X5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("X5 Retail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inn").value("11111111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.kpp").value("12121212"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("ул. Центральная, 99"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/organization/{id}", 666)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getOrganizations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/organization/list")
                        .content(asJsonString(new OrganizationDtoForListIn("X5", "true")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name").value("X5"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/organization/list")
                        .content(asJsonString(new OrganizationDtoForListIn("X5", "true")))
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
    void updOrganization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/organization/update")
                        .content(asJsonString(new OrganizationDtoForUpd(1L,"Upd Org", "Full Upd Org", "22222", "333333", "Upd Org Address")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/organization/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Upd Org"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("Full Upd Org"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inn").value("22222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.kpp").value("333333"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("Upd Org Address"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/organization/update")
                        .content(asJsonString(new OrganizationDtoForUpd()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveOrganization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/organization/save")
                        .content(asJsonString(new OrganizationDtoForSave("New Org", "Full New Org", "11111", "222222", "New Org Address")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/organization/{id}", 3)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("New Org"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("Full New Org"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inn").value("11111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("New Org Address"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/organization/save")
                        .content(asJsonString(new OrganizationDtoForSave()))
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