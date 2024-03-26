package com.api.inventorysystem.Service;

import com.api.inventorysystem.DTO.UserDTO;
import com.api.inventorysystem.Entity.User;
import com.api.inventorysystem.Mapper.UserMapper;
import com.api.inventorysystem.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDTO create(User user) {
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public UserDTO getUser(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

    public UserDTO update(Long id, User userUpdate) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(userUpdate.getPassword());
        user.setRole(userUpdate.getRole());

        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

}
