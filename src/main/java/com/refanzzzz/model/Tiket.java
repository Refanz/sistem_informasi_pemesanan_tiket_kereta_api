package com.refanzzzz.model;

import java.sql.Timestamp;

public class Tiket extends Jadwal {

    private int idTiket;
    private int noKursi;
    private int stok;
    private int harga;

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public int getNoKursi() {
        return noKursi;
    }

    public void setNoKursi(int noKursi) {
        this.noKursi = noKursi;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "Tiket{" +
                "idTiket=" + idTiket +
                ", noKursi=" + noKursi +
                ", stok=" + stok +
                ", harga=" + harga +
                '}';
    }
}
