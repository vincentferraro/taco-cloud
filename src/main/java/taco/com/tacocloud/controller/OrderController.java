package taco.com.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;

import lombok.extern.slf4j.Slf4j;
import taco.com.tacocloud.data.OrderRepository;
import taco.com.tacocloud.tacos.TacoOrder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;





@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()){
            return "orderForm"; // send orderForm.html view
        }
        orderRepo.save(order);
        
        sessionStatus.setComplete();

        return "redirect:/";
    }
    
}
