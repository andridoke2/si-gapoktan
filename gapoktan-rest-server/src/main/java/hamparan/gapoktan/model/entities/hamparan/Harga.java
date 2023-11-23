package hamparan.gapoktan.model.entities.hamparan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "harga_hamparan")
public class Harga implements Serializable {

  @Id
  private String kd_harga;

  private int sawit;

  private int mobil;

  private int pemuat;

  private int penimbang;

  private int pengurus;

  private double pajak;

  private String date_created;

  public Harga() {
  }

  public Harga(String kd_harga, int sawit, int mobil, int pemuat, int penimbang, int pengurus, double pajak,
      String date_created) {
    this.kd_harga = kd_harga;
    this.sawit = sawit;
    this.mobil = mobil;
    this.pemuat = pemuat;
    this.penimbang = penimbang;
    this.pengurus = pengurus;
    this.pajak = pajak;
    this.date_created = date_created;
  }

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

  public void setDate_created(String date_created) {
    this.date_created = date_created;
  }

  public String getDate_created() {
    return date_created;
  }

}
