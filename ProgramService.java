package com.example.CleanGreenIndore;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProgramService{           //Add new program funtion

    public void addNewProgram() {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "INSERT INTO Programs (P_id,Program_Name, Date, Location, Description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Program id");
            int id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Program Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Program Date (YYYY-MM-DD):");
            String date = scanner.nextLine();
            System.out.println("Enter Location:");
            String location = scanner.nextLine();
            System.out.println("Enter Description:");
            String description = scanner.nextLine();
             stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, date);
            stmt.setString(4, location);
            stmt.setString(5, description);

            stmt.executeUpdate();
            System.out.println("Program added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Display Function
        public void displayAllPrograms() {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT * FROM Programs";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                System.out.println("No programs found in the database.");
             while (rs.next()) {
                System.out.println("Program ID: " + rs.getInt("P_id"));
                System.out.println("Name: " + rs.getString("Program_Name"));
                System.out.println("Date: " + rs.getString("Date"));
                System.out.println("Location: " + rs.getString("Location"));
                System.out.println("Description: " + rs.getString("Description"));
                System.out.println("=================================");
            }
        }
    }
    
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
//update function
public void updateProgram() {
    try (Connection connection = DataBaseConnection.getConnection()) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Program ID to update:");
        int programId = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        System.out.println("Enter new Program Name (or press enter to skip):");
        String name = scanner.nextLine();

        System.out.println("Enter new Date (YYYY-MM-DD) (or press enter to skip):");
        String date = scanner.nextLine();

        System.out.println("Enter new Location (or press enter to skip):");
        String location = scanner.nextLine();

        System.out.println("Enter new Description (or press enter to skip):");
        String description = scanner.nextLine();

        // Start building the SQL query
        StringBuilder queryBuilder = new StringBuilder("UPDATE Programs SET ");
        boolean hasUpdates = false;

        if (!name.isEmpty()) {
            queryBuilder.append("Program_Name = ?, ");
            hasUpdates = true;
        }
        if (!date.isEmpty()) {
            queryBuilder.append("Date = ?, ");
            hasUpdates = true;
        }
        if (!location.isEmpty()) {
            queryBuilder.append("Location = ?, ");
            hasUpdates = true;
        }
        if (!description.isEmpty()) {
            queryBuilder.append("Description = ?, ");
            hasUpdates = true;
        }

        if (hasUpdates) {
            queryBuilder.setLength(queryBuilder.length() - 2); // Remove trailing ", "
            queryBuilder.append(" WHERE P_id = ?");
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
        if (!date.isEmpty()) {
            stmt.setString(paramIndex++, date);
        }
        if (!location.isEmpty()) {
            stmt.setString(paramIndex++, location);
        }
        if (!description.isEmpty()) {
            stmt.setString(paramIndex++, description);
        }

        stmt.setInt(paramIndex, programId);

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Program updated successfully!");
        } else {
            System.out.println("Program ID not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// delete function
public void deleteProgram() {
    try (Connection connection = DataBaseConnection.getConnection()) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Program ID to delete:");
        int programId = scanner.nextInt();

        // Validate input
        if (programId <= 0) {
            System.out.println("Invalid Program ID. It must be a positive integer.");
            return;
        }

        // Confirm column name and table name
        String query = "DELETE FROM Programs WHERE P_id = ?"; // Ensure 'P_id' is correct
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, programId);

        // Execute the query
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Program deleted successfully!");
        } else {
            System.out.println("Program ID not found.");
        }
    } catch (SQLException e) {
        // Print stack trace for debugging
        e.printStackTrace();
    }
}
}