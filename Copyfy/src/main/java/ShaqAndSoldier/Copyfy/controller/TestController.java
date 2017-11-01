
package ShaqAndSoldier.Copyfy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aram
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/field")
    public String testField() {
        return "testfield";
    }
}   
