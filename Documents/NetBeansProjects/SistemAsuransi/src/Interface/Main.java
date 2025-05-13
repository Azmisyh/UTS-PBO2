/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/sistem_asuransi";
    private static final String USER = "root"; // ganti dengan username MySQL Anda
    private static final String PASSWORD = ""; // ganti dengan password MySQL Anda
    
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Koneksi ke database berhasil!");
            
            Scanner scanner = new Scanner(System.in);
            NasabahDAO nasabahDAO = new NasabahDAO(connection);
            PolisDAO polisDAO = new PolisDAO(connection);
            KlaimDAO klaimDAO = new KlaimDAO(connection);
            
            while (true) {
                System.out.println("\nSistem Asuransi Sederhana");
                System.out.println("1. Tambah Nasabah");
                System.out.println("2. Tambah Polis");
                System.out.println("3. Tambah Klaim");
                System.out.println("4. Tampilkan Nasabah");
                System.out.println("5. Tampilkan Polis");
                System.out.println("6. Tampilkan Klaim");
                System.out.println("0. Keluar");
                System.out.print("Pilihan: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1:
                        nasabahDAO.tambahNasabah(scanner);
                        break;
                    case 2:
                        polisDAO.tambahPolis(scanner, nasabahDAO);
                        break;
                    case 3:
                        klaimDAO.tambahKlaim(scanner, polisDAO);
                        break;
                    case 4:
                        nasabahDAO.tampilkanNasabah();
                        break;
                    case 5:
                        polisDAO.tampilkanPolis();
                        break;
                    case 6:
                        klaimDAO.tampilkanKlaim();
                        break;
                    case 0:
                        System.out.println("Keluar dari program...");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }
    }
}