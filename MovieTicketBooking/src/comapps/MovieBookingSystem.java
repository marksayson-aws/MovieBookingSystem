/**
 * Package for MovieBookingSystem and other classes involved
 */
package comapps;

/**
 * Everything looks good functionally and checkstyle-wise.
 * Code Reviewed by: Christine Marasigan
 */

public class MovieBookingSystem extends BookingSystem {
    /** array that stores list of showtimes. */
    private String[] showTimes = {"10:00 AM", "1:00 PM", "4:00 PM", "6:00 PM"};
    /**
     * Array that stores list of available seats.
     * Showtimes it is referencing is based on index.
     */
    private int[] bookedSeats = new int[showTimes.length];
    /**
     * Fixed amount of maximum seats.
     * Assumed that only one cinema is being referenced.
     */
    private static final int MAX_SEATS = 30;

    /**
     * Gets index of showtime.
     * @return integer
     * @param showTime
     */
    public int getShowTimeIndex(final String showTime) {
        for (int i = 0; i < showTimes.length; i++) {
            if (showTimes[i].equals(showTime)) {
                return i;
            }
        }
        return -1; // if showtime is not found
    }

    @Override
    public final int checkAvailability(final String showTime) {
        int index = getShowTimeIndex(showTime);
        if (index == -1) {
            System.out.println("Showtime " + showTime + " does not exist.");
            return -1;
        }
        int available = MAX_SEATS - getBookedSeats()[index];
        return available;
    }

    @Override
    public final void bookTicket(final String showTime, final int tickets) {
        int index = getShowTimeIndex(showTime);
        int available = checkAvailability(showTime);

        if (available <= -1) {
            return;
        }

        if (tickets <= available) {
            getBookedSeats()[index] += tickets;
            System.out.println(
                    "Booked " + tickets + " tickets for " + showTime + ".");
        } else {
            System.out.println(
                    "Only " + available + " seats left. "
                            + "Cannot book "
            + tickets + " tickets.");
        }
    }

    @Override
    public final void cancelReservation(
            final String showTime, final int tickets) {
        int index = getShowTimeIndex(showTime);
        int available = checkAvailability(showTime);

        if (available <= -1) {
            return;
        }

        if (tickets <= getBookedSeats()[index]) {
            getBookedSeats()[index] -= tickets;
            System.out.println(
                    "Cancelled " + tickets + " tickets for " + showTime + ".");
        } else {
            System.out.println(
                    "You only have " + getBookedSeats()[index]
                            + " tickets booked. Cannot cancel "
                            + tickets + ".");
        }
    }

    /**
     * Getter for booked seats.
     * @return bookedSeats
     */
    public int[] getBookedSeats() {
        return bookedSeats;
    }

    /**
     * Getter for showTimes array.
     * @return showTimes
     */
    public String[] getShowTimes() {
        return showTimes;
    }

//    /**
//     * For main method testing.
//     * @param args
//     */
//    public static void main(final String[] args) {
//        final int ticketsToBook = 5;
//        final int tooMuchTickets = 100;
//        final int ticketsToCancel = 3;
//        final int otherTicketsToBook = 2;
//        final int tooMuchToCancel = 5;
//
//        MovieBookingSystem mbs = new MovieBookingSystem();
//
//        mbs.bookTicket("10:00 AM", ticketsToBook);      // book 5
//        mbs.bookTicket("10:00 AM", tooMuchTickets);     // book 100 (too much)
//        mbs.cancelReservation("10:00 AM", ticketsToCancel);     // cancel 3
//        // book 5 at 1:00 PM time
//        mbs.bookTicket("1:00 PM", otherTicketsToBook);
//        // cancel 5 (too much)
//        mbs.cancelReservation("1:00 PM", tooMuchToCancel);
//    }

}

