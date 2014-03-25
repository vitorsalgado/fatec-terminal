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
package br.com.fatecpg.repositories.sharepoint;

import br.com.fatecpg.core.entities.Discipline;
import br.com.fatecpg.core.repositories.SpContext;
import br.com.fatecpg.core.entities.EnrolledDiscipline;
import br.com.fatecpg.core.entities.History;
import br.com.fatecpg.core.entities.HistoryEntry;
import br.com.fatecpg.core.entities.Student;
import br.com.fatecpg.core.repositories.StudentRepository;
import com.microsoft.schemas.sharepoint.soap.GetListItems.Query;
import com.microsoft.schemas.sharepoint.soap.GetListItems.ViewFields;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author vitor.salgado
 */
@Repository
public class SharepointStudentRepository implements StudentRepository {

    private static final String[] defaultViewFieldsList = {"Nome_x0020_Completo", "Curso", "Turno", "Registro_x0020_Geral", "Org_x00e3_o_x0020_Emissor_x0020_", "Data_x0020_de_x0020_Emiss_x00e3_", "Data_x0020_de_x0020_Nascimento", "Naturalidade", "Endere_x00e7_o", "Bairro", "Munic_x00ed_pio_x0020_da_x0020_R", "Estado_x0020_de_x0020_Resid_x00e", "Telefone_x0020__x0028_res_x0029_", "Celular", "Email", "Turma_x0020_de_x0020_Ingresso", "Data_x0020_da_x0020_Matr_x00ed_c", "Observa_x00e7__x00f5_es_x0020_pa"};
    private static final String listPath = "http://www.fatecpg.com.br/fatec/_vti_bin/lists.asmx";

    private SpContext spContext;

    @Autowired
    public void setSpContext(SpContext spContext) {
        this.spContext = spContext;
    }

    @Override
    public Student getStudent(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        String listName = "Matrículas";
        ViewFields viewFields = spContext.createViewFieldsNode(defaultViewFieldsList);
        String strQuery = String.format("<Query><Where><Eq><FieldRef Name='Title'/><Value Type='Text'>%s</Value></Eq></Where></Query>", enrollment);
        Query query = spContext.createQueryNode(strQuery);

        NodeList list = spContext.executeQuery(listPath, listName, query, viewFields, 1);

        if (list.getLength() == 0) {
            return null;
        }

        NamedNodeMap attributes = list.item(0).getAttributes();
        Student student = new Student();

        student.setId(SharepointFieldsReader.readIntegerField(attributes, "ows_ID"));
        student.setName(SharepointFieldsReader.readStringField(attributes, "ows_Nome_x0020_Completo"));
        student.setEnroll(SharepointFieldsReader.readStringField(attributes, "ows_Title"));
        student.setCourse(SharepointFieldsReader.readStringField(attributes, "ows_Curso"));

        return student;
    }

