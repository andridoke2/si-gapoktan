package hamparan.gapoktan.dto.request;

import hamparan.gapoktan.validator.GolonganPetaniKebun;
import jakarta.validation.constraints.NotEmpty;

public class KebunRequest {

  private String kd_kebun;

  @NotEmpty(message = "Kode Anggota harus diisi!")
  @GolonganPetaniKebun(message = "Kebun khusus bagi Anggota dengan golongan Petani!")
  private String kd_anggota;

  @NotEmpty(message = "Lokasi Kebun harus diisi!")
  private String lokasi;

  @NotEmpty(message = "Jenis Kebun harus diisi!")
  private String jenis_kebun;

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota.trim();
  }

  public String getLokasi() {
    return lokasi;
  }

  public void setLokasi(String lokasi) {
    this.lokasi = lokasi.trim();
  }

  public String getJenis_kebun() {
    return jenis_kebun;
  }

  public void setJenis_kebun(String jenis_kebun) {
    this.jenis_kebun = jenis_kebun.trim();
  }

  public String getKd_kebun() {
    return kd_kebun;
  }

  public void setKd_kebun(String kd_kebun) {
    this.kd_kebun = kd_kebun.trim();
  }

}
