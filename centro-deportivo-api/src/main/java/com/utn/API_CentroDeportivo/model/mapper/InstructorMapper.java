package com.utn.API_CentroDeportivo.model.mapper;

import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;


public class InstructorMapper {

    public static InstructorDetailsDTO mapToInstructorDetailsDTO(Instructor instructor){
        InstructorDetailsDTO instructorDetailsDTO = new InstructorDetailsDTO();
        instructorDetailsDTO.setName(instructor.getName());
        instructorDetailsDTO.setLastname(instructor.getLastname());
        instructorDetailsDTO.setDni(instructor.getDni());
        instructorDetailsDTO.setBirthdate(instructor.getBirthdate());
        instructorDetailsDTO.setPhone(instructor.getPhone());
        instructorDetailsDTO.setEmail(instructor.getEmail());
        instructorDetailsDTO.setSpecialty(instructor.getSpecialty());
        
        return instructorDetailsDTO;
    }
}
