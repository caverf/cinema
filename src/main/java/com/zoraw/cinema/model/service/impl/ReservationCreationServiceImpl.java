package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ReservationRepository;
import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.dao.Screening;
import com.zoraw.cinema.model.db.mongo.mapper.ReservationMapper;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.dto.ReservationDto;
import com.zoraw.cinema.model.dto.RoomDto;
import com.zoraw.cinema.model.dto.SeatDto;
import com.zoraw.cinema.model.exception.BusinessException;
import com.zoraw.cinema.model.service.ReservationCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ReservationCreationServiceImpl implements ReservationCreationService {

    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ScreeningMapper screeningMapper;

    @Override
    public boolean create(ReservationDto reservationDto) {

        Screening screening = getScreening(reservationDto.getScreeningId());
        RoomDto roomDto = getScreeningRoom(screening);

        Set<SeatDto> seatsToReserve = reservationDto.getSeats();
        if (canReserve(roomDto, seatsToReserve)) {
            updateSeats(screening, seatsToReserve);
            try {
                screeningRepository.save(screening);
            } catch (OptimisticLockingFailureException ex) {
                this.create(reservationDto);
            }
            reservationRepository.save(reservationMapper.toReservation(reservationDto));
            return true;
        }

        return false;
    }

    private void updateSeats(Screening screening, Set<SeatDto> seatsToReserve) {
        screening.getRoom().getSeats()
                .stream()
                .filter(seat -> seatsToReserve.stream().anyMatch(seatToReserve -> seat.getRow().equals(seatToReserve.getRow())
                        && seat.getNumber().equals(seatToReserve.getNumber())))
                .forEach(seat -> seat.setAvailable(false));
    }

    private RoomDto getScreeningRoom(Screening screening) {
        return screeningMapper.toScreeningDto(screening).getRoom();
    }

    private Screening getScreening(String screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(BusinessException::new);
    }

    private boolean canReserve(RoomDto room, Set<SeatDto> seatsToReserve) {
        return room.canReserveSeats(seatsToReserve);
    }
}
