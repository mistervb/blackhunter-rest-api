package br.com.blackhunter.rest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseData {
    private UUID userId;
    private String token;
    private String tokenType;
}
