package ShaqAndSoldier.Copyfy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ShaqAndSoldier.Copyfy.model.User;
import ShaqAndSoldier.Copyfy.service.UserService;
import ShaqAndSoldier.Copyfy.service.exceptions.UsernameOrEmailInUseException;
/**
 * @author Aram
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        if(userService.isUserLoggedIn(name)){
            model.addAttribute("name", name);
            return "greeting";
        }else{
            model.addAttribute("illegalLogin", true);
            return "greeting";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (userService.isValid(user)) {
            if(userService.isBanned(user)){
                model.addAttribute("userBanned", true);
                return "login";
            }
            userService.setLoggedIn(user);
            return redirectToGreeting(user);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if(userService.isUsernameInUse(user)){
            model.addAttribute("usernameInUse", true);
            return "register";
        }
        if(userService.isEmailInUse(user)){
            model.addAttribute("emailInUse", true);
            return "register";
        }
        try{
        userService.register(user);
        }catch(UsernameOrEmailInUseException e){};
        return redirectToGreeting(user);
    }

    private String redirectToGreeting(@ModelAttribute User user) {
        return "redirect:/user/greet?name=" + user.getUsername();
    }
}