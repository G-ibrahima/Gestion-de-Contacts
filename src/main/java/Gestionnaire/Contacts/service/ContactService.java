package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.DTO.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO.Response> getAllContacts();
    ContactDTO.Response addContact(ContactDTO.Request contact);
    ContactDTO.Response updateContact(Long id,ContactDTO.Request contact);
    void deleteContact(Long id);
}
