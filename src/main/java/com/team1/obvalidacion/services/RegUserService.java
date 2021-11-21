package com.team1.obvalidacion.services;

import com.team1.obvalidacion.entities.RegUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegUserService {

    ResponseEntity<List<RegUser>> findAll();

    ResponseEntity<RegUser> findOneById(Long id);

    ResponseEntity<RegUser> create(RegUser regUser);

    ResponseEntity<RegUser> update(RegUser regUser);

    ResponseEntity delete(Long id);

    ResponseEntity deleteAll();

}
