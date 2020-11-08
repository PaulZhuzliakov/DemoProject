package org.demo.project.resource;

import org.demo.project.model.Patient;
import org.demo.project.service.PatientService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    @Inject
    PatientService patientService;

    @GET
    @Path("/{id}")
    public Patient getPatientById(@PathParam("id") Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @GET
    public List<Patient> getListOfPatients() {
        return patientService.getListOfPatients();
    }

    @POST
    public Patient createPatient(Patient patient) {
        patientService.createPatient(patient);
        return patient;
    }


    @PUT
    public Patient updatePatient(Patient patient) {
        patientService.updatePatient(patient);
        return patient;
    }

    @DELETE
    @Path("/{id}")
    public Patient deletePatientById(@PathParam("id") Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient.getId() != 0) {
            patientService.deletePatientById(patientId);
        }
        return patient;
    }

}
