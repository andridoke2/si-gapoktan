package hamparan.gapoktan.services.hamparan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.hamparan.Pajak;
import hamparan.gapoktan.model.repository.hamparan.PajakRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PajakService {

  @Autowired
  private PajakRepository repository;

  public Pajak save(Pajak pajak) {
    return repository.save(pajak);
  }

  public Pajak findById(int id) {
    Optional<Pajak> pajak = repository.findById(id);
    if (!pajak.isPresent()) {
      return null;
    } else {
      return pajak.get();
    }
  }

  public Iterable<Pajak> findAll() {
    return repository.findAll();
  }

  public void removeOne(int id) {
    repository.deleteById(id);
  }

  public Pajak findPajakByKodeHasil(String kd_hasil) {
    return repository.findPajakByKodeHasil(kd_hasil);
  }

}
