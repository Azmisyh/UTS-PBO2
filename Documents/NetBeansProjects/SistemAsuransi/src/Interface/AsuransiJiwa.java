/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

public class AsuransiJiwa extends Asuransi {
    public AsuransiJiwa() {
        super("Jiwa");
    }

    @Override
    public double hitungPremi() {
        // Contoh perhitungan premi asuransi jiwa
        premi = 300000; // Premi dasar
        premi += premi * 0.15; // Pajak 15%
        return premi;
    }
}