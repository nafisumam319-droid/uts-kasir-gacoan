/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.posmiegacoan;

// IMPORT: mengambil class yang dibutuhkan untuk membuat tampilan
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

// CLASS: cetak struk bertugas menampilkan dan mencetak struk
public class cetakstruk {

    // METHOD: menerima isi struk dari class transaksi
    public void tampilkan(String isiStruk) {

        // OBJECT: membuat jendela untuk menampilkan struk
        JFrame jendela = new JFrame("Struk Mie Gacoan");

        // OBJECT: membuat tempat untuk menampilkan tulisan struk
        JTextArea areaStruk = new JTextArea(isiStruk);

        // Agar tulisan struk tidak bisa diedit oleh pengguna
        areaStruk.setEditable(false);

        // OBJECT: membuat tombol untuk mencetak struk
        JButton tombolCetak = new JButton("Cetak Struk");

        // OBJECT: membuat tombol untuk menutup jendela
        JButton tombolTutup = new JButton("Tutup");

        // Mengatur tindakan ketika tombol cetak ditekan
        tombolCetak.addActionListener(e -> {

            try {
                // METHOD: print() digunakan untuk mencetak isi struk
                areaStruk.print();

            } catch (Exception kesalahan) {

                // Menampilkan pesan jika proses cetak mengalami masalah
                JOptionPane.showMessageDialog(
                        jendela,
                        "Struk gagal dicetak.");
            }
        });

        // Mengatur tindakan ketika tombol tutup ditekan
        tombolTutup.addActionListener(e -> {

            // METHOD: dispose() menutup jendela struk
            jendela.dispose();
        });

        // Menyusun tombol cetak dan tutup di bagian bawah
        javax.swing.JPanel panelTombol =
                new javax.swing.JPanel();

        panelTombol.add(tombolCetak);
        panelTombol.add(tombolTutup);

        // Mengatur posisi isi struk dan tombol di jendela
        jendela.setLayout(new BorderLayout());

        // Menampilkan tulisan struk dan menyediakan scroll
        jendela.add(
                new JScrollPane(areaStruk),
                BorderLayout.CENTER);

        // Menampilkan tombol di bagian bawah jendela
        jendela.add(panelTombol, BorderLayout.SOUTH);

        // Mengatur ukuran jendela
        jendela.setSize(350, 450);

        // Menempatkan jendela di tengah layar
        jendela.setLocationRelativeTo(null);

        // Menampilkan jendela struk
        jendela.setVisible(true);
    }
}
