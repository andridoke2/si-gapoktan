package hamparan.gapoktan.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class MenuRequest {

  private int id;

  @NotEmpty(message = "Menu harus diisi!")
  private String menu;

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu.trim();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
