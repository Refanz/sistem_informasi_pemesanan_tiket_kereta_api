package com.refanzzzz.model;

public class Pembayaran {

    private int idPembayaran;
    private String jenisPembayaran;
    private int totalBiaya;


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

    @Override
    public String toString() {
        return "Pembayaran{" +
                "idPembayaran=" + idPembayaran +
                ", jenisPembayaran='" + jenisPembayaran + '\'' +
                ", totalBiaya=" + totalBiaya +
                '}';
    }
}
