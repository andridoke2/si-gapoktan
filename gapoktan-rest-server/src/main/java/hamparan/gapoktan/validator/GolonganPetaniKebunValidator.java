package hamparan.gapoktan.validator;

import org.springframework.beans.factory.annotation.Autowired;

import hamparan.gapoktan.model.repository.hamparan.KebunRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GolonganPetaniKebunValidator implements ConstraintValidator<GolonganPetaniKebun, String> {

  @Autowired
  private KebunRepository kebunRepository;

  @Override
  public boolean isValid(String kd_anggota, ConstraintValidatorContext arg1) {
    try {
      switch (kebunRepository.findGolonganAnggotaByKodeAnggota(kd_anggota)) {
        case "Petani":
          return true;
        default:
          return false;
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return false;
    }
  }

}
