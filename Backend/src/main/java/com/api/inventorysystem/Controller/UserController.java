package com.api.inventorysystem.Controller;
import com.api.inventorysystem.DTO.UserDTO;
import com.api.inventorysystem.Entity.User;
import com.api.inventorysystem.Service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/by-email")
    public UserDTO getUser(@RequestParam String email) {
        return userService.getUser(email);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/by-id")
    public UserDTO update(@RequestParam Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/by-id")
    public void delete(@RequestParam Long id) {
        userService.delete(id);
    }

}
