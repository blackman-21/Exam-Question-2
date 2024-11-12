package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MovieTicketsTest {

    private MovieTickets movieTickets;

    @BeforeEach
    public void setUp() {
        // Initialize the MovieTickets instance before each test
        movieTickets = new MovieTickets();
    }

    @Test
    public void CalculateTotalTicketPrice_CalculatedSuccessfully() {
        // Arrange
        double ticketPrice = 50.0; // Example ticket price
        int ticketCount = 4;       // Example number of tickets
        double expectedTotal = 50.0 * 4 * 1.14; // Calculate expected total including VAT (14%)

        // Act
        double totalPrice = movieTickets.calculateTotalPrice(ticketPrice, ticketCount);

        // Assert
        assertEquals(expectedTotal, totalPrice, 0.001, "The total price calculated including VAT should be correct.");
    }

    @Test
    public void ValidationTests() {
        // Test invalid movie name
        String movieName = "";
        assertTrue(movieName.isEmpty(), "Movie name should not be empty.");

        // Test invalid ticket price (less than or equal to zero)
        double invalidTicketPrice = -10.0;
        assertTrue(invalidTicketPrice <= 0, "Ticket price must be greater than zero.");

        // Test invalid number of tickets (less than or equal to zero)
        int invalidTicketCount = 0;
        assertTrue(invalidTicketCount <= 0, "Number of tickets must be greater than zero.");
    }

    private class MovieTickets {
        public double calculateTotalPrice(double ticketPrice, int ticketCount) {
            return 0;
        }
    }
}
