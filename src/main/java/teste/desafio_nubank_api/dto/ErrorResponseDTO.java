package teste.desafio_nubank_api.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
                LocalDateTime timestamp,
                Integer status, 
                String error, 
                String message, 
                String path
                ) {

}
