/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.services;

import com.thecodecorner.IHaveIt.entities.Account;
import com.thecodecorner.IHaveIt.entities.User;
import com.thecodecorner.IHaveIt.exceptions.WebException;
import com.thecodecorner.IHaveIt.repositories.AccountRepository;
import com.thecodecorner.IHaveIt.repositories.RedSocialRepository;
import com.thecodecorner.IHaveIt.repositories.UserRepository;
import java.util.Date;
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
public class AccountService {

    @Autowired
    private AccountRepository ar;

    @Autowired
    private UserRepository ur;

    @Autowired
    private RedSocialRepository rsr;

    @Transactional
    private Account saveAccount(String userId, String nombre, String apellido, String password, String password2, String username, String mail, String mailRespaldo, Date fechaCreacion, String redSocial) throws WebException {

        if (userId.isEmpty() || userId == null) {
            throw new WebException("Ocurrio un problema con el ID");
        }

        Optional<User> opt = ur.findById(userId);

        if (opt.isPresent()) {

            validateAccount(nombre, apellido, password, password2, username, mail, mailRespaldo, fechaCreacion, redSocial);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            Account account = new Account();
            account.setId(userId);
            account.setUsuarioName(opt.get().getUsuarioName());
            account.setUsuarioPassword(opt.get().getUsuarioPassword());
            account.setRol(opt.get().getRol());
            account.setNombre(nombre);
            account.setApellido(apellido);
            account.setPassword(encoder.encode(password));
            account.setMail(mail);
            account.setMailRespaldo(mailRespaldo);
            account.setFechaCreacion(fechaCreacion);
            account.setRedSocial(rsr.getById(redSocial));
            ur.deleteById(userId);
            return ar.save(account);
        } else {
            throw new WebException("No se encontro usuario con esa ID");
        }
    }

    @Transactional
    private Account editAccount(String userId, String nombre, String apellido, String password, String password2, String username, String mail, String mailRespaldo, Date fechaCreacion, String redSocial) throws WebException {

        if (userId.isEmpty() || userId == null) {
            throw new WebException("No se encontro cuenta con esa ID");
        }

        validateAccount(nombre, apellido, password, password2, username, mail, mailRespaldo, fechaCreacion, redSocial);

        Optional<Account> opt = ar.findById(userId);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (opt.isPresent()) {
            Account account = opt.get();
            account.setNombre(nombre);
            account.setApellido(apellido);
            account.setPassword(encoder.encode(password));
            account.setMail(mail);
            account.setMailRespaldo(mailRespaldo);
            account.setFechaCreacion(fechaCreacion);
            account.setRedSocial(rsr.getById(redSocial));

            return ar.save(account);
        } else {
            throw new WebException("No existe cuenta con esa ID");
        }
    }

    //Comprobar si al borrar una cuenta tambien me borra el usuario    
    @Transactional
    private void deleteAccount(String id) throws WebException {
        if (id.isEmpty() || id == null) {
            throw new WebException("No se encontro la cuenta");
        }

        Optional<Account> opt = ar.findById(id);

        if (opt.isPresent()) {
            ar.deleteById(id);
        } else {
            throw new WebException("No se encontro la cuenta");
        }
    }

    private Account getById(String id) throws WebException {
        if (id.isEmpty() || id == null) {
            throw new WebException("No se encontro cuenta con esa ID");
        }
        return ar.getById(id);
    }

    private List<Account> listAll() {
        return ar.findAll();
    }

    private List<Account> listAllByQ(String q) {
        return ar.findAllByQ("%" + q + "%");
    }

    //falta validar el tema fechas!
    private void validateAccount(String nombre, String apellido, String password, String password2, String username, String mail, String mailRespaldo, Date fechaCreacion, String redSocial) throws WebException {
        if (nombre.isEmpty() || nombre == null) {
            throw new WebException("El nombre no puede estar vacío.");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new WebException("El apellido no puede estar vacío.");
        }
        if (password.isEmpty() || password == null) {
            throw new WebException("La contraseña no puede estar vacía.");
        }
        if (password2.isEmpty() || password2 == null) {
            throw new WebException("El campo de 'repetir contraseña' no puede estar vacío.");
        }
        if (username.isEmpty() || username == null) {
            throw new WebException("El nombre de usuario no puede estar vacío.");
        }
        if (mail.isEmpty() || mail == null) {
            throw new WebException("El mail no puede estar vacío.");
        }
        if (mailRespaldo.isEmpty() || mailRespaldo == null) {
            throw new WebException("El mail de respaldo no puede estar vacío.");
        }
        if (fechaCreacion == null) {
            throw new WebException("La fecha de creacion no puede estar vacía.");
        }
        if (redSocial.isEmpty() || redSocial == null) {
            throw new WebException("La red social no puede estar vacía.");
        }
        if (!password.equals(password2)) {
            throw new WebException("Las contraseñas no coinciden");
        }

    }
}
