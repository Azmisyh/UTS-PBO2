/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

public abstract class Asuransi implements LayananAsuransi {
    protected String jenis;
    protected double premi;

    public Asuransi(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public abstract double hitungPremi();

    @Override
    public String getJenis() {
        return jenis;
    }

    public double getPremi() {
        return premi;
    }
}