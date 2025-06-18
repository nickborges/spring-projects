package br.com.spring.webflux.user.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
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
