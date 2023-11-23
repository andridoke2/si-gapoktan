package hamparan.gapoktan.model.repository.hamparan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.DetailHasil;
import jakarta.websocket.server.PathParam;

public interface DetailHasilRepository extends JpaRepository<DetailHasil, String> {

	/** TABLE NAME */
	public static final String TABLE_ANGGOTA = "anggota_hamparan";
	public static final String TABLE_KEBUN = "kebun_hamparan";
	public static final String TABLE_MOBIL = "mobil_hamparan";
	public static final String TABLE_HASIL = "hasil_hamparan";
	public static final String TABLE_PAJAK = "pajak_hamparan";
	public static final String TABLE_HARGA = "harga_hamparan";
	public static final String TABLE_PENGURU = "pengurus_hamparan";
	public static final String TABLE_DETAIL_HASIL = "detail_hasil_hamparan";

	/** FIELD ANGGOTA */
	public static final String FIELD_KODE_ANGGOTA = "kd_anggota";
	public static final String FIELD_NAMA_ANGGOTA = "nama_anggota";
	public static final String FIELD_ALAMAT = "alamat";
	public static final String FIELD_JENIS_KELAMIN = "jenis_kelamin";
	public static final String FIELD_NO_TELPON = "no_telpon";
	public static final String FIELD_GOLONGAN = "golongan";
	public static final String FIELD_EMAIL = "email";

	/** FIELD KEBUN */
	public static final String FIELD_KODE_KEBUN = "kd_kebun";
	public static final String FIELD_LOKASI = "lokasi";
	public static final String FIELD_JENIS_KEBUN = "jenis_kebun";

	/** FIELD MOBIL */
	public static final String FIELD_KODE_MOBIL = "kd_mobil";
	public static final String FIELD_MERK_MOBIL = "merk_mobil";
	public static final String FIELD_NO_PLAT = "nomor_plat_kendaraan";

	/** FIELD HASIL */
	public static final String FIELD_KODE_HASIL = "kd_hasil";
	public static final String FIELD_TONASE = "tonase";
	public static final String FIELD_TANGGAL = "tanggal";
	public static final String FIELD_TOTAL_HASIL = "total_hasil";
	public static final String FIELD_POTONGAN_MOBIL = "potongan_mobil";
	public static final String FIELD_POTONGAN_PEMUAT = "potongan_pemuat";
	public static final String FIELD_POTONGAN_PENIMBANG = "potongan_penimbang";
	public static final String FIELD_POTONGAN_PENGURUS = "potongan_pengurus";
	public static final String FIELD_POTONGAN_PAJAK = "potongan_pajak";
	public static final String FIELD_TOTAL_POTONGAN = "total_potongan";

	/** FIELD HARGA */
	public static final String FIELD_KODE_HARGA = "kd_harga";
	public static final String FIELD_HARGA_SAWIT = "sawit";
	public static final String FIELD_HARGA_MOBIL = "mobil";
	public static final String FIELD_HARGA_PEMUAT = "pemuat";
	public static final String FIELD_HARGA_PENIMBANG = "penimbang";
	public static final String FIELD_HARGA_PENGURUS = "pengurus";
	public static final String FIELD_HARGA_PAJAK = "pajak";

	String queryFindDetailHasilByKodeHasil = "SELECT * FROM " + TABLE_DETAIL_HASIL + " WHERE " + TABLE_DETAIL_HASIL + "."
			+ FIELD_KODE_HASIL + " = :" + FIELD_KODE_HASIL + "";

	@Query(value = queryFindDetailHasilByKodeHasil, nativeQuery = true)
	public DetailHasil findDetailHasilByKodeHasil(@PathParam(FIELD_KODE_HASIL) String kd_hasil);

	String queryFindDetailHasilByKodeAnggota = "SELECT * FROM " + TABLE_DETAIL_HASIL + " WHERE " + TABLE_DETAIL_HASIL
			+ "." + FIELD_KODE_ANGGOTA + " = :" + FIELD_KODE_ANGGOTA + "";

	@Query(value = queryFindDetailHasilByKodeAnggota, nativeQuery = true)
	public Iterable<DetailHasil> findDetailHasilByKodeAnggota(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota);

