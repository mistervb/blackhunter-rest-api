package br.com.blackhunter.rest.api.v1.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h2>Enum dos tipos de conta de usuário no sistema da blackhunter.</h2>
 * <p>
 * Cada tipo de conta deve conter um enumSet de níveis de permissões permissões.
 * do tipo {@link br.com.blackhunter.rest.api.v1.enums.UserAccountPermission UserAccountPermission}
 * </p>
 *
 * <p>Veja também:</p>
 * <ul>
 *   <li>{@see UserAccountPermission}</li>
 * </ul>
 * */
public enum UserAccountType {
    OWNER(EnumSet.allOf(UserAccountPermission.class)), // Dono do app com todas as permissões
    CURADOR(EnumSet.of(
            UserAccountPermission.MANAGE_COMMUNITY,    // Permissões de curador
            UserAccountPermission.CREATE_OWN,
            UserAccountPermission.DELETE_OWN,
            UserAccountPermission.EDIT_OWN,
            UserAccountPermission.READ_ALLOWED)),     // Herdando permissões de usuário comum
    USER(EnumSet.of(
            UserAccountPermission.CREATE_OWN,
            UserAccountPermission.DELETE_OWN,
            UserAccountPermission.EDIT_OWN,
            UserAccountPermission.READ_ALLOWED)),     // Usuário comum com permissões básicas
    CONTENT_CREATOR(EnumSet.of(
            UserAccountPermission.CREATE_CONTENT,
            UserAccountPermission.EDIT_CONTENT,
            UserAccountPermission.DELETE_CONTENT,
            UserAccountPermission.CREATE_OWN,
            UserAccountPermission.DELETE_OWN,
            UserAccountPermission.EDIT_OWN,
            UserAccountPermission.READ_ALLOWED));

    private final Set<UserAccountPermission> permissions;

    UserAccountType(Set<UserAccountPermission> permissions) {
        this.permissions = permissions;
    }

    // Método para verificar se um tipo de conta tem uma permissão específica
    public boolean hasPermission(UserAccountPermission permission) {
        return permissions.contains(permission);
    }

    // Retorna as permissões para exibição ou uso
    public Set<UserAccountPermission> getPermissions() {
        return permissions;
    }

    // Mapeia permissões para GrantedAuthorities
    public Set<GrantedAuthority> getGrantedAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_" + permission.name()))
                .collect(Collectors.toSet());
    }
}
