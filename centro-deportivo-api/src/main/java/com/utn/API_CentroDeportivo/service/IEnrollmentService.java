package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.request.EnrollmentRequestDTO;

public interface IEnrollmentService {
    void enrollMemberToActivity(EnrollmentRequestDTO enrollmentDTO);
}
