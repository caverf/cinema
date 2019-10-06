package com.zoraw.cinema.model.dto

import spock.lang.Specification
import spock.lang.Unroll

class RoomDtoTest extends Specification {

    @Unroll
    def "should check seat validation"() {
        given: "empty 4x4 room"
        def roomDto = RoomDto.builder()
                .seats(getSeats())
                .build()

        expect:
        roomDto.canReserveSeats(reservedSeats) == result

        where:
        reservedSeats         | result
        [] as Set             | false
        [seat('A', 1), seat('A', 3)] as Set | false
    }

    private SeatDto seat(String row, int number) {
        SeatDto.builder().row(row).number(number).build()
    }

    Set<SeatDto> getSeats() {
        def rows = ['A', 'B', 'C', 'D']

        def seats = new HashSet<SeatDto>()

        for (int i = 1; i < 5; i++) {
            for (def row : rows) {
                def seat = SeatDto.builder()
                        .row(row)
                        .number(i)
                        .isAvailable(true)
                        .build()
                if (i == 1 || i == 4) {
                    seat.setEdge(true)
                } else {
                    seat.setEdge(false)
                }
                seats.add(seat)
            }
        }
        seats
    }
}
