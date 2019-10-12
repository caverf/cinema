package com.zoraw.cinema.model.db.mongo;

import com.zoraw.cinema.model.db.mongo.dao.ScreeningDao;
import com.zoraw.cinema.model.domain.Screening;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface ScreeningRepository extends MongoRepository<ScreeningDao, String> {

    Set<Screening> findByTimeBetween(LocalDateTime from, LocalDateTime to);

}
