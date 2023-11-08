package com.restapi.rizqnasionalwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.AuthRequest;
import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.AuthResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.JwtService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1/auth") 
public class AuthenticationController {
    @Autowired
    private JwtService jwtProvider;
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String welcome() { 
        return "Welcome this endpoint is not secure"; 
    } 
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Check if the user with the provided identityNumber already exists
            User existingUser = userService.getUserByIdentityNumber(user.getIdentityNumber());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse<>(true,"User with this identity number already exists",null));
            }
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse<>(false,"User created",null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = userService.getUserByIdentityNumber(authRequest.getIdentityNumber());
            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true, "Invalid identity number", null));
            }
            else if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true, "Invalid password", null));
            }

            String jwt = jwtProvider.generateTokenWithIdentityNumber(authRequest.getIdentityNumber());
            return ResponseEntity.ok(new CommonResponse<>(false,"success",new AuthResponse(jwt, user.getIdentityNumber(), user.getFullName())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true,e.getLocalizedMessage(),null));
        }
    }
}

