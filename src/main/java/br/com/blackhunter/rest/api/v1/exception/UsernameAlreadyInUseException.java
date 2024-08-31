package br.com.blackhunter.rest.api.v1.exception;

import lombok.Getter;

public class UsernameAlreadyInUseException extends Exception {
    @Getter
    private String message;

    public UsernameAlreadyInUseException(String message) {
        super(message);
        this.message = message;
    }
}
