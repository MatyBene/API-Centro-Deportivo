package com.utn.API_CentroDeportivo.service;
import com.utn.API_CentroDeportivo.model.dto.response.SportActivitySummaryDTO;
import com.utn.API_CentroDeportivo.model.entity.SportActivity;
import com.utn.API_CentroDeportivo.model.mapper.SportActivityMapper;
import com.utn.API_CentroDeportivo.model.repository.ISportActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SportActivityService {
    @Autowired
    private ISportActivityRepository sportActivityRepository;

    public List<SportActivitySummaryDTO> getActivities() {
        return sportActivityRepository.findAll().stream().map(SportActivityMapper::mapToSportActivitySummaryDTO).toList();
    }

    public Optional<SportActivity> getActivityById(Long id) {
        return sportActivityRepository.findById(id);
    }
}
