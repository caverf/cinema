package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ReservationRepository;
import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.dao.ScreeningDao;
import com.zoraw.cinema.model.db.mongo.mapper.ReservationMapper;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.domain.Reservation;
import com.zoraw.cinema.model.domain.Room;
import com.zoraw.cinema.model.domain.Seat;
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
    public boolean create(Reservation reservation) {

        ScreeningDao screeningDao = getScreening(reservation.getScreeningId());
        Room room = getScreeningRoom(screeningDao);

        Set<Seat> seatsToReserve = reservation.getSeats();
        if (canReserve(room, seatsToReserve)) {
            updateSeats(screeningDao, seatsToReserve);
            try {
                screeningRepository.save(screeningDao);
            } catch (OptimisticLockingFailureException ex) {
                this.create(reservation);
            }
            reservationRepository.save(reservationMapper.toReservation(reservation));
            return true;
        }

        return false;
    }

    private void updateSeats(ScreeningDao screeningDao, Set<Seat> seatsToReserve) {
        screeningDao.getRoom().getSeats()
                .stream()
                .filter(seat -> seatsToReserve.stream().anyMatch(seatToReserve -> seat.getRow().equals(seatToReserve.getRow())
                        && seat.getNumber().equals(seatToReserve.getNumber()))) //todo: na contains
                .forEach(seat -> seat.setAvailable(false));
    }

    private Room getScreeningRoom(ScreeningDao screeningDao) {
        return screeningMapper.toScreeningDto(screeningDao).getRoom();
    }

    private ScreeningDao getScreening(String screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(BusinessException::new);
    }

    private boolean canReserve(Room room, Set<Seat> seatsToReserve) {
        return room.canReserveSeats(seatsToReserve);
    }
}
