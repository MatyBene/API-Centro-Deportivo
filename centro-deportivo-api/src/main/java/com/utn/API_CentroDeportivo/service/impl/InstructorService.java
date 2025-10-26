package com.utn.API_CentroDeportivo.service.impl;


import com.utn.API_CentroDeportivo.model.dto.response.InstructorDetailsDTO;
import com.utn.API_CentroDeportivo.model.dto.response.InstructorSummaryDTO;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.exception.InstructorNotFoundException;
import com.utn.API_CentroDeportivo.model.mapper.InstructorMapper;
import com.utn.API_CentroDeportivo.model.mapper.SportActivityMapper;
import com.utn.API_CentroDeportivo.model.repository.IUserRepository;
import com.utn.API_CentroDeportivo.service.ICredentialService;
import com.utn.API_CentroDeportivo.service.IInstructorService;
import com.utn.API_CentroDeportivo.service.ISportActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorService implements IInstructorService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISportActivityService sportActivityService;
    @Autowired
    private ICredentialService credentialService;

    @Override
    public Optional<InstructorSummaryDTO> getInstructorSummaryById(long id) {
        Optional<User> instructor = userRepository.findById(id);

        if (instructor.isPresent() && instructor.get() instanceof Instructor) {
            Instructor inst = (Instructor) instructor.get();
            InstructorSummaryDTO instructorSummaryDTO = InstructorMapper.mapToInstructorSummaryDTO(inst);
            int activityCount = inst.getActivities() != null ? inst.getActivities().size() : 0;
            instructorSummaryDTO.setActivityCount(activityCount);

            return Optional.of(instructorSummaryDTO);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Instructor> findByUsername(String username) {
        return Optional.ofNullable((Instructor) userRepository.findById(credentialService.getUserByUsername(username).getId())
                .orElseThrow(() -> new InstructorNotFoundException("Instructor no encontrado")));
    }

    @Override
    public Optional<InstructorDetailsDTO> getInstructorDetailsById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent() && user.get() instanceof Instructor) {
            Instructor instructor = (Instructor) user.get();
            List<SportActivitySummaryDTO> activitySummaries = instructor.getActivities() != null
                    ? instructor.getActivities().stream()
                    .map(SportActivityMapper::mapToSportActivitySummaryDTO)
                    .collect(Collectors.toList())
                    : new ArrayList<>();
            InstructorDetailsDTO instructorDetailsDTO = InstructorMapper.mapToInstructorDetailsDTO(instructor, activitySummaries);
            return Optional.of(instructorDetailsDTO);
        }
        return Optional.empty();
    }
}
