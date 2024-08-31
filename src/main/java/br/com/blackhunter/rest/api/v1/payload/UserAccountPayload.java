package br.com.blackhunter.rest.api.v1.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountPayload {
    @NotBlank(message = "Nome social não informado.")
    private String socialName;
    @NotBlank(message = "Username não informado.")
    private String username;
    @NotBlank(message = "Email não informado.")
    @Email(message = "Email inválido.")
    private String email;
    @NotBlank(message = "Senha não informado.")
    @Length(min = 8, message = "Senha deve conter no mínimo 8 caracteres")
    private String password;
}
