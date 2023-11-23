package hamparan.gapoktan.validator;

import org.springframework.beans.factory.annotation.Autowired;

import hamparan.gapoktan.model.repository.hamparan.AnggotaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUniqueConstraint, String> {

  @Autowired
  private AnggotaRepository anggotaRepository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    try {

      switch (anggotaRepository.existsAnggotaByEmail(email)) {
        case 0:
          return true;
        case 1:
          if (anggotaRepository.findEmailByEmail(email).equals(email)) {
            return true;
          } else {
            return false;
          }
        default:
          return false;
      }

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return false;
    }
  }

}
