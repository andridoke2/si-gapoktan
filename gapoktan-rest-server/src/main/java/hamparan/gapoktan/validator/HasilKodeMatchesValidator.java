package hamparan.gapoktan.validator;

import org.springframework.beans.factory.annotation.Autowired;

import hamparan.gapoktan.dto.request.HasilRequest;
import hamparan.gapoktan.model.repository.hamparan.AnggotaRepository;
import hamparan.gapoktan.model.repository.hamparan.KebunRepository;
import hamparan.gapoktan.model.repository.hamparan.MobilRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HasilKodeMatchesValidator implements ConstraintValidator<HasilKodeMatches, Object> {

  @Autowired
  private AnggotaRepository anggotaRepository;

  @Autowired
  private KebunRepository kebunRepository;

  @Autowired
  private MobilRepository mobilRepository;

  @Override
  public boolean isValid(Object data, ConstraintValidatorContext arg1) {

    boolean isValid = false;

    HasilRequest hasilRequest = (HasilRequest) data;

    switch (hasilRequest.getGolongan()) {
      case "Petani":
        if (kebunRepository.findKebunByKodeKebunAndKodeAnggota(hasilRequest.getKd_kebun(),
            hasilRequest.getKd_anggota()).isPresent()) {
          isValid = true;
        }
        break;
      case "Mobil":
        if (mobilRepository.findMobilByKodeMobilAndKodeAnggota(hasilRequest.getKd_mobil(), hasilRequest.getKd_anggota())
            .isPresent()) {
          isValid = true;
        }
        break;
      case "Pemuat":
        if (anggotaRepository.findOneAnggotaByKode(hasilRequest.getKd_anggota()).get().getGolongan()
            .equals(hasilRequest.getGolongan())) {
          isValid = true;
        }
        break;
      case "Penimbang":
        if (anggotaRepository.findOneAnggotaByKode(hasilRequest.getKd_anggota()).get().getGolongan()
            .equals(hasilRequest.getGolongan())) {
          isValid = true;
        }
        break;
    }

    return isValid;
  }

}
