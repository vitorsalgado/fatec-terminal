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

import br.com.fatecpg.core.entities.EnrolledDiscipline;
import br.com.fatecpg.core.entities.Student;
import br.com.fatecpg.core.repositories.StudentRepository;
import br.com.fatecpg.web.viewmodels.EnrolledDisciplinesModel;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private HttpSession session;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody()
    public Student student() {

        Student student = studentRepository.getStudent("F0713376");

        return student;
    }

    @RequestMapping(value = "/enrollments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EnrolledDisciplinesModel enrollments() {

        Student student = (Student)session.getAttribute("student");
        String enrollment = student.getEnroll();
                
        List<EnrolledDiscipline> enrolledDisciplines = studentRepository.getEnrolledDisciplines("F0713376");
        EnrolledDisciplinesModel model = new EnrolledDisciplinesModel();
        
        if(enrolledDisciplines == null || enrolledDisciplines.size() <= 0){
            model.setSuccess(false);
            model.setMessage("Não foi encontrada nenhuma disciplina matrícula.");
            
            return model;
        }
        
        model.setEnrolledDisciplines(enrolledDisciplines);
        model.setSuccess(true);
                
        return model;
    }
}
