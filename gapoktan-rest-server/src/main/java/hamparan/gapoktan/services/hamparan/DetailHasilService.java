package hamparan.gapoktan.services.hamparan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.hamparan.DetailHasil;
import hamparan.gapoktan.model.repository.hamparan.DetailHasilRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetailHasilService {

  @Autowired
  private DetailHasilRepository repository;

  /**
   * 
   * @param detailHasil
   * @return
   */
  public DetailHasil save(DetailHasil detailHasil) {
    return repository.save(detailHasil);
  }

  /**
   * 
   * @return
   */
  public Iterable<DetailHasil> findAll() {
    return repository.findAll();
  }

  public Iterable<DetailHasil> findDetailHasilByKodeAnggota(String kd_anggota) {
    return repository.findDetailHasilByKodeAnggota(kd_anggota);
  }

  public DetailHasil findDetailHasilByKodeHasil(String kd_hasil) {
    return repository.findDetailHasilByKodeHasil(kd_hasil);
  }

  /**
   * Fungsi berikut belum digunakan sejauh ini.
   * fungsi ini menggunakan perintah join table untuk mendapatkan data yang
   * dibutuhkan oleh user.
   * 
   * @param kd_anggota
   * @param kd_kebun
   * @param kd_hasil
   * @return
   */
  public DetailHasil findDetailHasilPetani(String kd_anggota, String kd_kebun, String kd_hasil) {
    return repository.findDetailHasilPetani(kd_anggota, kd_kebun, kd_hasil);
  }

  /**
   * Fungsi berikut belum digunakan sejauh ini.
   * fungsi ini mengunakan perintah join table untuk mendapatkan data yang
   * dibutuhkan oleh user.
   * 
   * @param kd_anggota
   * @param kd_mobil
   * @param kd_hasil
   * @return
   */
  public DetailHasil findDetailHasilMobil(String kd_anggota, String kd_mobil, String kd_hasil) {
    return repository.findDetailHasilMobil(kd_anggota, kd_mobil, kd_hasil);
  }

}
