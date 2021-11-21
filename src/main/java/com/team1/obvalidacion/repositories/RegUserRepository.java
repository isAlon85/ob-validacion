package com.team1.obvalidacion.repositories;

import com.team1.obvalidacion.entities.RegUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegUserRepository extends JpaRepository<RegUser,Long> {

//    List<RegUser> findByCategory(String category);
//
//    List<RegUser> findByUsername(String username);
//
//    List<RegUser> findByRating(Integer rating);

}