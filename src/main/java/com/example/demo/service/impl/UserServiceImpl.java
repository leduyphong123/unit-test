package com.example.demo.service.impl;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.request.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;

    @Override
    public ResponseDTO login(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseDTO findUserById(Long userId) {
        try {
            User user= userRepository.findById(userId).orElse(new User());
            return ResponseDTO.builder().massage("success").status(HttpStatus.OK).data(user).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.builder().massage("error").status(HttpStatus.BAD_REQUEST).data(new User()).build();
        }
    }

    @Override
    public ResponseDTO addUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
        try {
            User useResult= userRepository.save(user);
            return ResponseDTO.builder().massage("success").status(HttpStatus.OK).data(useResult).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.builder().massage("error").status(HttpStatus.BAD_REQUEST).data(new User()).build();
        }
    }

    @Override
    public ResponseDTO editUser(UserDTO userDTO) {
        User user  = userRepository.findById(userDTO.getId()).orElse(new User());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        try {
            User useResult= userRepository.save(user);
            return ResponseDTO.builder().massage("success").status(HttpStatus.OK).data(useResult).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.builder().massage("error").status(HttpStatus.BAD_REQUEST).data(new User()).build();
        }
    }

    @Override
    public ResponseDTO deleteUser(long id) {
        try{
            userRepository.deleteById(id);
            return ResponseDTO.builder().massage("success").status(HttpStatus.OK).data(true).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.builder().massage("error").status(HttpStatus.BAD_REQUEST).data(false).build();
        }
    }
}
