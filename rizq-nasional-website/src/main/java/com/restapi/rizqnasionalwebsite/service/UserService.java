package com.restapi.rizqnasionalwebsite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.AuthUserDetails;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService { 

	@Autowired
	private UserMapper userMapper; 

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userDetail = userMapper.findByIdentityNumber(username); 
        // Converting userDetail to UserDetails 
        return userDetail.map(AuthUserDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

} 

