package ShaqAndSoldier.Copyfy.api;

import ShaqAndSoldier.Copyfy.model.Playlist;
import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.User;
import static ShaqAndSoldier.Copyfy.model.User.Role.ADMIN;
import static ShaqAndSoldier.Copyfy.model.User.Role.USER;
import ShaqAndSoldier.Copyfy.service.SongService;
import ShaqAndSoldier.Copyfy.service.UserService;
import ShaqAndSoldier.Copyfy.service.annotations.Role;
import ShaqAndSoldier.Copyfy.service.exceptions.UserNotValidException;
import ShaqAndSoldier.Copyfy.service.exceptions.UsernameOrEmailInUseException;
import com.sun.istack.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final SongService songService;
    
    @Autowired
    public UserApiController(UserService userService, SongService songService) {
        this.userService = userService;
        this.songService = songService;
    }

    @Role({USER, ADMIN})
    @GetMapping
    public ResponseEntity<User> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("search")
    public ResponseEntity<Iterable<String> > search(@RequestBody String username){
        try{
        return ResponseEntity.ok(userService.getUsername(username));
        }catch (NoSuchElementException nsee){
            return ResponseEntity.ok(new HashSet<>(Arrays.asList("Nincs ilyen felhasználó!")));
        }
    }
    @PostMapping("searchall")
    public ResponseEntity<Iterable<String> > search(){
        return ResponseEntity.ok(userService.getUsernames());
    }
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/bann")
    public ResponseEntity<String> bann(@RequestBody String username){
        if(this.userService.getUser().getRole().equals(User.Role.ADMIN) && !username.equals(this.userService.getUser().getUsername())){
            this.userService.bann(username);
            return ResponseEntity.ok(username+" banned!");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/changepassword")
    public ResponseEntity<User> changePassword(@RequestBody User user){
        if(this.userService.getUser().getUsername().equals(user.getUsername())){
            User u = this.userService.getUserRepository().findByUsername(user.getUsername()).get();
            u.setPassword(user.getPassword());
            this.userService.getUserRepository().save(u);
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/logout")
    public ResponseEntity logout(@RequestBody User user) {
        this.userService.setUser(null);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try{
            return ResponseEntity.ok(userService.register(user));
        }catch(UsernameOrEmailInUseException e){
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    @PostMapping("/addplaylist")
    public ResponseEntity<User> addToPlaylist(@RequestBody String newPlaylistName){
        Playlist playlist = new Playlist();
        playlist.setName(newPlaylistName);
        User user = userService.getUserRepository().findByUsername(userService.getUser().getUsername()).get();
        userService.getPlaylistRepo().save(playlist);
        user.getPlaylists().add(playlist);
        userService.getUserRepository().save(user);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/addtoplaylist")
    public ResponseEntity<User> addToPlaylist(@RequestBody String songName, String playlistName){
        Song sg = songService.getSongRepo().findByTitle(songName).get();
        Playlist playlist = userService.getPlaylistRepo().findByName(playlistName).get();
        User user = userService.getUserRepository().findByUsername(userService.getUser().getUsername()).get();
        playlist.getSongs().add(sg);
        userService.getPlaylistRepo().save(playlist);
        userService.getUserRepository().save(user);
        return ResponseEntity.ok(user);
    }
}