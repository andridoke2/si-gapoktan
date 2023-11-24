package hamparan.gapoktan.model.repository.menumanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.menumanagement.AccessMenu;
import jakarta.websocket.server.PathParam;

public interface AccessMenuRepository extends JpaRepository<AccessMenu, Integer> {

    public static final String TABLE_ACCESS_MENU = "user_access_menu";

    public static final String FIELD_ID = "id";
    public static final String FIELD_ROLE_ID = "role_id";
    public static final String FIELD_MENU_ID = "menu_id";

    String queryFindAccessMenuById = "SELECT * FROM " + TABLE_ACCESS_MENU + " WHERE " + TABLE_ACCESS_MENU + "."
            + FIELD_ID
            + " = :" + FIELD_ID + "";

    @Query(value = queryFindAccessMenuById, nativeQuery = true)
    public AccessMenu findAccessMenuById(@PathParam(FIELD_ID) int id);

    String queryFindAccessMenuByRoleId = "SELECT * FROM " + TABLE_ACCESS_MENU + " WHERE " + TABLE_ACCESS_MENU + "."
            + FIELD_ROLE_ID + " = :" + FIELD_ROLE_ID + "";

    @Query(value = queryFindAccessMenuByRoleId, nativeQuery = true)
    public AccessMenu findAccessMenuByRoleId(@PathParam(FIELD_ROLE_ID) int role_id);

    String queryFindAccessMenuByMenuId = "SELECT * FROM " + TABLE_ACCESS_MENU + " WHERE " + TABLE_ACCESS_MENU + "."
            + FIELD_MENU_ID + " = :" + FIELD_MENU_ID + "";

    @Query(value = queryFindAccessMenuByMenuId, nativeQuery = true)
    public AccessMenu findAccessMenuByMenuId(@PathParam(FIELD_MENU_ID) int menu_id);

    String queryFindAccessMenuByMenuIdRoleId = "SELECT * FROM " + TABLE_ACCESS_MENU + " WHERE " + TABLE_ACCESS_MENU
            + "." + FIELD_ROLE_ID + " = :" + FIELD_ROLE_ID + " AND " + TABLE_ACCESS_MENU + "."
            + FIELD_MENU_ID + " = :" + FIELD_MENU_ID + "";

    @Query(value = queryFindAccessMenuByMenuIdRoleId, nativeQuery = true)
    public AccessMenu findAccessMenuByRoleIdMenuId(@PathParam(FIELD_ROLE_ID) int role_id,
            @PathParam(FIELD_MENU_ID) int menu_id);

    String queryExistsAccessMenu = "SELECT * FROM " + TABLE_ACCESS_MENU + " WHERE "
            + TABLE_ACCESS_MENU + "." + FIELD_ROLE_ID + " = :" + FIELD_ROLE_ID + " AND " + TABLE_ACCESS_MENU + "."
            + FIELD_MENU_ID + " = :" + FIELD_MENU_ID + "";

    @Query(value = queryExistsAccessMenu, nativeQuery = true)
    public AccessMenu existsAccessMenu(@PathParam(FIELD_ROLE_ID) int role_id, @PathParam(FIELD_MENU_ID) int menu_id);

    String queryExistsAccessMenuCount = "SELECT COUNT(" + FIELD_ROLE_ID + ") FROM " + TABLE_ACCESS_MENU
            + " WHERE " + TABLE_ACCESS_MENU + "." + FIELD_ROLE_ID + " = :" + FIELD_ROLE_ID + " AND " + TABLE_ACCESS_MENU
            + "." + FIELD_MENU_ID + " = :" + FIELD_MENU_ID + "";

    @Query(value = queryExistsAccessMenuCount, nativeQuery = true)
    public String existsAccessMenuCount(@PathParam(FIELD_ROLE_ID) int role_id, @PathParam(FIELD_MENU_ID) int menu_id);

}
