/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.posmiegacoan;

// CLASS:
// Class produk  cetakan untuk membuat object produk
public class produk {

    // ATRIBUT:untuk menyimpan data
    // private merupakan bagian dari ENKAPSULASI,yaitu membatasi akses langsung dari luar class
    private String nama;
    private double harga;
    private int stok;

    
    public produk(String nama, double harga, int stok) {

        
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // GETTER:Method untuk mengambil nilai atribut yg gunakan karena atribut nama bersifat private
    public String getNama() {
        return nama;
    }

  
    // Method untuk mengambil nilai harga
    public double getHarga() {
        return harga;
    }

    
    public int getStok() {
        return stok;
    }

    
    public void tampilkanInfo() {

        System.out.println("Nama: " + nama);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Stok: " + stok);
    }

    // METHOD:
    // Menghitung subtotal berdasarkan harga dan jumlah pembelian
    // Parameter jumlah adalah banyak produk yang dibeli
    // return digunakan untuk mengembalikan hasil perhitungan
    public double hitungSubtotal(int jumlah) {

        double subtotal = harga * jumlah;

        return subtotal;
    }

    // METHOD:
    // Mengurangi stok setelah transaksi berhasil
    // Parameter jumlah adalah banyak produk yang terjual
    public void kurangiStok(int jumlah) {

        // Memastikan jumlah pengurangan valid
        // dan stok yang tersedia mencukupi
        if (jumlah > 0 && jumlah <= stok) {

            stok = stok - jumlah;

        } else {

            System.out.println(
                "Stok tidak cukup atau jumlah tidak valid."
            );
        }
    }
}
