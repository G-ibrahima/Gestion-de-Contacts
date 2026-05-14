package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.exeption.ContactException;
import Gestionnaire.Contacts.DTO.ContactDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import static org.junit.jupiter.api.Assertions.*;
public class ContactServiceImplTest {
    private ContactServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ContactServiceImpl();
    }

    @Test
    void getAllContacts_devraitRetournerListeVide() {
        Page<ContactDTO.Response> contacts = service.getAllContacts(PageRequest.of(0, 10));
        assertNotNull(contacts);
        assertEquals(0, contacts.getTotalElements());
    }

    @Test
    void addContact_devraitAjouterUnContact() {
        ContactDTO.Request request = new ContactDTO.Request(
                "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000"
        );
        ContactDTO.Response response = service.addContact(request);
        assertNotNull(response.getId());
        assertEquals("Ibrahima", response.getFirstName());
        assertEquals(1, service.getAllContacts(PageRequest.of(0, 10)).getTotalElements());
    }

    @Test
    void deleteContact_avecIdInexistant_devraitLancerException() {
        assertThrows(ContactException.class, () -> {
            service.deleteContact(999L);
        });
    }
}