package hamparan.gapoktan.dto.request;

public class HargaRequest {

  private String kd_harga;

  private int sawit;

  private int mobil;

  private int pemuat;

  private int penimbang;

  private int pengurus;

  private double pajak;

  public String getKd_harga() {
    return kd_harga;
  }

  public void setKd_harga(String kd_harga) {
    this.kd_harga = kd_harga;
  }

  public int getSawit() {
    return sawit;
  }

  public void setSawit(int sawit) {
    this.sawit = sawit;
  }

  public int getMobil() {
    return mobil;
  }

  public void setMobil(int mobil) {
    this.mobil = mobil;
  }

  public int getPemuat() {
    return pemuat;
  }

  public void setPemuat(int pemuat) {
    this.pemuat = pemuat;
  }

  public int getPenimbang() {
    return penimbang;
  }

  public void setPenimbang(int penimbang) {
    this.penimbang = penimbang;
  }

  public int getPengurus() {
    return pengurus;
  }

  public void setPengurus(int pengurus) {
    this.pengurus = pengurus;
  }

  public double getPajak() {
    return pajak;
  }

  public void setPajak(double pajak) {
    this.pajak = pajak;
  }

}
