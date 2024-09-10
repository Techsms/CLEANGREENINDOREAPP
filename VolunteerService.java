package com.example.CleanGreenIndore;
   import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Scanner;
    
    public class VolunteerService {
    
        // Method to add a new volunteer
        public void addNewVolunteer() {
            try (Connection connection = DataBaseConnection.getConnection()) {
                String query = "INSERT INTO Volunteers (name, contact, email) VALUES (?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(query);
    
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Volunteer Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Contact Number:");
                String contact = scanner.nextLine();
                System.out.println("Enter Email:");
                String email = scanner.nextLine();
    
                stmt.setString(1, name);
                stmt.setString(2, contact);
                stmt.setString(3, email);
    
                stmt.executeUpdate();
                System.out.println("Volunteer added successfully!");
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        // Method to display all volunteers
        public void displayAllVolunteers() {
            try (Connection connection = DataBaseConnection.getConnection()) {
                String query = "SELECT * FROM Volunteers";
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        
                // Check if ResultSet has any data
                if (!rs.isBeforeFirst()) {
                    System.out.println("No Volunteer found in the database.");
                } else {
                    // Process each row and display the data
                    while (rs.next()) {
                        System.out.println("Volunteer ID: " + rs.getInt("id"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Contact: " + rs.getString("contact"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("=================================");
                    }
                }
            } catch (SQLException e) {
                System.out.println("DataBase is Empty!!!");
                e.printStackTrace();
            }
        }
        
    
        // Method to update a volunteer
        public void updateVolunteer() {
            try (Connection connection = DataBaseConnection.getConnection()) {
                Scanner scanner = new Scanner(System.in);
        
                System.out.println("Enter the Volunteer ID to update:");
                int volunteerId = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
        
                System.out.println("Enter new Volunteer Name (or press enter to skip):");
                String name = scanner.nextLine();
        
                System.out.println("Enter new Contact (or press enter to skip):");
                String contact = scanner.nextLine();
        
                System.out.println("Enter new Email (or press enter to skip):");
                String email = scanner.nextLine();
        
                // Start building the SQL query
                StringBuilder queryBuilder = new StringBuilder("UPDATE Volunteers SET ");
                boolean hasUpdates = false;
        
                if (!name.isEmpty()) {
                    queryBuilder.append("name = ?, ");
                    hasUpdates = true;
                }
                if (!contact.isEmpty()) {
                    queryBuilder.append("contact = ?, ");
                    hasUpdates = true;
                }
                if (!email.isEmpty()) {
                    queryBuilder.append("email = ?, ");
                    hasUpdates = true;
                }
        
                if (hasUpdates) {
                    queryBuilder.setLength(queryBuilder.length() - 2); // Remove trailing ", "
                    queryBuilder.append(" WHERE id = ?");
                } else {
                    System.out.println("No updates provided.");
                    return;
                }
        
                // Prepare the SQL statement
                PreparedStatement stmt = connection.prepareStatement(queryBuilder.toString());
        
                int paramIndex = 1;
        
                if (!name.isEmpty()) {
                    stmt.setString(paramIndex++, name);
                }
                if (!contact.isEmpty()) {
                    stmt.setString(paramIndex++, contact);
                }
                if (!email.isEmpty()) {
                    stmt.setString(paramIndex++, email);
                }
        
                stmt.setInt(paramIndex, volunteerId);
        
                // Execute the update
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Volunteer updated successfully!");
                } else {
                    System.out.println("Volunteer ID not found.");
                }
            } catch (SQLException e) {
                // Print stack trace for debugging
                e.printStackTrace();
            }
        }
        
    
        // Method to delete a volunteer
        public void deleteVolunteer() {
            try (Connection connection = DataBaseConnection.getConnection()) {
                Scanner scanner = new Scanner(System.in);
    
                System.out.println("Enter the Volunteer ID to delete:");
                int volunteerId = scanner.nextInt();
    
                String query = "DELETE FROM Volunteers WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, volunteerId);
    
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Volunteer deleted successfully!");
                } else {
                    System.out.println("Volunteer ID not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     

