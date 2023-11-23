package hamparan.gapoktan.services.hamparan;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.helpers.DefaultData;
import hamparan.gapoktan.helpers.GenerateKode;
import hamparan.gapoktan.model.entities.hamparan.Anggota;
import hamparan.gapoktan.model.entities.hamparan.Harga;
import hamparan.gapoktan.model.repository.hamparan.HargaRepository;
import hamparan.gapoktan.utils.ClearDataIntoDatabase;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HargaService {

  @Autowired
  private HargaRepository repository;

  @Autowired
  private ModelMapper modelMapper;

  private GenerateKode generateKode = new GenerateKode();

  public Harga save(Harga harga) {
    ClearDataIntoDatabase clear = new ClearDataIntoDatabase();
    if (harga.getKd_harga() != null) {
      harga.setKd_harga(clear.cleanKode(harga.getKd_harga()));

      Harga currentHarga = repository.findById(harga.getKd_harga()).get();

      /**
       * Data date_created lama yang tidak perlu diubah ketika dilakukan update harga
       */
      harga.setDate_created(currentHarga.getDate_created());

      if (harga.getSawit() == 0) {
        harga.setSawit(currentHarga.getSawit());
      }

      if (harga.getMobil() == 0) {
        harga.setMobil(currentHarga.getMobil());
      }

      if (harga.getPemuat() == 0) {
        harga.setPemuat(currentHarga.getPemuat());
      }

      if (harga.getPenimbang() == 0) {
        harga.setPenimbang(currentHarga.getPenimbang());
      }

      if (harga.getPengurus() == 0) {
        harga.setPengurus(currentHarga.getPengurus());
      }

      if (harga.getPajak() == 0.0d) {
        harga.setPajak(currentHarga.getPajak());
      }

      return repository.save(harga);
    } else {
      /** Set default kode harga */
      String maxKode = repository.findMaxKodeHarga();
      harga.setKd_harga(generateKode.generateKodeHarga(maxKode));

      DefaultData defaultData = new DefaultData();
      harga.setDate_created(defaultData.defaultDateCreated(modelMapper.map(harga, Anggota.class)));

      return repository.save(harga);
    }
  }

  public Harga findByKode(String kode) {
    Optional<Harga> harga = repository.findById(kode);
    if (!harga.isPresent()) {
      return null;
    } else {
      return harga.get();
    }
  }

  public Harga findOneHargaByKode(String kd_harga) {
    return repository.findOneHargaByKode(kd_harga);
  }

  public Iterable<Harga> findAll() {
    return repository.findAll();
  }

  public void removeOne(String kode) {
    repository.deleteById(kode);
  }

  public Harga findLastHarga() {
    if (repository.findLastHarga().isEmpty()) {
      return null;
    } else {
      return repository.findLastHarga().get(0);
    }
  }

}
