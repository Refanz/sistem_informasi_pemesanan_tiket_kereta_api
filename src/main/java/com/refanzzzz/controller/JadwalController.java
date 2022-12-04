package com.refanzzzz.controller;

import com.refanzzzz.model.Jadwal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JadwalController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/daftar_jadwal")
    public String jadwal(Model model) {
        String sql = "SELECT * FROM jadwal WHERE is_delete = ? ";

        List<Jadwal> jadwal = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Jadwal.class), "false");

        model.addAttribute("jadwal", jadwal);
        model.addAttribute("jadwalActive", "true");

        return "jadwal";
    }

    @GetMapping("/tambah_jadwal")
    public String tambahJadwal(Model model) {

        Jadwal jadwal = new Jadwal();

        model.addAttribute("jadwal", jadwal);

        return "add_jadwal";
    }

    @PostMapping("/proses_tambah_jadwal")
    public String prosesTambahJadwal(Jadwal jadwal) {
        String sql = "INSERT INTO jadwal (stasiun_asal, stasiun_tujuan, waktu_berangkat, waktu_tiba, id_kereta, is_delete) VALUES (?, ?, ?, ?, ?, ?)";

        String waktuBerangkat = jadwal.getWaktuBerangkat().replace("T",":");

        String waktuTiba = jadwal.getWaktuTiba().replace("T", ":");

        jdbcTemplate.update(sql, jadwal.getStasiunAsal(), jadwal.getStasiunTujuan(), waktuBerangkat, waktuTiba, jadwal.getIdKereta(), "false");

        return "redirect:/daftar_jadwal";
    }

    @GetMapping("/edit_jadwal/{id_jadwal}")
    public String editJadwal(@PathVariable("id_jadwal") int id_jadwal, Model model) {

        String sql = "SELECT * FROM jadwal WHERE id_jadwal = ?";

        Jadwal jadwal = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Jadwal.class), id_jadwal);

        model.addAttribute("jadwal", jadwal);

        return "edit_jadwal";
    }

    @PostMapping("/proses_edit_jadwal")
    public String prosesEditJadwal(Jadwal jadwal) {

        String sql = "UPDATE jadwal SET stasiun_asal = ?, stasiun_tujuan = ?, waktu_berangkat = ?, waktu_tiba = ?, id_kereta = ? WHERE id_jadwal = ?";

        jdbcTemplate.update(sql, jadwal.getStasiunAsal(), jadwal.getStasiunTujuan(), jadwal.getWaktuBerangkat(), jadwal.getWaktuTiba(), jadwal.getIdKereta(), jadwal.getIdJadwal());

        return "redirect:/daftar_jadwal";
    }

    @GetMapping("/proses_hapus_jadwal/{id_jadwal}")
    public String prosesHapusJadwal(@PathVariable("id_jadwal") int id_jadwal, Jadwal jadwal) {
        String sql = "UPDATE jadwal SET is_delete = ? WHERE id_jadwal = ?";

        jdbcTemplate.update(sql, "true", id_jadwal);

        return "redirect:/daftar_jadwal";
    }

    @GetMapping("/pulihkan_jadwal")
    public String formPemulihan(Model model) {
        String sql = "SELECT * FROM jadwal WHERE is_delete = ? ";

        List<Jadwal> jadwal = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Jadwal.class), "true");

        model.addAttribute("jadwal", jadwal);
        model.addAttribute("jadwalActive", "true");

        return "restore_jadwal";
    }

    @GetMapping("/pulihkan_jadwal/recover/{id_jadwal}")
    public String pulihkanJadwal(@PathVariable("id_jadwal") int id_jadwal) {
        String sql = "UPDATE jadwal SET is_delete = ? WHERE id_jadwal = ?";

        jdbcTemplate.update(sql, "false", id_jadwal);

        return "redirect:/daftar_jadwal";
    }

    @GetMapping("/pulihkan_jadwal/delete/{id_jadwal}")
    public String hapusJadwalPermanen(@PathVariable("id_jadwal") int id_jadwal) {
        String sql = "DELETE FROM jadwal WHERE id_jadwal = ?";

        jdbcTemplate.update(sql, id_jadwal);

        return "redirect:/pulihkan_jadwal";
    }


}
