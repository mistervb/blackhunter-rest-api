package br.com.blackhunter.rest.api.v1.dto;

import br.com.blackhunter.rest.api.v1.entity.UserAccount;
import br.com.blackhunter.rest.api.v1.enums.UserAccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountData {
    private UUID accountId;
    private String username;
    private boolean useGenisPassword;
    private UserAccountType accountType;

    public UserAccountData(UserAccount user) {
        this.accountId = user.getId();
        this.username = user.getPerfilUsername();
        this.useGenisPassword = user.isUseGenesisPassword();
        this.accountType = user.getAccountType();
    }
}
