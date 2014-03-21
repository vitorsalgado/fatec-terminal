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
package br.com.fatecpg.core.entities;

/**
 *
 * @author vitor.salgado
 */
public class Student {

    private int id;
    private String enroll;
    private String name;
    private String course;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String Course) {
        this.course = Course;
    }
}
