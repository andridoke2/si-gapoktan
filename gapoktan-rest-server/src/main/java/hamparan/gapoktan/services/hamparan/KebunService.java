package hamparan.gapoktan.services.hamparan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.helpers.GenerateKode;
import hamparan.gapoktan.model.entities.hamparan.Kebun;
import hamparan.gapoktan.model.repository.hamparan.KebunRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class KebunService {

  @Autowired
  private KebunRepository repository;

  private GenerateKode generateKode = new GenerateKode();

  public Kebun save(Kebun kebun) {
    if (kebun.getKd_kebun() != null) {
      return repository.save(kebun);
    } else {
      String maxKodeKebun = repository.findMaxKodeKebun();
      kebun.setKd_kebun(generateKode.generateKodeKebun(maxKodeKebun));

      return repository.save(kebun);
    }
  }

  public List<Kebun> findAll() {
    if (repository.findAll().isEmpty()) {
      return null;
    } else {
      return repository.findAll();
    }
  }

  public Kebun findKebunByKode(String kodeKebun) {
    Optional<Kebun> kebun = repository.findById(kodeKebun);
    if (!kebun.isPresent()) {
      return null;
    }
    return kebun.get();
  }

  public void removeOne(String kodeKebun) {
    repository.deleteById(kodeKebun);
  }

  public List<Kebun> findKebunByKodeAnggota(String kodeAnggota) {
    return repository.findKebunByKodeAnggota("%" + kodeAnggota + "%");
  }

  public Iterable<Kebun> findKebunByJenisKebun(String jenisKebun, Pageable pageable) {
    return repository.findKebunByJenisKebun("%" + jenisKebun + "%", pageable);
  }

  public Iterable<Kebun> findKebunByLokasiKebun(String lokasi, Pageable pageable) {
    return repository.findKebunByLokasiKebun("%" + lokasi + "%", pageable);
  }

  public Kebun findOneKebunByKode(String kodeKebun) {
    return repository.findOneKebunByKode(kodeKebun);
  }

  public Kebun findKebunByKodeKebunAndKodeAnggota(String kd_kebun, String kd_anggota) {
    return repository.findKebunByKodeKebunAndKodeAnggota(kd_kebun, kd_anggota).get();
  }

}
