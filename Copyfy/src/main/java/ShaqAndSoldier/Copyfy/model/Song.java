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

    @Column(nullable = false)
    private String tags;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Access access;

    public enum Access {
        PUBLIC, PRIVATE
    }
}
