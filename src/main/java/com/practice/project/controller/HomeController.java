package com.practice.project.controller;

import com.practice.project.global.GlobalData;
import com.practice.project.service.CategoryService;
import com.practice.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HomeController {
    private ProductService productService;

    private CategoryService categoryService;

    public HomeController(CategoryService theCategoryService, ProductService theProductService) {
        categoryService = theCategoryService;
        productService = theProductService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "shop";
    }

    @GetMapping("/payNow")
    public String payNow(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "orderPlaced";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

}