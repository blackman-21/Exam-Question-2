public class MovieTickets {

    // Method to calculate total price including VAT (14%)
    public double calculateTotalPrice(double ticketPrice, int ticketCount) {
        double total = ticketPrice * ticketCount;
        double vat = total * 0.14;
        return total + vat;
    }

    // Add other validation methods if needed
}
