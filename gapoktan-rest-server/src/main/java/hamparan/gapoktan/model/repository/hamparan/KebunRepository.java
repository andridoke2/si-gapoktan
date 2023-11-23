package hamparan.gapoktan.model.repository.hamparan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Anggota;
import hamparan.gapoktan.model.entities.hamparan.Kebun;
import jakarta.websocket.server.PathParam;

public interface KebunRepository extends JpaRepository<Kebun, String> {

	/**
	 * Table Kebun
	 */
	public static final String TABLE_KEBUN = "kebun_hamparan";

	/**
	 * Table Angota
	 */
	public static final String TABLE_ANGGOTA = "anggota_hamparan";

	/**
	 * Field Dari Table Kebun
	 */
	public static final String FIELD_KODE_KEBUN = "kd_kebun";
	public static final String FIELD_KODE_ANGGOTA = "kd_anggota";
	public static final String FIELD_LOKASI = "lokasi";
	public static final String FIELD_JENIS_KEBUN = "jenis_kebun";

	/**
	 * Field Dari Table Anggota
	 */
	public static final String FIELD_GOLONGAN = "golongan";

	String queryFindAnggotaByKode = "SELECT * FROM " + TABLE_ANGGOTA + " WHERE " + TABLE_ANGGOTA + "."
			+ FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindAnggotaByKode, nativeQuery = true)
	public Anggota findAnggotaByKode(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	String queryFindMaxKodeKebun = "SELECT MAX(" + FIELD_KODE_KEBUN + ") FROM " + TABLE_KEBUN + "";

	@Query(value = queryFindMaxKodeKebun, nativeQuery = true)
	public String findMaxKodeKebun();

	String queryFindOneKebunByKode = "SELECT * FROM " + TABLE_KEBUN + " WHERE " + TABLE_KEBUN + "."
			+ FIELD_KODE_KEBUN
			+ " = :" + FIELD_KODE_KEBUN + "";

	@Query(value = queryFindOneKebunByKode, nativeQuery = true)
	public Kebun findOneKebunByKode(@PathParam(FIELD_KODE_KEBUN) String kd_kebun);

	String queryFindKebunByJenisKebun = "SELECT * FROM " + TABLE_KEBUN + " WHERE " + TABLE_KEBUN + "."
			+ FIELD_JENIS_KEBUN + " LIKE :" + FIELD_JENIS_KEBUN + "";

	@Query(value = queryFindKebunByJenisKebun, nativeQuery = true)
	public Page<Kebun> findKebunByJenisKebun(@PathParam(FIELD_JENIS_KEBUN) String jenis_kebun, Pageable pageable);

	String queryFindKebunByLokasiKebun = "SELECT * FROM " + TABLE_KEBUN + " WHERE " + TABLE_KEBUN + "."
			+ FIELD_LOKASI
			+ " LIKE :" + FIELD_LOKASI + "";

	@Query(value = queryFindKebunByLokasiKebun, nativeQuery = true)
	public Page<Kebun> findKebunByLokasiKebun(@PathParam(FIELD_LOKASI) String lokasi, Pageable pageable);

	String queryFindKebunByKodeAnggota = "SELECT * FROM " + TABLE_KEBUN + " WHERE " + TABLE_KEBUN + "."
			+ FIELD_KODE_ANGGOTA + " LIKE :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindKebunByKodeAnggota, nativeQuery = true)
	public List<Kebun> findKebunByKodeAnggota(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	/**
	 * fungsi untuk validasi golongan petani yang dapat memiliki kebun
	 * ketika menambahkan data kebun dan golongan anggota bukan petani, maka akan
	 * direject.
	 */
	String queryFindGolonganAnggotaByKodeAnggota = "SELECT " + FIELD_GOLONGAN + " FROM " + TABLE_ANGGOTA + " WHERE "
			+ TABLE_ANGGOTA + "." + FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindGolonganAnggotaByKodeAnggota, nativeQuery = true)
	public String findGolonganAnggotaByKodeAnggota(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	/** fungsi untuk validasi input hasil */
	String queryFindKebunByKodeKebunAndKodeAnggota = "SELECT * FROM " + TABLE_KEBUN + " WHERE " + TABLE_KEBUN + "."
			+ FIELD_KODE_KEBUN + " = :" + FIELD_KODE_KEBUN + " AND " + TABLE_KEBUN + "." + FIELD_KODE_ANGGOTA + " = :"
			+ FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindKebunByKodeKebunAndKodeAnggota, nativeQuery = true)
	public Optional<Kebun> findKebunByKodeKebunAndKodeAnggota(@PathParam(FIELD_KODE_KEBUN) String kd_kebun,
			@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

}
