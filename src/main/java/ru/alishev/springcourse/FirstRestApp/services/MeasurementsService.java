package ru.alishev.springcourse.FirstRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.dto.VisualMeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.exceptions.MeasurementsEmptyException;
import ru.alishev.springcourse.FirstRestApp.exceptions.MeasurementsOutOfBoundsException;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final DeviceService deviceService;
    private final ModelMapper modelMapper;


    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, DeviceService deviceService, ModelMapper modelMapper) {
        this.measurementsRepository = measurementsRepository;
        this.deviceService = deviceService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void save(Measurements measurements) {
        measurements.setCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurements);
    }

    @Transactional
    public void save(MeasurementsDto measurementsDto) {
        if (measurementsDto.getValue() == null || measurementsDto.getDivece() == null || measurementsDto.getRaining() == null) {
            throw new MeasurementsEmptyException();
        }
        if (measurementsDto.getValue() > 100 || measurementsDto.getValue() < -100) {
            throw new MeasurementsOutOfBoundsException();
        }
        Measurements map = modelMapper.map(measurementsDto, Measurements.class);
        map.setDevice(deviceService.getSensorByName(measurementsDto.getDivece().getName()));
        map.setCreatedAt(LocalDateTime.now());

        measurementsRepository.save(map);
    }

    public List<Measurements> findAll() {
        return measurementsRepository.findAll();
    }

    public List<Measurements> findByMeasurementsIsRaining() {
        return measurementsRepository.findByRainingTrue();
    }

    public VisualMeasurementsDto creatVisualDto(Measurements measurements) {
        VisualMeasurementsDto visualDto = new VisualMeasurementsDto();
        visualDto.setRaining(measurements.getRaining());
        visualDto.setValue(measurements.getValue());
        visualDto.setSensorName(measurements.getDevice().getName());
        return visualDto;
    }


}
