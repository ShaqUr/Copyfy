/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShaqAndSoldier.Copyfy.model;

/**
 *
 * @author kjdav
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Tag {

    @Column(nullable = false, unique = true)
    @Id
    private String Tags;
    
}
