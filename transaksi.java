
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.posmiegacoan;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

// CLASS: mengelola keranjang dan proses transaksi
public class transaksi {

    // ENKAPSULASI: menyimpan daftar produk yang dibeli
    private ArrayList<produk> daftarProduk = new ArrayList<>();

    // Menyimpan jumlah pembelian setiap produk
    private ArrayList<Integer> daftarJumlah = new ArrayList<>();

    // Membuat format angka sesuai penulisan Indonesia
    private NumberFormat formatRupiah =
            NumberFormat.getInstance(new Locale("id", "ID"));

    // METHOD: menambahkan produk ke keranjang
    public boolean tambahProduk(produk item, int jumlah) {

        // VALIDASI: produk harus ada dan jumlah harus lebih dari nol
        if (item == null || jumlah <= 0) {
            System.out.println("Produk atau jumlah tidak valid.");
            return false;
        }

        // Menghitung jumlah produk yang sama di keranjang
        int jumlahDiKeranjang = 0;

        for (int i = 0; i < daftarProduk.size(); i++) {

            // Memeriksa apakah produk yang dipilih sudah ada di keranjang
            if (daftarProduk.get(i) == item) {
                jumlahDiKeranjang += daftarJumlah.get(i);
            }
        }

        // Menghitung total jumlah setelah penambahan
        int totalJumlah = jumlah + jumlahDiKeranjang;

        // VALIDASI: total pembelian tidak boleh melebihi stok
        if (totalJumlah > item.getStok()) {
            System.out.println("Stok tidak mencukupi.");
            System.out.println("Stok tersedia: " + item.getStok());
            System.out.println("Jumlah di keranjang: " + jumlahDiKeranjang);
            return false;
        }

        // Menyimpan produk dan jumlahnya ke keranjang
        daftarProduk.add(item);
        daftarJumlah.add(jumlah);

        System.out.println("Produk berhasil ditambahkan ke keranjang.");
        return true;
    }

    // METHOD: memeriksa apakah keranjang kosong
    public boolean isKosong() {
        return daftarProduk.isEmpty();
    }

    // METHOD: menghitung total seluruh belanja
    public double hitungTotal() {

        double total = 0;

        // Menghitung subtotal setiap produk di keranjang
        for (int i = 0; i < daftarProduk.size(); i++) {

            produk item = daftarProduk.get(i);
            int jumlah = daftarJumlah.get(i);

            total += item.hitungSubtotal(jumlah);
        }

        return total;
    }

    // METHOD: menampilkan semua produk dalam keranjang
    public void tampilkanKeranjang() {

        System.out.println("===== KERANJANG BELANJA =====");

        for (int i = 0; i < daftarProduk.size(); i++) {

            produk item = daftarProduk.get(i);
            int jumlah = daftarJumlah.get(i);
            double subtotal = item.hitungSubtotal(jumlah);

            System.out.println("Produk   : " + item.getNama());
            System.out.println("Harga    : Rp"
                    + formatRupiah.format(item.getHarga()));
            System.out.println("Jumlah   : " + jumlah);
            System.out.println("Subtotal : Rp"
                    + formatRupiah.format(subtotal));
            System.out.println("--------------------------");
        }

        System.out.println("Total Belanja: Rp"
                + formatRupiah.format(hitungTotal()));
    }

    // METHOD: memeriksa stok seluruh keranjang
    public boolean cekStokKeranjang() {

        // Memeriksa semua produk sebelum stok dikurangi
        for (int i = 0; i < daftarProduk.size(); i++) {

            produk item = daftarProduk.get(i);
            int jumlah = daftarJumlah.get(i);

            // Jika jumlah melebihi stok yang tersedia, proses dibatalkan
            if (jumlah <= 0 || jumlah > item.getStok()) {

                System.out.println("Stok tidak mencukupi untuk produk: "
                        + item.getNama());

                System.out.println("Stok tersedia: " + item.getStok());
                System.out.println("Jumlah dibeli: " + jumlah);

                return false;
            }
        }

        // Semua produk memiliki stok yang cukup
        return true;
    }

    // METHOD: mengurangi stok setelah pembayaran berhasil
    public boolean kurangiStokSetelahBayar() {

        // Periksa semua stok terlebih dahulu
        // agar tidak ada stok yang terlanjur dikurangi
        if (!cekStokKeranjang()) {
            System.out.println("Stok tidak berubah.");
            return false;
        }

        // Jika semua stok cukup, kurangi stok setiap produk
        for (int i = 0; i < daftarProduk.size(); i++) {

            produk item = daftarProduk.get(i);
            int jumlah = daftarJumlah.get(i);

            item.kurangiStok(jumlah);
        }

        System.out.println("Stok berhasil diperbarui.");
        return true;
    }

    // METHOD BARU: menyusun data transaksi menjadi teks struk
    public String buatTeksStruk(
            double total, double bayar, double kembalian) {

        // OBJECT: StringBuilder digunakan untuk menyusun teks struk
        StringBuilder struk = new StringBuilder();

        // Menampilkan judul struk
        struk.append("================================\n");
        struk.append("       KEDAI MIE GACOAN\n");
        struk.append("        STRUK PEMBAYARAN\n");
        struk.append("================================\n\n");

        // Mengambil produk dan jumlah dari keranjang
        for (int i = 0; i < daftarProduk.size(); i++) {

            produk item = daftarProduk.get(i);
            int jumlah = daftarJumlah.get(i);

            // Menghitung subtotal produk
            double subtotal = item.hitungSubtotal(jumlah);

            // Menampilkan nama produk
            struk.append(item.getNama()).append("\n");

            // Menampilkan harga dan jumlah produk
            struk.append("  ")
                    .append(jumlah)
                    .append(" x Rp")
                    .append(formatRupiah.format(item.getHarga()))
                    .append("\n");

            // Menampilkan subtotal produk
            struk.append("  Subtotal: Rp")
                    .append(formatRupiah.format(subtotal))
                    .append("\n\n");
        }

        // Menampilkan total dan informasi pembayaran
        struk.append("--------------------------------\n");
        struk.append("Total       : Rp")
                .append(formatRupiah.format(total)).append("\n");
        struk.append("Uang bayar  : Rp")
                .append(formatRupiah.format(bayar)).append("\n");
        struk.append("Kembalian   : Rp")
                .append(formatRupiah.format(kembalian)).append("\n");
        struk.append("================================\n");
        struk.append("       Terima kasih!\n");
        struk.append("================================\n");

        // Mengembalikan teks struk agar bisa digunakan class CetakStruk
        return struk.toString();
    }
}
