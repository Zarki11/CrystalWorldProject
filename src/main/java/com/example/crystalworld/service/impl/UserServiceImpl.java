package com.example.crystalworld.service.impl;

import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.model.entity.UserRole;
import com.example.crystalworld.model.enums.RoleEnum;
import com.example.crystalworld.repository.UserRepository;
import com.example.crystalworld.repository.UserRoleRepository;
import com.example.crystalworld.service.UserService;
import com.example.crystalworld.validation.EditProfileBindingModel;
import com.example.crystalworld.validation.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Value("${ADMIN_PASS}")
    public String adminPassword;

    @Value("${MODERATOR_PASS}")
    public String moderatorPass;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        User user = modelMapper.map(userRegisterBindingModel, User.class);

        if (userRepository.findByUsername(user.getUsername()) != null){
            return;
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    @Override
    public void init() {

        if (userRepository.count() != 0) return;

        UserRole adminRole = new UserRole(RoleEnum.ADMIN);
        UserRole moderatorRole = new UserRole(RoleEnum.MODERATOR);

        userRoleRepository.save(adminRole);
        userRoleRepository.save(moderatorRole);

        User admin = new User("Zarki11", passwordEncoder.encode(adminPassword), "velizar.madanski1@gmail.com", "Velizar", "Madanski");
        admin.getUserRoles().add(adminRole);
        admin.getUserRoles().add(moderatorRole);
        admin.setTown("Rudozem");
        userRepository.save(admin);

        User moderator = new User("moderator", passwordEncoder.encode(moderatorPass), "uli@abv.bg", "Yulhan", "Madanski");
        moderator.getUserRoles().add(moderatorRole);
        moderator.setTown("Rudozem");
        userRepository.save(moderator);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void loginUser(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void editProfileData(String username, EditProfileBindingModel editProfileBindingModel) {

        User user = userRepository.findByUsername(username);
        user.setUsername(editProfileBindingModel.getUsername());
        user.setFirstName(editProfileBindingModel.getFirstName());
        user.setLastName(editProfileBindingModel.getLastName());
        user.setEmail(editProfileBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(editProfileBindingModel.getPassword()));

        userRepository.save(user);
    }


}
