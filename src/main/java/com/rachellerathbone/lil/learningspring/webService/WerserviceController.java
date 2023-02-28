package com.rachellerathbone.lil.learningspring.webService;

import com.rachellerathbone.lil.learningspring.business.ReservationService;
import com.rachellerathbone.lil.learningspring.business.RoomReservation;
import com.rachellerathbone.lil.learningspring.data.Guest;
import com.rachellerathbone.lil.learningspring.data.Room;
import com.rachellerathbone.lil.learningspring.util.DateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WerserviceController {

    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WerserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservation(@RequestParam(value="date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString((dateString));
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getRooms();
    }
}
