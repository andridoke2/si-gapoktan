package hamparan.gapoktan.model.entities.menumanagement;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_role")
public class Role implements Serializable {

  @Id
  private int id;

  private String role;

  public Role() {
  }

  public Role(int id, String role) {
    this.id = id;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
