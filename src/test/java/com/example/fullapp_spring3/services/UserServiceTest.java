package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.UserDAO;
import com.example.fullapp_spring3.dtos.UserDTO;
import com.example.fullapp_spring3.models.Role;
import com.example.fullapp_spring3.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = User.builder().id(5).username("johnny").password("johnny123456")
                .firstname("Johnny").surname("Storm").email("johnny@gmail.com")
                .phoneNumber("0123456789").role(Role.USER).build();
        userDTO = UserDTO.builder().id(5).username("johnny").firstname("Johnny")
                .surname("Storm").email("johnny@gmail.com").phoneNumber("0123456789")
                .role(Role.USER).build();
    }

    @Test
    void saveUser() {
        when(userDAO.save(user)).thenReturn(user);
        User savedUser = userDAO.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void findUser() {
        when(userService.findUser(user.getUsername())).thenReturn(userDTO);
        UserDTO userDTO1 = userService.findUser(user.getUsername());
        assertThat(userDTO1.getUsername()).isEqualTo("johnny");
        assertEquals(userDTO, userDTO1);
    }

    @Test
    void throwsExceptionOnInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () -> userService.findUser(null));
    }

    @Nested
    @DisplayName("throw an illegal argument exception")
    class ThrowsExceptionTests {
        @Test
        @DisplayName("when passed a 'null' username for findUser")
        void throwsExceptionOnNullUsername() {
            assertThrows(IllegalArgumentException.class, () -> userService.findUser(null));
        }

        @Test
        @DisplayName("when passed a blank username for findUser")
        void throwsExceptionOnBlankUsername() {
            assertThrows(IllegalArgumentException.class, () -> userService.findUser("   "));
        }

        @Test
        @DisplayName("when passed a empty username for findUser")
        void throwsExceptionOnEmptyUsername() {
            assertThrows(IllegalArgumentException.class, () -> userService.findUser(""));
        }
    }

    @Test
    void convertToDto() {
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        assertEquals(userDTO, userService.convertToDto(user));
    }

    @Test
    void convertToEntity() {
        when(userService.convertToEntity(userDTO)).thenReturn(user);
        assertEquals(user, userService.convertToEntity(userDTO));
    }
}