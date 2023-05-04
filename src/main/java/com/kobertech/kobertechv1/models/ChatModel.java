package com.kobertech.kobertechv1.models;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatModel {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}