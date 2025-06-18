package br.com.spring.webflux.core.exception;

import lombok.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorApiResponse implements Serializable {

    private String message;

}
