package com.zoraw.cinema.model.db.mongo.mapper;

import com.zoraw.cinema.model.db.mongo.dao.SeatDao;
import com.zoraw.cinema.model.domain.Seat;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Set<Seat> toSeatDto(Set<SeatDao> screening);

}
