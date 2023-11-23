package hamparan.gapoktan.validator;

import org.springframework.beans.factory.annotation.Autowired;

import hamparan.gapoktan.model.repository.hamparan.MobilRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GolonganMobilValidator implements ConstraintValidator<GolonganMobil, String> {

  @Autowired
  private MobilRepository mobilRepository;

  @Override
  public boolean isValid(String kd_anggota, ConstraintValidatorContext arg1) {
    try {
      switch (mobilRepository.findGolonganAnggotaByKodeAnggota(kd_anggota)) {
        case "Mobil":
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
