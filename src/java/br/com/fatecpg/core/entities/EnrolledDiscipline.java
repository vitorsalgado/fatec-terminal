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
public class EnrolledDiscipline {

    private String discipline;
    private double grade1;
    private double grade2;
    private int abscenses1;
    private int abscenses2;
    private double workGrade;
    private String concept;
    private double grade;

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public double getGrade1() {
        return grade1;
    }

    public void setGrade1(double grade1) {
        this.grade1 = grade1;
    }

    public double getGrade2() {
        return grade2;
    }

    public void setGrade2(double grade2) {
        this.grade2 = grade2;
    }

    public int getAbscenses1() {
        return abscenses1;
    }

    public void setAbscenses1(int abscenses1) {
        this.abscenses1 = abscenses1;
    }

    public int getAbscenses2() {
        return abscenses2;
    }

    public void setAbscenses2(int abscenses2) {
        this.abscenses2 = abscenses2;
    }

    public double getWorkGrade() {
        return workGrade;
    }

    public void setWorkGrade(double workGrade) {
        this.workGrade = workGrade;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        if (concept == null || concept.isEmpty() || concept.length() > 1) {
            this.concept = "-";
        } else {
            this.concept = concept;
        }
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
