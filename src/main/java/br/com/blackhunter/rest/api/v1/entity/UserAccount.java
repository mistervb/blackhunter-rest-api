package br.com.blackhunter.rest.api.v1.entity;

import br.com.blackhunter.rest.api.v1.enums.UserAccountType;
import br.com.blackhunter.rest.api.v1.payload.UserAccountPayload;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "bh_useraccounts")
public class UserAccount implements EntityModel<UserAccountPayload>, UserDetails {
    /* Dados de acesso */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "social_name", nullable = false)
    private String socialName;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAccountType accountType;

    /* Atributos de configuração */

    @Column(name = "use_genesis_password", nullable = false)
    private boolean useGenesisPassword;

    /* Construtores */

    public UserAccount() {
        useDefault();
    }

    public UserAccount(UserAccountPayload pl) {
        useDefault();
        this.socialName = pl.getSocialName();
        this.username = pl.getUsername();
        this.email = pl.getEmail();
    }

    /* Métodos implementados */

    @Override
    public void updateData(UserAccountPayload pl) {
        this.socialName = pl.getSocialName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accountType.getGrantedAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /* Métodos publicos */

    public String getPerfilUsername() {
        return this.username;
    }

    /* Métodos privados */

    private void useDefault() {
        // Isso irá mudar mais para frente e o padrão passará a valer como true.
        this.useGenesisPassword = false;
        this.accountType = UserAccountType.USER;
    }
}
