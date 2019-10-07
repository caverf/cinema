package com.zoraw.cinema.model.dto

import spock.lang.Specification
import spock.lang.Unroll

class RoomDtoTest extends Specification {

    @Unroll
    def "should check seat validation for standard empty room"() {
        given: "empty 4x5 room"
        def roomDto = RoomDto.builder()
                .seats(getSeats())
                .build()

        expect:
        roomDto.canReserveSeats(seatsToReserve) == canReserve

        where:
        seatsToReserve                      | canReserve
        [] as Set                           | false
        [seat('A', 1), seat('A', 3)] as Set | false
        [seat('A', 1), seat('A', 2)] as Set | true
        [seat('A', 1), seat('A', 4)] as Set | true
        [seat('A', 1), seat('B', 3)] as Set | true
    }

    @Unroll
    def "should check seat validation for room with corridor"() {
        given: "empty 4x5 room with corridor between 2 and 3 column "
        def roomDto = RoomDto.builder()
                .seats(getSeatsWithCorridor())
                .build()

        expect:
        roomDto.canReserveSeats(seatsToReserve) == canReserve

        where:
        seatsToReserve                      | canReserve
        [seat('A', 3), seat('A', 5)] as Set | false
        [seat('A', 3)] as Set               | true
        [seat('A', 3), seat('B', 5)] as Set | true
        [seat('A', 5)] as Set               | true
    }

    private SeatDto seat(String row, int number) {
        SeatDto.builder().row(row).number(number).build()
    }

    private Set<SeatDto> getSeats() {
        def rows = ['A', 'B', 'C', 'D']

        def seats = new HashSet<SeatDto>()

        for (int i = 1; i < 6; i++) {
            for (def row : rows) {
                def seat = SeatDto.builder()
                        .row(row)
                        .number(i)
                        .isAvailable(true)
                        .build()
                if (i == 1) {
                    seat.setEdge(Edge.LEFT)
                } else if (i == 5) {
                    seat.setEdge(Edge.RIGHT)
                } else {
                    seat.setEdge(Edge.NO)
                }
                seats.add(seat)
            }
        }
        seats
    }

    private Set<SeatDto> getSeatsWithCorridor() {
        def rows = ['A', 'B', 'C', 'D']

        def seats = new HashSet<SeatDto>()

        for (int i = 1; i < 6; i++) {
            for (def row : rows) {
                def seat = SeatDto.builder()
                        .row(row)
                        .number(i)
                        .isAvailable(true)
                        .build()
                switch (i) {
                    case 1:
                        seat.setEdge(Edge.LEFT)
                        break
                    case 2:
                        seat.setEdge(Edge.RIGHT)
                        break
                    case 3:
                        seat.setEdge(Edge.LEFT)
                        break
                    case 5:
                        seat.setEdge(Edge.RIGHT)
                        break
                    default:
                        seat.setEdge(Edge.NO)

                }
                seats.add(seat)
            }
        }
        seats
    }
}
