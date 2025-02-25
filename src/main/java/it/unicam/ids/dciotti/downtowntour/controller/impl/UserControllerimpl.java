//package it.unicam.ids.dciotti.downtowntour.controller.impl;
//
//import it.unicam.ids.dciotti.downtowntour.controller.UserController;
//import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
//import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
//import it.unicam.ids.dciotti.downtowntour.exception.InvalidCredentialsException;
//import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
//import it.unicam.ids.dciotti.downtowntour.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class UserControllerimpl implements UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserControllerimpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    public ResponseEntity<UserDTO> loginUser(LoginDTO loginDTO) {
//        try {
//            UserDTO userDTO = userService.loginUser(loginDTO);
//            return new ResponseEntity<>(userDTO, HttpStatus.OK);
//        } catch (InvalidCredentialsException e) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
//        }
//    }
//
//    @Override
//    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
//        UserDTO dtoSaved = userService.createUser(userDTO);
//        return new ResponseEntity<>(dtoSaved, HttpStatus.CREATED);
//    }
//
//    @Override
//    public ResponseEntity<UserDTO> retrieveUser(Integer id) {
//        UserDTO dto = userService.retrieveUser(id);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<UserDTO> updateUser(Integer id, UserDTO userDTO) throws UserNotFoundException {
//        UserDTO dto = userService.updateUser(id, userDTO);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<List<UserDTO>> retrieveAllUsers() {
//        List<UserDTO> dtos = userService.retrieveAllUsers();
//        return new ResponseEntity<>(dtos, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<Void> deleteUser(Integer id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
