package ShaqAndSoldier.Copyfy.api;

import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.service.SongService;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @Autowired
    public SongApiController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Song>> song() {
        return ResponseEntity.ok(songService.getSongs());
    }
}
