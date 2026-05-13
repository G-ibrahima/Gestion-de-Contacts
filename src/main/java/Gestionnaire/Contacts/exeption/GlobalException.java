package Gestionnaire.Contacts.exeption;

import Gestionnaire.Contacts.model.ContactModel;
import Gestionnaire.Contacts.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ContactException.class)
    public ResponseEntity<ErrorResponse> catchCalculatriceException(ContactException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
