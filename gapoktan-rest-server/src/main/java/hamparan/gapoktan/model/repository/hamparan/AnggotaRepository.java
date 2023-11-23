package hamparan.gapoktan.model.repository.hamparan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Anggota;
import jakarta.websocket.server.PathParam;

public interface AnggotaRepository extends JpaRepository<Anggota, String> {

	public static final String TABLE_ANGGOTA = "anggota_hamparan";

	public static final String FIELD_KODE_ANGGOTA = "kd_anggota";
	public static final String FIELD_NAMA = "nama_anggota";
	public static final String FIELD_ALAMAT = "alamat";
	public static final String FIELD_JK = "jenis_kelamin";
	public static final String FIELD_NO_TELP = "no_telpon";
	public static final String FIELD_IS_ACTIVE = "is_active";
	public static final String FIELD_GOLONGAN = "golongan";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PASSWORD = "password";
	public static final String FIELD_IMAGE = "image";
	public static final String FIELD_ROLE_ID = "role_id";
	public static final String FIELD_DATE_CREATED = "date_created";

	String queryFindKodeByGolongan = "SELECT MAX(" + FIELD_KODE_ANGGOTA + ") FROM " + TABLE_ANGGOTA
			+ " WHERE " + TABLE_ANGGOTA + "." + FIELD_GOLONGAN + " = :" + FIELD_GOLONGAN + "";

	@Query(value = queryFindKodeByGolongan, nativeQuery = true)
	public String findKodeByGolongan(@PathParam(FIELD_GOLONGAN) String golongan);

	String queryFindAnggotaByName = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "." + FIELD_NAMA
			+ " LIKE :"
			+ FIELD_NAMA + "";

	@Query(value = queryFindAnggotaByName, nativeQuery = true)
	public List<Anggota> findAnggotaByName(@PathParam(FIELD_NAMA) String nama_anggota);

	String queryFindAnggotaByGolongan = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "."
			+ FIELD_GOLONGAN + " LIKE :" + FIELD_GOLONGAN + "";

	@Query(value = queryFindAnggotaByGolongan, nativeQuery = true)
	public Page<Anggota> findAnggotaByGolongan(@PathParam(FIELD_GOLONGAN) String golongan, Pageable pageable);

	String queryFindOneAnggotaByKode = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "."
			+ FIELD_KODE_ANGGOTA
			+ " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindOneAnggotaByKode, nativeQuery = true)
	public Optional<Anggota> findOneAnggotaByKode(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	String queryFindOneAnggotaByEmail = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "." + FIELD_EMAIL
			+ " = :" + FIELD_EMAIL + "";

	@Query(value = queryFindOneAnggotaByEmail, nativeQuery = true)
	public Anggota findOneAnggotaByEmail(@PathParam(FIELD_EMAIL) String email);

	String queryExistsAnggotaByEmail = "SELECT COUNT(" + FIELD_EMAIL + ") FROM " + TABLE_ANGGOTA + " WHERE " + FIELD_EMAIL
			+ " = :" + FIELD_EMAIL + "";

	@Query(value = queryExistsAnggotaByEmail, nativeQuery = true)
	public int existsAnggotaByEmail(@PathParam(FIELD_EMAIL) String email);

	String queryFindEmailByEmail = "SELECT " + FIELD_EMAIL + " FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "."
			+ FIELD_EMAIL + " = :" + FIELD_EMAIL + "";

	@Query(value = queryFindEmailByEmail, nativeQuery = true)
	public String findEmailByEmail(@PathParam(FIELD_EMAIL) String email);

	String queryExistsAnggotaByKode = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "."
			+ FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryExistsAnggotaByKode, nativeQuery = true)
	public boolean existsAnggotaByKode(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

}
