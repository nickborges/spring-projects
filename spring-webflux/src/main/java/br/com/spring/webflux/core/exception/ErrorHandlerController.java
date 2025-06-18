package br.com.spring.webflux.core.exception;

import com.mongodb.MongoWriteException;
//import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(MongoWriteException.class)
    public final ResponseEntity<ErrorApiException> handler(final MongoWriteException exception){
        log.error("Error: ", exception);
        return new ResponseEntity<>(
                build(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /*@ExceptionHandler(FeignException.class)
    public final ResponseEntity<ErrorApiException> handler(final FeignException exception){
        log.error("Error: ", exception);
        return new ResponseEntity<>(
                build(exception.getMessage()),
                HttpStatus.valueOf(exception.status())
        );
    }*/

    @ExceptionHandler(ErrorApiException.class)
    public final ResponseEntity<ErrorApiResponse> handler(final ErrorApiException exception){
        log.warn("Warning: " + exception.getErrorApiResponse().getMessage());
        return new ResponseEntity<>(
                exception.getErrorApiResponse(),
                exception.getHttpStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorApiException> handler(final Exception exception){
        log.warn("Error: " + exception.getMessage());
        return new ResponseEntity<>(
                build("Erro interno do servidor"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private ErrorApiException build(String message){
        return ErrorApiException
                .builder()
                .errorApiResponse(
                        ErrorApiResponse
                                .builder()
                                .message(message)
                                .build()
                )
                .build();
    }
}
