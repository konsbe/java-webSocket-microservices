package com.kobertech.kobertechv1.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountModel {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}