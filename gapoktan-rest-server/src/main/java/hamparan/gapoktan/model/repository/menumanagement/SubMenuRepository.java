package hamparan.gapoktan.model.repository.menumanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.menumanagement.SubMenu;
import jakarta.websocket.server.PathParam;

public interface SubMenuRepository extends JpaRepository<SubMenu, Integer> {

	public static final String TABLE_SUB_MENU = "user_sub_menu";
	public static final String TABLE_MENU = "user_menu";

	public static final String FIELD_ID_SUB_MENU = "id";
	public static final String FIELD_MENU_ID = "menu_id";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_URL = "url";
	public static final String FIELD_ICON = "icon";
	public static final String FIELD_IS_ACTIVE = "is_active";

	public static final String FIELD_ID_MENU = "id";
	public static final String FIELD_MENU = "menu";

	String queryFindAllSubMenu = "SELECT * FROM " + TABLE_SUB_MENU + "";

	@Query(value = queryFindAllSubMenu, nativeQuery = true)
	public List<SubMenu> findAllSubMenu();

	String queryFindSubMenuById = "SELECT * FROM " + TABLE_SUB_MENU + " WHERE " + TABLE_SUB_MENU + "."
			+ FIELD_ID_SUB_MENU + " = :" + FIELD_ID_SUB_MENU + "";

	@Query(value = queryFindSubMenuById, nativeQuery = true)
	public SubMenu findSubMenuById(@PathParam(FIELD_ID_SUB_MENU) int id);

	String queryFindSubMenuByTitle = "SELECT * FROM " + TABLE_SUB_MENU + " WHERE " + TABLE_SUB_MENU + "." + FIELD_TITLE
			+ " = :" + FIELD_TITLE + "";

	@Query(value = queryFindSubMenuByTitle, nativeQuery = true)
	public SubMenu findSubMenuByTitle(@PathParam(FIELD_TITLE) String title);

	String queryFindAccessSubMenuByMenuId = "SELECT " + TABLE_SUB_MENU + "." + FIELD_ID_SUB_MENU + ", "
			+ TABLE_SUB_MENU + "." + FIELD_MENU_ID + ", " + TABLE_SUB_MENU + "." + FIELD_TITLE + ", " + TABLE_SUB_MENU + "."
			+ FIELD_URL + ", " + TABLE_SUB_MENU + "." + FIELD_ICON + ", " + TABLE_SUB_MENU + "." + FIELD_IS_ACTIVE + ", "
			+ TABLE_MENU + "." + FIELD_ID_MENU + " AS id_menu, " + TABLE_MENU + "." + FIELD_MENU + " AS menu_name FROM "
			+ TABLE_SUB_MENU + " JOIN " + TABLE_MENU + " ON " + TABLE_SUB_MENU + "." + FIELD_MENU_ID + " = " + TABLE_MENU
			+ "." + FIELD_ID_MENU + " WHERE " + TABLE_SUB_MENU + "." + FIELD_MENU_ID + " =:" + FIELD_MENU_ID + " AND "
			+ TABLE_SUB_MENU + "." + FIELD_IS_ACTIVE + " = '1'";

	@Query(value = queryFindAccessSubMenuByMenuId, nativeQuery = true)
	public List<SubMenu> findAccessSubMenuByMenuId(@PathParam(FIELD_MENU_ID) int menu_id);

}
