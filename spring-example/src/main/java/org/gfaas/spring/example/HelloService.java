package org.gfaas.spring.example;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloDTO sendHello(HelloDTO request){
        var helloDto = new HelloDTO();
        helloDto.body = request.body;
        return helloDto;
    }

}
