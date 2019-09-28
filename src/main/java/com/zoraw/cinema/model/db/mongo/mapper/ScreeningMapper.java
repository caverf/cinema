package com.zoraw.cinema.model.db.mongo.mapper;

import com.zoraw.cinema.model.db.mongo.dao.Screening;
import com.zoraw.cinema.model.dto.ScreeningDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    ScreeningDto toScreeningDto(Screening screening);
}
