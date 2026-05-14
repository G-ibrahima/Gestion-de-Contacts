package Gestionnaire.Contacts.controller;

import Gestionnaire.Contacts.DTO.ContactDTO;
import Gestionnaire.Contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public Page<ContactDTO.Response> getContactId(Pageable pageable) {
        return contactService.getAllContacts(pageable);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO.Response addContact(@Valid @RequestBody ContactDTO.Request contact) {
        return contactService.addContact(contact);
    }

    @PutMapping("{id}")
    public ContactDTO.Response updateContact(@PathVariable Long id,@Valid @RequestBody ContactDTO.Request contact) {
        return contactService.updateContact(id,contact);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
