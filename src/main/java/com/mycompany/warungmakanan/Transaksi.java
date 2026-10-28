package com.mycompany.warungmakanan;

public class Transaksi {

    public void prosesTransaksi(Pelanggan pelanggan, Produk produk)
            throws DataTidakValidException {

        if (pelanggan == null || produk == null) {
            throw new DataTidakValidException(
                "Pelanggan atau produk tidak valid."
            );
        }

        System.out.println("\n===== TRANSAKSI BERHASIL =====");
        System.out.println("ID Pelanggan : " + pelanggan.getId());
        System.out.println("Nama         : " + pelanggan.getNama());
        System.out.println("Produk       : " + produk.getNama());
        System.out.printf("Harga        : Rp%.0f%n", produk.getHarga());
        System.out.println("Status       : Berhasil");
        System.out.println("==============================");
    }
}
