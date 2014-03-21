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
public class History {

    private int ciclo;
    private String discipline;
    private String period;
    private double Grade1;
    private double Grade2;
    private double np;
    private double average;
    private String concept;

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getGrade1() {
        return Grade1;
    }

    public void setGrade1(double Grade1) {
        this.Grade1 = Grade1;
    }

    public double getGrade2() {
        return Grade2;
    }

    public void setGrade2(double Grade2) {
        this.Grade2 = Grade2;
    }

    public double getNp() {
        return np;
    }

    public void setNp(double np) {
        this.np = np;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

}
