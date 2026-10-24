
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.posmiegacoan;

import java.util.Scanner; // Untuk membaca input dari keyboard
import java.text.NumberFormat; // Untuk mengatur format angka uang
import java.util.Locale; // Untuk menggunakan format angka Indonesia

// CLASS UTAMA: tempat program LevelUp POS dijalankan
public class Posmiegacoan {

    // METHOD MAIN: bagian pertama yang dijalankan Java
    public static void main(String[] args) {

        // OBJECT: membuat Scanner untuk membaca input pengguna
        Scanner input = new Scanner(System.in);

        // OBJECT: membuat objek untuk menampilkan dan memilih menu
        daftarmenu menu = new daftarmenu();

        // OBJECT: membuat objek untuk menyimpan riwayat transaksi
        riwayattransaksi riwayat = new riwayattransaksi();

        // Mengatur tampilan angka uang menggunakan format Indonesia
        NumberFormat formatRupiah =
                NumberFormat.getInstance(new Locale("id", "ID"));

        // ATRIBUT LOKAL: menyimpan pilihan menu utama pengguna
        int pilihan;

        // DO-WHILE: menu utama terus ditampilkan sampai pengguna memilih 4
        do {
            System.out.println("\n===== kedai mie level =====");
            System.out.println("1. Lihat Daftar Menu");
            System.out.println("2. Transaksi Baru");
            System.out.println("3. Lihat Riwayat Transaksi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();

            // SWITCH: menjalankan perintah sesuai pilihan pengguna
            switch (pilihan) {

                // PILIHAN 1: menampilkan semua produk
                case 1:
                    menu.tampilkanSemua();
                    break;

                // PILIHAN 2: memulai transaksi baru
                case 2: {

                    // PBO: membuat objek keranjang untuk transaksi baru
                    transaksi keranjang = new transaksi();

                    // Nilai 1 berarti pengguna masih memilih produk
                    int tambahLagi = 1;

                    // Menandai apakah pengguna kembali ke menu utama
                    boolean kembaliMenuUtama = false;

                    // WHILE: mengulang proses pemilihan produk
                    while (tambahLagi == 1) {

                        System.out.println("\n===== PILIH KATEGORI =====");
                        System.out.println("1. Makanan");
                        System.out.println("2. Minuman");
                        System.out.println("0. Kembali ke Menu Utama");
                        System.out.print("Pilih kategori: ");

                        int kategori = input.nextInt();

                        // Jika memilih 0, pengguna ingin kembali ke menu utama
                        if (kategori == 0) {

                            // Jika keranjang kosong, transaksi dapat dihentikan
                            if (keranjang.isKosong()) {
                                kembaliMenuUtama = true;
                                break;
                            }

                            // Jika keranjang berisi produk, pengguna memilih
                            // apakah ingin membayar atau melanjutkan pemesanan
                            System.out.println(
                                    "Keranjang masih berisi pesanan.");
                            System.out.println("1. Lanjut ke pembayaran");
                            System.out.println("2. Kembali memilih produk");
                            System.out.print("Pilih: ");

                            int pilihanKembali = input.nextInt();

                            if (pilihanKembali == 1) {
                                // Mengakhiri pemilihan produk
                                tambahLagi = 2;
                                break;

                            } else if (pilihanKembali == 2) {
                                // Kembali ke proses memilih kategori
                                continue;

                            } else {
                                System.out.println(
                                        "Pilihan tidak tersedia.");
                                continue;
                            }
                        }

                        // Memeriksa apakah kategori yang dipilih tersedia
                        if (kategori != 1 && kategori != 2) {
                            System.out.println(
                                    "Kategori tidak tersedia.");
                            continue;
                        }

                        // Menampilkan produk sesuai kategori pilihan
                        menu.tampilkanKategori(kategori);

                        System.out.println(
                                "0. Kembali ke Pilihan Kategori");
                        System.out.print("Pilih nomor produk: ");

                        int nomorProduk = input.nextInt();

                        // Angka 0 digunakan untuk kembali memilih kategori
                        if (nomorProduk == 0) {
                            continue;
                        }

                        // Memanggil method untuk mengambil produk yang dipilih
                        // berdasarkan kategori dan nomor produk
                        produk item = menu.getProdukByKategori(
                                kategori, nomorProduk);

                        // Jika produk tidak ditemukan, kembali memilih
                        if (item == null) {
                            System.out.println(
                                    "Nomor produk tidak tersedia.");
                            continue;
                        }

                        // Meminta jumlah produk yang ingin dibeli
                        System.out.print(
                                "Masukkan jumlah " + item.getNama() + ": ");

                        int jumlah = input.nextInt();

                        // Meminta keranjang menambahkan produk dan jumlahnya
                        // Nilai boolean menunjukkan berhasil atau tidak
                        boolean berhasil =
                                keranjang.tambahProduk(item, jumlah);

                        if (berhasil) {
                            System.out.println(
                                    "Produk berhasil ditambahkan.");

                            System.out.println(
                                    "1. Tambah produk lain");
                            System.out.println(
                                    "0. Selesai memilih dan bayar");
                            System.out.print("Pilih: ");

                            int pilihanTambah = input.nextInt();

                            if (pilihanTambah == 1) {
                                // Melanjutkan pemilihan produk
                                tambahLagi = 1;

                            } else if (pilihanTambah == 0) {
                                // Mengakhiri pemilihan dan menuju pembayaran
                                tambahLagi = 2;

                            } else {
                                System.out.println(
                                        "Pilihan tidak tersedia. "
                                        + "Kembali memilih kategori.");

                                // Tetap berada dalam proses pemilihan
                                tambahLagi = 1;
                            }

                        } else {
                            // Ditampilkan jika produk gagal ditambahkan
                            System.out.println(
                                    "Produk belum ditambahkan.");
                        }
                    }

                    // Jika pengguna kembali atau keranjang kosong,
                    // proses transaksi dihentikan
                    if (kembaliMenuUtama || keranjang.isKosong()) {
                        System.out.println(
                                "Transaksi belum dilakukan.");
                        break;
                    }

                    // Menampilkan semua produk dalam keranjang
                    System.out.println("\n===== RINGKASAN PESANAN =====");
                    keranjang.tampilkanKeranjang();

                    // Menghitung total harga seluruh pesanan
                    double total = keranjang.hitungTotal();

                    // Variabel untuk menyimpan jumlah uang yang dibayarkan
                    double bayar = 0;

                    // Menandai apakah pembayaran berhasil
                    boolean pembayaranBerhasil = false;

                    // Menandai apakah transaksi dibatalkan
                    boolean dibatalkan = false;

                    // Mengulang pembayaran sampai berhasil atau dibatalkan
                    while (!pembayaranBerhasil && !dibatalkan) {

                        System.out.println(
                                "Total belanja: Rp"
                                + formatRupiah.format(total));

                        System.out.println(
                                "Masukkan 0 untuk membatalkan transaksi.");
                        System.out.print("Uang bayar: Rp");

                        bayar = input.nextDouble();

                        // Angka 0 digunakan untuk membatalkan transaksi
                        if (bayar == 0) {
                            dibatalkan = true;

                            System.out.println(
                                    "Transaksi dibatalkan.");
                            continue;
                        }

                        // IF: memeriksa agar pembayaran tidak negatif
                        if (bayar < 0) {
                            System.out.println(
                                    "Pembayaran tidak boleh negatif.");
                            continue;
                        }

                        // Memeriksa apakah uang bayar kurang dari total
                        if (bayar < total) {
                            double kekurangan = total - bayar;

                            System.out.println(
                                    "Uang pembayaran kurang.");

                            System.out.println(
                                    "Kekurangan: Rp"
                                    + formatRupiah.format(kekurangan));

                            // Meminta pengguna memasukkan pembayaran lagi
                            continue;
                        }

                        // Jika pemeriksaan lolos, pembayaran dinyatakan berhasil
                        pembayaranBerhasil = true;
                    }

                    // Menyelesaikan transaksi hanya jika pembayaran berhasil
                    if (pembayaranBerhasil) {

                        // Menghitung uang kembalian
                        double kembalian = bayar - total;

                        // Mengurangi stok produk yang telah dibayar
                        keranjang.kurangiStokSetelahBayar();

                        // Menampilkan struk pembayaran di terminal
                        System.out.println(
                                "\n===== STRUK PEMBAYARAN =====");

                        keranjang.tampilkanKeranjang();

                        System.out.println(
                                "Total Bayar: Rp"
                                + formatRupiah.format(total));

                        System.out.println(
                                "Uang Diterima: Rp"
                                + formatRupiah.format(bayar));

                        System.out.println(
                                "Kembalian: Rp"
                                + formatRupiah.format(kembalian));

                        System.out.println("Transaksi berhasil!");

// PBO: membuat objek dari class cetakstruk
cetakstruk cetak = new cetakstruk();

// Menampilkan struk pembayaran langsung di terminal
System.out.println(
        keranjang.buatTeksStruk(
                total, bayar, kembalian));

                        // Menyimpan total, pembayaran, dan kembalian
                        // ke dalam objek riwayat transaksi
                        riwayat.simpanTransaksi(
                                total, bayar, kembalian);

                        System.out.println(
                                "============================");

                    } else if (dibatalkan) {

                        // Stok tidak dikurangi jika transaksi dibatalkan
                        System.out.println("Stok tidak berubah.");
                    }

                    break;
                }

                // PILIHAN 3: menampilkan riwayat transaksi
                case 3:
                    riwayat.tampilkanRiwayat();
                    break;

                // PILIHAN 4: keluar dari aplikasi
                case 4:
                    System.out.println(
                            "Terima kasih telah menggunakan LevelUp POS.");
                    break;

                // DEFAULT: menangani pilihan menu yang tidak tersedia
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 4);

        // Menutup Scanner setelah program selesai
        input.close();
    }
}
