package com.refanzzzz.model;

import javax.persistence.*;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idPembayaran;
    private String jenisPembayaran;
    private int totalBiaya;
    private int idPemesan;


    public int getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(int idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public int getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(int totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public int getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(int idPemesan) {
        this.idPemesan = idPemesan;
    }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "idPembayaran=" + idPembayaran +
                ", jenisPembayaran='" + jenisPembayaran + '\'' +
                ", totalBiaya=" + totalBiaya +
                ", idPemesan=" + idPemesan +
                '}';
    }
}
