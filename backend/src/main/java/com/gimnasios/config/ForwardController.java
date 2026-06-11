package com.gimnasios.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController implements ErrorController {

    @RequestMapping("/error")
    public String forward() {
        return "forward:/index.html";
    }
}
