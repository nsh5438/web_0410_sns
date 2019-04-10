package kr.hs.dgsw.dgsw_sns.Repository;

import kr.hs.dgsw.dgsw_sns.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRep extends JpaRepository<User, Long> {

    public Optional<User> findByUserid(String userid);
}
