/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.repositories;

import com.thecodecorner.IHaveIt.entities.RedSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface RedSocialRepository extends JpaRepository<RedSocial, String> {

}
