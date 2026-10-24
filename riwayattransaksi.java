/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.posmiegacoan;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

// CLASS: menyimpan dan menampilkan riwayat transaksi
public class riwayattransaksi {

    private ArrayList<String> daftarRiwayat = new ArrayList<>();

    private NumberFormat formatRupiah =
            NumberFormat.getInstance(new Locale("id", "ID"));

    // METHOD: menyimpan transaksi yang berhasil
    public void simpanTransaksi(double total, double bayar,
                                double kembalian) {

        String data = "Total: Rp" + formatRupiah.format(total)
                + " | Bayar: Rp" + formatRupiah.format(bayar)
                + " | Kembalian: Rp" + formatRupiah.format(kembalian);

        daftarRiwayat.add(data);

        System.out.println("Riwayat transaksi berhasil disimpan.");
    }

    // METHOD: menampilkan seluruh riwayat
    public void tampilkanRiwayat() {

        System.out.println("\n===== RIWAYAT TRANSAKSI =====");

        if (daftarRiwayat.isEmpty()) {
            System.out.println("Belum ada transaksi berhasil.");
            return;
        }

        for (int i = 0; i < daftarRiwayat.size(); i++) {
            System.out.println((i + 1) + ". " + daftarRiwayat.get(i));
        }

        System.out.println("=============================");
    }
}
