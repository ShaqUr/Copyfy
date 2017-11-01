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
import ShaqAndSoldier.Copyfy.model.Song.Access;
import ShaqAndSoldier.Copyfy.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends CrudRepository<Song, String> {
    Optional<Song> findByTags(String tags);

    Optional<Song> findByTitle(String title);

    Optional<Song> findByAccess(Access access); // Should only work if the access is public.*/
}
