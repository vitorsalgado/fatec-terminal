/* 
 * Copyright (C) 2014 Vitor Hugo Salgado <vsalgadopb@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

        return student;
    }

    @RequestMapping(value = "/enrollments", method = RequestMethod.GET)
    public String enrollments(ModelMap model) {

        Student student = studentRepository.getStudent("F0713376");
        model.addAttribute("message", student.getName());

        return "enrollments";
    }
}
