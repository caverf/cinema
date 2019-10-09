package com.zoraw.cinema.model.db.mongo.mapper;

import com.zoraw.cinema.model.db.mongo.dao.ReservationDao;
import com.zoraw.cinema.model.dto.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDao toReservation(Reservation reservation);
}
