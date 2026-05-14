package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.DTO.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {
    Page<ContactDTO.Response> getAllContacts(Pageable pageable);
    ContactDTO.Response addContact(ContactDTO.Request contact);
    ContactDTO.Response updateContact(Long id,ContactDTO.Request contact);
    void deleteContact(Long id);
}
