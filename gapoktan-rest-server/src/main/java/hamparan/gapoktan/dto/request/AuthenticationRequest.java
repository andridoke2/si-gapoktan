package hamparan.gapoktan.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  @NotEmpty(message = "Email harus diisi!")
  private String email;

  @NotEmpty(message = "Password harus diisi!")
  String password;

}
