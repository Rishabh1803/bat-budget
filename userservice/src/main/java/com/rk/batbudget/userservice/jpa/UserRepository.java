package com.rk.batbudget.userservice.jpa;

import com.rk.batbudget.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
