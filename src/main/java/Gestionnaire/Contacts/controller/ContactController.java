package Gestionnaire.Contacts.controller;

import Gestionnaire.Contacts.model.ContactModel;
import Gestionnaire.Contacts.service.ContactService;
import jakarta.validation.Valid;
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
    public List<ContactModel> getContactId() {
        return contactService.getAllContacts();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactModel addContact(@Valid @RequestBody ContactModel contact) {
        return contactService.addContact(contact);
    }

    @PutMapping("{id}")
    public ContactModel updateContact(@PathVariable Long id,@Valid @RequestBody ContactModel contact) {
        return contactService.updateContact(id,contact);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
