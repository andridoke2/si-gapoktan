package hamparan.gapoktan.model.repository.menumanagement;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.menumanagement.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  public static final String TABLE_ROLE = "user_role";

  public static final String FIELD_ID = "id";
  public static final String FIELD_ROLE = "role";

  String queryFindUserRoleById = "SELECT * FROM " + TABLE_ROLE + " WHERE " + TABLE_ROLE + "." + FIELD_ID
      + " = :" + FIELD_ID + "";

  @Query(value = queryFindUserRoleById, nativeQuery = true)
  public Role findRoleById(int id);

  String queryFindAllUserRole = "SELECT * FROM " + TABLE_ROLE + "";

  @Query(value = queryFindAllUserRole, nativeQuery = true)
  public Collection<Role> findUserRole();

  String queryFindRoleByName = "SELECT * FROM " + TABLE_ROLE + " WHERE " + TABLE_ROLE + "." + FIELD_ROLE + " = :"
      + FIELD_ROLE + "";

  @Query(value = queryFindRoleByName, nativeQuery = true)
  public Role findRoleByName(String role);

}
