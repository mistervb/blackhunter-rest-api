package br.com.blackhunter.rest.api.v1.service;

import br.com.blackhunter.rest.api.v1.dto.UserAccountData;
import br.com.blackhunter.rest.api.v1.entity.UserAccount;
import br.com.blackhunter.rest.api.v1.payload.UserAccountPayload;
import br.com.blackhunter.rest.api.v1.repository.UserAccountRepository;
import br.com.blackhunter.rest.api.v1.util.JpaUtil;
import br.com.blackhunter.rest.api.v1.util.UsernameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAccountService implements CrudService<UserAccountData, UserAccountPayload> {
    @Autowired
    private UserAccountRepository userRepository;
    @Autowired
    private JpaUtil<UserAccount, UserAccountPayload, UserAccountRepository> jpaUtil;
    @Autowired
    private UsernameUtil usernameUtil;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserAccountData doCreate(UserAccountPayload userAccountPayload) throws Exception {
        usernameUtil.usernameIsValid(userAccountPayload.getUsername());
        UserAccount user = new UserAccount(userAccountPayload);
        user.setPassword(encoder.encode(userAccountPayload.getPassword()));

        /* Mais para frente será implementado um serviço de envio de email para o admin master.
        *  - Victor Barbeirno. */

        return new UserAccountData(this.userRepository.saveAndFlush(user));
    }

    @Override
    public UserAccountData doUpdateById(UUID id, UserAccountPayload userAccountPayload) throws Exception {
        return new UserAccountData(jpaUtil.findByIdAndUpdate(id, userAccountPayload));
    }

    /**
     * [<b>Método exlcusivo de uma conta do tipo <code><i>Owner</i></code></b>]
     * */
    @Override
    public List<UserAccountData> getAll() {
        return this.userRepository.findAll().stream().map(UserAccountData::new).toList();
    }

    @Override
    public UserAccountData detailsById(UUID id) {
        return new UserAccountData(this.jpaUtil.findById(id));
    }

    /**
     * [<b>Método exlcusivo de uma conta do tipo <code><i>Owner</i></code></b> caso o ID de usuário não seja do usuário corrente]
     * */
    @Override
    public void doDeleteById(UUID id) {
        this.jpaUtil.findByIdAndDelete(id);
    }
}
