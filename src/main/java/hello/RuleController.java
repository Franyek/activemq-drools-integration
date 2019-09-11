package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;

@RestController
public class RuleController {

    @Autowired
    private DroolsConfiguration droolsConfiguration;


    @RequestMapping(value = "/rule", method = RequestMethod.POST)
    @ResponseBody
    public String getNewRule(
            @RequestBody String rule) throws FileNotFoundException {
        droolsConfiguration.updateRules(rule);
        return "New rule is added";
    }
}
