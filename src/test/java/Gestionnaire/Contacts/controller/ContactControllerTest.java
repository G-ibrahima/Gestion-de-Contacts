package Gestionnaire.Contacts.controller;

import Gestionnaire.Contacts.DTO.ContactDTO;
import Gestionnaire.Contacts.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ContactController.class)
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService contactService;
    @Autowired
    private ObjectMapper objectMapper;

    // Test 1 — GET /api/v1/contacts
    @Test
    void getAllContacts_devraitRetourner200() throws Exception {
        when(contactService.getAllContacts(any(Pageable.class))).thenReturn(Page.empty()); // ✅
        mockMvc.perform(get("/api/v1/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // Test 2 — POST /api/v1/contacts
    @Test
    void addContact_devraitRetourner201() throws Exception {
        ContactDTO.Request request = new ContactDTO.Request("Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");
        ContactDTO.Response response = new ContactDTO.Response(1L, "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");
        when(contactService.addContact(any(ContactDTO.Request.class))).thenReturn(response);
        mockMvc.perform(post("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ibrahima"));
    }

    // Test 3 — DELETE /api/v1/contacts/{id}
    @Test
    void deleteContact_devraitRetourner204() throws Exception {
        mockMvc.perform(delete("/api/v1/contacts/1"))
                .andExpect(status().isNoContent());
    }

    // Test 4 — POST avec champs vides
    @Test
    void addContact_avecChampsVides_devraitRetourner400() throws Exception {
        ContactDTO.Request contactInvalide = new ContactDTO.Request("", "", "", "");
        mockMvc.perform(post("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactInvalide)))
                .andExpect(status().isBadRequest());
    }
}