package teste.desafio_nubank_api.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){

        super(message);
    }

}
