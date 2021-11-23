package com.team1.obvalidacion.controllers;

import com.team1.obvalidacion.entities.User;
import com.team1.obvalidacion.security.payload.MessageResponse;
import com.team1.obvalidacion.security.payload.RegisterRequest;
import com.team1.obvalidacion.services.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private final String ROOT = "/api/users";
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(ROOT)
    @ApiOperation("Find all Users in DB")
    public ResponseEntity<List<User>> findAll() {
        return userService.findAll();
    }

    @GetMapping(ROOT + "/" + "{id}")
    @ApiOperation("Find a User in DB by ID")
    public ResponseEntity<User> findOneById(@PathVariable Long id) {
        return userService.findOneById(id);
    }

    @PostMapping(ROOT + "/register")
    @ApiOperation("Create a User in DB with a JSON")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {
        ResponseEntity<MessageResponse> result = userService.register(signUpRequest);

        if (result.getStatusCode().equals(HttpStatus.BAD_REQUEST))
            log.warn("Trying to create a User with ID or email/username is already used");

        return result;
    }

    @PutMapping(ROOT)
    @ApiOperation("Update a User in DB with a JSON")
    public ResponseEntity<User> updateBook(@RequestBody User user) {
        ResponseEntity<User> result = userService.update(user);

        if (result.getStatusCode().equals(HttpStatus.BAD_REQUEST))
            log.warn("Trying to update a User without ID");

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("Trying to update a User with a non existing ID");

        return result;
    }

    @DeleteMapping(ROOT + "/" + "{id}")
    @ApiOperation("Delete a User in DB by ID")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseEntity result = userService.delete(id);

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("Trying to delete a User with a non existing ID");

        return result;
    }

    @DeleteMapping(ROOT + "/" + "restartDB")
    public ResponseEntity deleteAll(@RequestHeader HttpHeaders headers) {
        ResponseEntity result = userService.deleteAll();

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("The DB is already empty");
        else
            log.warn("Deleting all by request of " + headers.get("User-Agent"));

        return result;
    }
}
