package com.practice.project.dao;

import com.practice.project.entity.Product;
import com.practice.project.entity.User;
import com.practice.project.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCartRepo extends JpaRepository<UserCart, Long> {
    UserCart findByProductAndUser(Product product, User user);
    List<UserCart> findByUser(User user);
}