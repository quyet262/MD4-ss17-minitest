package com.codegym.service.user;


import com.codegym.model.user.AppUser;
import com.codegym.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGenerateService<AppUser>, UserDetailsService {
}
