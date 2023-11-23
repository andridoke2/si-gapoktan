package hamparan.gapoktan.model.repository.hamparan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Mobil;
import jakarta.websocket.server.PathParam;

public interface MobilRepository extends JpaRepository<Mobil, String> {

	public static final String TABLE_MOBIL = "mobil_hamparan";
	public static final String TABLE_ANGGOTA = "anggota_hamparan";

	/** Field dari table kebun */
	public static final String FIELD_KODE_MOBIL = "kd_mobil";
	public static final String FIELD_KODE_ANGGOTA = "kd_anggota";
	public static final String FIELD_MERK_MOBIL = "merk_mobil";
	public static final String FIELD_NO_PLAT_KENDARAAN = "nomor_plat_kendaraan";

	/** Field dari table anggota */
	public static final String FIELD_GOLONGAN = "golongan";

	String queryFindMaxKodeMobil = "SELECT MAX(" + FIELD_KODE_MOBIL + ") FROM " + TABLE_MOBIL + "";

	@Query(value = queryFindMaxKodeMobil, nativeQuery = true)
	public String findMaxKodeMobil();

	String queryFindOneMobilByKode = "SELECT * FROM " + TABLE_MOBIL + " WHERE " + TABLE_MOBIL + "." + FIELD_KODE_MOBIL
			+ " = :" + FIELD_KODE_MOBIL + "";

	@Query(value = queryFindOneMobilByKode, nativeQuery = true)
	public Mobil findOneMobilByKode(@PathParam(FIELD_KODE_MOBIL) String kd_mobil);

	String queryFindMobilByKodeAnggota = "SELECt * FROM " + TABLE_MOBIL + " WHERE " + TABLE_MOBIL + "."
			+ FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindMobilByKodeAnggota, nativeQuery = true)
	public List<Mobil> findMobilByKodeAnggota(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	String queryFindMobilByMerk = "SELECT * FROM " + TABLE_MOBIL + " WHERE " + TABLE_MOBIL + "." + FIELD_MERK_MOBIL
			+ " = :" + FIELD_MERK_MOBIL + "";

	@Query(value = queryFindMobilByMerk, nativeQuery = true)
	public List<Mobil> findMobilByMerk(@PathParam(FIELD_MERK_MOBIL) String merk_mobil);

	String queryFindMobilByNomorPlat = "SELECT * FROM " + TABLE_MOBIL + " WHERE " + TABLE_MOBIL + "."
			+ FIELD_NO_PLAT_KENDARAAN + " LIKE :" + FIELD_NO_PLAT_KENDARAAN + "";

	@Query(value = queryFindMobilByNomorPlat, nativeQuery = true)
	public List<Mobil> findMobilByNomorPlat(@PathParam(FIELD_NO_PLAT_KENDARAAN) String nomor_plat_kendaraan);

	/**
	 * fungsi untuk validasi golongan mobil yang dapat memiliki mobil
	 * ketika menambahkan data mobil dan golongan anggota bukan mobil, maka akan
	 * direject.
	 */
	String queryFindGolonganAnggotaByKodeAnggota = "SELECT " + FIELD_GOLONGAN + " FROM " + TABLE_ANGGOTA + " WHERE "
			+ TABLE_ANGGOTA + "." + FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindGolonganAnggotaByKodeAnggota, nativeQuery = true)
	public String findGolonganAnggotaByKodeAnggota(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	String queryFindMobilByKodeMobilAndKodeAnggta = "SELECT * FROM " + TABLE_MOBIL + " WHERE " + TABLE_MOBIL + "."
			+ FIELD_KODE_MOBIL + " = :" + FIELD_KODE_MOBIL + " AND " + TABLE_MOBIL + "."
			+ FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindMobilByKodeMobilAndKodeAnggta, nativeQuery = true)
	public Optional<Mobil> findMobilByKodeMobilAndKodeAnggota(@PathParam(FIELD_KODE_MOBIL) String kd_mobil,
			@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

}
