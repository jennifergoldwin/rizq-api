package com.restapi.rizqnasionalwebsite.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class AuthUserDetails implements UserDetails {
    
    private String identityNumber; 
    private String password; 
    private List<GrantedAuthority> authorities;

    public AuthUserDetails(User user) { 
        identityNumber = user.getIdentityNumber(); 
        password = user.getPassword(); 
        authorities = Arrays.stream(user.getRole().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList()); 
    } 

    public AuthUserDetails(Admin admin) { 
        identityNumber = admin.getUsername(); 
        password = admin.getPassword(); 
        authorities = Arrays.stream(admin.getRole().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList()); 
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return identityNumber;
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
