/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.repositories;

import com.thecodecorner.IHaveIt.entities.Account;
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
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.nombre LIKE :q OR a.apellido LIKE :q OR a.password LIKE :q OR a.username LIKE :q OR a.mail LIKE :q OR a.mailRespaldo LIKE :q OR a.fechaCreacion LIKE :q OR a.redSocial LIKE :q")
    List<Account> findAllByQ(@Param("q") String q);

}
