package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.repository.SongRepository;
import java.util.LinkedList;
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
public class SongService { 
    @Autowired
    private SongRepository songRepo;
   
    public Iterable<Song> getSongs(){
        return songRepo.findAll();
    } 

    
}
