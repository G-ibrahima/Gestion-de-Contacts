package Gestionnaire.Contacts.exeption;

import Gestionnaire.Contacts.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ContactException.class)
        public ResponseEntity<ErrorResponse> handleContactException(
                ContactException ex, HttpServletRequest request) {
            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    404,
                    ex.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(404).body(error);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidation(
                MethodArgumentNotValidException ex, HttpServletRequest request) {
            String message = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + " : " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    400,
                    message,
                    request.getRequestURI()
            );
            return ResponseEntity.status(400).body(error);
        }
}
