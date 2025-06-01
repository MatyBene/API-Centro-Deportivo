package com.utn.API_CentroDeportivo.service;


import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivityDetailsDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.mapper.InstructorMapper;
import com.utn.API_CentroDeportivo.model.mapper.SportActivityMapper;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService implements IInstructorService{

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISportActivityService sportActivityService;

    @Override
    public Optional<InstructorDetailsDTO> getInstructorById(long id) {
        Optional<User> instructor = userRepository.findById(id);
        if (instructor.isPresent()) {
            InstructorDetailsDTO instructorDetailsDTO = InstructorMapper.mapToInstructorDetailsDTO((Instructor) instructor.get());
            instructorDetailsDTO.setActivities(sportActivityService.getActivitiesByInstructor(id));
            return Optional.of(instructorDetailsDTO);
        }
        return Optional.empty();
    }

}
