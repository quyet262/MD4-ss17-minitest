package com.codegym.repository;


import com.codegym.model.user.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AppRoleRepository extends JpaRepository<AppRole, Long> {
}
