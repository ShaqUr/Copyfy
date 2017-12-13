/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.model.User;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aram
 */

@Service
public class UploadService {
    
    private final UserService userService;
     
    @Autowired
    public UploadService(UserService userService) {
        this.userService = userService;
    }
    
   public boolean isLoggedIn(){
       return userService.isLoggedIn();
   }
   
   public User getUser(){
       return userService.getUser();
   }
}
