package com.kobertech.kobertechv1.models;
import java.util.Set;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RoomModel {
    private Set<String> users;
    private Set<String> connctedUsers;
}