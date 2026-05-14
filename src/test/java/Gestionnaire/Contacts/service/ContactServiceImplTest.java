package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.exeption.ContactException;
import Gestionnaire.Contacts.DTO.ContactDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ContactServiceImplTest {
    private ContactServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ContactServiceImpl();
    }

    @Test
    void getAllContacts_devraitRetournerListeVide() {
        List<ContactDTO.Response> contacts = service.getAllContacts(); //
        assertNotNull(contacts);
        assertEquals(0, contacts.size());
    }

    @Test
    void addContact_devraitAjouterUnContact() {
        // Arrange — Request au lieu de ContactModel
        ContactDTO.Request request = new ContactDTO.Request(
                "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000"
        );

        // Act
        ContactDTO.Response response = service.addContact(request); //

        // Assert
        assertNotNull(response.getId());
        assertEquals("Ibrahima", response.getFirstName());
        assertEquals(1, service.getAllContacts().size());
    }

    @Test
    void deleteContact_avecIdInexistant_devraitLancerException() {
        assertThrows(ContactException.class, () -> {
            service.deleteContact(999L);
        });
    }
}