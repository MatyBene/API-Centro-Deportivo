package com.utn.API_CentroDeportivo.service;

import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;

import java.util.Optional;

public interface IInstructorService {
    Optional<InstructorDetailsDTO> getInstructorById(long id);
}
