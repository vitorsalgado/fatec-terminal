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

import br.com.fatecpg.core.entities.EnrolledDiscipline;
import br.com.fatecpg.core.entities.History;
import br.com.fatecpg.core.entities.Student;
import br.com.fatecpg.core.repositories.StudentRepository;
import com.microsoft.schemas.sharepoint.soap.GetListItems.Query;
import com.microsoft.schemas.sharepoint.soap.GetListItems.ViewFields;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Student getStudent(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        String listName = "Matrículas";
        ViewFields viewFields = SharepointListsHelper.createViewFieldsNode(defaultViewFieldsList);
        String strQuery = String.format("<Query><Where><Eq><FieldRef Name='Title'/><Value Type='Text'>%s</Value></Eq></Where></Query>", enrollment);
        Query query = SharepointListsHelper.createQueryNode(strQuery);

        NodeList list = SharepointListsHelper.executeQuery(listPath, listName, query, viewFields, 1);

        if(list.getLength() == 0)
            return null;
        
        Student student = new Student();

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();

            student.setId(SharepointFieldsReader.readIntegerField(attributes, "ows_ID"));
            student.setName(SharepointFieldsReader.readStringField(attributes, "ows_Nome_x0020_Completo"));
            student.setEnroll(SharepointFieldsReader.readStringField(attributes, "ows_Title"));
            student.setCourse(SharepointFieldsReader.readStringField(attributes, "ows_Curso"));
        }

        return student;
    }

    @Override
    public List<EnrolledDiscipline> getEnrolledDisciplines(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        String listName = "Disciplinas Matriculadas no Semestre";
        ViewFields viewFields = SharepointListsHelper.createViewFieldsNode("Disciplina", "Matr_x00ed_cula", "Situa_x00e7__x00e3_o", "Hist_x00f3_rico", "Turno", "Nota_x0020_1", "Nota_x0020_2", "Faltas_x0020__x0028_1_x00ba__x00", "Faltas_x0020__x0028_2_x00ba__x00", "Faltas", "M_x00e9_dia", "Conceito", "NP", "Author", "Created");
        String strQuery = String.format("<Query><Where><Eq><FieldRef Name='Matr_x00ed_cula'/><Value Type='Text'>%s</Value></Eq></Where><OrderBy><FieldRef Name='Disciplina' Ascending='True' /></OrderBy></Query>", enrollment);
        Query query = SharepointListsHelper.createQueryNode(strQuery);

        NodeList list = SharepointListsHelper.executeQuery(listPath, listName, query, viewFields);

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
    public List<History> getHistory(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
