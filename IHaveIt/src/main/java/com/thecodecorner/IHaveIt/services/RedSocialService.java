/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.services;

import com.thecodecorner.IHaveIt.entities.RedSocial;
import com.thecodecorner.IHaveIt.exceptions.WebException;
import com.thecodecorner.IHaveIt.repositories.RedSocialRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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

    @Transactional
    private RedSocial saveRedSocial(String nombre, String link) throws WebException {

        validateRedSocial(nombre, link);

        RedSocial rs = new RedSocial();
        rs.setLink(link);
        rs.setNombre(nombre);

        return rsr.save(rs);
    }

    @Transactional
    private RedSocial editRedSocial(String nombre, String link) throws WebException {

        if (link.isEmpty() || link == null) {
            throw new WebException("El link no puede ser nulo");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new WebException("El nombre no puede ser nulo");
        }

        Optional<RedSocial> opt = rsr.findById(nombre);

        if (opt.isPresent()) {
            RedSocial rs = opt.get();
            rs.setLink(nombre);
            rs.setLink(link);

            return rsr.save(rs);
        } else {
            throw new WebException("No se encontro la Red Social");
        }
    }

    @Transactional
    private void deleteRedSocial(String nombre) {
        rsr.deleteById(nombre);
    }

    private RedSocial getRedSocial(String nombre) throws WebException {
        if (nombre.isEmpty() || nombre == null) {
            throw new WebException("El nombre no puede ser nulo");
        }
        return rsr.getById(nombre);
    }

    private List<RedSocial> listAll() {
        return rsr.findAll();
    }

    private List<RedSocial> listAllByQ(String q) {
        return rsr.findAllByQ("%" + q + "%");
    }

    private void validateRedSocial(String nombre, String link) throws WebException {
        if (nombre.isEmpty() || nombre == null) {
            throw new WebException("El nombre no puede estar vacío");
        }
        if (link.isEmpty() || link == null) {
            throw new WebException("El link no puede ser nulo");
        }
        if (rsr.existsById(nombre)) {
            throw new WebException("Ya existe una Red Social con ese nombre");
        }
    }
}
