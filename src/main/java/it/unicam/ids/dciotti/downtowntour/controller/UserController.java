//package it.unicam.ids.dciotti.downtowntour.controller;
//
//import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
//import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
//import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping(path = "/user")
//public interface UserController {
//    @PostMapping("/login")
//    ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO loginDTO);
//
//    @PostMapping
//    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO);
//
//    @GetMapping(path = "/by-id/{id}")
//    ResponseEntity<UserDTO> retrieveUser(@PathVariable(name = "id") Integer id);
//
//    @PutMapping(path = "/by-id/{id}")
//    ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDTO userDTO) throws UserNotFoundException;
//
//    @GetMapping(path = "/all")
//    ResponseEntity<List<UserDTO>> retrieveAllUsers();
//
//    @DeleteMapping(path = "/by-id/{id}")
//    ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Integer id);
//}
