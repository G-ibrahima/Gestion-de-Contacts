package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.model.ContactModel;

import java.util.List;

public interface ContactService {
    List<ContactModel> getAllContacts();
    ContactModel addContact(ContactModel contact);
    ContactModel updateContact(Long id,ContactModel contact);
    void deleteContact(Long id);
}
