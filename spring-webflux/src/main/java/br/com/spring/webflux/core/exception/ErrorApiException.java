package br.com.spring.webflux.core.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorApiException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    private ErrorApiResponse errorApiResponse;
    private HttpStatus httpStatus;

    public ErrorApiException(final String message,
                             final HttpStatus httpStatus){
        this.errorApiResponse = new ErrorApiResponse(message);
        this.httpStatus = httpStatus;
    }

    public ErrorApiException(final Exception message,
                             final HttpStatus httpStatus){
        this.errorApiResponse = new ErrorApiResponse(message.toString());
        this.httpStatus = httpStatus;
    }

}
