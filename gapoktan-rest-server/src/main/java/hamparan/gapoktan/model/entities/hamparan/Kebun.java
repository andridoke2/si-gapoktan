package hamparan.gapoktan.model.entities.hamparan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "kebun_hamparan")
public class Kebun implements Serializable {

  @Id
  @NotEmpty(message = "Kode Kebun harus diisi.")
  private String kd_kebun;

  @NotEmpty(message = "Kode Anggota harus diisi.")
  private String kd_anggota;

  @NotEmpty(message = "Lokasi Kebun harus diisi.")
  private String lokasi;

  @NotEmpty(message = "Jenis Kebun harus diisi.")
  private String jenis_kebun;

  public Kebun() {
  }

  public Kebun(String kd_kebun, String kd_anggota, String lokasi, String jenis_kebun) {
    this.kd_kebun = kd_kebun;
    this.kd_anggota = kd_anggota;
    this.lokasi = lokasi;
    this.jenis_kebun = jenis_kebun;
  }

  public String getKd_kebun() {
    return kd_kebun;
  }

  public void setKd_kebun(String kd_kebun) {
    this.kd_kebun = kd_kebun;
  }

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota;
  }

  public String getLokasi() {
    return lokasi;
  }

  public void setLokasi(String lokasi) {
    this.lokasi = lokasi;
  }

  public String getJenis_kebun() {
    return jenis_kebun;
  }

  public void setJenis_kebun(String jenis_kebun) {
    this.jenis_kebun = jenis_kebun;
  }

}