    @Override
    public List<EnrolledDiscipline> getEnrolledDisciplines(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        String listName = "Disciplinas Matriculadas no Semestre";
        ViewFields viewFields = spContext.createViewFieldsNode("Disciplina", "Matr_x00ed_cula", "Situa_x00e7__x00e3_o", "Hist_x00f3_rico", "Turno", "Nota_x0020_1", "Nota_x0020_2", "Faltas_x0020__x0028_1_x00ba__x00", "Faltas_x0020__x0028_2_x00ba__x00", "Faltas", "M_x00e9_dia", "Conceito", "NP", "Author", "Created");
        String strQuery = String.format("<Query><Where><Eq><FieldRef Name='Matr_x00ed_cula'/><Value Type='Text'>%s</Value></Eq></Where><OrderBy><FieldRef Name='Disciplina' Ascending='True' /></OrderBy></Query>", enrollment);
        Query query = spContext.createQueryNode(strQuery);

        NodeList list = spContext.executeQuery(listPath, listName, query, viewFields);

        List<EnrolledDiscipline> enrolledDisciplines = new ArrayList<>();

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();
            EnrolledDiscipline enrolledDiscipline = new EnrolledDiscipline();

            String discipline = SharepointFieldsReader.readStringField(attributes, "ows_Disciplina");
            String[] disciplineArray = discipline.split(";#");

            enrolledDiscipline.setAbscenses1(SharepointFieldsReader.readIntegerField(attributes, "ows_Faltas_x0020__x0028_1_x00ba__x00"));
            enrolledDiscipline.setAbscenses2(SharepointFieldsReader.readIntegerField(attributes, "ows_Faltas_x0020__x0028_2_x00ba__x00"));
            enrolledDiscipline.setConcept(SharepointFieldsReader.readStringField(attributes, "ows_Conceito"));
            enrolledDiscipline.setDiscipline(disciplineArray[1]);
            enrolledDiscipline.setGrade1(SharepointFieldsReader.readDoubleField(attributes, "ows_Nota_x0020_1"));
            enrolledDiscipline.setGrade2(SharepointFieldsReader.readDoubleField(attributes, "ows_Nota_x0020_2"));
            enrolledDiscipline.setGrade(SharepointFieldsReader.readDoubleField(attributes, "ows_M_x00e9_dia"));
            enrolledDiscipline.setWorkGrade(SharepointFieldsReader.readDoubleField(attributes, "ows_NP"));

            enrolledDisciplines.add(enrolledDiscipline);
        }

