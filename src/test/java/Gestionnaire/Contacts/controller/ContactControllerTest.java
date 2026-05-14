package Gestionnaire.Contacts.controller;

import Gestionnaire.Contacts.DTO.ContactDTO;
import Gestionnaire.Contacts.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.util.ArrayList;

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

    // Test 1 — GET /contacts
    @Test
    void getAllContacts_devraitRetourner200() throws Exception {
        when(contactService.getAllContacts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // Test 2 — POST /contacts
    @Test
    void addContact_devraitRetourner201() throws Exception {
        // Request pour envoyer, Response pour recevoir
        ContactDTO.Request request = new ContactDTO.Request("Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");
        ContactDTO.Response response = new ContactDTO.Response(1L, "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");

        when(contactService.addContact(any(ContactDTO.Request.class))).thenReturn(response); //

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ibrahima"));
    }

    // Test 3 — DELETE /contacts/{id}
    @Test
    void deleteContact_devraitRetourner204() throws Exception {
        mockMvc.perform(delete("/contacts/1"))
                .andExpect(status().isNoContent());
    }

    // Test 4 — POST avec champs vides
    @Test
    void addContact_avecChampsVides_devraitRetourner400() throws Exception {
        ContactDTO.Request contactInvalide = new ContactDTO.Request("", "", "", ""); // Request pas Response
        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactInvalide)))
                .andExpect(status().isBadRequest());
    }
}