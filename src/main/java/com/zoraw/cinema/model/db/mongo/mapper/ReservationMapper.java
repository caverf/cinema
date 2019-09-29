package com.zoraw.cinema.model.db.mongo.mapper;

import com.zoraw.cinema.model.db.mongo.dao.Reservation;
import com.zoraw.cinema.model.dto.ReservationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toReservation(ReservationDto reservationDto);
}
