package hamparan.gapoktan.model.repository.hamparan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hamparan.gapoktan.model.entities.hamparan.Harga;
import jakarta.websocket.server.PathParam;

public interface HargaRepository extends JpaRepository<Harga, String> {

  /** Table Harga */
  public static final String TABLE_HARGA = "harga_hamparan";

  /** Field dari Table Harga */
  public static final String FIELD_KODE_HARGA = "kd_harga";
  public static final String FIELD_SAWIT = "sawit";
  public static final String FIELD_MOBIL = "mobil";
  public static final String FIELD_PEMUAT = "pemuat";
  public static final String FIELD_PENIMBANG = "penimbang";
  public static final String FIELD_PENGURUS = "pengurus";
  public static final String FIELD_PAJAK = "pajak";

  String queryFindMaxKodeHarga = "SELECT MAX(" + FIELD_KODE_HARGA + ") FROM " + TABLE_HARGA + "";

  @Query(value = queryFindMaxKodeHarga, nativeQuery = true)
  public String findMaxKodeHarga();

  String queryFindOneHargaByKode = "SELECT * FROM " + TABLE_HARGA + " WHERE " + TABLE_HARGA + "." + FIELD_KODE_HARGA
      + " = :" + FIELD_KODE_HARGA + "";

  @Query(value = queryFindOneHargaByKode, nativeQuery = true)
  public Harga findOneHargaByKode(@PathParam(FIELD_KODE_HARGA) String kd_harga);

  String queryfindLastHarga = "SELECT * FROM " + TABLE_HARGA + " ORDER BY " + FIELD_KODE_HARGA + " DESC ";

  @Query(value = queryfindLastHarga, nativeQuery = true)
  public List<Harga> findLastHarga();

}
