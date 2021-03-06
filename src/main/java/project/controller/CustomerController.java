package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.entity.Customer;
import project.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @RequestMapping("/list")
    public String showList(Model model) {
        List<Customer> customers = customerService.getCustomerList();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/addCustomerForm")
    public String showAddCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    //Below method is used for both addition and updation of customers
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateCustomerForm")
    public String showUpdateCustomerForm(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/removeCustomer")
    public String removeCustomer(@RequestParam("id") int id) {
        Customer customer = customerService.getCustomer(id);
        customerService.removeCustomer(customer);
        return "redirect:/customer/list";
    }
}
