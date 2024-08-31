package br.com.blackhunter.rest.api.v1.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPayload {
    @NotBlank(message = "")
    private String email;
    @NotBlank(message = "")
    private String password;
}
