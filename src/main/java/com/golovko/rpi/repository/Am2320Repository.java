package com.golovko.rpi.repository;

import com.golovko.rpi.model.DetectorTemperatureAndHumidity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Am2320Repository extends CrudRepository<DetectorTemperatureAndHumidity, Long> {
}
