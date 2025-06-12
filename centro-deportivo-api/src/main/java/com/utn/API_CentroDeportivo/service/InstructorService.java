package com.utn.API_CentroDeportivo.service;


import com.utn.API_CentroDeportivo.model.dto.response.InstructorSummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.Instructor;
import com.utn.API_CentroDeportivo.model.entity.User;
import com.utn.API_CentroDeportivo.model.exception.InstructorNotFoundException;
import com.utn.API_CentroDeportivo.model.exception.MemberNotFoundException;
import com.utn.API_CentroDeportivo.model.mapper.InstructorMapper;
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

}
