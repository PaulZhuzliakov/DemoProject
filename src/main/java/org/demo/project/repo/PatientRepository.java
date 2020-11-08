package org.demo.project.repo;

import org.demo.project.model.Patient;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class PatientRepository {
    final String url = "jdbc:postgresql://localhost:5432/demo";
    final String user = "postgres";
    final String pass = "postgres";


    public Patient getPatientById(long id) {
        Patient patient = new Patient();
        String sql = "SELECT * FROM patients WHERE id = " + id;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                patient.setId(resultSet.getLong(1));
                patient.setLastName(resultSet.getString(2));
                patient.setFirstName(resultSet.getString(3));
                patient.setMiddleName(resultSet.getString(4));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return patient;
    }

    public List<Patient> getListOfPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getLong(1));
                patient.setLastName(resultSet.getString(2));
                patient.setFirstName(resultSet.getString(3));
                patient.setMiddleName(resultSet.getString(4));
                patients.add(patient);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return patients;
    }

    ;

    public void createPatient(Patient patient) {
        String sql = "INSERT INTO patients VALUES (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedSt = connection.prepareStatement(sql);) {
            preparedSt.setLong(1, patient.getId());
            preparedSt.setString(2, patient.getLastName());
            preparedSt.setString(3, patient.getFirstName());
            preparedSt.setString(4, patient.getMiddleName());
            preparedSt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE patients SET lastname=?, firstname=?, middlename=? WHERE id=?";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedSt = connection.prepareStatement(sql);) {
            preparedSt.setString(1, patient.getLastName());
            preparedSt.setString(2, patient.getFirstName());
            preparedSt.setString(3, patient.getMiddleName());
            preparedSt.setLong(4, patient.getId());
            preparedSt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void deletePatientById(Long patientId) {
        String sql = "DELETE FROM patients WHERE id=?";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedSt = connection.prepareStatement(sql);) {
            preparedSt.setLong(1, patientId);
            preparedSt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//    Connection connection = null;
//
//    public PatientRepository() {
//        final String url = "jdbc:postgresql://localhost:5432/demo";
//        final String user = "postgres";
//        final String pass = "postgres";
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(url, user, pass);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }