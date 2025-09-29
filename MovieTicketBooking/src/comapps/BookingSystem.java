package comapps;

/**
 * Everything looks good functionally and checkstyle-wise.
 * Code Reviewed by: Christine Marasigan
 */

public abstract class BookingSystem {
    /**
     * An abstract method that checks if there
     * are available tickets for a particular showtime.
     * @return int
     * @param showTime
     */
    public abstract int checkAvailability(String showTime);

    /**
     * An abstract method that books the specified
     * number of tickets for a showtime.
     * @param showTime
     * @param tickets
     */
    public abstract void bookTicket(String showTime, int tickets);

    /**
     * An abstract method that cancels the specified
     * number of tickets for a showtime.
     * @param showTime
     * @param tickets
     */
    public abstract void cancelReservation(String showTime, int tickets);
}
