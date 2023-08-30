package com.practice.project.repository;

import com.practice.project.modal.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCartRepo extends JpaRepository<UserCart, Long> {
}
