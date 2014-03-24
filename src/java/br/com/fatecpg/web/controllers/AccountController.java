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
import br.com.fatecpg.web.viewmodels.LoginResponse;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private HttpSession session;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LoginResponse login(String enrollment) {

        if(!enrollment.toLowerCase().contains("f"))
            enrollment = "f" + enrollment;
        
        LoginResponse response = new LoginResponse();
        Student student = studentRepository.getStudent(enrollment);

        if (student == null) {
            response.setSuccess(false);
            response.setMessage(String.format("Nenhum aluno encontrado para o número de matrícula %s", enrollment));
        } else {
            response.setSuccess(true);
            response.setStudent(student);
            this.session.setAttribute("student", student);
        }
        
        return response;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {

        session.removeAttribute("student");
        return "index";
    }

}
