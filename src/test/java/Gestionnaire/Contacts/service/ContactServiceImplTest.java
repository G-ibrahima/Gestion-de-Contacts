package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.exeption.ContactException;
import Gestionnaire.Contacts.model.ContactModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceImplTest {

    private ContactServiceImpl service;


    @BeforeEach
    void setUp(){
        service = new ContactServiceImpl();
    }

    @Test
    void getAllContacts_devraitRetournerListeVide() {
        // Act
        List<ContactModel> contacts = service.getAllContacts();

        // Assert
        assertNotNull(contacts);        // la liste n'est pas null
        assertEquals(0, contacts.size()); // la liste est vide
    }

    @Test
    void addContact_devraitAjouterUnContact() {
        // Arrange
        ContactModel contact = new ContactModel(null, "Ibrahima", "Gueye", "ibrahima@gmail.com", "514-000-0000");

        // Act
        ContactModel resultat = service.addContact(contact);

        // Assert
        assertNotNull(resultat.getId());
        assertEquals("Ibrahima", resultat.getFirstName());
        assertEquals(1, service.getAllContacts().size());
    }

    @Test
    void deleteContact_avecIdInexistant_devraitLancerException() {
        assertThrows(ContactException.class, () -> {
            service.deleteContact(999L);
        });
    }
}
