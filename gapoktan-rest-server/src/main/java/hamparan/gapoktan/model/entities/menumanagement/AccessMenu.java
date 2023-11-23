package hamparan.gapoktan.model.entities.menumanagement;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_access_menu")
public class AccessMenu implements Serializable {

  @Id
  private int id;

  private int role_id;

  private int menu_id;

  public AccessMenu() {
  }

  public AccessMenu(int id, int role_id, int menu_id) {
    this.id = id;
    this.role_id = role_id;
    this.menu_id = menu_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRole_id() {
    return role_id;
  }

  public void setRole_id(int role_id) {
    this.role_id = role_id;
  }

  public int getMenu_id() {
    return menu_id;
  }

  public void setMenu_id(int menu_id) {
    this.menu_id = menu_id;
  }

}
