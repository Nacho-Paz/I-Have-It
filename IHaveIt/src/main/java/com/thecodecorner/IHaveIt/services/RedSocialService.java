/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.services;

import com.thecodecorner.IHaveIt.repositories.RedSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class RedSocialService {

    @Autowired
    private RedSocialRepository rsr;
}
