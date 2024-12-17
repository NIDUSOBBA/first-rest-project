package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.dto.VisualMeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;


    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional
    public void save(Measurements measurements) {
        measurements.setCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurements);
    }

    public List<Measurements> findAll() {
        return measurementsRepository.findAll();
    }

    public List<Measurements> findByMeasurementsIsRaining() {
        return measurementsRepository.findByRainingTrue();
    }

    public VisualMeasurementsDto creatVisualDto(Measurements measurements) {
        VisualMeasurementsDto visualDto = new VisualMeasurementsDto();
        visualDto.setRaining(measurements.isRaining());
        visualDto.setValue(measurements.getValue());
        visualDto.setSensorName(measurements.getSensor().getName());
        return visualDto;
    }


}
