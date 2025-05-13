/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Interface.Nasabah;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NasabahDAO {
    private Connection connection;

    public NasabahDAO(Connection connection) {
        this.connection = connection;
    }

    public void tambahNasabah(Scanner scanner) {
        System.out.print("Masukkan nama nasabah: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan umur nasabah: ");
        int umur = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String sql = "INSERT INTO nasabah (nama, umur) VALUES (?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, nama);
            statement.setInt(2, umur);
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Nasabah berhasil ditambahkan!");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan nasabah: " + e.getMessage());
        }
    }

    public List<Nasabah> getAllNasabah() {
        List<Nasabah> nasabahList = new ArrayList<>();
        String sql = "SELECT * FROM nasabah";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Nasabah nasabah = new Nasabah(
                    resultSet.getString("nama"),
                    resultSet.getInt("umur")
                );
                nasabah.setId(resultSet.getInt("id"));
                nasabahList.add(nasabah);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data nasabah: " + e.getMessage());
        }
        
        return nasabahList;
    }

    public void tampilkanNasabah() {
        List<Nasabah> nasabahList = getAllNasabah();
        if (nasabahList.isEmpty()) {
            System.out.println("Tidak ada data nasabah.");
        } else {
            System.out.println("\nDaftar Nasabah:");
            for (Nasabah nasabah : nasabahList) {
                System.out.println(nasabah);
            }
        }
    }

    public Nasabah getNasabahById(int id) {
        String sql = "SELECT * FROM nasabah WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Nasabah nasabah = new Nasabah(
                    resultSet.getString("nama"),
                    resultSet.getInt("umur")
                );
                nasabah.setId(resultSet.getInt("id"));
                return nasabah;
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil nasabah: " + e.getMessage());
        }
        
        return null;
    }
}
