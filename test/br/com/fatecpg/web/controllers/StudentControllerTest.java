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
import br.com.fatecpg.repositories.sharepoint.SharepointStudentRepository;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
public class StudentControllerTest {
    
    public StudentControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setStudentRepository method, of class StudentController.
     */
    @Test
    public void testSetStudentRepository() {
//        System.out.println("setStudentRepository");
//        
//        StudentRepository studentRepository = new SharepointStudentRepository();
//        StudentController controller = new StudentController();
//        controller.setStudentRepository(studentRepository);
//        
//        List<EnrolledDiscipline> model = controller.enrollments("");
    }

    /**
     * Test of student method, of class StudentController.
     */
    @Test
    public void testStudent() {
//        System.out.println("student");
//        ModelMap model = null;
//        StudentController instance = new StudentController();
//        Student expResult = null;
//        Student result = instance.student(model);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of enrollments method, of class StudentController.
     */
    @Test
    public void testEnrollments() {
        System.out.println("enrollments");
        String enrollment = "";
        StudentController instance = new StudentController();
        ModelAndView expResult = null;
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
