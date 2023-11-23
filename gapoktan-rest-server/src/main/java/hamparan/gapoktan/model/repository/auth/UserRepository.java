package hamparan.gapoktan.model.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.auth.User;
import jakarta.websocket.server.PathParam;

public interface UserRepository extends JpaRepository<User, String> {

    public static final String TABLE_ANGGOTA = "anggota_hamparan";
    public static final String TABLE_ROLE = "user_role";

    public static final String FIELD_GOLONGAN = "golongan";
    public static final String FIELD_EMAIL = "email";

    public static final String FIELD_ROLE_NAME = "role";

    Optional<User> findByEmail(String email);

    String queryFindKodeByGolongan = "SELECT MAX(kd_anggota) FROM " + TABLE_ANGGOTA
            + " WHERE " + TABLE_ANGGOTA + "." + FIELD_GOLONGAN + " = :" + FIELD_GOLONGAN + "";

    @Query(value = queryFindKodeByGolongan, nativeQuery = true)
    public String findKodeByGolongan(@PathParam(FIELD_GOLONGAN) String golongan);

    String queryFindAppUserByEmail = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "." + FIELD_EMAIL
            + " = :" + FIELD_EMAIL + "";

    @Query(value = queryFindAppUserByEmail, nativeQuery = true)
    public User findAppUserByEmail(@PathParam(FIELD_EMAIL) String email);

}
