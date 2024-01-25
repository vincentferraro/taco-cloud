package taco.com.tacocloud.controller;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;

import lombok.extern.slf4j.Slf4j;
import taco.com.tacocloud.configuration.OrderProps;
import taco.com.tacocloud.models.TacoOrder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import taco.com.tacocloud.models.User;
import taco.com.tacocloud.repositories.OrderRepository;
import taco.com.tacocloud.repositories.UserRepository;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix="taco.orders")
public class OrderController  {
    
    private OrderRepository orderRepo;
    private OrderProps props;
    private UserRepository userRepository;

    public OrderController(OrderRepository orderRepo, OrderProps props){
        this.orderRepo = orderRepo;
        this.props = props;
    }
    

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, Principal principal) {
        if(errors.hasErrors()){
            return "orderForm"; // send orderForm.html view
        }

        User user = userRepository.findByUsername(principal.getName());
        order.setUser(user);
        orderRepo.save(order);
        
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String orderForUser(@AuthenticationPrincipal User user, Model model){
            Pageable pageable = PageRequest.of(0,props.getPageSize());
            model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user,pageable));

            return "orderList";
    }
   
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
      orderRepo.deleteAll();
    }

    
    
}
