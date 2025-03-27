package BajajPlacementTask.BajajTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    @Autowired
    private HashService hashService;

    @GetMapping("/generate-hash")
    public String generateHash(){
        return "Generated Hash:" + hashService.generateHash();
    }
}
