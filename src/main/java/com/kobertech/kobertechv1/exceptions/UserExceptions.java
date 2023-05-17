package com.kobertech.kobertechv1.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExceptions extends RuntimeException {
    
    private String message;
    private String code;

    public UserExceptions() {
        super();
    }
    public UserExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public UserExceptions(String message) {
        super(message);
    }
    public UserExceptions(Throwable cause) {
        super(cause);
    }
}
