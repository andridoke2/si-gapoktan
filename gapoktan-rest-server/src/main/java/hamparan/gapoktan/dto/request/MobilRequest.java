package hamparan.gapoktan.dto.request;

import hamparan.gapoktan.validator.GolonganMobil;
import jakarta.validation.constraints.NotEmpty;

public class MobilRequest {

  private String kd_mobil;

  /**
   * Disini letak anotasi untuk kebutuhan validasi bahwa data mobil khusus bagi
   * anggota dengan golongan mobil saja.
   */
  @NotEmpty(message = "Kode anggota harus diisi!")
  @GolonganMobil(message = "Mobil khusus bagi Anggota dengan golongan Mobil!")
  private String kd_anggota;

  @NotEmpty(message = "Merk mobil harus diisi!")
  private String merk_mobil;

  @NotEmpty(message = "No plat kendaraan harus diisi!")
  private String nomor_plat_kendaraan;

  public String getKd_mobil() {
    return kd_mobil;
  }

  public void setKd_mobil(String kd_mobil) {
    this.kd_mobil = kd_mobil;
  }

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota;
  }

  public String getMerk_mobil() {
    return merk_mobil;
  }

  public void setMerk_mobil(String merk_mobil) {
    this.merk_mobil = merk_mobil;
  }

  public String getNomor_plat_kendaraan() {
    return nomor_plat_kendaraan;
  }

  public void setNomor_plat_kendaraan(String nomor_plat_kendaraan) {
    this.nomor_plat_kendaraan = nomor_plat_kendaraan;
  }

}
