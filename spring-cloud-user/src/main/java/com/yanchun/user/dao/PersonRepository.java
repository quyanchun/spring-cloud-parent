package com.yanchun.user.dao;

import com.yanchun.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Passport, Long> {
    Passport findById(long id);
}