/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.web.controllers;

import br.com.fatecpg.core.entities.Student;
import br.com.fatecpg.core.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vitor.salgado
 */
@Controller
@Service
@RequestMapping("/student/")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody()
    public Student student(ModelMap model) {
        
        Student student = studentRepository.getStudent("F0713376");
        model.addAttribute("message", student.getName());

        return student;
    }

    @RequestMapping(value = "/enrollments", method = RequestMethod.GET)
    public String enrollments(ModelMap model) {

        Student student = studentRepository.getStudent("F0713376");
        model.addAttribute("message", student.getName());

        return "enrollments";
    }
}
