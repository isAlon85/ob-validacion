package com.team1.obvalidacion.services;

import com.team1.obvalidacion.entities.RegUser;
import com.team1.obvalidacion.repositories.RegUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegUserServiceImpl implements RegUserService {

    private final RegUserRepository regUserRepository;

    public RegUserServiceImpl(RegUserRepository regUserRepository) {
        this.regUserRepository = regUserRepository;
    }

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

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

        //AQUI HAY QUE REVISAR SI EL MAIL ES EXISTENTE EN EL REPOSITORIO
        for (int i = 0; i < regUserRepository.count(); i++){
            if (Objects.equals(regUser.getEmail(), regUserRepository.findAll().get(i).getEmail())) {
                return ResponseEntity.badRequest().build();
            }
        }

        regUser.setPassword(bcryptEncoder.encode(regUser.getPassword()));
        RegUser result = regUserRepository.save(regUser);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<RegUser> update(RegUser regUser){

        if (regUser.getId() == null)
            return ResponseEntity.badRequest().build();

        if (!regUserRepository.existsById(regUser.getId()))
            return ResponseEntity.notFound().build();

//        for (int i = 0; i < regUserRepository.count(); i++){
//            if (Objects.equals(regUser.getEmail(), regUserRepository.findAll().get(i).getEmail())) {
//                return ResponseEntity.badRequest().build();
//            }
//        }
//
//        for (int i = 0; i < regUserRepository.count(); i++){
//            if (Objects.equals(regUser.getUsername(), regUserRepository.findAll().get(i).getUsername())) {
//                return ResponseEntity.badRequest().build();
//            }
//        }

        regUser.setPassword(bcryptEncoder.encode(regUser.getPassword()));
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

