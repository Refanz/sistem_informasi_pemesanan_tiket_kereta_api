package com.refanzzzz.model;

public class Kereta {

    private int idKereta;
    private String namaKereta;
    private String kelas;
    private int jumlahGerbong;

    public int getIdKereta() {
        return idKereta;
    }

    public void setIdKereta(int idKereta) {
        this.idKereta = idKereta;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getJumlahGerbong() {
        return jumlahGerbong;
    }

    public void setJumlahGerbong(int jumlahGerbong) {
        this.jumlahGerbong = jumlahGerbong;
    }

    @Override
    public String toString() {
        return "Kereta{" +
                "idKereta=" + idKereta +
                ", namaKereta='" + namaKereta + '\'' +
                ", kelas='" + kelas + '\'' +
                ", jumlahGerbong=" + jumlahGerbong +
                '}';
    }
}

