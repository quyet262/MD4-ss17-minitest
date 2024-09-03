package com.codegym.service.user.impl;


import com.codegym.model.user.AppUser;
import com.codegym.model.user.UserPrinciple;
import com.codegym.repository.AppUserRepository;

import com.codegym.service.user.IAppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserService implements IAppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
    appUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByName(username);
        AppUser appUser = userOptional.get();
        if (appUser == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return  UserPrinciple.build(appUser);
    }
}