        return enrolledDisciplines;
    }

    @Override
    public History getHistory(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        String listName = "Histórico Escolar";
        ViewFields viewFields = spContext.createViewFieldsNode("Semestre", "Disciplina", "Turno", "Conceito");
        String strQuery = String.format("<Query><OrderBy><FieldRef Name='Semestre' Ascending='True'/><FieldRef Name='Disciplina' Ascending='True'/></OrderBy><Where>"
                + "<And>"
                + "<Eq><FieldRef Name='Matr_x00ed_cula'/><Value Type='Text'>%s</Value></Eq>"
                + "<Or>"
                + "<Eq><FieldRef Name='Conceito'/><Value Type='Text'>D</Value></Eq>"
                + "<Eq><FieldRef Name='Conceito'/><Value Type='Text'>A</Value></Eq>"
                + "</Or>"
                + "</And>"
                + "</Where></Query>", enrollment);
        Query query = spContext.createQueryNode(strQuery);

        NodeList list = spContext.executeQuery(listPath, listName, query, viewFields);
        List<HistoryEntry> historyEntries = new ArrayList<>();

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();
            HistoryEntry historyEntry = new HistoryEntry();

            String semester = SharepointFieldsReader.readStringField(attributes, "ows_Semestre");
            String[] semesterArray = semester.split(";#");

            String disciplineFieldValue = SharepointFieldsReader.readStringField(attributes, "ows_Disciplina");
            String[] disciplineArray = disciplineFieldValue.split(";#");

            Discipline discipline = new Discipline();
            discipline.setId(Integer.parseInt(disciplineArray[0]));
            historyEntry.setDiscipline(discipline);

            historyEntry.setAverage(SharepointFieldsReader.readDoubleField(attributes, "ows_M_x00e9_dia"));
            historyEntry.setConcept(SharepointFieldsReader.readStringField(attributes, "ows_Conceito"));
            historyEntry.setPeriod(SharepointFieldsReader.readStringField(attributes, "ows_Turno"));
            historyEntry.setSemester(semesterArray[1]);

            historyEntries.add(historyEntry);
        }

        Collections.sort(historyEntries, new Comparator<HistoryEntry>() {

            @Override
            public int compare(HistoryEntry o1, HistoryEntry o2) {
                return o1.getDiscipline().getId() - o2.getDiscipline().getId();
            }

        });

        StringBuilder disciplineQueryBuilder = new StringBuilder();
        disciplineQueryBuilder.append("<Query><Where>");

        for (int i = 0; i < historyEntries.size(); i++) {
            HistoryEntry objHistory = historyEntries.get(i);

            if (i == 0) {
                for (int x = 0; x < historyEntries.size() - 1; x++) {
                    disciplineQueryBuilder.append("<Or>");
                }
                disciplineQueryBuilder.append(String.format("<Eq><FieldRef Name='ID'/><Value Type='Number'>%s</Value></Eq>", objHistory.getDiscipline().getId()));
            } else {
                disciplineQueryBuilder.append(String.format("<Eq><FieldRef Name='ID'/><Value Type='Number'>%s</Value></Eq>", objHistory.getDiscipline().getId()));
                disciplineQueryBuilder.append("</Or>");
            }
        }
        
        disciplineQueryBuilder.append("</Where></Query>");

        String disciplinesListName = "Disciplinas";
        Query disciplineQuery = spContext.createQueryNode(disciplineQueryBuilder.toString());
        ViewFields disciplineViewFields = spContext.createViewFieldsNode(
                "ID", "Title", "C_x00ed_clo", "Curso", "Carga_x0020_Hor_x00e1_ria_x0020_", "Carga_x0020_Hor_x00e1_ria_x0020_0", "Cr_x00e9_ditos");

        NodeList listDisciplines = spContext.executeQuery(listPath, disciplinesListName, disciplineQuery, disciplineViewFields);

        for (HistoryEntry objHistoryEntry : historyEntries) {

            for (int i = 0; i < listDisciplines.getLength(); i++) {
                NamedNodeMap attributes = listDisciplines.item(i).getAttributes();

                if (objHistoryEntry.getDiscipline().getId() != SharepointFieldsReader.readIntegerField(attributes, "ows_ID")) {
                    continue;
                }

                objHistoryEntry.getDiscipline().setCiclo(Integer.parseInt(
                        SharepointFieldsReader.readStringField(attributes, "ows_C_x00ed_clo").substring(0, 1)));
                objHistoryEntry.getDiscipline().setName(SharepointFieldsReader.readStringField(attributes, "ows_Title"));
                objHistoryEntry.getDiscipline().setWorkload(SharepointFieldsReader.readIntegerField(attributes, "ows_Carga_x0020_Hor_x00e1_ria_x0020_"));
                objHistoryEntry.getDiscipline().setCredits(SharepointFieldsReader.readIntegerField(attributes, "ows_Cr_x00e9_ditos"));
                objHistoryEntry.getDiscipline().setTotalWorkload(SharepointFieldsReader.readIntegerField(attributes, "ows_Carga_x0020_Hor_x00e1_ria_x0020_0"));
            }

        }

        History history = new History();
        history.setEntries(historyEntries);
        history.setEfficiencyPercent(getEfficiencyPercent(enrollment));

        return history;
    }

    private double getEfficiencyPercent(String enrollment) {

        List<HistoryEntry> historyEntries = getHistoryEntriesForEfficiencyCalculation(enrollment);
        historyEntries = getDisciplinesForEfficiencyCalculation(historyEntries);

        double grade = 0;
        double workload = 0;
        double total = 0;
        double efficiencyPercent = 0;

        for (int i = 0; i < historyEntries.size(); i++) {
            HistoryEntry historyEntry = historyEntries.get(i);

            grade += historyEntry.getAverage() * historyEntry.getDiscipline().getTotalWorkload();
            workload += historyEntry.getDiscipline().getTotalWorkload();
        }

        efficiencyPercent = ((grade * 10) / workload);

        return efficiencyPercent;
    }

    private List<HistoryEntry> getHistoryEntriesForEfficiencyCalculation(String enrollment) {
        String listName = "Histórico Escolar";
        ViewFields viewFields = spContext.createViewFieldsNode("Disciplina", "M_x00e9_dia");
        String strQuery = String.format("<Query><OrderBy><FieldRef Name='Disciplina' Ascending='True'/></OrderBy><Where>"
                + "<And>"
                + "<Eq><FieldRef Name='Matr_x00ed_cula'/><Value Type='Text'>%s</Value></Eq>"
                + "<Or>"
                + "<Eq><FieldRef Name='Conceito'/><Value Type='Text'>A</Value></Eq>"
                + "<Eq><FieldRef Name='Conceito'/><Value Type='Text'>F</Value></Eq>"
                + "</Or>"
                + "</And>"
                + "</Where></Query>", enrollment);
        Query query = spContext.createQueryNode(strQuery);

        NodeList list = spContext.executeQuery(listPath, listName, query, viewFields);
        List<HistoryEntry> historyEntries = new ArrayList<>();

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();
            HistoryEntry historyEntry = new HistoryEntry();

            String disciplineFieldValue = SharepointFieldsReader.readStringField(attributes, "ows_Disciplina");
            String[] disciplineArray = disciplineFieldValue.split(";#");

            Discipline discipline = new Discipline();
            discipline.setId(Integer.parseInt(disciplineArray[0]));
            historyEntry.setDiscipline(discipline);

            historyEntry.setAverage(SharepointFieldsReader.readDoubleField(attributes, "ows_M_x00e9_dia"));
            
            historyEntries.add(historyEntry);
        }

        Collections.sort(historyEntries, new Comparator<HistoryEntry>() {

            @Override
            public int compare(HistoryEntry o1, HistoryEntry o2) {
                return o1.getDiscipline().getId() - o2.getDiscipline().getId();
            }

        });

        return historyEntries;
    }

    private List<HistoryEntry> getDisciplinesForEfficiencyCalculation(List<HistoryEntry> historyEntries) {

        StringBuilder disciplineQueryBuilder = new StringBuilder();
        disciplineQueryBuilder.append("<Query><Where>");

        for (int i = 0; i < historyEntries.size(); i++) {
            HistoryEntry objHistory = historyEntries.get(i);

            if (i == 0) {
                for (int x = 0; x < historyEntries.size() - 1; x++) {
                    disciplineQueryBuilder.append("<Or>");
                }
                disciplineQueryBuilder.append(String.format("<Eq><FieldRef Name='ID'/><Value Type='Number'>%s</Value></Eq>", objHistory.getDiscipline().getId()));
            } else {
                disciplineQueryBuilder.append(String.format("<Eq><FieldRef Name='ID'/><Value Type='Number'>%s</Value></Eq>", objHistory.getDiscipline().getId()));
                disciplineQueryBuilder.append("</Or>");
            }
        }
        
        disciplineQueryBuilder.append("</Where></Query>");

        String disciplinesListName = "Disciplinas";
        Query disciplineQuery = spContext.createQueryNode(disciplineQueryBuilder.toString());
        ViewFields disciplineViewFields = spContext.createViewFieldsNode(
                "ID", "Title", "Carga_x0020_Hor_x00e1_ria_x0020_", "Carga_x0020_Hor_x00e1_ria_x0020_0");

        NodeList listDisciplines = spContext.executeQuery(listPath, disciplinesListName, disciplineQuery, disciplineViewFields);

        for (HistoryEntry objHistoryEntry : historyEntries) {

            for (int i = 0; i < listDisciplines.getLength(); i++) {
                NamedNodeMap attributes = listDisciplines.item(i).getAttributes();

                if (objHistoryEntry.getDiscipline().getId() != SharepointFieldsReader.readIntegerField(attributes, "ows_ID")) {
                    continue;
                }

                objHistoryEntry.getDiscipline().setName(SharepointFieldsReader.readStringField(attributes, "ows_Title"));
                objHistoryEntry.getDiscipline().setWorkload(SharepointFieldsReader.readIntegerField(attributes, "ows_Carga_x0020_Hor_x00e1_ria_x0020_"));
                objHistoryEntry.getDiscipline().setTotalWorkload(SharepointFieldsReader.readIntegerField(attributes, "ows_Carga_x0020_Hor_x00e1_ria_x0020_0"));
            }

        }

        return historyEntries;
    }
}
