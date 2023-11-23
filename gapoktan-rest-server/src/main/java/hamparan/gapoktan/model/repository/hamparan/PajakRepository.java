package hamparan.gapoktan.model.repository.hamparan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Pajak;
import jakarta.websocket.server.PathParam;

public interface PajakRepository extends JpaRepository<Pajak, Integer> {

  public static final String TABLE_PAJAK = "pajak_hamparan";

  public static final String FIELD_ID = "id";
  public static final String FIELD_KODE_ANGGOTA = "kd_anggota";
  public static final String FIELD_KODE_HASIL = "kd_hasil";
  public static final String FIELD_KODE_HARGA = "kd_harga";
  public static final String FIELD_TANGGAL = "tanggal";
  public static final String FIELD_TOTAL_PAJAK = "total_pajak";
  public static final String FIELD_GOLONGAN = "golongan";

  String queryFindPajakByKodeHasil = "SELECT * FROM " + TABLE_PAJAK + " WHERE " + TABLE_PAJAK + "." + FIELD_KODE_HASIL
      + " = :" + FIELD_KODE_HASIL + "";

  @Query(value = queryFindPajakByKodeHasil, nativeQuery = true)
  public Pajak findPajakByKodeHasil(@PathParam(FIELD_KODE_HASIL) String kd_hasil);

}
