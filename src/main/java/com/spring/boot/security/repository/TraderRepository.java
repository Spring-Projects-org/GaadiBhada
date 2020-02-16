package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.traderData.Trader;
import com.spring.boot.security.userdata.UserVO;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Integer> {
	
	
}
