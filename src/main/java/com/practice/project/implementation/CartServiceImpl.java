package com.practice.project.implementation;

import com.practice.project.entity.Product;
import com.practice.project.entity.User;
import com.practice.project.entity.UserCart;
import com.practice.project.dao.UserCartRepo;
import com.practice.project.service.CartService;
import com.practice.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private UserCartRepo userCartRepository;
    private ProductService productService;

    @Autowired
    public CartServiceImpl(UserCartRepo theUserCartRepository, ProductService theProductService) {
        userCartRepository = theUserCartRepository;
        productService = theProductService;
    }
    public UserCart addToCart(User user, Product product) {
        UserCart cartItem = new UserCart();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        user.getCartItems().add(cartItem);
        userCartRepository.save(cartItem);
        return cartItem;
    }
    public void removeById(Long id){
        userCartRepository.deleteById(id);
    }
    public Long getUserCartIdByProductId(Long productId, User user) {
        UserCart userCart = userCartRepository.findByProductAndUser(productService.getProductById(productId).get(), user);
        return userCart != null ? userCart.getId() : null;
    }
    public List<UserCart> getUserCartItems(User user) {
        return userCartRepository.findByUser(user);
    }
}