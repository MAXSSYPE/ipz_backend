package com.myproject.controllers;

import com.myproject.Ajax;
import com.myproject.DAO.CompanyDAO;
import com.myproject.DAO.UserDAO;
import com.myproject.Units.Company;
import com.myproject.Units.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(maxAge = 3600)
@RestController
public class Controller {

    @GetMapping(path = "/search")
    public Map<String, Object> search(@RequestParam(value = "name") String name) {
        CompanyDAO companyDAO = new CompanyDAO();
        List<Company> companies = companyDAO.getAll();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getName().length() < name.length() || !companies.get(i).getName().substring(0, name.length()).equals(name)) {
                companies.remove(i);
                i--;
            }
        }
        if (companies == null || companies.isEmpty()) {
            return Ajax.emptyResponse();
        }
        return Ajax.successResponse(name);
    }

    @GetMapping(path = "/login")
    public Map<String, Object> login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return Ajax.emptyResponse();
            }
        }
        return Ajax.errorResponse("User not found");
    }

    @GetMapping(path = "/registration")
    public Map<String, Object> registration(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return Ajax.errorResponse("User is found");
            }
        }
        userDAO.create(new User(new Random().nextInt(), name, password));
        return Ajax.emptyResponse();
    }
}
