package hamparan.gapoktan.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class RoleRequest {

  private int id;

  @NotEmpty(message = "Role harus diisi!")
  private String role;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role.trim();
  }

}
