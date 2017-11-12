package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.model.User.Role;
import static ShaqAndSoldier.Copyfy.model.User.Role.USER;
import ShaqAndSoldier.Copyfy.repository.UserRepository;
import ShaqAndSoldier.Copyfy.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aram
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User loggedIn;
    private User user;
    
    public void setLoggedIn(User userLogged){
        loggedIn=userRepository.findByUsername(userLogged.getUsername()).get();
    }
    
    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }
    public User register(User user) {
        user.setRole(USER);
        this.user = userRepository.save(user);
        return user;
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }
    public boolean isBanned(User user){
        return  userRepository.findByUsername(user.getUsername()).get().getRole().equals(User.Role.BANNED);
    }
    public boolean isUsernameInUse(User user){
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }
    public boolean isEmailInUse(User user){
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }
    public boolean isLoggedIn() {
        return loggedIn != null;
    }
    public boolean isUserLoggedIn(String username){
        return loggedIn.getUsername().equals(username);
    }
}