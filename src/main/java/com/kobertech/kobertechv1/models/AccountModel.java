package com.kobertech.kobertechv1.models;

import lombok.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountModel {
    private String userName;
    private String email;
    private String password;
    private Instant date;
}