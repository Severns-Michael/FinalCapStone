package com.techelevator.controller;

import jakarta.validation.Valid;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.security.Principal;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);

        User user;
        try {
            user = userDao.getUserByUsername(loginDto.getUsername());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            if (userDao.getUserByUsername(newUser.getUsername()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
            } else {
                userDao.createUser(newUser);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path="/promote")
    public void promoteUser(@RequestBody User user, Principal p){
        String errorVar="test";
        if(p.getName().equals(user.getUsername())){
            errorVar=null;
        }
        try {
            errorVar.substring(0,1);
            userDao.promoteUser(user);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path="/demote")
    public void demoteUser(@RequestBody User user, Principal p){
        String errorVar="test";
        if(p.getName().equals(user.getUsername())){
            errorVar=null;
        }
        try {
            errorVar.substring(0,1);
            userDao.demoteUser(user);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Cannot change the role of the current user");
        }

    }

    @DeleteMapping(path="/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@RequestBody User user, Principal p){
        String errorVar="test";
        if(p.getName().equals(user.getUsername())){
            errorVar=null;
        }
        try {
            String testString=errorVar.substring(0,1);
            userDao.deleteUser(user);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Cannot delete current user");
        }
    }

}

