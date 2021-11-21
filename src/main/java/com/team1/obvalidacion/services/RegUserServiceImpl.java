package com.team1.obvalidacion.services;

import com.team1.obvalidacion.entities.RegUser;
import com.team1.obvalidacion.repositories.RegUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegUserServiceImpl implements RegUserService {

    private final RegUserRepository regUserRepository;

    public RegUserServiceImpl(RegUserRepository regUserRepository) {
        this.regUserRepository = regUserRepository;
    }

    @Override
    public ResponseEntity<List<RegUser>> findAll(){
        if (regUserRepository.count() == 0)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(regUserRepository.findAll());
    }

    @Override
    public ResponseEntity<RegUser> findOneById(Long id){
        Optional<RegUser> regUserOpt = regUserRepository.findById(id);

        if (regUserOpt.isPresent())
            return ResponseEntity.ok(regUserOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<RegUser> create(RegUser regUser){

        if (regUser.getId() != null)
            return ResponseEntity.badRequest().build();

        RegUser result = regUserRepository.save(regUser);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<RegUser> update(RegUser regUser){

        if (regUser.getId() == null)
            return ResponseEntity.badRequest().build();

        if (!regUserRepository.existsById(regUser.getId()))
            return ResponseEntity.notFound().build();

        RegUser result = regUserRepository.save(regUser);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity delete(Long id){

        if (!regUserRepository.existsById(id))
            return ResponseEntity.notFound().build();

        regUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity deleteAll(){

        if (regUserRepository.count() == 0)
            return ResponseEntity.notFound().build();

        regUserRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

