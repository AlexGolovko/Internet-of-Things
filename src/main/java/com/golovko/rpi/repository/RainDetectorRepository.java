package com.golovko.rpi.repository;

import com.golovko.rpi.model.RainDetector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RainDetectorRepository extends CrudRepository<RainDetector, Long> {
}
