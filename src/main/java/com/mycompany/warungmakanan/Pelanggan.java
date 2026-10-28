package com.mycompany.warungmakanan;

public class Pelanggan {
    private String id;
    private String nama;

    public Pelanggan(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanInfo() {
        System.out.println("ID Pelanggan : " + id);
        System.out.println("Nama         : " + nama);
    }
}