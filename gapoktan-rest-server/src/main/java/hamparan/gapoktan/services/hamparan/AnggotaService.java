package hamparan.gapoktan.services.hamparan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.helpers.DefaultData;
import hamparan.gapoktan.helpers.GenerateKode;
import hamparan.gapoktan.model.entities.auth.UserRoleEnum;
import hamparan.gapoktan.model.entities.hamparan.Anggota;
import hamparan.gapoktan.model.repository.hamparan.AnggotaRepository;
import hamparan.gapoktan.utils.ClearDataIntoDatabase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AnggotaService {

  /**
   * inject repository
   */
  @Autowired
  private AnggotaRepository repository;

  private GenerateKode generateKode = new GenerateKode();

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Anggota save(Anggota anggota) {
    /**
     * Clear Data Into Database
     */
    ClearDataIntoDatabase clearData = new ClearDataIntoDatabase();

    anggota.setNama_anggota(clearData.capitalize(anggota.getNama_anggota()));
    anggota.setAlamat(clearData.capitalize(anggota.getAlamat()));
    anggota.setGolongan(clearData.capitalize(anggota.getGolongan()));

    if (anggota.getKd_anggota() != null) {
      anggota.setKd_anggota(clearData.cleanKode(anggota.getKd_anggota()));

      /**
       * Current Data Anggota
       */
      Anggota currentDataAnggota = repository.findById(anggota.getKd_anggota()).get();

      /**
       * data yang tidak dapat diubah oleh admin, beberapa data khusus bagi user.
       */
      anggota.setIs_active(currentDataAnggota.getIs_active());
      anggota.setPassword(currentDataAnggota.getPassword());
      anggota.setImage(currentDataAnggota.getImage());
      anggota.setRole(currentDataAnggota.getRole());
      anggota.setDate_created(currentDataAnggota.getDate_created());

      return repository.save(anggota);
    } else {
      /**
       * Auto Generate Kode Anggota Hamparan
       * Generate kode berdasarkan golongan anggota.
       */
      anggota.setKd_anggota(generateKode.generateKodeAnggota(anggota.getGolongan(),
          repository.findKodeByGolongan(anggota.getGolongan())));

      DefaultData defaultData = new DefaultData();

      /** Set Default & Encrypt Password */
      anggota
          .setPassword(defaultData.setDefaultPassword(anggota.getPassword(), anggota.getKd_anggota()));

      String encodedPassword = bCryptPasswordEncoder.encode(anggota.getPassword());
      anggota.setPassword(encodedPassword);

      /**
       * Generated Date Created
       * set timestamp date_created pendaftaran anggota baru
       */
      anggota.setDate_created(defaultData.defaultDateCreated(anggota));

      /**
       * Set Default User Image
       * set gambar profil user default ketika dilakukan pendaftaran anggota baru
       */
      anggota.setImage(defaultData.defaultImage(anggota));

      /**
       * Set Default Role
       * set role user ketika dilakukan pendaftaran anggota
       */
      if (anggota.getGolongan().equals("Pengurus")) {
        anggota.setRole(UserRoleEnum.ADMIN.name());
      } else {
        anggota.setRole(UserRoleEnum.USER.name());
      }

      /**
       * Set Default Is Active
       * set user active ketika dilakukan pendaftaran
       */
      anggota.setIs_active(defaultData.defaultActive(anggota)); // 1 jika aktif & 2 jika non-aktif

      return repository.save(anggota);
    }
  }

  public Anggota findAnggotaByKode(String kodeAnggota) {
    Optional<Anggota> anggota = repository.findById(kodeAnggota);
    if (!anggota.isPresent()) {
      return null;
    }
    return anggota.get();
  }

  public Anggota findAnggotaByEmail(String email) {
    if (repository.findOneAnggotaByEmail(email).equals(null)) {
      return null;
    } else {
      return repository.findOneAnggotaByEmail(email);
    }
  }

  public Iterable<Anggota> findAll() {
    return repository.findAll();
  }

  public void removeOne(String kodeAnggota) {
    repository.deleteById(kodeAnggota);
  }

  public List<Anggota> findAnggotaByName(String nama_anggota) {
    return repository.findAnggotaByName("%" + nama_anggota + "%");
  }

  public Iterable<Anggota> findAnggotaByGolongan(String golongan, Pageable pageable) {
    return repository.findAnggotaByGolongan("%" + golongan + "%", pageable);
  }

  public Anggota findOneAnggotaByKode(String kd_anggota) {
    return repository.findOneAnggotaByKode(kd_anggota).get();
  }

  public String findMaxKodeByGolongan(String golongan) {
    return repository.findKodeByGolongan(golongan);
  }
}
