package hamparan.gapoktan.model.entities.menumanagement;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_sub_menu")
public class SubMenuJoinMenu implements Serializable {

  @Id
  private int id;

  private int menu_id;

  private String title;

  private String url;

  private String icon;

  private int is_active;

  private int id_menu;

  private String menu_name;

  public SubMenuJoinMenu() {
  }

  public SubMenuJoinMenu(int id, int menu_id, String title, String url, String icon, int is_active) {
    this.id = id;
    this.menu_id = menu_id;
    this.title = title;
    this.url = url;
    this.icon = icon;
    this.is_active = is_active;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMenu_id() {
    return menu_id;
  }

  public void setMenu_id(int menu_id) {
    this.menu_id = menu_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public int getIs_active() {
    return is_active;
  }

  public void setIs_active(int is_active) {
    this.is_active = is_active;
  }

  /**
   * ====================== untuk kebutuhan JOIN MENU ======================
   */

  public int getId_menu() {
    return id_menu;
  }

  public void setId_menu(int id_menu) {
    this.id_menu = id_menu;
  }

  public String getMenu_name() {
    return menu_name;
  }

  public void setMenu_name(String menu_name) {
    this.menu_name = menu_name;
  }

}
