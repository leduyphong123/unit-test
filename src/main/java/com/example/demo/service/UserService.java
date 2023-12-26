package com.example.demo.service;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.request.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    ResponseDTO login(UserDTO userDTO);

    ResponseDTO findUserById(Long userId);

    ResponseDTO addUser(UserDTO userDTO);

    ResponseDTO editUser(UserDTO userDTO);

    ResponseDTO deleteUser(long id);
}
