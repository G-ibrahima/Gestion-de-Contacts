package Gestionnaire.Contacts.controller;

import Gestionnaire.Contacts.DTO.ContactDTO;
import Gestionnaire.Contacts.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
@Tag(name = "Contacts", description = "Gestion des contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Operation(summary = "Récupérer tous les contacts")
    @GetMapping
    public Page<ContactDTO.Response> getContactId(Pageable pageable) {
        return contactService.getAllContacts(pageable);
    }

    @Operation(summary = "Créer un nouveau contact")
    @ApiResponse(responseCode = "201", description = "Contact créé avec succès")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO.Response addContact(@Valid @RequestBody ContactDTO.Request contact) {
        return contactService.addContact(contact);
    }


    @Operation(summary = "Modifier un contact existant")
    @ApiResponse(responseCode = "200", description = "Contact modifié avec succès")
    @ApiResponse(responseCode = "404", description = "Contact non trouvé")
    @PutMapping("{id}")
    public ContactDTO.Response updateContact(@PathVariable Long id,@Valid @RequestBody ContactDTO.Request contact) {
        return contactService.updateContact(id,contact);
    }

    @Operation(summary = "Supprimer un contact")
    @ApiResponse(responseCode = "204", description = "Contact supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Contact non trouvé")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
