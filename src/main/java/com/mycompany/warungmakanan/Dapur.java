package com.mycompany.warungmakanan;

public class Dapur {
    private String namaDapur;
    private String status;

    public Dapur() {
        namaDapur = "Dapur Utama";
        status = "Siap digunakan";
    }

    public void tampilkanInfo() {
        System.out.println("Nama Dapur : " + namaDapur);
        System.out.println("Status     : " + status);
    }
}

