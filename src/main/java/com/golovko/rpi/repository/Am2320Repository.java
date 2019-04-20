package com.golovko.rpi.repository;

import com.golovko.rpi.model.AM2320TemperatureAndHumidity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Am2320Repository extends CrudRepository<AM2320TemperatureAndHumidity, Long> {
}
