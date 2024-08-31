package br.com.blackhunter.rest.api.v1.util;

import br.com.blackhunter.rest.api.v1.exception.UsernameAlreadyInUseException;
import br.com.blackhunter.rest.api.v1.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsernameUtil {
    @Autowired
    private UserAccountRepository accountRepository;

    public boolean usernameAlreadyInUse(String username) throws UsernameAlreadyInUseException {
        if(accountRepository.getUsernameCounts(username) > 0)
            throw new UsernameAlreadyInUseException("O nome de usuário [" + username + "] já está em uso.");
        return true;
    }

    public String formatUsername(String username) {
        if(!username.startsWith("@"))
            username = "@" + username;
        return username.trim();
    }

    public boolean usernameIsValid(String username) throws UsernameAlreadyInUseException {
        String usernameFomatted = formatUsername(username);
        return usernameAlreadyInUse(usernameFomatted);
    }
}
