package br.com.spring.webflux.user.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private Boolean isPreferred;

}
