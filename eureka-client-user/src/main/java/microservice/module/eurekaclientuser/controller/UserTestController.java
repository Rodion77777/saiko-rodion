package microservice.module.eurekaclientuser.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-main")
public class UserTestController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/test")
    public String test() {
        return instanceId;
    }
}
