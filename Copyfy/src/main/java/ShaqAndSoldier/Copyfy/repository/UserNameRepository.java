/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShaqAndSoldier.Copyfy.repository;

import ShaqAndSoldier.Copyfy.model.UserName;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Aram
 */
public interface UserNameRepository  extends CrudRepository<UserName, String> {
    Optional<UserName> findByName(String name);
}
