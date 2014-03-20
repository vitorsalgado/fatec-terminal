/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.repositories.sharepoint;

import br.com.fatecpg.core.entities.EnrolledDisciplines;
import br.com.fatecpg.core.entities.History;
import br.com.fatecpg.core.entities.Student;
import br.com.fatecpg.core.repositories.StudentRepository;
import br.com.fatecpg.sharepoint.SharepointListsHelper;
import com.microsoft.schemas.sharepoint.soap.GetListItems.Query;
import com.microsoft.schemas.sharepoint.soap.GetListItems.ViewFields;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author vitor.salgado
 */
@Service
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

        Student student = new Student();

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();

            student.setId(Integer.parseInt(attributes.getNamedItem("ows_ID").getNodeValue()));
            student.setName(attributes.getNamedItem("ows_Nome_x0020_Completo").getNodeValue());
            student.setEnroll(attributes.getNamedItem("ows_Title").getNodeValue());
            student.setCourse(attributes.getNamedItem("ows_Curso").getNodeValue());
        }

        return student;
    }

    @Override
    public List<EnrolledDisciplines> getEnrolledDisciplines(String enrollment) {

        if (enrollment == null || enrollment.isEmpty()) {
            throw new IllegalArgumentException("enrollment can't be null or empty.");
        }

        String listName = "Disciplinas Matriculadas no Semestre";
        ViewFields viewFields = SharepointListsHelper.createViewFieldsNode("Disciplina", "Matr_x00ed_cula", "Situa_x00e7__x00e3_o", "Hist_x00f3_rico", "Turno", "Nota_x0020_1", "Nota_x0020_2", "Faltas_x0020__x0028_1_x00ba__x00", "Faltas_x0020__x0028_2_x00ba__x00", "Faltas", "M_x00e9_dia", "Conceito", "NP", "Author", "Created");
        String strQuery = String.format("<Query><Where><Eq><FieldRef Name='Matr_x00ed_cula'/><Value Type='Text'>{0}</Value></Eq></Where></Query>", enrollment);
        Query query = SharepointListsHelper.createQueryNode(strQuery);

        NodeList list = SharepointListsHelper.executeQuery(listPath, listName, query, viewFields);

        List<EnrolledDisciplines> enrolledDisciplines = new ArrayList<>();

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();
            EnrolledDisciplines enrolledDiscipline = new EnrolledDisciplines();

            String[] disciplineArray = attributes.getNamedItem("ows_Conceito").getNodeValue().split(";#");

            enrolledDiscipline.setAbscenses1(Integer.parseInt(attributes.getNamedItem("ows_Faltas_x0020__x0028_1_x00ba__x00").getNodeValue()));
            enrolledDiscipline.setAbscenses2(Integer.parseInt(attributes.getNamedItem("ows_Faltas_x0020__x0028_2_x00ba__x00").getNodeValue()));
            enrolledDiscipline.setConcept(attributes.getNamedItem("ows_Conceito").getNodeValue());
            enrolledDiscipline.setDiscipline(disciplineArray[1]);
            enrolledDiscipline.setGrade1(Double.parseDouble(attributes.getNamedItem("ows_Nota_x0020_1").getNodeValue()));
            enrolledDiscipline.setGrade2(Double.parseDouble(attributes.getNamedItem("ows_Nota_x0020_2").getNodeValue()));
            enrolledDiscipline.setGrade(Double.parseDouble(attributes.getNamedItem("ows_M_x00e9_dia").getNodeValue()));
            enrolledDiscipline.setWorkGrade(Double.parseDouble(attributes.getNamedItem("ows_NP").getNodeValue()));

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
