package com.zoraw.cinema.model.domain

import spock.lang.Specification
import spock.lang.Unroll

class RoomDaoDtoTest extends Specification {

    @Unroll
    def "should check seat validation for standard empty room"() {
        given: "empty 4x5 room"
        def room = Room.builder()
                .seats(getSeats())
                .build()

        expect:
        room.canReserveSeats(seatsToReserve) == canReserve

        where:
        seatsToReserve                      | canReserve
        [seat('A', 1), seat('A', 3)] as Set | false
        [seat('A', 1), seat('A', 2)] as Set | true
        [seat('A', 1), seat('A', 4)] as Set | true
        [seat('A', 1), seat('B', 3)] as Set | true
    }

    @Unroll
    def "should check seat validation for room with corridor"() {
        given: "empty 4x5 room with corridor between 2 and 3 column "
        def room = Room.builder()
                .seats(getSeatsWithCorridor())
                .build()

        expect:
        room.canReserveSeats(seatsToReserve) == canReserve

        where:
        seatsToReserve                      | canReserve
        [seat('A', 3), seat('A', 5)] as Set | false
        [seat('A', 3)] as Set               | true
        [seat('A', 3), seat('B', 5)] as Set | true
        [seat('A', 5)] as Set               | true
        [seat('A', 2)] as Set               | true
    }

    private Seat seat(String row, int number) {
        Seat.builder().row(row).number(number).build()
    }

    private Set<Seat> getSeats() {
        def rows = ['A', 'B', 'C', 'D']

        def seats = new HashSet<Seat>()

        for (int i = 1; i < 6; i++) {
            for (def row : rows) {
                def seat = Seat.builder()
                        .row(row)
                        .number(i)
                        .available(true)
                        .build()
                if (i == 1 || i == 5) {
                    seat.setEdge(true)
                }
                seats.add(seat)
            }
        }
        seats
    }

    private Set<Seat> getSeatsWithCorridor() {
        def rows = ['A', 'B', 'C', 'D']

        def seats = new HashSet<Seat>()

        for (int i = 1; i < 6; i++) {
            for (def row : rows) {
                def seat = Seat.builder()
                        .row(row)
                        .number(i)
                        .available(true)
                        .build()
                if (i != 4) {
                    seat.setEdge(true)
                }
                seats.add(seat)
            }
        }
        seats
    }
}