	String queryFindDetailHasilPetani = "SELECT " + TABLE_ANGGOTA + "." + FIELD_KODE_ANGGOTA + ", " + TABLE_ANGGOTA + "."
			+ FIELD_NAMA_ANGGOTA + ", " + TABLE_ANGGOTA + "." + FIELD_ALAMAT + ", " + TABLE_ANGGOTA + "."
			+ FIELD_JENIS_KELAMIN + ", " + TABLE_ANGGOTA + "." + FIELD_NO_TELPON + ", " + TABLE_ANGGOTA + "." + FIELD_GOLONGAN
			+ ", " + TABLE_ANGGOTA + "." + FIELD_EMAIL + ", " + TABLE_KEBUN + "." + FIELD_KODE_KEBUN + ", " + TABLE_KEBUN
			+ "." + FIELD_LOKASI + ", " + TABLE_KEBUN + "." + FIELD_JENIS_KEBUN + ", " + TABLE_DETAIL_HASIL + "."
			+ FIELD_KODE_MOBIL + ", " + TABLE_HASIL + "." + FIELD_KODE_HASIL + ", " + TABLE_HASIL + "." + FIELD_KODE_HARGA
			+ ", " + TABLE_HASIL + "." + FIELD_TONASE + ", " + TABLE_HASIL + "." + FIELD_TANGGAL + ", " + TABLE_HASIL + "."
			+ FIELD_TOTAL_HASIL + ", " + TABLE_HASIL + "." + FIELD_POTONGAN_MOBIL + ", " + TABLE_HASIL + "."
			+ FIELD_POTONGAN_PEMUAT + ", " + TABLE_HASIL + "." + FIELD_POTONGAN_PENIMBANG + ", " + TABLE_HASIL + "."
			+ FIELD_POTONGAN_PENGURUS + ", " + TABLE_HASIL + "." + FIELD_POTONGAN_PAJAK + ", " + TABLE_HASIL + "."
			+ FIELD_TOTAL_POTONGAN + " FROM " + TABLE_ANGGOTA + " INNER JOIN " + TABLE_KEBUN + " ON " + TABLE_ANGGOTA + "."
			+ FIELD_KODE_ANGGOTA + "=" + TABLE_KEBUN + "." + FIELD_KODE_ANGGOTA + " INNER JOIN " + TABLE_HASIL
			+ " INNER JOIN " + TABLE_DETAIL_HASIL + " ON " + TABLE_HASIL + "." + FIELD_KODE_HASIL + "=" + TABLE_DETAIL_HASIL
			+ "." + FIELD_KODE_HASIL + " WHERE " + TABLE_ANGGOTA + "." + FIELD_KODE_ANGGOTA + " =:" + FIELD_KODE_ANGGOTA
			+ " AND " + " " + TABLE_KEBUN + "." + FIELD_KODE_KEBUN + " =:" + FIELD_KODE_KEBUN + " AND " + " " + TABLE_HASIL
			+ "." + FIELD_KODE_HASIL + " =:" + FIELD_KODE_HASIL + "";

	/**
	 * fungsi ini digunakan untuk mendapatkan data detail anggota, kebun & hasil
	 * menggunakan perintah join sql.
	 * 
	 * @param kd_anggota
	 * @param kd_kebun
	 * @param kd_hasil
	 * @return
	 */
	@Query(value = queryFindDetailHasilPetani, nativeQuery = true)
	public DetailHasil findDetailHasilPetani(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota,
			@PathParam(FIELD_KODE_KEBUN) String kd_kebun, @PathParam(FIELD_KODE_HASIL) String kd_hasil);

	String queryFindDetailHasilMobil = "SELECT " + TABLE_ANGGOTA + "." + FIELD_KODE_ANGGOTA + ", " + TABLE_ANGGOTA + "."
			+ FIELD_NAMA_ANGGOTA + ", " + TABLE_ANGGOTA + "." + FIELD_ALAMAT + ", " + TABLE_ANGGOTA + "."
			+ FIELD_JENIS_KELAMIN + ", " + TABLE_ANGGOTA + "." + FIELD_NO_TELPON + ", " + TABLE_ANGGOTA + "." + FIELD_GOLONGAN
			+ ", " + TABLE_ANGGOTA + "." + FIELD_EMAIL + ", " + TABLE_MOBIL + "." + FIELD_KODE_MOBIL + ", " + TABLE_MOBIL
			+ "." + FIELD_MERK_MOBIL + ", " + TABLE_MOBIL + "." + FIELD_NO_PLAT + ", " + TABLE_DETAIL_HASIL + "."
			+ FIELD_KODE_MOBIL + ", " + TABLE_HASIL + "." + FIELD_KODE_HASIL + ", " + TABLE_HASIL + "." + FIELD_KODE_HARGA
			+ ", " + TABLE_HASIL + "." + FIELD_TONASE + ", " + TABLE_HASIL + "." + FIELD_TANGGAL + ", " + TABLE_HASIL + "."
			+ FIELD_TOTAL_HASIL + ", " + TABLE_HASIL + "." + FIELD_POTONGAN_MOBIL + ", " + TABLE_HASIL + "."
			+ FIELD_POTONGAN_PEMUAT + ", " + TABLE_HASIL + "." + FIELD_POTONGAN_PENIMBANG + ", " + TABLE_HASIL + "."
			+ FIELD_POTONGAN_PENGURUS + ", " + TABLE_HASIL + "." + FIELD_POTONGAN_PAJAK + ", " + TABLE_HASIL + "."
			+ FIELD_TOTAL_POTONGAN + " FROM " + TABLE_ANGGOTA + " INNER JOIN " + TABLE_MOBIL + " ON " + TABLE_ANGGOTA + "."
			+ FIELD_KODE_ANGGOTA + "=" + TABLE_MOBIL + "." + FIELD_KODE_ANGGOTA + " INNER JOIN " + TABLE_HASIL
			+ " INNER JOIN " + TABLE_DETAIL_HASIL + " ON " + TABLE_HASIL + "." + FIELD_KODE_HASIL + "=" + TABLE_DETAIL_HASIL
			+ "." + FIELD_KODE_HASIL + " WHERE " + TABLE_ANGGOTA + "." + FIELD_KODE_ANGGOTA + " =:" + FIELD_KODE_ANGGOTA
			+ " AND  " + TABLE_MOBIL + "." + FIELD_KODE_MOBIL + " =:" + FIELD_KODE_MOBIL + " AND  " + TABLE_HASIL + "."
			+ FIELD_KODE_HASIL + " =:" + FIELD_KODE_HASIL + "";

	/**
	 * 
	 * @param kd_anggota
	 * @param kd_mobil
	 * @param kd_hasil
	 * @return
	 */
	@Query(value = queryFindDetailHasilMobil, nativeQuery = true)
	public DetailHasil findDetailHasilMobil(@PathParam(FIELD_KODE_ANGGOTA) String kd_anggota,
			@PathParam(FIELD_KODE_MOBIL) String kd_mobil, @PathParam(FIELD_KODE_HASIL) String kd_hasil);

}
