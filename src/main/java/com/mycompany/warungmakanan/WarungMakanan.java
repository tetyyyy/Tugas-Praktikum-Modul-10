
package com.mycompany.warungmakanan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WarungMakanan {

    // ArrayList bertipe Superclass Produk
    private static final ArrayList<Produk> daftarProduk =
            new ArrayList<>();

    // HashMap dengan ID sebagai Key dan Pelanggan sebagai Value
    private static final HashMap<String, Pelanggan> dataPelanggan =
            new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean jalan = true;

        // Lima data awal untuk ArrayList
        daftarProduk.add(new Makanan("M001", "Nasi Goreng", 15000, 2));
        daftarProduk.add(new Makanan("M002", "Mie Ayam", 12000, 1));
        daftarProduk.add(new Makanan("M003", "Bakso", 13000, 2));
        daftarProduk.add(new Makanan("M004", "Ayam Geprek", 18000, 1));
        daftarProduk.add(new Makanan("M005", "Soto Ayam", 14000, 3));

        // Tiga data awal untuk HashMap
        dataPelanggan.put("P001",
                new Pelanggan("P001", "Andi"));
        dataPelanggan.put("P002",
                new Pelanggan("P002", "Budi"));
        dataPelanggan.put("P003",
                new Pelanggan("P003", "Siti"));

        try {
            while (jalan) {
                try {
                    System.out.println("\n================================");
                    System.out.println(" SISTEM PEMESANAN WARUNG MAKANAN");
                    System.out.println("================================");
                    System.out.println("1. Tambah Pesanan Makanan");
                    System.out.println("2. Tampilkan Semua Pesanan");
                    System.out.println("3. Cari Pesanan");
                    System.out.println("4. Tambah Pelanggan");
                    System.out.println("5. Cari Pelanggan");
                    System.out.println("6. Tampilkan Semua Pelanggan");
                    System.out.println("0. Keluar");
                    System.out.print("Pilih menu: ");

                    int pilihan = scanner.nextInt();
                    scanner.nextLine();

                    switch (pilihan) {
                        case 1:
                            tambahMakanan(scanner);
                            break;

                        case 2:
                            tampilkanSemuaProduk();
                            break;

                        case 3:
                            System.out.print("Masukkan kode makanan: ");
                            String kode = scanner.nextLine();
                            cariProduk(kode);
                            break;

                        case 4:
                            tambahPelanggan(scanner);
                            break;

                        case 5:
                            System.out.print("Masukkan ID pelanggan: ");
                            String id = scanner.nextLine();
                            cariPelanggan(id);
                            break;

                        case 6:
                            tampilkanSemuaPelanggan();
                            break;

                        case 0:
                            jalan = false;
                            System.out.println("Program selesai.");
                            break;

                        default:
                            System.out.println("Pilihan menu tidak valid.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println(
                            "Error: Input harus sesuai tipe data!");
                    scanner.nextLine();

                } catch (DataTidakValidException
                        | DataTidakDitemukanException e) {
                    System.out.println("Error: " + e.getMessage());

                } finally {
                    System.out.println(
                            "Finally: Proses menu telah selesai.");
                }
            }
        } finally {
            scanner.close();
            System.out.println("Scanner ditutup. Program berakhir.");
        }
    }

    // Menambahkan makanan dengan validasi
    public static void tambahMakanan(Scanner scanner)
            throws DataTidakValidException {

        System.out.print("Masukkan kode makanan: ");
        String kode = scanner.nextLine().trim();

        System.out.print("Masukkan nama makanan: ");
        String nama = scanner.nextLine().trim();

        System.out.print("Masukkan harga makanan: ");
        double harga = scanner.nextDouble();

        System.out.print("Masukkan jumlah pesanan: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        if (kode.isEmpty() || nama.isEmpty()) {
            throw new DataTidakValidException(
                    "Kode dan nama makanan tidak boleh kosong!");
        }

        if (harga <= 0 || jumlah <= 0) {
            throw new DataTidakValidException(
                    "Harga dan jumlah harus lebih dari nol!");
        }

        for (Produk produk : daftarProduk) {
            if (produk.getKode().equalsIgnoreCase(kode)) {
                throw new DataTidakValidException(
                        "Kode makanan sudah digunakan!");
            }
        }

        Makanan makanan = new Makanan(
                kode, nama, harga, jumlah);

        daftarProduk.add(makanan);

        System.out.println("Pesanan berhasil ditambahkan.");
        System.out.println("Total harga: Rp" + makanan.hitungTotal());
    }

    // Menampilkan seluruh isi ArrayList
    public static void tampilkanSemuaProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        System.out.println("\n=== DAFTAR PESANAN MAKANAN ===");

        for (int i = 0; i < daftarProduk.size(); i++) {
            System.out.println("Pesanan ke-" + (i + 1));
            daftarProduk.get(i).tampilkanInfo();
        }

        System.out.println(
                "Jumlah jenis makanan: " + daftarProduk.size());
    }

    // Mencari makanan dengan throw dan throws
    public static void cariProduk(String kode)
            throws DataTidakDitemukanException {

        for (Produk produk : daftarProduk) {
            if (produk.getKode().equalsIgnoreCase(kode.trim())) {
                System.out.println("Data makanan ditemukan:");
                produk.tampilkanInfo();
                return;
            }
        }

        throw new DataTidakDitemukanException(
                "Makanan dengan kode " + kode + " tidak ditemukan!");
    }

    // Menambahkan pelanggan ke HashMap
    public static void tambahPelanggan(Scanner scanner)
            throws DataTidakValidException {

        System.out.print("Masukkan ID pelanggan: ");
        String id = scanner.nextLine().trim();

        System.out.print("Masukkan nama pelanggan: ");
        String nama = scanner.nextLine().trim();

        if (id.isEmpty() || nama.isEmpty()) {
            throw new DataTidakValidException(
                    "ID dan nama pelanggan tidak boleh kosong!");
        }

        if (dataPelanggan.containsKey(id)) {
            throw new DataTidakValidException(
                    "ID pelanggan sudah terdaftar!");
        }

        Pelanggan pelanggan = new Pelanggan(id, nama);
        dataPelanggan.put(id, pelanggan);

        System.out.println("Pelanggan berhasil ditambahkan.");
    }

    // Mencari pelanggan menggunakan Key HashMap
    public static void cariPelanggan(String id)
            throws DataTidakDitemukanException {

        if (dataPelanggan.containsKey(id.trim())) {
            Pelanggan pelanggan = dataPelanggan.get(id.trim());

            System.out.println("Pelanggan ditemukan:");
            pelanggan.tampilkanInfo();
        } else {
            throw new DataTidakDitemukanException(
                    "Pelanggan dengan ID " + id
                    + " tidak ditemukan!");
        }
    }

    // Menampilkan semua Value dari HashMap
    public static void tampilkanSemuaPelanggan() {
        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }

        System.out.println("\n=== DAFTAR PELANGGAN ===");

        for (String id : dataPelanggan.keySet()) {
            Pelanggan pelanggan = dataPelanggan.get(id);
            pelanggan.tampilkanInfo();
            System.out.println("----------------------------");
        }

        System.out.println(
                "Jumlah pelanggan: " + dataPelanggan.size());
    }
}
