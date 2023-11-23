package hamparan.gapoktan.dto.response;

public class PajakResponse {

  private String kd_anggota;

  private String kd_hasil;

  private String kd_harga;

  private String tanggal;

  private int total_pajak;

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota;
  }

  public String getKd_hasil() {
    return kd_hasil;
  }

  public void setKd_hasil(String kd_hasil) {
    this.kd_hasil = kd_hasil;
  }

  public String getKd_harga() {
    return kd_harga;
  }

  public void setKd_harga(String kd_harga) {
    this.kd_harga = kd_harga;
  }

  public String getTanggal() {
    return tanggal;
  }

  public void setTanggal(String tanggal) {
    this.tanggal = tanggal;
  }

  public int getTotal_pajak() {
    return total_pajak;
  }

  public void setTotal_pajak(int total_pajak) {
    this.total_pajak = total_pajak;
  }

}
