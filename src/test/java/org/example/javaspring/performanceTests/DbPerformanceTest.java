package org.example.javaspring.performanceTests;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbPerformanceTest {

    @Test
    public void measureDbQueryTime() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT COUNT(*) FROM rentals";

            long start = System.nanoTime();
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Total rentals: " + rs.getInt(1));
                }
            }
            long end = System.nanoTime();

            System.out.println("DB Query took " + (end - start) / 1_000_000.0 + " ms");
        }
    }
}
