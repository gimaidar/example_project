package com.gimaletdinov.exampleProject.security;

import com.gimaletdinov.exampleProject.dao.AuthorizationUserRepository;
import com.gimaletdinov.exampleProject.model.security.AuthorizationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorizationUserRepository authorizationUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AuthorizationUserRepository authorizationUserRepository) {
        this.authorizationUserRepository = authorizationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        AuthorizationUser authorizationUser = authorizationUserRepository.findByEmail(userEmail).orElseThrow(
                () -> new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(authorizationUser);
    }
}
