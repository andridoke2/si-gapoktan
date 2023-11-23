package hamparan.gapoktan.dto.response;

public class DetailHasilResponse {

  private String kd_hasil;

  private String kd_anggota;

  private String kd_kebun;

  private String kd_mobil;

  private char status;

  public String getKd_hasil() {
    return kd_hasil;
  }

  public void setKd_hasil(String kd_hasil) {
    this.kd_hasil = kd_hasil;
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

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = status;
  }

}
