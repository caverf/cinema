package com.zoraw.cinema.model.db.mongo;

import com.zoraw.cinema.model.db.mongo.dao.ReservationDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<ReservationDao, String> {

}
