package com.java.profileservice.repository;

import com.java.profileservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Transactional
    @Modifying
    @Query("delete from Image i where i.id IN (:ids)")
    void deleteByIds(@Param("ids") List<Long> ids);
}
