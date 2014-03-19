/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.core.entities;

/**
 *
 * @author vitor.salgado
 */
public class EnrolledDisciplines {

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
        this.concept = concept;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
