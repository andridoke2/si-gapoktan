package hamparan.gapoktan.model.repository.hamparan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Pengurus;
import jakarta.websocket.server.PathParam;

public interface PengurusRepository extends JpaRepository<Pengurus, Integer> {

  /** Table Pengurus */
  public static final String TABLE_PENGURUS = "pengurus_hamparan";

  /** Field Pengurus */
  public static final String FIELD_ID = "id";
  public static final String FIELD_KODE_ANGGOTA = "kd_anggota";
  public static final String FIELD_KODE_HASIL = "kd_hasil";
  public static final String FIELD_KODE_HARGA = "kd_harga";
  public static final String FIELD_TANGGAL = "tanggal";
  public static final String FIELD_TOTAL_PENGURUS = "total_pengurus";
  public static final String FIELD_GOLONGAN = "golongan";

  String queryFindPengurusByKodeHasil = "SELECT * FROM " + TABLE_PENGURUS + " WHERE " + TABLE_PENGURUS + "."
      + FIELD_KODE_HASIL + " = :" + FIELD_KODE_HASIL + "";

  @Query(value = queryFindPengurusByKodeHasil, nativeQuery = true)
  public Pengurus findPengurusByKodeHasil(@PathParam(FIELD_KODE_HASIL) String kd_hasil);

}
