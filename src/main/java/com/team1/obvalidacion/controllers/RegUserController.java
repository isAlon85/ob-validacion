package com.team1.obvalidacion.controllers;

import com.team1.obvalidacion.entities.RegUser;
import com.team1.obvalidacion.services.RegUserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RegUserController {

    private final String ROOT = "/api/regUsers";
    private final Logger log = LoggerFactory.getLogger(RegUserController.class);
    private final RegUserServiceImpl regUserService;

    public RegUserController(RegUserServiceImpl regUserService) {
        this.regUserService = regUserService;
    }

    @GetMapping(ROOT)
    @ApiOperation("Find all regUsers in DB")
    public ResponseEntity<List<RegUser>> findAll() {
        return regUserService.findAll();
    }

    @GetMapping(ROOT + "/" + "{id}")
    @ApiOperation("Find a regUser in DB by ID")
    public ResponseEntity<RegUser> findOneById(@PathVariable Long id) {
        return regUserService.findOneById(id);
    }

    @PostMapping(ROOT + "/register")
    @ApiOperation("Create a regUser in DB with a JSON")
    public ResponseEntity<RegUser> create(@RequestBody RegUser regUser) {
        ResponseEntity<RegUser> result = regUserService.create(regUser);

        if (result.getStatusCode().equals(HttpStatus.BAD_REQUEST))
            log.warn("Trying to create a RegUser with ID or email/username is already used");

        return result;
    }

    @PutMapping(ROOT)
    @ApiOperation("Update a regUser in DB with a JSON")
    public ResponseEntity<RegUser> updateBook(@RequestBody RegUser regUser) {
        ResponseEntity<RegUser> result = regUserService.update(regUser);

        if (result.getStatusCode().equals(HttpStatus.BAD_REQUEST))
            log.warn("Trying to update a RegUser without ID");

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("Trying to update a RegUser with a non existing ID");

        return result;
    }

    @DeleteMapping(ROOT + "/" + "{id}")
    @ApiOperation("Delete a regUser in DB by ID")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseEntity result = regUserService.delete(id);

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("Trying to delete a RegUser with a non existing ID");

        return result;
    }

    @DeleteMapping(ROOT + "/" + "restartDB")
    public ResponseEntity deleteAll(@RequestHeader HttpHeaders headers) {
        ResponseEntity result = regUserService.deleteAll();

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("The DB is already empty");
        else
            log.warn("Deleting all by request of " + headers.get("User-Agent"));

        return result;
    }
}
