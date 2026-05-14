package Gestionnaire.Contacts.DTO;

import Gestionnaire.Contacts.model.ContactModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ContactDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "Le prénom est obligatoire")
        private String firstName;

        @NotBlank(message = "Le nom est obligatoire")
        private String lastName;

        @Email(message = "L'email n'est pas valide")
        @NotBlank(message = "L'email est obligatoire")
        private String email;

        @NotBlank(message = "Le téléphone est obligatoire")
        private String phone;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
    }

    // Méthodes de mapping static
    public static ContactModel toModel(Request dto) {
        ContactModel model = new ContactModel();
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setEmail(dto.getEmail());
        model.setPhone(dto.getPhone());
        return model;
    }

    public static Response toResponse(ContactModel model) {
        return new Response(
                model.getId(),
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                model.getPhone()
        );
    }
}