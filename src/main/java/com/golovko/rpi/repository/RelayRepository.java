package com.golovko.rpi.repository;

import com.golovko.rpi.model.RelayOneChannel;
import org.springframework.data.repository.CrudRepository;

public interface RelayRepository extends CrudRepository<RelayOneChannel, Long> {
}
