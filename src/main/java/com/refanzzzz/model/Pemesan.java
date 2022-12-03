package com.refanzzzz.model;

import javax.persistence.*;

@Entity
@Table(name = "pemesan")
public class Pemesan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idPemesan;
    private String namaPemesan;
    private String jenisKelamin;
    private String email;
    private int jumlahBeli;
    private int idTiket;

    public int getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(int idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    @Override
    public String toString() {
        return "Pemesan{" +
                "idPemesan=" + idPemesan +
                ", namaPemesan='" + namaPemesan + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                ", email='" + email + '\'' +
                ", jumlahBeli=" + jumlahBeli +
                ", idTiket=" + idTiket +
                '}';
    }
}
