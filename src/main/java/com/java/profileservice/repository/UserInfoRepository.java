package com.java.profileservice.repository;

import com.java.profileservice.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
