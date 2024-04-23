package com.example.crystalworld.service.impl;

import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.model.entity.UserRole;
import com.example.crystalworld.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

            return getUserDetails(user);
    }

    private UserDetails getUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getUserRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<UserRole> userRoles) {
        return userRoles.stream().map(this::getAuthority).collect(Collectors.toList());
    }

    private GrantedAuthority getAuthority(UserRole userRole) {
        return new SimpleGrantedAuthority(userRole.getUserRole().name());
    }

}
