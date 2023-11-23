package hamparan.gapoktan.services.hamparan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.helpers.GenerateKode;
import hamparan.gapoktan.model.entities.hamparan.Harga;
import hamparan.gapoktan.model.entities.hamparan.Hasil;
import hamparan.gapoktan.model.repository.hamparan.HasilRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HasilService {

  @Autowired
  private HasilRepository repository;

  @Autowired
  private HargaService hargaService;

  private GenerateKode generateKode = new GenerateKode();

  public Hasil save(Hasil hasil) {
    /** Ambil harga terbaru */
    Harga harga = hargaService.findLastHarga();

    if (hasil.getKd_hasil() != null) {
      hasil = hitungHasil(hasil, harga);
      return repository.save(hasil);
    } else {
      /** Generate Kode Hasil */
      hasil.setKd_hasil(
          generateKode.generateKodeHasil(hasil.getGolongan(), repository.findMaxKodeHasil(hasil.getGolongan())));

      hasil = hitungHasil(hasil, harga);
      return repository.save(hasil);
    }
  }

  public Iterable<Hasil> findAll() {
    return repository.findAll();
  }

  public Hasil findOneHasilByKode(String kd_hasil) {
    return repository.findOneHasilByKode(kd_hasil);
  }

  private Hasil hitungHasil(Hasil hasil, Harga harga) {
    switch (hasil.getGolongan()) {
      case "Petani":
        /** Harga */
        hasil.setKd_harga(harga.getKd_harga());

        /** Potongan Petani */
        hasil.setPotongan_mobil(harga.getMobil() * hasil.getTonase());
        hasil.setPotongan_pemuat(harga.getPemuat() * hasil.getTonase());
        hasil.setPotongan_pengurus(harga.getPengurus() * hasil.getTonase());
        hasil.setPotongan_penimbang(harga.getPenimbang() * hasil.getTonase());
        hasil.setPotongan_pajak((int) ((harga.getPajak() / 100) * hasil.getTonase() * harga.getSawit()));

        /** Total Potongan Petani */
        hasil.setTotal_potongan(
            hasil.getPotongan_mobil() +
                hasil.getPotongan_pemuat() +
                hasil.getPotongan_penimbang() +
                hasil.getPotongan_pengurus() +
                hasil.getPotongan_pajak());

        /** Hasil Bersih Petani */
        hasil.setTotal_hasil((harga.getSawit() * hasil.getTonase()) - hasil.getTotal_potongan());
        break;
      case "Mobil":
        hasil.setKd_harga(harga.getKd_harga());
        hasil.setTotal_hasil(harga.getMobil() * hasil.getTonase());
        break;
      case "Pemuat":
        hasil.setKd_harga(harga.getKd_harga());
        hasil.setTotal_hasil(harga.getPemuat() * hasil.getTonase());
        break;
      case "Penimbang":
        hasil.setKd_harga(harga.getKd_harga());
        hasil.setTotal_hasil(harga.getPenimbang() * hasil.getTonase());
        break;
    }
    return hasil;
  }

}
