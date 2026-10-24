/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.posmiegacoan;

import java.util.ArrayList;

// CLASS: menyimpan dan mengatur daftar menu kedai
public class daftarmenu {

    // ArrayList digunakan untuk menyimpan kumpulan objek produk
    private ArrayList<produk> daftar = new ArrayList<>();

    // CONSTRUCTOR: dijalankan saat objek daftarmenu dibuat
    public daftarmenu() {

        // ===== DAFTAR MAKANAN =====
        daftar.add(new produk("Mie Original", 12000, 20));
        daftar.add(new produk("Mie Level 1", 13000, 20));
        daftar.add(new produk("Mie Level 2", 13000, 20));
        daftar.add(new produk("Mie Level 3", 13000, 20));
        daftar.add(new produk("Mie Level 4", 14000, 20));
        daftar.add(new produk("Mie Level 5", 14000, 20));

        // ===== DAFTAR MINUMAN =====
        daftar.add(new produk("Es Teh", 4000, 20));
        daftar.add(new produk("Jeruk", 5000, 20));
        daftar.add(new produk("Lemon Tea", 5000, 20));
        daftar.add(new produk("Air Mineral", 4000, 20));
        daftar.add(new produk("Es Campur", 8000, 20));
    }

    // METHOD: menampilkan seluruh menu makanan dan minuman
    public void tampilkanSemua() {
        tampilkanKategori(1);
        tampilkanKategori(2);
    }

    // METHOD: menampilkan menu berdasarkan kategori
    // kategori 1 = makanan, kategori 2 = minuman
    public void tampilkanKategori(int kategori) {

        if (kategori == 1) {
            System.out.println("\n===== MENU MAKANAN =====");

            // Perulangan untuk menampilkan 6 produk makanan
            for (int i = 0; i < 6; i++) {
                System.out.print((i + 1) + ". ");

                // Memanggil method milik objek produk
                // Informasi nama, harga, dan stok ditampilkan oleh method ini
                daftar.get(i).tampilkanInfo();

                System.out.println("--------------------------");
            }

        } else if (kategori == 2) {
            System.out.println("\n===== MENU MINUMAN =====");

            // Perulangan untuk menampilkan 5 produk minuman
            for (int i = 6; i < 11; i++) {
                System.out.print((i - 5) + ". ");

                // Menampilkan informasi produk minuman
                daftar.get(i).tampilkanInfo();

                System.out.println("--------------------------");
            }
        }
    }

    // METHOD: mengambil produk berdasarkan kategori dan nomor pilihan
    public produk getProdukByKategori(int kategori, int nomor) {

        // Jika kategori makanan dan nomor valid
        if (kategori == 1 && nomor >= 1 && nomor <= 6) {
            return daftar.get(nomor - 1);
        }

        // Jika kategori minuman dan nomor valid
        if (kategori == 2 && nomor >= 1 && nomor <= 5) {
            return daftar.get(nomor + 5);
        }

        // Jika kategori atau nomor tidak valid, kembalikan null
        return null;
    }

    // METHOD: mengambil produk menggunakan nomor global
    // Method ini dipertahankan agar fitur lama tetap bisa menggunakannya
    public produk getProduk(int nomor) {

        if (nomor >= 1 && nomor <= daftar.size()) {
            return daftar.get(nomor - 1);
        }

        return null;
    }
}
