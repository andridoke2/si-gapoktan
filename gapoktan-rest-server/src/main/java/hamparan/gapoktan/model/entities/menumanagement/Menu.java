package hamparan.gapoktan.model.entities.menumanagement;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_menu")
public class Menu implements Serializable {

  @Id
  private int id;

  private String menu;

  public Menu() {
  }

  public Menu(int id, String menu) {
    this.id = id;
    this.menu = menu;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

}
