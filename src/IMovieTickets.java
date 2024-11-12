public interface IMovieTickets<MovieTicketData> {
    double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice);
    boolean ValidateData(MovieTicketData movieTicketData);
}
