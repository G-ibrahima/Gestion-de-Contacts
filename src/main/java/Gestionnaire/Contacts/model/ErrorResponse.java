package Gestionnaire.Contacts.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    public String message;
    public Integer status;
}
