package br.com.blackhunter.rest.api.v1.service;

import br.com.blackhunter.rest.api.v1.dto.AuthResponseData;
import br.com.blackhunter.rest.api.v1.entity.UserAccount;
import br.com.blackhunter.rest.api.v1.payload.LoginPayload;
import br.com.blackhunter.rest.api.v1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public AuthResponseData doLogin(LoginPayload payload) throws AuthenticationException {
        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));

        // Se a autenticação for bem-sucedida, gere o token JWT
        UserAccount user = (UserAccount) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(user.getUsername());

        return new AuthResponseData(user.getId(), jwt, "JWT");
    }
}
