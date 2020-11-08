package org.demo.project.service;

import org.demo.project.model.Patient;
import org.demo.project.repo.PatientRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class PatientService {

    @Inject
    PatientRepository patientRepository;

    public Patient getPatientById(long patientId) {
        try {
            return patientRepository.getPatientById(patientId);
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    public List<Patient> getListOfPatients() {
        try {
            return patientRepository.getListOfPatients();
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    };

    public void createPatient(Patient patient) {
        try {
            patientRepository.createPatient(patient);
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    public void updatePatient(Patient patient) {
        try {
            patientRepository.updatePatient(patient);
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    public void deletePatientById(Long patientId) {
        try {
            patientRepository.deletePatientById(patientId);
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

}
