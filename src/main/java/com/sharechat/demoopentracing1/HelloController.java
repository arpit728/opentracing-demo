package com.sharechat.demoopentracing1;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {



    @RequestMapping("/hello")
    public String hello() {
        return "Hello From Spring Boot";
    }

    @RequestMapping("/chaining")
    public String chaining() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9000/hello")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e){
            return "couldn't call downstream";
        }
       /* RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:9000/hello", String.class);
        return "Chaining" + responseEntity.getBody();*/
    }
}
