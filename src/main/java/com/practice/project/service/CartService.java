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

    public UserCart addToCart(User user, Product product) {
        UserCart cartItem = new UserCart();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        user.getCartItems().add(cartItem);
        userCartRepository.save(cartItem);
        System.out.println(cartItem.getId());
        return cartItem;
    }

    public void removeById(Long id){
        userCartRepository.deleteById(id);

    }
}