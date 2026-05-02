package com.application.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.manager.domain.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{
}
