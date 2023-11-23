package hamparan.gapoktan.services.hamparan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.helpers.GenerateKode;
import hamparan.gapoktan.model.entities.hamparan.Mobil;
import hamparan.gapoktan.model.repository.hamparan.MobilRepository;
import hamparan.gapoktan.utils.ClearDataIntoDatabase;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MobilService {

  @Autowired
  private MobilRepository repository;

  private GenerateKode generateKode = new GenerateKode();

  public Mobil save(Mobil mobil) {
    ClearDataIntoDatabase clear = new ClearDataIntoDatabase();

    mobil.setMerk_mobil(clear.capitalize(mobil.getMerk_mobil()));

    if (mobil.getKd_mobil() != null) {
      mobil.setKd_mobil(clear.cleanKode(mobil.getKd_mobil()));
      mobil.setKd_anggota(clear.cleanKode(mobil.getKd_anggota()));

      return repository.save(mobil);
    } else {
      /** Auto Generate Kode Mobil */
      String maxKode = repository.findMaxKodeMobil();
      mobil.setKd_mobil(generateKode.generateKodeMobil(maxKode));

      return repository.save(mobil);
    }
  }

  public Mobil findByKode(String kode) {
    Optional<Mobil> mobil = repository.findById(kode);
    if (!mobil.isPresent()) {
      return null;
    } else {
      return mobil.get();
    }
  }

  public Mobil findOneMobilByKode(String kd_mobil) {
    return repository.findOneMobilByKode(kd_mobil);
  }

  public Iterable<Mobil> findAll() {
    return repository.findAll();
  }

  public void removeOne(String kode) {
    repository.deleteById(kode);
  }

  public List<Mobil> findMobilByKodeAnggota(String kode) {
    return repository.findMobilByKodeAnggota(kode);
  }

  /**
   * ini buat jadi search like pada mysql syntax.
   */
  public List<Mobil> findMobilByMerk(String merk_mobil) {
    return repository.findMobilByMerk(merk_mobil);
  }

  public Mobil findMobilByKodeMobilAndKodeAnggota(String kd_mobil, String kd_anggota) {
    return repository.findMobilByKodeMobilAndKodeAnggota(kd_mobil, kd_anggota).get();
  }

  public List<Mobil> findMobilByNomorPlat(String nomor_plat) {
    return repository.findMobilByNomorPlat("%" + nomor_plat + "%");
  }

}
