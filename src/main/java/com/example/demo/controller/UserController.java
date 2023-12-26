package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.request.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> login (@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = userService.login(userDTO);
        return new ResponseEntity<>(responseDTO.getStatus());
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO){
        userDTO.setName("test");
        userDTO.setPassword("testPassword");
        ResponseDTO responseDTO = userService.addUser(userDTO);
        return new ResponseEntity<>(responseDTO.getStatus());
    }
}
