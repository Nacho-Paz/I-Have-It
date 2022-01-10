/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.services;

import com.thecodecorner.IHaveIt.entities.User;
import com.thecodecorner.IHaveIt.enums.Roles;
import com.thecodecorner.IHaveIt.exceptions.WebException;
import com.thecodecorner.IHaveIt.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    @Transactional
    private User saveUser(String user, String password, String password2) throws WebException {

        validateUser(user, password, password2);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user1 = new User();

        user1.setUsuarioName(user);
        user1.setUsuarioPassword(encoder.encode(password));
        user1.setRol(Roles.USER);

        return ur.save(user1);
    }

    @Transactional
    private User editUser(String id, String username, String password, String password2) throws WebException {

        if (id.isEmpty() || id == null) {
            throw new WebException("Ocurrio un problema con el ID");
        }

        validateUser(username, password, password2);

        Optional<User> opt = ur.findById(id);

        if (opt.isPresent()) {
            User user = opt.get();

            user.setUsuarioName(username);
            user.setUsuarioPassword(password);

            return ur.save(user);
        } else {
            throw new WebException("No existe usuario con esa ID");
        }
    }

    @Transactional
    private void deleteUser(String id) throws WebException {

        if (id.isEmpty() || id == null) {
            throw new WebException("Ocurrio un problema con el ID");
        }

        Optional<User> opt = ur.findById(id);

        if (opt.isPresent()) {

            User user = opt.get();
            ur.delete(user);

        } else {
            throw new WebException("No se encontro usuario con esa ID");
        }
    }

    private User getUser(String username) throws WebException {
        if (username.isEmpty() || username == null) {
            throw new WebException("El usuario no puede ser nulo");
        }
        return ur.findByUsername(username);
    }

    private List<User> listAll() {
        return ur.findAll();
    }

    private List<User> listAllByQ(String q) {
        return ur.findAllByQ("%" + q + "%");
    }

    private void validateUser(String user, String password, String password2) throws WebException {
        if (user.isEmpty() || user == null) {
            throw new WebException("El nombre de usuario no puede ser nulo");
        }
        if (password.isEmpty() || password == null || password2.isEmpty() || password2 == null) {
            throw new WebException("La contraseña no puede ser nula");
        }
        if (!password.equals(password2)) {
            throw new WebException("Las contraseñas no coinciden");
        }
    }
}
