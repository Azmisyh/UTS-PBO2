/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KlaimDAO {
    private Connection connection;

    public KlaimDAO(Connection connection) {
        this.connection = connection;
    }

    public void tambahKlaim(Scanner scanner, PolisDAO polisDAO) {
        polisDAO.tampilkanPolis();
        System.out.print("Pilih ID polis: ");
        int polisId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Masukkan tanggal klaim (YYYY-MM-DD): ");
        String tanggalStr = scanner.nextLine();
        Date tanggal = Date.valueOf(tanggalStr);

        System.out.print("Masukkan status klaim: ");
        String status = scanner.nextLine();

        String sql = "INSERT INTO klaim (polis_id, tanggal, status) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, polisId);
            statement.setDate(2, tanggal);
            statement.setString(3, status);
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Klaim berhasil ditambahkan!");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan klaim: " + e.getMessage());
        }
    }

    public List<Klaim> getAllKlaim() {
        List<Klaim> klaimList = new ArrayList<>();
        String sql = "SELECT * FROM klaim";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Klaim klaim = new Klaim(
                    resultSet.getInt("polis_id"),
                    resultSet.getDate("tanggal"),
                    resultSet.getString("status")
                );
                klaim.setId(resultSet.getInt("id"));
                klaimList.add(klaim);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data klaim: " + e.getMessage());
        }
        
        return klaimList;
    }

    public void tampilkanKlaim() {
        List<Klaim> klaimList = getAllKlaim();
        if (klaimList.isEmpty()) {
            System.out.println("Tidak ada data klaim.");
        } else {
            System.out.println("\nDaftar Klaim:");
            for (Klaim klaim : klaimList) {
                System.out.println(klaim);
            }
        }
    }
}
