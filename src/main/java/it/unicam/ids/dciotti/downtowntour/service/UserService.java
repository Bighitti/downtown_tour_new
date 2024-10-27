package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
import it.unicam.ids.dciotti.downtowntour.exception.InvalidCredentialsException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO loginUser(LoginDTO loginDTO) throws InvalidCredentialsException;
    UserDTO retrieveUser(Integer id);
    List<UserDTO> retrieveAllUsers();
    void deleteUser(Integer id);
    UserDTO updateUser(Integer id, UserDTO userDTO) throws UserNotFoundException;
}
