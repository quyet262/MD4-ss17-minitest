package com.codegym.service.user.impl;


import com.codegym.model.user.AppRole;
import com.codegym.repository.AppRoleRepository;

import com.codegym.service.user.IAppRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppRoleService implements IAppRoleService {

    private final AppRoleRepository appRoleRepository;

    public AppRoleService(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return appRoleRepository.findById(id);
    }

    @Override
    public void save(AppRole appRole) {
    appRoleRepository.save(appRole);
    }

    @Override
    public void remove(Long id) {
        appRoleRepository.deleteById(id);
    }
}
