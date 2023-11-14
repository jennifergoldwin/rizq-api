package com.restapi.rizqnasionalwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.Admin;
import com.restapi.rizqnasionalwebsite.entity.AuthRequest;
import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.AuthResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.AdminService;
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
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String welcome() {
    return "Welcome this endpoint is not secure";
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable String id) {
        try {
            User user = userService.getUserByIdentityNumber(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid identity number", null));
            }
            return ResponseEntity.ok(new CommonResponse<>(false, "success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin){
        try {
            Admin existingAdmin = adminService.getAdminByUsername(admin.getUsername());
            if (existingAdmin!=null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CommonResponse<>(true, "Username already exists", null));
            }
            adminService.registerAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Admin created", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Check if the user with the provided identityNumber already exists
            User existingUser = userService.getUserByIdentityNumber(user.getIdentityNumber());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CommonResponse<>(true, "User with this identity number already exists", null));
            }
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "User created", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = userService.getUserByIdentityNumber(authRequest.getIdentityNumber());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid identity number", null));
            } else if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid password", null));
            }

            String jwt = jwtProvider.generateTokenWithIdentityNumber(authRequest.getIdentityNumber());
            return ResponseEntity.ok(new CommonResponse<>(false, "success",
                    new AuthResponse(jwt, user.getIdentityNumber(), user.getFullName(),user.getRole())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/login-admin")
    public ResponseEntity<?> authenticateAdmin(@RequestBody AuthRequest authRequest){
        try {
            Admin admin = adminService.getAdminByUsername(authRequest.getIdentityNumber());
            if (admin == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid username", null));
            } else if (!passwordEncoder.matches(authRequest.getPassword(), admin.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid password", null));
            }
            String jwt = jwtProvider.generateTokenWithIdentityNumber(authRequest.getIdentityNumber());
            return ResponseEntity.ok(new CommonResponse<>(false, "success",
                    new AuthResponse(jwt, admin.getUsername(), admin.getFullName(),admin.getRole())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
}
