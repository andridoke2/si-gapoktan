package hamparan.gapoktan.dto.response;

public class SubMenuResponse {

  private int id;

  private int menu_id;

  private String title;

  private String url;

  private String icon;

  private int is_active;

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

}