package comapps;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

/**
 * Variable and method names aren't descriptive enough.
 * Give variables and methods more appropriate names.
 * Code Reviewed by: Christine Marasigan
 */

@TestMethodOrder(OrderAnnotation.class)
class MovieBookingTest {
    /** for creating new instance of MovieBookingSystem. */
    private MovieBookingSystem mbs;
    /** amount of tickets to book for test case 1. */
    private final int ticketsToBook = 5;
    /** too much tickets to book for test case 2. */
    private final int tooMuchTickets = 100;
    /** tickets to cancel for test case 3. */
    private final int ticketsToCancel = 3;
    /** tickets to book for test case 4. */
    private final int otherTicketsToBook = 2;
    /** too much tickets to cancel for test case 5. */
    private final int tooMuchToCancel = 5;
    /** available tickets left for EXTRA test case 1 & 2. */
    private final int availableTickets = 30;
    /** available tickets left for EXTRA test case 3. */
    private final int updatedAvailableTickets = 25;

    /** for ordering test cases. */
    private final int orderOne = 1;
    /** for ordering test cases. */
    private final int orderTwo = 2;
    /** for ordering test cases. */
    private final int orderThree = 3;
    /** for ordering test cases. */
    private final int orderFour = 4;
    /** for ordering test cases. */
    private final int orderFive = 5;
    /** for ordering test cases. */
    private final int orderSix = 6;
    /** for ordering test cases. */
    private final int orderSeven = 7;
    /** for ordering test cases. */
    private final int orderEight = 8;

    @BeforeEach
    void setUp() {
        mbs = new MovieBookingSystem();
        System.out.println("[New instance created for New Test Case]");
    }

    @Order(orderOne)
    @DisplayName("Test 1: Book 5 tickets for 10:00 AM showtime")
    @Test
    void bookTickets() {
        String showtime = "10:00 AM";
        mbs.bookTicket(showtime, ticketsToBook);
        assertEquals(ticketsToBook,
                mbs.getBookedSeats()[mbs.getShowTimeIndex(showtime)]);
    }

    @Order(orderTwo)
    @DisplayName("Test 2: Try booking more tickets"
            + "than available (100 tickets)")
    @Test
    void bookTooMuchTickets() {
        String showtime = "10:00 AM";
        mbs.bookTicket(showtime, tooMuchTickets);
        assertEquals(0, mbs.getBookedSeats()[mbs.getShowTimeIndex(showtime)]);
    }

    @Order(orderThree)
    @DisplayName("Test 3: Cancel 3 tickets for a showtime")
    @Test
    void cancelBookings() {
        String showtime = "10:00 AM";
        mbs.bookTicket(showtime, ticketsToBook);
        mbs.cancelReservation(showtime, ticketsToCancel);
        assertEquals(2, mbs.getBookedSeats()[mbs.getShowTimeIndex(showtime)]);
    }

    @Order(orderFour)
    @DisplayName("Test 4: Book 2 tickets for another showtime")
    @Test
    void bookOtherShowtimeTickets() {
        String showtime = "1:00 PM";
        mbs.bookTicket(showtime, otherTicketsToBook);
        assertEquals(2, mbs.getBookedSeats()[mbs.getShowTimeIndex(showtime)]);
    }

    @Order(orderFive)
    @DisplayName("Test 5: Try to cancel more tickets than booked")
    @Test
    void cancelTooMuchTickets() {
        String showtime = "1:00 PM";
        mbs.bookTicket(showtime, otherTicketsToBook);
        mbs.cancelReservation(showtime, tooMuchToCancel);
        assertEquals(2, mbs.getBookedSeats()[mbs.getShowTimeIndex(showtime)]);
    }

    @Order(orderSix)
    @DisplayName("EXTRA 1: Invalid showtime for booking tickets")
    @Test
    void testInvalidShowtimeBookTicket() {
        String showtime = "10:00";
        mbs.bookTicket(showtime, otherTicketsToBook);
        assertEquals(-1, mbs.getShowTimeIndex(showtime));

        assertEquals(availableTickets, mbs.checkAvailability("10:00 AM"));
        assertEquals(availableTickets, mbs.checkAvailability("1:00 PM"));
        assertEquals(availableTickets, mbs.checkAvailability("4:00 PM"));
        assertEquals(availableTickets, mbs.checkAvailability("6:00 PM"));
    }

    @Order(orderSeven)
    @DisplayName("EXTRA 2: Invalid showtime for cancelling reservation")
    @Test
    void testInvalidShowtimeCancelReservation() {
        String showtime = "10:01 AM";
        mbs.cancelReservation(showtime, ticketsToCancel);
        assertEquals(-1, mbs.getShowTimeIndex(showtime));

        assertEquals(availableTickets, mbs.checkAvailability("10:00 AM"));
        assertEquals(availableTickets, mbs.checkAvailability("1:00 PM"));
        assertEquals(availableTickets, mbs.checkAvailability("4:00 PM"));
        assertEquals(availableTickets, mbs.checkAvailability("6:00 PM"));
    }

    @Order(orderEight)
    @DisplayName("EXTRA 3: Test Check Availability")
    @Test
    void testCheckAvailability() {
        // Book tickets for the current showtime
        mbs.bookTicket("10:00 AM", ticketsToBook);

        // Loop through each element in the showTimes array
        for (String showtime : mbs.getShowTimes()) {

            // Check availability for the current showtime
            int avail = mbs.checkAvailability(showtime);

            // Print the result
            System.out.println("There are " + avail
                    + " seats left for " + showtime + " slot.");
        }

        assertEquals(updatedAvailableTickets,
                mbs.checkAvailability("10:00 AM"));
        assertEquals(availableTickets,
                mbs.checkAvailability("1:00 PM"));
        assertEquals(availableTickets,
                mbs.checkAvailability("4:00 PM"));
        assertEquals(availableTickets,
                mbs.checkAvailability("6:00 PM"));

    }

}
