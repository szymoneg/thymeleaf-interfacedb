package com.szymonbilinski.thymeleafdb.domain.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String firstname;
    private String lastname;
}
