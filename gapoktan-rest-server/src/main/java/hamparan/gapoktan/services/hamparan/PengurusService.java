package hamparan.gapoktan.services.hamparan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.hamparan.Pengurus;
import hamparan.gapoktan.model.repository.hamparan.PengurusRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PengurusService {

  @Autowired
  private PengurusRepository repository;

  public Pengurus save(Pengurus pengurus) {
    return repository.save(pengurus);
  }

  public Pengurus findPengurusByKodeHasil(String kd_hasil) {
    return repository.findPengurusByKodeHasil(kd_hasil);
  }

  public Iterable<Pengurus> findAll() {
    return repository.findAll();
  }

}
