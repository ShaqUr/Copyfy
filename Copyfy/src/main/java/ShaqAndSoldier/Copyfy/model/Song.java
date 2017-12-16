package ShaqAndSoldier.Copyfy.model;

/**
 *
 * @author kjdavid <kjdavid96 at gmail.com>
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "SONGS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Song extends BaseEntity {

    @Column(nullable = false, unique = false)
    private String title;

    @ManyToMany(targetEntity= Tag.class)
    private Set<Tag> tags=new HashSet();
    
    @Column(columnDefinition="TEXT")
    private String base64str;
    
    @Column(nullable= false, unique = false)
    private String owner;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Access access;
    
    @ManyToMany(targetEntity = UserName.class)
    private Set<UserName> friendUserNames = new HashSet();
    
    public enum Access {
        PUBLIC, PRIVATE
    }
}
