/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShaqAndSoldier.Copyfy.repository;

/**
 *
 * @author kjdavid <kjdavid96 at gmail.com>
 */
import ShaqAndSoldier.Copyfy.model.Playlist;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, String> {
       Optional<Playlist> findByName(String title);
}
