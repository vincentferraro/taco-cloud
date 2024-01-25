package taco.com.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    public OrderAdminService adminService;

    public AdminController (OrderAdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    public void deleteAllOrder(){
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }

}
