/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;
import Interface.NasabahDAO;
import Interface.Asuransi;
import Interface.AsuransiKesehatan;
import Interface.Polis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolisDAO {
    private Connection connection;

    public PolisDAO(Connection connection) {
        this.connection = connection;
    }

    public void tambahPolis(Scanner scanner, NasabahDAO nasabahDAO) {
        nasabahDAO.tampilkanNasabah();
        System.out.print("Pilih ID nasabah: ");
        int nasabahId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Pilih jenis asuransi:");
        System.out.println("1. Kesehatan");
        System.out.println("2. Jiwa");
        System.out.print("Pilihan: ");
        int jenisChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Asuransi asuransi;
        if (jenisChoice == 1) {
            asuransi = new AsuransiKesehatan();
        } else if (jenisChoice == 2) {
            asuransi = new AsuransiJiwa();
        } else {
            System.out.println("Pilihan tidak valid!");
            return;
        }

        double premi = asuransi.hitungPremi();

        String sql = "INSERT INTO polis (nasabah_id, jenis, premi) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, nasabahId);
            statement.setString(2, asuransi.getJenis());
            statement.setDouble(3, premi);
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Polis berhasil ditambahkan dengan premi: " + premi);
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan polis: " + e.getMessage());
        }
    }

    public List<Polis> getAllPolis() {
        List<Polis> polisList = new ArrayList<>();
        String sql = "SELECT * FROM polis";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Polis polis = new Polis(
                    resultSet.getInt("nasabah_id"),
                    resultSet.getString("jenis"),
                    resultSet.getDouble("premi")
                );
                polis.setId(resultSet.getInt("id"));
                polisList.add(polis);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data polis: " + e.getMessage());
        }
        
        return polisList;
    }

    public void tampilkanPolis() {
        List<Polis> polisList = getAllPolis();
        if (polisList.isEmpty()) {
            System.out.println("Tidak ada data polis.");
        } else {
            System.out.println("\nDaftar Polis:");
            for (Polis polis : polisList) {
                System.out.println(polis);
            }
        }
    }

    public Polis getPolisById(int id) {
        String sql = "SELECT * FROM polis WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Polis polis = new Polis(
                    resultSet.getInt("nasabah_id"),
                    resultSet.getString("jenis"),
                    resultSet.getDouble("premi")
                );
                polis.setId(resultSet.getInt("id"));
                return polis;
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil polis: " + e.getMessage());
        }
        
        return null;
    }
}
