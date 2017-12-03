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

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToMany(targetEntity= Tag.class)
    private Set<Tag> tags=new HashSet();
    
    @Column(/*nullable = false,*/ unique = true)
    private String filePath;
    
    @Column(/*nullable= false,*/ unique = false)
    private String owner;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Access access;

    public enum Access {
        PUBLIC, PRIVATE
    }
}
