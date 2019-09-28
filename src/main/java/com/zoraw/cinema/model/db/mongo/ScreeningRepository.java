package com.zoraw.cinema.model.db.mongo;

import com.zoraw.cinema.model.db.mongo.dao.Screening;
import com.zoraw.cinema.model.dto.ScreeningDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface ScreeningRepository extends MongoRepository<Screening, Long> {

    Set<ScreeningDto> findByTimeBetween(LocalDateTime from, LocalDateTime to);

}
