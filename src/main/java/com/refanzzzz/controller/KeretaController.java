package com.refanzzzz.controller;

import com.refanzzzz.model.Kereta;
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
public class KeretaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/daftar_kereta")
    public String daftarKereta(Model model) {
        String sql = "SELECT * FROM kereta WHERE is_delete = ?";

        List<Kereta> kereta = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kereta.class), "false");

        model.addAttribute("keretaActive", "true");
        model.addAttribute("kereta", kereta);

        return "kereta";
    }

    @GetMapping("/tambah_kereta")
    public String tambahKereta(Model model) {
        Kereta kereta = new Kereta();

        model.addAttribute("kereta", kereta);

        return "add_kereta";
    }

    @PostMapping("/proses_tambah_kereta")
    public String prosesTambahKereta(Kereta kereta) {
        String sql = "INSERT INTO kereta (nama_kereta, kelas, jumlah_gerbong, is_delete) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, kereta.getNamaKereta(), kereta.getKelas(), kereta.getJumlahGerbong(), "false");

        return "redirect:/daftar_kereta";
    }

    @GetMapping("/edit_kereta/{id_kereta}")
    public String editKereta(@PathVariable("id_kereta") int id_kereta, Model model) {
        String sql = "SELECT * FROM kereta WHERE id_kereta = ?";

        Kereta kereta = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Kereta.class), id_kereta);

        model.addAttribute("kls", kereta.getKelas());
        model.addAttribute("kereta", kereta);

        return "edit_kereta";
    }

    @PostMapping("/proses_edit_kereta")
    public String prosesEditKereta(Kereta kereta) {
        String sql = "UPDATE kereta SET nama_kereta = ?, kelas = ?, jumlah_gerbong = ? WHERE id_kereta = ?";

        jdbcTemplate.update(sql, kereta.getNamaKereta(), kereta.getKelas(), kereta.getJumlahGerbong(), kereta.getIdKereta());

        return "redirect:/daftar_kereta";
    }

    @GetMapping("/proses_hapus_kereta/{id_kereta}")
    public String prosesHapusKereta(@PathVariable("id_kereta") int id_kereta, Kereta kereta) {
        String sql = "UPDATE kereta SET is_delete = ? WHERE id_kereta = ?";

        jdbcTemplate.update(sql, "true", id_kereta);

        return "redirect:/daftar_kereta";
    }

    @GetMapping("/pulihkan_kereta")
    public String restoreKereta(Model model) {
        String sql = "SELECT * FROM kereta WHERE is_delete = ?";

        List<Kereta> kereta = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kereta.class), "true");

        model.addAttribute("kereta", kereta);
        model.addAttribute("keretaActive", "true");

        return "restore_kereta";
    }

    @GetMapping("/pulihkan_kereta/recover/{id_kereta}")
    public String pulihkanJadwal(@PathVariable("id_kereta") int id_kereta) {
        String sql = "UPDATE kereta SET is_delete = ? WHERE id_kereta = ?";

        jdbcTemplate.update(sql, "false", id_kereta);

        return "redirect:/pulihkan_kereta";
    }

    @GetMapping("/pulihkan_kereta/delete/{id_kereta}")
    public String hapusJadwalPermanen(@PathVariable("id_kereta") int id_kereta) {
        String sql = "DELETE FROM kereta WHERE id_kereta = ?";

        jdbcTemplate.update(sql, id_kereta);

        return "redirect:/pulihkan_kereta";
    }

}
