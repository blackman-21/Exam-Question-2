import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MovieTicketsApp {
    private JComboBox<String> movieComboBox;
    private JTextField ticketPriceField;
    private JTextField ticketCountField;
    private JTextArea reportArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketsApp().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Movie Ticket Sales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null);

        // Movie selection combo box
        JLabel movieLabel = new JLabel("Select Movie:");
        movieLabel.setBounds(20, 20, 100, 25);
        frame.add(movieLabel);

        movieComboBox = new JComboBox<>(new String[]{"", "Napoleon", "Oppenheimer", "Damsel"});
        movieComboBox.setBounds(130, 20, 200, 25);
        frame.add(movieComboBox);

        // Ticket price input
        JLabel priceLabel = new JLabel("Ticket Price:");
        priceLabel.setBounds(20, 60, 100, 25);
        frame.add(priceLabel);

        ticketPriceField = new JTextField();
        ticketPriceField.setBounds(130, 60, 100, 25);
        frame.add(ticketPriceField);

        // Number of tickets input
        JLabel countLabel = new JLabel("Number of Tickets:");
        countLabel.setBounds(20, 100, 150, 25);
        frame.add(countLabel);

        ticketCountField = new JTextField();
        ticketCountField.setBounds(130, 100, 100, 25);
        frame.add(ticketCountField);

        // Report area (read-only)
        reportArea = new JTextArea();
        reportArea.setBounds(20, 150, 440, 150);
        reportArea.setEditable(false);
        frame.add(reportArea);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processMenuItem = new JMenuItem("Process");
        processMenuItem.addActionListener(e -> processSale());
        toolsMenu.add(processMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(e -> saveReport());
        toolsMenu.add(saveMenuItem);

        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(e -> clearFields());
        toolsMenu.add(clearMenuItem);

        menuBar.add(toolsMenu);

        frame.setVisible(true);
    }

    private void processSale() {
        String movie = (String) movieComboBox.getSelectedItem();
        String priceText = ticketPriceField.getText();
        String countText = ticketCountField.getText();

        // Validation
        if (movie == null || movie.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Movie selection cannot be empty.");
            return;
        }
        double price;
        int count;
        try {
            price = Double.parseDouble(priceText);
            count = Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values for ticket price and count.");
            return;
        }
        if (price <= 0 || count <= 0) {
            JOptionPane.showMessageDialog(null, "Ticket price and number of tickets must be greater than zero.");
            return;
        }

        // Calculate total price with VAT
        MovieTickets movieTickets = new MovieTickets();
        double totalPrice = movieTickets.calculateTotalPrice(price, count);

        // Display report
        String report = String.format("Movie: %s\nTicket Price: %.2f\nNumber of Tickets: %d\nTotal (incl. VAT): %.2f",
                movie, price, count, totalPrice);
        reportArea.setText(report);

        // Save report to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt", true))) {
            writer.write(report + "\n\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving report to file.");
        }
    }

    private void saveReport() {
        String report = reportArea.getText();
        if (report.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No report to save. Please process a sale first.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt", true))) {
            writer.write(report + "\n\n");
            JOptionPane.showMessageDialog(null, "Report saved to report.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving report to file.");
        }
    }

    private void clearFields() {
        movieComboBox.setSelectedIndex(0);
        ticketPriceField.setText("");
        ticketCountField.setText("");
        reportArea.setText("");
    }
}
