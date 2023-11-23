package hamparan.gapoktan.model.repository.menumanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.menumanagement.Menu;
import jakarta.websocket.server.PathParam;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	public static final String TABLE_MENU = "user_menu";
	public static final String TABLE_ACCESS_MENU = "user_access_menu";

	/** field dari table user menu */
	public static final String FIELD_ID_MENU = "id";
	public static final String FIELD_MENU = "menu";

	/** field dari table user access menu */
	public static final String FIELD_ID_ACCESS_MENU = "id";
	public static final String FIELD_ROLE_ID = "role_id";
	public static final String FIELD_MENU_ID = "menu_id";

	String queryFindMenuById = "SELECT * FROM " + TABLE_MENU + " WHERE " + TABLE_MENU + "." + FIELD_ID_MENU + " = :"
			+ FIELD_ID_MENU + "";

	@Query(value = "", nativeQuery = true)
	public Menu findMenuById(@PathParam(FIELD_ID_MENU) int id);

	String queryFindMenuByName = "SELECT * FROM " + TABLE_MENU + " WHERE " + TABLE_MENU + "." + FIELD_MENU + " = :"
			+ FIELD_MENU + "";

	@Query(value = queryFindMenuByName, nativeQuery = true)
	public Menu findMenuByName(@PathParam(FIELD_MENU) String menu);

	String queryFindAccessMenuByRoleId = "SELECT " + TABLE_MENU + "." + FIELD_ID_MENU + "," + FIELD_MENU + " FROM "
			+ TABLE_MENU + " JOIN " + TABLE_ACCESS_MENU + " ON " + TABLE_MENU + "." + FIELD_ID_MENU + " = "
			+ TABLE_ACCESS_MENU + "." + FIELD_MENU_ID + " WHERE " + TABLE_ACCESS_MENU + "." + FIELD_ROLE_ID
			+ " = :" + FIELD_ROLE_ID + " ORDER BY " + TABLE_ACCESS_MENU + "." + FIELD_MENU_ID + " ASC";

	@Query(value = queryFindAccessMenuByRoleId, nativeQuery = true)
	public List<Menu> findAccessMenuByRoleId(@PathParam(FIELD_ROLE_ID) int role_id);

	String queryRoleAccessMenu = "SELECT * FROM " + TABLE_MENU + " WHERE " + FIELD_MENU + " != 'ADMIN'";

	@Query(value = queryRoleAccessMenu, nativeQuery = true)
	public List<Menu> findRoleAccessMenu();

}
