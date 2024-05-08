package com.portal_tech.portal_tech.controllers.controllersMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AutenticacaoController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login.html";
    }

    }


