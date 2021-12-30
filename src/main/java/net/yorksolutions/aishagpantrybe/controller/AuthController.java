package net.yorksolutions.aishagpantrybe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.aishagpantrybe.model.Auth;
import net.yorksolutions.aishagpantrybe.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@RestController
public class AuthController {

    @Autowired
    AuthRepository authRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody String body) {
        try {
            Auth user = objectMapper.readValue(body, Auth.class);
            authRepository.save(user);
            return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Auth user) {
        Optional<Auth> userData = authRepository.findByUsernameAndPassword(user.getUserName(), user.getPassword());
        try{
        if (userData.isPresent()) {
            Auth login = userData.get();
            return new ResponseEntity<>("Login Success", HttpStatus.OK);
        }
            return new ResponseEntity<>("Login Failed", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}