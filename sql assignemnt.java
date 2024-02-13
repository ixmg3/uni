import java.sql.*;
import java.util.Scanner;

public class CRUDApplication {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/my_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("Choose an operation:");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createPerson(connection, scanner);
                        break;
                    case 2:
                        readPersons(connection);
                        break;
                    case 3:
                        updatePerson(connection, scanner);
                        break;
                    case 4:
                        deletePerson(connection, scanner);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createPerson(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter name:");
        String name = scanner.next();
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        System.out.println("Enter email:");
        String email = scanner.next();

        String sql = "INSERT INTO person (name, age, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new person has been inserted successfully.");
            }
        }
    }

    private static void readPersons(Connection connection) throws SQLException {
        String sql = "SELECT * FROM person";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Email: " + email);
            }
        }
    }

    private static void updatePerson(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter ID of the person to update:");
        int id = scanner.nextInt();
        System.out.println("Enter new name:");
        String name = scanner.next();
        System.out.println("Enter new age:");
        int age = scanner.nextInt();
        System.out.println("Enter new email:");
        String email = scanner.next();

        String sql = "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, email);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Person with ID " + id + " has been updated successfully.");
            } else {
                System.out.println("No person found with ID " + id);
            }
        }
    }

    private static void deletePerson(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter ID of the person to delete:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Person with ID " + id + " has been deleted successfully.");
            } else {
                System.out.println("No person found with ID " + id);
            }
        }
    }
}
