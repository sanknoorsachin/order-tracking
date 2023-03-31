package com.order.tracking.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.tracking.consumer.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserName(String username);
}
