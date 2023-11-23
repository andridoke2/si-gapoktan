package hamparan.gapoktan.model.repository.hamparan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Hasil;
import jakarta.websocket.server.PathParam;

public interface HasilRepository extends JpaRepository<Hasil, String> {

  /** Table Hasil */
  public static final String TABLE_HASIL = "hasil_hamparan";

  /** Field Hasil */
  public static final String FIELD_KODE_HASIL = "kd_hasil";
  public static final String FIELD_KODE_HARGA = "kd_harga";
  public static final String FIELD_TONASE = "tonase";
  public static final String FIELD_TANGGAL = "tanggal";
  public static final String FIELD_TOTAL_HASIL = "total_hasil";
  public static final String FIELD_POTONGAN_MOBIL = "potongan_mobil";
  public static final String FIELD_POTONGAN_PEMUAT = "potongan_pemuat";
  public static final String FIELD_POTONGAN_PENIMBANG = "potongan_penimbang";
  public static final String FIELD_POTONGAN_PENGURUS = "potongan_pengurus";
  public static final String FIELD_POTONGAN_PAJAK = "potongan_pajak";
  public static final String FIELD_TOTAL_POTONGAN = "total_potongan";
  public static final String FIELD_GOLONGAN = "golongan";

  String queryFindMaxKodeHasil = "SELECT MAX(" + FIELD_KODE_HASIL + ") FROM " + TABLE_HASIL + " WHERE " + TABLE_HASIL
      + "." + FIELD_GOLONGAN + " = :" + FIELD_GOLONGAN + "";

  @Query(value = queryFindMaxKodeHasil, nativeQuery = true)
  public String findMaxKodeHasil(@PathParam(FIELD_GOLONGAN) String golongan);

  String queryFindOneHasilByKode = "SELECT * FROM " + TABLE_HASIL + " WHERE " + TABLE_HASIL + "." + FIELD_KODE_HASIL
      + " = :" + FIELD_KODE_HASIL + "";

  @Query(value = queryFindOneHasilByKode, nativeQuery = true)
  public Hasil findOneHasilByKode(@PathParam(FIELD_KODE_HASIL) String kd_hasil);
}
