/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.repositories;

import com.thecodecorner.IHaveIt.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.usuarioName =:username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.usuarioName LIKE :q")
    List<User> findAllByQ(@Param("q") String q);
}
