package br.com.blackhunter.rest.api.v1.repository;

import br.com.blackhunter.rest.api.v1.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    @Query("SELECT COUNT(u) FROM UserAccount u WHERE u.username = :username")
    int getUsernameCounts(@Param(value = "username") String username);

    @Query("SELECT u FROM UserAccount u WHERE u.email = :email")
    Optional<UserAccount> findByEmail(@Param(value = "email") String email);
}
