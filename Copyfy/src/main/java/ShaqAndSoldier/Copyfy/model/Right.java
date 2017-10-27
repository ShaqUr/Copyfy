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
@Table(name = "RIGHTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Right {

    @Column(nullable = false, unique = true)
    @Id
    private String uID;

    @Column(nullable = false, unique = true)
    private String sID;

    @Column(nullable = false)
    private boolean delete;

    @Column(nullable = false)
    private boolean share;
    
    @Column(nullable = false)
    private boolean listen;
    
}
