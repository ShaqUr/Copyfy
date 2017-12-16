package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.model.User.Role;
import static ShaqAndSoldier.Copyfy.model.User.Role.USER;
import static ShaqAndSoldier.Copyfy.model.User.Role.BANNED;
import ShaqAndSoldier.Copyfy.model.UserName;
import ShaqAndSoldier.Copyfy.repository.PlaylistRepository;
import ShaqAndSoldier.Copyfy.repository.UserNameRepository;
import ShaqAndSoldier.Copyfy.repository.UserRepository;
import ShaqAndSoldier.Copyfy.service.exceptions.UserNotValidException;
import ShaqAndSoldier.Copyfy.service.exceptions.UsernameOrEmailInUseException;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author Aram
 */

@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private User user;
    
    @Autowired
    private UserNameRepository userNameRepository;
    @Autowired
    private PlaylistRepository playlistRepo;
    public void setLoggedIn(User userLogged){
        user=userRepository.findByUsername(userLogged.getUsername()).get();
    }
    
    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }
    public User register(User user) throws UsernameOrEmailInUseException {
        if(isUsernameInUse(user) || isEmailInUse(user)){
            throw new UsernameOrEmailInUseException();
        }else{
            user.setRole(USER);
            this.user = userRepository.save(user);
            UserName userName = new UserName();
            userName.setName(user.getUsername());
            userNameRepository.save(userName);
            return user;
        }
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
        return user != null;
    }
    public boolean isUserLoggedIn(String username){
        return user.getUsername().equals(username);
    }
    public Iterable<String> getUsernames(){
        Set<String> s=new HashSet<>();
        for(User u :this.userRepository.findAll()){
            s.add(u.getUsername());
        }
        return s;
    }
    public Iterable<String> getUsername(String username){
        Set<String> s = new HashSet<>();
        s.add(this.userRepository.findByUsername(username).get().getUsername());
        return s;
    }

    public void bann(String username) {
        this.userRepository.findByUsername(username).get().setRole(BANNED);
        User u = this.userRepository.findByUsername(username).get();
        this.userRepository.save(u);
        
    }
}