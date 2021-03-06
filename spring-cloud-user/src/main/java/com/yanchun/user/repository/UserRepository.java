package com.yanchun.user.repository;

import com.yanchun.common.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Passport, Long> , JpaSpecificationExecutor<Passport> {

    @Query(value = "select * from Passport where phone=:phone",nativeQuery = true)
    Passport findByPhone(@Param("phone") String phone);

    @Query(value = "select * from Passport where unionid=:unionid",nativeQuery = true)
    Passport findByUnionid(@Param("unionid") String unionid);

}