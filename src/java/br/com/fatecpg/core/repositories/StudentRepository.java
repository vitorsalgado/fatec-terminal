/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.core.repositories;

import br.com.fatecpg.core.entities.EnrolledDisciplines;
import br.com.fatecpg.core.entities.History;
import br.com.fatecpg.core.entities.Student;

import java.util.List;

/**
 *
 * @author vitor.salgado
 */
public interface StudentRepository {

    Student getStudent(String enrollment);

    List<EnrolledDisciplines> getEnrolledDisciplines(String enrollment);

    List<History> getHistory(String enrollment);

}
