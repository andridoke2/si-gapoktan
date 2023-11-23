package hamparan.gapoktan.model.entities.hamparan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mobil_hamparan")
public class Mobil implements Serializable {

  @Id
  private String kd_mobil;

  private String kd_anggota;

  private String merk_mobil;

  private String nomor_plat_kendaraan;

  public Mobil() {
  }

  public Mobil(String kd_mobil, String kd_anggota, String merk_mobil, String nomor_plat_kendaraan) {
    this.kd_mobil = kd_mobil;
    this.kd_anggota = kd_anggota;
    this.merk_mobil = merk_mobil;
    this.nomor_plat_kendaraan = nomor_plat_kendaraan;
  }

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
