package com.springcookieshop.controller;

import com.springcookieshop.model.Cart;
import com.springcookieshop.model.Product;
import com.springcookieshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop(){
        ModelAndView mav = new ModelAndView("shop");
        mav.addObject("products", productService.findAll());
        return mav;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()){
            return "error_404";
        }
        if(action.equals("show")){
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        if(action.equals("decrease")){
            cart.removeProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }
}
