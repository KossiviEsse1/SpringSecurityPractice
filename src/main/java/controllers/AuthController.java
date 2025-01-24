package controllers;

import Security.AuthResponse;
import Services.*;
import models.LoginForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.register(user);
            System.out.println(user.toString());
            return ResponseEntity.ok("User registered successfully");
        } catch (EmailExistsException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        } catch (UsernameExistsException e) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginForm form) {
        try {
            User user = userService.findUser(form.getUserCredential());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), form.getPassword()));
            String token = jwtUtil.generateToken(user.getUsername());
            System.out.println(token);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("Username or Email not found", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Bad Credentials", HttpStatus.BAD_REQUEST);
        }
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
