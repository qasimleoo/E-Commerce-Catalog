package com.practice.project.service;

import com.practice.project.modal.Product;
import com.practice.project.modal.User;
import com.practice.project.modal.UserCart;
import com.practice.project.repository.UserCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private UserCartRepo userCartRepository;

    public void addToCart(User user, Product product) {
        UserCart cartItem = new UserCart();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        user.getCartItems().add(cartItem);
        userCartRepository.save(cartItem);
    }
}