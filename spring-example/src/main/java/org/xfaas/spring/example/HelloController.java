package org.xfaas.spring.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public HelloDTO hello(@RequestParam String id, @RequestParam String id2, @RequestBody HelloDTO body){
        var result = new HelloDTO();
        result.body = "World: " + body.body + ", ID: " + id + ", ID2: " + id2;
        return result;
    }

    @GetMapping
    public WorldDTO getWorld(@RequestBody WorldDTO worldDTO){
        var result = new WorldDTO();
        result.world = "World: " + worldDTO.world;
        return result;
    }
}


