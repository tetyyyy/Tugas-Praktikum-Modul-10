package com.mycompany.warungmakanan;

public class DataTidakValidException extends Exception {
    public DataTidakValidException(String pesan) {
        super(pesan);
    }
}