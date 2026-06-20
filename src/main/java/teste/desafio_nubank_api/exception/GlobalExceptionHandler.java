package teste.desafio_nubank_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import teste.desafio_nubank_api.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex,  HttpServletRequest request){

        ErrorResponseDTO error = new ErrorResponseDTO(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI()
                                );

        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(error); 

    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicate(DuplicateException ex,  HttpServletRequest request){

        ErrorResponseDTO error = new ErrorResponseDTO(
                                LocalDateTime.now(),
                                HttpStatus.CONFLICT.value(),
                                HttpStatus.CONFLICT.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI()
                                );

        
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(error); 

    }

}
