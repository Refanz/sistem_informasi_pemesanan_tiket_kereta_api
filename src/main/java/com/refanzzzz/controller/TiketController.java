package com.refanzzzz.controller;

import com.refanzzzz.model.Pembayaran;
import com.refanzzzz.model.Pemesan;
import com.refanzzzz.model.Tiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TiketController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/pesan_tiket")
    public String pesanTiket(Model model) {
        String sql = "SELECT A.id_tiket, A.stok, A.harga, B.stasiun_asal, B.stasiun_tujuan, B.waktu_berangkat, B.waktu_tiba, C.nama_kereta, C.kelas FROM tiket A " +
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

        String sql1 = "SELECT * FROM pemesan ORDER BY id_pemesan DESC LIMIT 0 , 1";

        Pemesan pemesan = jdbcTemplate.queryForObject(sql1, BeanPropertyRowMapper.newInstance(Pemesan.class));


        Pemesan pemesan1 = new Pemesan();
        pemesan1.setIdTiket(id_tiket);

        if (pemesan == null) {
            pemesan1.setIdPemesan(1);
        } else {
            pemesan1.setIdPemesan(pemesan.getIdPemesan()+1);
        }

        model.addAttribute("tiket", tiket);
        model.addAttribute("pemesan", pemesan1);

        return "order_form";
    }

    @PostMapping("/order_form")
    public String tambahTiket(Pemesan pemesan, RedirectAttributes redirectAttributes) {
        String sql = "INSERT INTO pemesan (id_pemesan, nama_pemesan, jenis_kelamin, email, jumlah_beli, id_tiket) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, pemesan.getIdPemesan(), pemesan.getNamaPemesan(), pemesan.getJenisKelamin(), pemesan.getEmail(), pemesan.getJumlahBeli(), pemesan.getIdTiket());

        redirectAttributes.addAttribute("id_pemesan", pemesan.getIdPemesan());

        return "redirect:/payment_form";
    }

    @GetMapping("/payment_form")
    public String pembayaran(@RequestParam("id_pemesan") int id_pemesan,  Model model) {

        String sql = "SELECT A.id_pemesan, A.nama_pemesan, A.jenis_kelamin, A.email, A.jumlah_beli, A.id_tiket, B.harga, C.stasiun_asal, " +
                "C.stasiun_tujuan, C.waktu_berangkat, C.waktu_tiba, D.nama_kereta, D.kelas FROM pemesan A " +
                "INNER JOIN tiket B ON A.id_tiket = B.id_tiket " +
                "INNER JOIN jadwal C ON C.id_jadwal = B.id_jadwal " +
                "INNER JOIN kereta D ON D.id_kereta = C.id_kereta WHERE id_pemesan = ?";

        Pemesan pemesan = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Pemesan.class), id_pemesan);

        Pembayaran pembayaran1 = new Pembayaran();
        pembayaran1.setTotalBiaya(pemesan.getHarga() * pemesan.getJumlahBeli());
        pembayaran1.setIdPemesan(id_pemesan);

        model.addAttribute("pembayaran", pembayaran1);
        model.addAttribute("pemesan", pemesan);
        model.addAttribute("id_pemesan", id_pemesan);

        return "payment_form";
    }

    @PostMapping("/payment")
    public String bayar(Pembayaran pembayaran) {
        String sql = "INSERT INTO pembayaran (jenis_pembayaran, total_biaya, id_pemesan) VALUES (?, ?, ?)";
        String sql1 = "SELECT A.jumlah_beli, B.id_tiket, B.harga, B.stok FROM pemesan A INNER JOIN tiket B ON A.id_tiket = B.id_tiket WHERE id_pemesan = ?";
        String sql2 = "UPDATE tiket SET stok = ? WHERE id_tiket = ? ";

        Pemesan pemesan = jdbcTemplate.queryForObject(sql1, BeanPropertyRowMapper.newInstance(Pemesan.class), pembayaran.getIdPemesan());

        int stokSekarang = pemesan.getStok() - pemesan.getJumlahBeli();

        System.out.print(stokSekarang);

        int ttl = pemesan.getHarga() * pemesan.getJumlahBeli();

        jdbcTemplate.update(sql, pembayaran.getJenisPembayaran(), ttl, pembayaran.getIdPemesan());
        jdbcTemplate.update(sql2, stokSekarang, pemesan.getIdTiket());

        return "redirect:/payment_success";
    }

    @GetMapping("/payment_success")
    public String paymentSuccess() {
        return "payment_success";
    }


}
