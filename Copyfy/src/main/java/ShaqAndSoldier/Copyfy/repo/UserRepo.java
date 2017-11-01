package ShaqAndSoldier.Copyfy.repo;

import ShaqAndSoldier.Copyfy.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aram
 */
@Repository
public interface UserRepo extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
