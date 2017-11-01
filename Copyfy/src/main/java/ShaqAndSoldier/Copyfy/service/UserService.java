package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aram
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepoitory;

    public void register(User user) {
        userRepoitory.save(user);
    }

    public boolean isValid(User user) {
        return userRepoitory.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }
}