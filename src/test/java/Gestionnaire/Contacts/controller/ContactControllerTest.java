package Gestionnaire.Contacts.controller;

import Gestionnaire.Contacts.model.ContactModel;
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


    //Test 1 — GET /contacts
    @Test
    void getAllContacts_devraitRetourner200() throws Exception {
        // Arrange — le faux service retourne une liste vide
        when(contactService.getAllContacts()).thenReturn(new ArrayList<>());

        // Act + Assert
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    //Test 2 — POST /contacts
    @Test
    void addContact_devraitRetourner201() throws Exception {
        // Arrange
        ContactModel contact = new ContactModel(null, "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");
        ContactModel contactSauvegarde = new ContactModel(1L, "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");
        when(contactService.addContact(any(ContactModel.class))).thenReturn(contactSauvegarde);

        // Act + Assert
        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ibrahima"));
    }


    //Test 3 — DELETE /contacts/{id}
    @Test
    void deleteContact_devraitRetourner204() throws Exception {
        mockMvc.perform(delete("/contacts/1"))
                .andExpect(status().isNoContent());
    }


    //Test 4 — POST avec validation — champs vides
    @Test
    void addContact_avecChampsVides_devraitRetourner400() throws Exception {
        ContactModel contactInvalide = new ContactModel(null, "", "", "", "");

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactInvalide)))
                .andExpect(status().isBadRequest());
    }
}
