/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShaqAndSoldier.Copyfy.model;

/**
 *
 * @author kjdavid <kjdavid96 at gmail.com>
 */
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Playlists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= true)
public class Playlist extends BaseEntity{
    
    @Column(nullable = false)
    private String name;
    
    @ManyToMany(targetEntity = Song.class)
    private Set<Song> songs=new HashSet();
}
