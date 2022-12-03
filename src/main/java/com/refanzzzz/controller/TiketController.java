package com.refanzzzz.controller;

import com.refanzzzz.model.Pemesan;
import com.refanzzzz.model.Tiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TiketController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/pesan_tiket")
    public String pesanTiket(Model model) {
        String sql = "SELECT A.id_tiket, A.no_kursi, A.stok, A.harga, B.stasiun_asal, B.stasiun_tujuan, B.waktu_berangkat, B.waktu_tiba, C.nama_kereta, C.kelas FROM tiket A " +
                "INNER JOIN jadwal B ON A.id_jadwal = B.id_jadwal INNER JOIN kereta C ON B.id_kereta = C.id_kereta";

        List<Tiket> tiket = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Tiket.class));

        model.addAttribute("tiket", tiket);
        model.addAttribute("pesanActive", "true");

        return "daftar_jadwal";
    }

    @GetMapping("/order_form/{id_tiket}")
    public String ambilTiket(@PathVariable("id_tiket") int id_tiket, Model model) {
        String sql = "SELECT A.id_tiket, A.stok, A.harga, B.stasiun_asal, B.stasiun_tujuan, B.waktu_berangkat, B.waktu_tiba, C.nama_kereta, C.kelas FROM tiket A " +
                "INNER JOIN jadwal B ON A.id_jadwal = B.id_jadwal INNER JOIN kereta C ON B.id_kereta = C.id_kereta WHERE id_tiket = ?";

        Tiket tiket = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Tiket.class), id_tiket);

        Pemesan pemesan = new Pemesan();
        pemesan.setIdTiket(id_tiket);

        model.addAttribute("tiket", tiket);
        model.addAttribute("pemesan", pemesan);

        return "order_form";
    }

    @PostMapping("/order_form")
    public String tambahTiket(Pemesan pemesan) {
        String sql = "INSERT INTO pemesan (nama_pemesan, jenis_kelamin, email, jumlah_beli, id_tiket) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, pemesan.getNamaPemesan(), pemesan.getJenisKelamin(), pemesan.getEmail(), pemesan.getJumlahBeli(), pemesan.getIdTiket());

        return "index";
    }

    //TODO membuat payment form

//    @GetMapping("/payment_form")
//    public String pembayaran(@PathVariable("id_pemesan") int id_pemesanan, Model model) {
//        String sql = "SELECT A.id_pemesan, A.nama_pemesan, A.jenis_kelamin, A.email, A.jumlah_beli, A.id_tiket."
//    }

}
