package com.restapi.rizqnasionalwebsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.Admin;
import com.restapi.rizqnasionalwebsite.entity.AuthUserDetails;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.entity.UserInfoAdmin;
import com.restapi.rizqnasionalwebsite.mapper.AdminMapper;
import com.restapi.rizqnasionalwebsite.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService { 

	@Autowired
	private UserMapper userMapper; 

    @Autowired
	private AdminMapper adminMapper; 

	@Autowired
	private PasswordEncoder encoder; 

	public User registerUser(User user) {
        // Hash the password before saving to the database
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Save the user to the database
        userMapper.save(user);

        return user;
    }

    public User getUserByIdentityNumber(String identityNumber) {
        return userMapper.findByIdentityNumber(identityNumber).orElse(null);
    }

    public List<UserInfoAdmin> getListUser(String username){
        Admin admin = adminMapper.findByUsername(username).orElse(null);
        if (admin.getRole().equals("ROLE_MASTER_ADMIN")){
            return userMapper.findByCreatedByMasterRole(username);
        }
        return userMapper.findByCreatedBy(username);
    }

    public void updateBankDetails(User user){
        userMapper.updateBankDetails(user);
    }

    public void updateProfileDetails(User user){
        userMapper.updateProfileDetails(user);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userDetail = userMapper.findByIdentityNumber(username); 
        // Converting userDetail to UserDetails 
        return userDetail.map(AuthUserDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

} 

