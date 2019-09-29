package com.zoraw.cinema.model.db.mongo.mapper;

import com.zoraw.cinema.model.db.mongo.dao.Seat;
import com.zoraw.cinema.model.dto.SeatDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Set<SeatDto> toSeatDto(Set<Seat> screening);

}
