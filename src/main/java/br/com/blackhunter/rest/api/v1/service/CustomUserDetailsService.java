package br.com.blackhunter.rest.api.v1.service;

import br.com.blackhunter.rest.api.v1.entity.UserAccount;
import br.com.blackhunter.rest.api.v1.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserAccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário pelo email
        UserAccount userAccount = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não identificado - [" + email + "]"));

        Set<SimpleGrantedAuthority> authorities = userAccount.getAccountType().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_" + permission.name())) // Adiciona o prefixo ROLE_
                .collect(Collectors.toSet());

        return userAccount;
    }
}
