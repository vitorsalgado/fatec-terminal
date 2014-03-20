/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.web.controllers;

import br.com.fatecpg.core.entities.Student;
import br.com.fatecpg.core.repositories.StudentRepository;
import br.com.fatecpg.web.viewmodels.LoginResponse;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Vitor
 */
@Controller
@Service
@RequestMapping("/account/")
public class AccountController {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginResponse login(String enrollment, HttpSession session) {

        LoginResponse response = new LoginResponse();
        Student student = null;//studentRepository.getStudent(enrollment);

        if (student == null) {
            response.setSuccess(false);
            response.setMessage(String.format("Nenhum aluno encontrado para o número de matrícula %s", enrollment));
        } else {
            response.setSuccess(true);
            session.setAttribute("enrollment", enrollment);
        }

        return response;

    }

    @RequestMapping(value = "/account/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("enrollment");
        return "index";
    }

}
