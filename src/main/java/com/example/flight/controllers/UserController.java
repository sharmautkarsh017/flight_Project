package com.example.flight.controllers;
import com.example.flight.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.example.flight.repos.UserRepository;
import com.example.flight.entities.User;
import org.slf4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/showReg")
    public String ShowRegistrationPage()
    {
        return "login/registerUser";
    }
    @RequestMapping("/showLogin")
    public String ShowLoginPage()
    {
        return "login/login";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";

    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        ModelMap modelMap) {
        boolean loginResponse=securityService.login(email,password);
        LOGGER.error("ERROR");
        LOGGER.warn("WARN");
        LOGGER.info("INFO");
        LOGGER.debug("DEBUG");
        LOGGER.trace("TRACE");
        User user=userRepository.findByEmail(email);

        if(loginResponse)
        {
            return "findFlights";
        }
        else {
            modelMap.addAttribute("msg","Invalid details");
        }
        return "login/login";

    }

}
