package com.gcoce.bc.ws.security.services;

import com.gcoce.bc.ws.security.models.User;
import com.gcoce.bc.ws.security.models.UserDetailsImpl;
import com.gcoce.bc.ws.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsSvcImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsSvcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
