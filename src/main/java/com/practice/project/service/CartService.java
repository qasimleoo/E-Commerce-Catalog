package com.practice.project.service;

import com.practice.project.entity.Product;
import com.practice.project.entity.User;
import com.practice.project.entity.UserCart;

import java.util.List;

public interface CartService {
    UserCart addToCart(User user, Product product);

    void removeById(Long id);

    Long getUserCartIdByProductId(Long productId, User user);

    List<UserCart> getUserCartItems(User user);
}
