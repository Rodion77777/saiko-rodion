package microservice.module.eurekaclientuser.controller;

import microservice.module.eurekaclientuser.entity.UserEntity;
import microservice.module.eurekaclientuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody UserEntity user)
    {
        try {
            String loginToken = userService.login(user);
            if (loginToken != null) return ResponseEntity.ok().body("Bearer ".concat(loginToken));
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity registration (@RequestBody UserEntity user)
    {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User is successfully saved!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getOneUser (@RequestParam Long id)
    {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateUser (@RequestParam Long id, @RequestBody UserEntity user)
    {
        try {
            return ResponseEntity.ok(userService.updateUserByID(id, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong! " + e.getMessage());
        }
    }
}