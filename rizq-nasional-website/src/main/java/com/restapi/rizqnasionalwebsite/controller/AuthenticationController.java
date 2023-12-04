package com.restapi.rizqnasionalwebsite.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.Admin;
import com.restapi.rizqnasionalwebsite.entity.AuthAdminRequest;
import com.restapi.rizqnasionalwebsite.entity.AuthAdminResponse;
import com.restapi.rizqnasionalwebsite.entity.AuthRequest;
import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.EditUserRequest;
import com.restapi.rizqnasionalwebsite.entity.Investment;
import com.restapi.rizqnasionalwebsite.entity.AuthResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.entity.UserInfoAdmin;
import com.restapi.rizqnasionalwebsite.service.AdminService;
import com.restapi.rizqnasionalwebsite.service.JwtService;
import com.restapi.rizqnasionalwebsite.service.StatementService;
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
    private StatementService statementService;

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

    @GetMapping("/listadmin/{username}")
     public ResponseEntity<?> getListAdmin(@PathVariable String username) {
        try {
            List<Admin> list = adminService.getAdminByCreatedBy(username);
            
            return ResponseEntity.ok(new CommonResponse<>(false, "success", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

     @GetMapping("/listuser/{username}")
     public ResponseEntity<?> getListUser(@PathVariable String username) {
        try {
            List<UserInfoAdmin> list = userService.getListUser(username);
            
            return ResponseEntity.ok(new CommonResponse<>(false, "success", list));
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
            List<Admin> list = adminService.registerAdmin(admin, admin.getCreatedby());
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Admin created", list));
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
            int lenInv = statementService.getAllInvestment().size()+1;
            String idInv = "INV-" + UUID.randomUUID();
            Investment inv = new Investment(idInv, user.getIdentityNumber(), null, null, 0, 0, "Done", "false");
            userService.registerUser(user,inv);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "User created", user));
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
    public ResponseEntity<?> authenticateAdmin(@RequestBody AuthAdminRequest authRequest){
        try {
            Admin admin = adminService.getAdminByUsername(authRequest.getUsername());
            if (admin == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid username", null));
            } else if (!passwordEncoder.matches(authRequest.getPassword(), admin.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new CommonResponse<>(true, "Invalid password", null));
            }
            String jwt = jwtProvider.generateTokenWithIdentityNumber(authRequest.getUsername());
            return ResponseEntity.ok(new CommonResponse<>(false, "success",
                    new AuthAdminResponse(jwt, admin.getUsername(), admin.getFullName(),admin.getRole())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/update-admin")
    public ResponseEntity<?> updateAdmin(@RequestBody Admin admin){
        try {
            adminService.update(admin);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Admin updated", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
    @DeleteMapping("/delete-admin")
    public ResponseEntity<?> deleteAdmin(@RequestBody Admin admin) {
        try {
            // Check if the user with the provided identityNumber already exists
            adminService.delete(admin);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Admin deleted", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
    @PutMapping("/update-bank")
    public ResponseEntity<?> updateBankDetails(@RequestBody User user){
        try {
            userService.updateBankDetails(user);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Bank details updated", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfileDetails(@RequestBody User user){
         try {
            userService.updateProfileDetails(user);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Profile updated", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody EditUserRequest user){
         try {
            
            userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "User updated", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            // Check if the user with the provided identityNumber already exists
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "User deleted", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
}
