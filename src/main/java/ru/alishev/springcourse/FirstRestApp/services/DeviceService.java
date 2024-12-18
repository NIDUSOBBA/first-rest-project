package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.models.Device;
import ru.alishev.springcourse.FirstRestApp.repositories.DeviceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;

    }

    public Device getSensorByName(String name) {
        return deviceRepository.getDeviceByName(name);
    }

    @Transactional
    public void save(Device device) {
        device.setCreatedAt(LocalDateTime.now());
        device.setCreatedWhi("WeatherSensor");
        deviceRepository.save(device);
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }


}
