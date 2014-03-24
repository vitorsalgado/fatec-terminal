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
package br.com.fatecpg.core.repositories;

import br.com.fatecpg.core.entities.EnrolledDiscipline;
import br.com.fatecpg.core.entities.History;
import br.com.fatecpg.core.entities.Student;

import java.util.List;

/**
 *
 * @author vitor.salgado
 */
public interface StudentRepository {

    Student getStudent(String enrollment);

    List<EnrolledDiscipline> getEnrolledDisciplines(String enrollment);

    List<History> getHistory(String enrollment);

}
