
package ShaqAndSoldier.Copyfy.controller;

import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.Tag;
import ShaqAndSoldier.Copyfy.repository.SongRepository;
import ShaqAndSoldier.Copyfy.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aram
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    SongRepository sngRepo;
    Song sg;
    
    private UserService userService;
     
    @GetMapping("/upload")
    public String testField() {
        return "test";
    }
    
    
    @PostMapping("/upload")
    public String controllerMethod(@RequestParam(value="myArray[]") List<String> myArray, @RequestParam(value="string") String string){
        System.out.println(string);
        sg = new Song();
        sg.setOwner("Shaq");
        sg.setBase64str(string);
        sg.setAccess(Song.Access.PUBLIC);
        sg.setTitle("teszt");
        Set<Tag> set = new HashSet<>();
        sg.setTags(set);
        sngRepo.save(sg);
        return "test";
    }
}   
