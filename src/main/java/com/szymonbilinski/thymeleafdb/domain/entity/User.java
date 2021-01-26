package com.szymonbilinski.thymeleafdb.domain.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private Object password;
    private Double balance;

}
