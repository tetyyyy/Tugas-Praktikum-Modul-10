package com.mycompany.warungmakanan;

public class DataTidakDitemukanException extends Exception {
    public DataTidakDitemukanException(String pesan) {
        super(pesan);
    }
}