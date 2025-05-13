# UTS Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: {Azmi Syahri Ramadhan}</li>
  <li>NIM: {23552011068}</li>
  <li>Studi Kasus: {Kasir Asuransi}</li>
</ul>

## Judul Studi Kasus
<p>Implementasi Sistem Kasir Asuransi pada OOP dan Database.</p>

## Penjelasan Studi Kasus
<p>Sistem asuransi ini menerapkan OOP dengan inheritance melalui hierarki kelas Asuransi-Kesehatan/Jiwa, polymorphism dalam perhitungan premi yang berbeda, encapsulation dengan getter/setter untuk data nasabah, dan abstraction menggunakan interface LayananAsuransi serta abstract class.</p>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Kelas AsuransiKesehatan dan AsuransiJiwa mewarisi sifat dan metode dari kelas induk Asuransi.</p>

```java
// Parent Class
public abstract class Asuransi {
    protected String jenis;
    protected double premi;
    // ...
}

// Child Class 1
public class AsuransiKesehatan extends Asuransi {
    public AsuransiKesehatan() {
        super("Kesehatan"); // Memanggil constructor parent
    }
    // ...
}

// Child Class 2
public class AsuransiJiwa extends Asuransi {
    public AsuransiJiwa() {
        super("Jiwa"); // Memanggil constructor parent
    }
    // ...
}
```

### 2. Encapsulation
<p>Metode hitungPremi() diimplementasikan secara berbeda di AsuransiKesehatan dan AsuransiJiwa, menyesuaikan jenis asuransi.</p>

```java
// Parent Class
public abstract class Asuransi implements LayananAsuransi {
    public abstract double hitungPremi(); // Abstract method
}

// Child Class 1
public class AsuransiKesehatan extends Asuransi {
    @Override
    public double hitungPremi() {
        premi = 500000 + (500000 * 0.1); // Implementasi spesifik
        return premi;
    }
}

// Child Class 2
public class AsuransiJiwa extends Asuransi {
    @Override
    public double hitungPremi() {
        premi = 300000 + (300000 * 0.15); // Implementasi berbeda
        return premi;
    }
}
```

### 3. Polymorphism
<p>Data nasabah seperti nama dan umur dibungkus dalam kelas Nasabah dengan akses terbatas melalui getter/setter.</p>

```java
public class Nasabah {
    private int id;
    private String nama;
    private int umur;

    // Getter dan Setter
    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        if (umur > 0) { // Validasi
            this.umur = umur;
        }
    }
    // ...
}
```

### 4. Abstract
<p>Interface LayananAsuransi dan abstract class Asuransi menyembunyikan detail implementasi, hanya mengekspos fitur penting seperti hitungPremi().</p>

```java
// Interface
public interface LayananAsuransi {
    double hitungPremi();
    String getJenis();
}

// Abstract Class
public abstract class Asuransi implements LayananAsuransi {
    protected String jenis;
    protected double premi;

    public abstract double hitungPremi(); // Abstract method

    @Override
    public String getJenis() {
        return jenis; // Implementasi konkret
    }
}
```

## Demo Proyek
<ul>
  <li>Github: <a href="https://github.com/Azmisyh/UTS-PBO2">Github</a></li>
  <li>Youtube: <a href="https://youtu.be/fE94aaK8fbc">Youtube</a></li>
</ul>
