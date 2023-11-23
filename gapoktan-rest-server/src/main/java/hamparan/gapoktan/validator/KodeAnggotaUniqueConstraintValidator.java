package hamparan.gapoktan.validator;

import org.springframework.beans.factory.annotation.Autowired;

import hamparan.gapoktan.model.repository.hamparan.AnggotaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KodeAnggotaUniqueConstraintValidator implements ConstraintValidator<KodeAnggotaUniqueConstraint, String> {

  @Autowired
  private AnggotaRepository anggotaRepository;

  @Override
  public boolean isValid(String kd_anggota, ConstraintValidatorContext context) {
    return !anggotaRepository.existsAnggotaByKode(kd_anggota);
  }

}
