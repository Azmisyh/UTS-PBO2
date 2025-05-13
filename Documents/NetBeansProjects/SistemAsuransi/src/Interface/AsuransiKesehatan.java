/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

public class AsuransiKesehatan extends Asuransi {
    public AsuransiKesehatan() {
        super("Kesehatan");
    }

    @Override
    public double hitungPremi() {
        // Contoh perhitungan premi asuransi kesehatan
        premi = 500000; // Premi dasar
        premi += premi * 0.1; // Pajak 10%
        return premi;
    }
}