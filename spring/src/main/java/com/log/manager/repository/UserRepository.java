package com.log.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.log.manager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
