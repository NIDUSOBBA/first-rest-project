package ru.alishev.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstRestApp.models.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
     Device getDeviceByName(String name);
}
