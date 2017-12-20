package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.model.Playlist;
import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.repository.PlaylistRepository;
import ShaqAndSoldier.Copyfy.repository.SongRepository;
import ShaqAndSoldier.Copyfy.repository.TagRepository;
import ShaqAndSoldier.Copyfy.repository.UserNameRepository;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
public class SongService { 
    @Autowired
    private SongRepository songRepo;
    @Autowired
    private PlaylistRepository playlistRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private UserNameRepository userNameRepo;
    public Iterable<Song> getSongsByOwner(String owner){
        return songRepo.findByOwner(owner);
    }
    
    //public Iterable<Song> getSongsByOwner(String owner){
      //  return songRepo.findByUId(o)
    //}
    
    public Iterable<Song> getSongsByAccess(String access){
        return songRepo.findByAccess(access);
    }

    public void delete(Song s) {
        s.getTags().removeAll(s.getTags());
        s.getFriendUserNames().removeAll(s.getFriendUserNames());
        Iterator<Playlist> pI = playlistRepo.findAll().iterator();
        Playlist pl;
        while(pI.hasNext()){
            pl=pI.next();
            pl.getSongs().remove(s);
            playlistRepo.save(pl);
        }
        songRepo.save(s);
        songRepo.delete(s);
    }

    
}
