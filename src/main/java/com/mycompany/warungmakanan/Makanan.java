package com.mycompany.warungmakanan;

public class Makanan extends Produk {
    private int jumlah;

    public Makanan(String kode, String nama,
                   double harga, int jumlah) {
        super(kode, nama, harga);
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double hitungTotal() {
        return getHarga() * jumlah;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Total : Rp" + hitungTotal());
        System.out.println("----------------------------");
    }
}