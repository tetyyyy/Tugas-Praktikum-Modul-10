package com.mycompany.warungmakanan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WarungMakanan {

    // COMPOSITION
    // Dapur dibuat langsung di dalam constructor WarungMakanan.
    private final Dapur dapur;

    // AGGREGATION
    // WarungMakanan mengelola objek Produk dan Pelanggan.
    private final ArrayList<Produk> daftarProduk;
    private final HashMap<String, Pelanggan> dataPelanggan;

    // Constructor
    public WarungMakanan() {
        dapur = new Dapur();
        daftarProduk = new ArrayList<>();
        dataPelanggan = new HashMap<>();
    }

    // Menambahkan produk
    public void tambahProduk(Produk produk)
            throws DataTidakValidException {

        if (produk == null) {
            throw new DataTidakValidException(
                    "Produk tidak boleh kosong!"
            );
        }

        for (Produk p : daftarProduk) {
            if (p.getKode().equalsIgnoreCase(produk.getKode())) {
                throw new DataTidakValidException(
                        "Kode produk sudah digunakan!"
                );
            }
        }

        daftarProduk.add(produk);
        System.out.println("Produk berhasil ditambahkan.");
    }

    // Menambahkan pelanggan
    public void tambahPelanggan(Pelanggan pelanggan)
            throws DataTidakValidException {

        if (pelanggan == null) {
            throw new DataTidakValidException(
                    "Pelanggan tidak boleh kosong!"
            );
        }

        if (dataPelanggan.containsKey(pelanggan.getId())) {
            throw new DataTidakValidException(
                    "ID pelanggan sudah terdaftar!"
            );
        }

        dataPelanggan.put(pelanggan.getId(), pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan.");
    }

    // Mencari produk
    public Produk cariProduk(String kode)
            throws DataTidakDitemukanException {

        for (Produk produk : daftarProduk) {
            if (produk.getKode().equalsIgnoreCase(kode.trim())) {
                return produk;
            }
        }

        throw new DataTidakDitemukanException(
                "Produk dengan kode " + kode + " tidak ditemukan!"
        );
    }

    // Mencari pelanggan
    public Pelanggan cariPelanggan(String id)
            throws DataTidakDitemukanException {

        Pelanggan pelanggan = dataPelanggan.get(id.trim());

        if (pelanggan == null) {
            throw new DataTidakDitemukanException(
                    "Pelanggan dengan ID " + id + " tidak ditemukan!"
            );
        }

        return pelanggan;
    }

    // Menampilkan semua produk
    public void tampilkanSemuaProduk() {
        System.out.println("\n=== DAFTAR PRODUK ===");

        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk.");
            return;
        }

        for (Produk produk : daftarProduk) {
            produk.tampilkanInfo();
            System.out.println("--------------------");
        }

        System.out.println("Jumlah produk: " + daftarProduk.size());
    }

    // Menampilkan semua pelanggan
    public void tampilkanSemuaPelanggan() {
        System.out.println("\n=== DAFTAR PELANGGAN ===");

        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan.");
            return;
        }

        for (Pelanggan pelanggan : dataPelanggan.values()) {
            pelanggan.tampilkanInfo();
            System.out.println("--------------------");
        }

        System.out.println(
                "Jumlah pelanggan: " + dataPelanggan.size()
        );
    }

    // Menampilkan komponen Composition
    public void tampilkanDapur() {
        System.out.println("\n=== INFORMASI DAPUR ===");
        dapur.tampilkanInfo();
    }

    // ASSOCIATION
    // Transaksi menggunakan objek Pelanggan dan Produk.
    public void lakukanTransaksi(String id, String kode) {
        try {
            Pelanggan pelanggan = cariPelanggan(id);
            Produk produk = cariProduk(kode);

            Transaksi transaksi = new Transaksi();
            transaksi.prosesTransaksi(pelanggan, produk);

        } catch (DataTidakDitemukanException
                | DataTidakValidException e) {
            System.out.println("Transaksi gagal: " + e.getMessage());
        }
    }

    // Program utama
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WarungMakanan warung = new WarungMakanan();

        // Objek Produk dibuat di luar objek WarungMakanan.
        // Lima objek utama.
        try {
            warung.tambahProduk(
                    new Makanan("M001", "Nasi Goreng", 15000, 2));
            warung.tambahProduk(
                    new Makanan("M002", "Mie Ayam", 12000, 1));
            warung.tambahProduk(
                    new Makanan("M003", "Bakso", 13000, 2));
            warung.tambahProduk(
                    new Makanan("M004", "Ayam Geprek", 18000, 1));
            warung.tambahProduk(
                    new Makanan("M005", "Soto Ayam", 14000, 3));

            // Tiga objek pendukung Pelanggan.
            warung.tambahPelanggan(
                    new Pelanggan("P001", "Andi"));
            warung.tambahPelanggan(
                    new Pelanggan("P002", "Budi"));
            warung.tambahPelanggan(
                    new Pelanggan("P003", "Siti"));

        } catch (DataTidakValidException e) {
            System.out.println(e.getMessage());
        }

        boolean jalan = true;

        while (jalan) {
            try {
                System.out.println("\n================================");
                System.out.println(" SISTEM PEMESANAN WARUNG MAKANAN");
                System.out.println("================================");
                System.out.println("1. Informasi Dapur");
                System.out.println("2. Tampilkan Semua Produk");
                System.out.println("3. Cari Produk");
                System.out.println("4. Tambah Produk");
                System.out.println("5. Tampilkan Semua Pelanggan");
                System.out.println("6. Tambah Pelanggan");
                System.out.println("7. Cari Pelanggan");
                System.out.println("8. Lakukan Transaksi");
                System.out.println("0. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        warung.tampilkanDapur();
                        break;

                    case 2:
                        warung.tampilkanSemuaProduk();
                        break;

                    case 3:
                        System.out.print("Kode produk: ");
                        String kodeCari = scanner.nextLine();

                        try {
                            warung.cariProduk(kodeCari).tampilkanInfo();
                        } catch (DataTidakDitemukanException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("Kode produk: ");
                        String kode = scanner.nextLine();

                        System.out.print("Nama produk: ");
                        String nama = scanner.nextLine();

                        System.out.print("Harga produk: ");
                        double harga = scanner.nextDouble();

                        System.out.print("Jumlah pesanan: ");
                        int jumlah = scanner.nextInt();
                        scanner.nextLine();

                        if (kode.trim().isEmpty()
                                || nama.trim().isEmpty()
                                || harga <= 0 || jumlah <= 0) {
                            System.out.println("Data produk tidak valid.");
                            break;
                        }

                        warung.tambahProduk(
                                new Makanan(kode, nama, harga, jumlah)
                        );
                        break;

                    case 5:
                        warung.tampilkanSemuaPelanggan();
                        break;

                    case 6:
                        System.out.print("ID pelanggan: ");
                        String idBaru = scanner.nextLine();

                        System.out.print("Nama pelanggan: ");
                        String namaBaru = scanner.nextLine();

                        if (idBaru.trim().isEmpty()
                                || namaBaru.trim().isEmpty()) {
                            System.out.println(
                                    "ID dan nama tidak boleh kosong."
                            );
                            break;
                        }

                        warung.tambahPelanggan(
                                new Pelanggan(idBaru, namaBaru)
                        );
                        break;

                    case 7:
                        System.out.print("ID pelanggan: ");
                        String idCari = scanner.nextLine();

                        try {
                            warung.cariPelanggan(idCari).tampilkanInfo();
                        } catch (DataTidakDitemukanException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 8:
                        warung.tampilkanSemuaPelanggan();
                        System.out.print("ID pelanggan: ");
                        String idTransaksi = scanner.nextLine();

                        warung.tampilkanSemuaProduk();
                        System.out.print("Kode produk: ");
                        String kodeTransaksi = scanner.nextLine();

                        warung.lakukanTransaksi(
                                idTransaksi, kodeTransaksi
                        );
                        break;

                    case 0:
                        jalan = false;
                        System.out.println("Program selesai.");
                        break;

                    default:
                        System.out.println("Pilihan menu tidak valid.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus sesuai tipe data.");
                scanner.nextLine();

            } catch (DataTidakValidException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
