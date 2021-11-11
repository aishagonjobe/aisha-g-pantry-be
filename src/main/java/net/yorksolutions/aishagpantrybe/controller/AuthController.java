package net.yorksolutions.aishagpantrybe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.aishagpantrybe.model.Auth;
import net.yorksolutions.aishagpantrybe.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
public class AuthController {
    @Autowired
    AuthRepository authRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/create")
    String registerUser(@RequestBody String body) {
        try {
            Auth auth = objectMapper.readValue(body, Auth.class);
            authRepository.save(auth);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }

    @PostMapping("/login")
    String loginUser(@RequestBody String body) {
        try {
            Auth auth = objectMapper.readValue(body, Auth.class);
            authRepository.save(auth);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/user-settings/id")
    String getUser(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Auth> authList = (List<Auth>) authRepository.findAll();
        authList = authList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(authList);
    }

    @PutMapping("/update/id")
    String updateUser(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Auth> authList = (List<Auth>) authRepository.findAll();
        authList = authList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(authList);
    }

    @DeleteMapping("/delete/id")
    String deleteUser(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Auth> authList = (List<Auth>) authRepository.findAll();
        authList = authList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(authList);
    }
}
