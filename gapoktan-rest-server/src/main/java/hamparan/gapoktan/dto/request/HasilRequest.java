package hamparan.gapoktan.dto.request;

import hamparan.gapoktan.validator.HasilKodeMatches;
import jakarta.validation.constraints.NotEmpty;

@HasilKodeMatches(message = "Input tidak valid! (Kode & Golongan harus singkron).")
public class HasilRequest {

  private String kd_hasil;

  @NotEmpty(message = "Kode anggota harus diisi!")
  private String kd_anggota;

  private String kd_kebun;

  private String kd_mobil;

  @NotEmpty(message = "Tanggal harus diisi!")
  private String tanggal;

  private int tonase;

  @NotEmpty(message = "Golongan harus diisi!")
  private String golongan;

  private char status;

  public void setKd_hasil(String kd_hasil) {
    this.kd_hasil = kd_hasil;
  }

  public String getKd_hasil() {
    return kd_hasil;
  }

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota;
  }

  public String getKd_kebun() {
    return kd_kebun;
  }

  public void setKd_kebun(String kd_kebun) {
    this.kd_kebun = kd_kebun;
  }

  public String getKd_mobil() {
    return kd_mobil;
  }

  public void setKd_mobil(String kd_mobil) {
    this.kd_mobil = kd_mobil;
  }

  public String getTanggal() {
    return tanggal;
  }

  public void setTanggal(String tanggal) {
    this.tanggal = tanggal;
  }

  public int getTonase() {
    return tonase;
  }

  public void setTonase(int tonase) {
    this.tonase = tonase;
  }

  public void setGolongan(String golongan) {
    this.golongan = golongan;
  }

  public String getGolongan() {
    return golongan;
  }

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = status;
  }

}
