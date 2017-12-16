
package ShaqAndSoldier.Copyfy.controller;

import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.Tag;
import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.model.UserName;
import ShaqAndSoldier.Copyfy.repository.SongRepository;
import ShaqAndSoldier.Copyfy.repository.TagRepository;
import ShaqAndSoldier.Copyfy.repository.UserNameRepository;
import ShaqAndSoldier.Copyfy.service.UploadService;
import ShaqAndSoldier.Copyfy.service.UserService;
import java.util.HashSet;
import java.util.LinkedList;
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
    
    @Autowired
    private TagRepository tagRepo;
    
    @Autowired
    UserNameRepository userNameRepo;
    
    @Autowired
    private UploadService uploadService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/upload")
    public String testField() {
        return "test";
    }
    
    
    @PostMapping("/upload")
    public String controllerMethod(
            @RequestParam(value="myArray[]") List<String> myArray, 
            @RequestParam(value="string") String string,
            @RequestParam(value="privat") boolean privat,
            @RequestParam(value="title") String title
    ){
        sg = new Song();
        if(!uploadService.isLoggedIn()){
            System.out.println("fail");
            return "loginuidiot";
        }else{
            sg.setOwner(uploadService.getUser().getUsername());
        }
        System.out.println(string);
        sg.setBase64str(string);
        if(privat){
           sg.setAccess(Song.Access.PRIVATE);
           UserName usrnm = userNameRepo.findByName(userService.getUser().getUsername()).get();
           sg.getFriendUserNames().add(usrnm);
        }else{
           sg.setAccess(Song.Access.PUBLIC);
        }
        sg.setTitle(title);
        
        Set<Tag> set = new HashSet<>();
        for(String tag : myArray){
            Tag tg = new Tag();
            tg.setTag(tag);
            try{
                tagRepo.save(tg);
            }catch(Exception e){
                tg = tagRepo.findByTag(tag).get();
            }
            set.add(tg);
        }
        System.out.println(set);
        sg.setTags(set);
        sngRepo.save(sg);
        return "success";
    }
}   

