package com.refanzzzz.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Jadwal extends Kereta {

    private int idJadwal;
    private Timestamp waktuBerangkat;
    private Timestamp waktuTiba;
    private String stasiunAsal;
    private String stasiunTujuan;

    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Timestamp getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(Timestamp waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public Timestamp getWaktuTiba() {
        return waktuTiba;
    }

    public void setWaktuTiba(Timestamp waktuTiba) {
        this.waktuTiba = waktuTiba;
    }

    public String getStasiunAsal() {
        return stasiunAsal;
    }

    public void setStasiunAsal(String stasiunAsal) {
        this.stasiunAsal = stasiunAsal;
    }

    public String getStasiunTujuan() {
        return stasiunTujuan;
    }

    public void setStasiunTujuan(String stasiunTujuan) {
        this.stasiunTujuan = stasiunTujuan;
    }

    @Override
    public String toString() {
        return "Jadwal{" +
                "idJadwal=" + idJadwal +
                ", waktuBerangkat=" + waktuBerangkat +
                ", waktuTiba=" + waktuTiba +
                ", stasiunAsal='" + stasiunAsal + '\'' +
                ", stasiunTujuan='" + stasiunTujuan + '\'' +
                '}';
    }
}
