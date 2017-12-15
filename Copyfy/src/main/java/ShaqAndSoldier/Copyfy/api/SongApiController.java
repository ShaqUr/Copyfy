package ShaqAndSoldier.Copyfy.api;

import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.model.User.Role;
import ShaqAndSoldier.Copyfy.service.SongService;
import ShaqAndSoldier.Copyfy.service.UserService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aram
 */

@RestController
@RequestMapping("/api/songs")
public class SongApiController {
   
    private final SongService songService;
    private final UserService userService;
    @Autowired
    public SongApiController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteSongById(@RequestBody Song s){
        if(userService.getUser().getRole().equals(Role.ADMIN) || userService.getUser().getUsername().equals(songService.getSongRepo().findById(s.getId()).get().getOwner())){
            songService.delete(songService.getSongRepo().findById(s.getId()).get());
            return ResponseEntity.ok("Törölve!");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/searchall")
    public ResponseEntity<Iterable<Song> > allSongs(){
        if(userService.getUser().getRole().equals(Role.ADMIN)){
            Iterator<Song>sI=songService.getSongRepo().findAll().iterator();
            Set<Song> songs= new HashSet<>();
            Song s;
            while(sI.hasNext()){
                s=sI.next();
                s.setBase64str("");
                songs.add(s);
            }
            return ResponseEntity.ok(songs);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/owner")
    public ResponseEntity<Iterable<Song>> songByOwner(@RequestBody String owner) {
        return ResponseEntity.ok(songService.getSongsByOwner(owner));
    }
}
