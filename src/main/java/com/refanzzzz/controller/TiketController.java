package com.refanzzzz.controller;

import com.refanzzzz.model.Tiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TiketController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/pesan_tiket")
    public String pesanTiket(Model model) {
        String sql = "SELECT A.no_kursi, A.stok, A.harga, B.stasiun_asal, B.stasiun_tujuan, B.waktu_berangkat, B.waktu_tiba FROM tiket A INNER JOIN jadwal B ON A.id_jadwal = B.id_jadwal";
        List<Tiket> tiket = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Tiket.class));
        model.addAttribute("tiket", tiket);
        model.addAttribute("pesanActive", "true");
        return "daftar_jadwal";
    }
}
