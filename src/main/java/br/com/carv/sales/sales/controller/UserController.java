package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.dto.CredentialsDto;
import br.com.carv.sales.sales.dto.TokenDto;
import br.com.carv.sales.sales.entities.User;
import br.com.carv.sales.sales.exceptions.PasswordInvalidException;
import br.com.carv.sales.sales.repositories.UserRepository;
import br.com.carv.sales.sales.security.jwt.JWTService;
import br.com.carv.sales.sales.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user) {

        String encryptedPassword = passwordEncoder.encode(user.getPasswordUser());
        user.setPasswordUser(encryptedPassword);
        return userService.saveUser(user);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    public TokenDto authenticate(@RequestBody CredentialsDto credentialsDto) {
        try{
            User user = new User();
            user.setLoginUser(credentialsDto.getLoginUser());
            user.setPasswordUser(credentialsDto.getPasswordUser());

            UserDetails userAuthenticate = userService.authenticate(user);

            String token = jwtService.generateToken(user);
            return new TokenDto(user.getLoginUser(), user.getPasswordUser());

        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

        } catch (PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

}
