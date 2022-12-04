package com.refanzzzz.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Jadwal extends Kereta {

    private int idJadwal;

    private String waktuBerangkat;

    private String waktuTiba;
    private String stasiunAsal;
    private String stasiunTujuan;
    private int idKereta;
    private boolean deleted;

    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(String waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getWaktuTiba() {
        return waktuTiba;
    }

    public void setWaktuTiba(String waktuTiba) {
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
    public int getIdKereta() {
        return idKereta;
    }

    @Override
    public void setIdKereta(int idKereta) {
        this.idKereta = idKereta;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Jadwal{" +
                "idJadwal=" + idJadwal +
                ", waktuBerangkat='" + waktuBerangkat + '\'' +
                ", waktuTiba='" + waktuTiba + '\'' +
                ", stasiunAsal='" + stasiunAsal + '\'' +
                ", stasiunTujuan='" + stasiunTujuan + '\'' +
                ", idKereta=" + idKereta +
                ", deleted=" + deleted +
                '}';
    }
}
