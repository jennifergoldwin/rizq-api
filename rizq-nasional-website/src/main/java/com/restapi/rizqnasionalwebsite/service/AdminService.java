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
import com.restapi.rizqnasionalwebsite.mapper.AdminMapper;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
	private AdminMapper adminMapper; 

	@Autowired
	private PasswordEncoder encoder; 

	public List<Admin> registerAdmin(Admin admin,String username) {
        // Hash the password before saving to the database
        String hashedPassword = encoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);

        // Save the user to the database
        adminMapper.save(admin);

        return adminMapper.findByCreatedBy(username);
    }

    public List<Admin> getAdminByCreatedBy(String username) {
        return adminMapper.findByCreatedBy(username);
    }

    public Admin getAdminByUsername(String username) {
        return adminMapper.findByUsername(username).orElse(null);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> adminDetail = adminMapper.findByUsername(username); 
        // Converting userDetail to UserDetails 
        return adminDetail.map(AuthUserDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found " + username));
	}
      
    public void delete(Admin admin) {
        adminMapper.delete(admin);
    }

    public void update(Admin admin){
        String hashedPassword = encoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);
        adminMapper.update(admin);
    }
}
