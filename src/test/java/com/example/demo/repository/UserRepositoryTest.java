package com.example.demo.repository;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.request.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindUserById(){
        User mockUser = new User(1L, "john.doe@example.com", "John Doe");
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        ResponseDTO responseDTO = userService.findUserById(userId);

        User result = (User) responseDTO.getData();
        assertEquals(mockUser, result);

        verify(userRepository, times(1)).findById(userId);
    }
    @Test
    public void testAddUser(){
        UserDTO userDTO = UserDTO.builder().name("test").password("testPassword").build();

        when(userRepository.save(any(User.class))).then(invocationOnMock -> {
            User savedUser = invocationOnMock.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });
        ResponseDTO responseDTO = userService.addUser(userDTO);

        User userResult = (User) responseDTO.getData();
        assertNotNull(userResult);
        assertEquals(userDTO.getName(),userResult.getName());
        assertEquals(userDTO.getPassword(),userResult.getPassword());
        assertNotNull(userResult.getId());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testEditUser(){
        UserDTO userDTO = UserDTO.builder().id(1L).name("test1").password("testPassword").build();

        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> {
            User savedUser = invocationOnMock.getArgument(0);
            return savedUser;
        });
        ResponseDTO responseDTO = userService.editUser(userDTO);

        User userResult = (User) responseDTO.getData();
        assertNotNull(userResult);
        assertEquals(userDTO.getName(),userResult.getName());
        assertEquals(userDTO.getPassword(),userResult.getPassword());
        verify(userRepository,times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUser(){
        long id = 1L;
        doNothing().when(userRepository).deleteById(any(Long.class));

        ResponseDTO responseDTO = userService.deleteUser(id);

        boolean isResult = (boolean) responseDTO.getData();
        assertEquals(true,isResult);
        assertEquals("success",responseDTO.getMassage());

        verify(userRepository,times(1)).deleteById(id);
    }
}
