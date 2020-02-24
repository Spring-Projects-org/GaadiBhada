package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.TraderAgent;

@Repository
public interface TraderAgentRespository extends JpaRepository<TraderAgent, Integer> {

}
