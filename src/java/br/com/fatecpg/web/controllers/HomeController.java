/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vitor
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
