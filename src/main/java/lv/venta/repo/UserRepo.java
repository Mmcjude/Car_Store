package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.model.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
