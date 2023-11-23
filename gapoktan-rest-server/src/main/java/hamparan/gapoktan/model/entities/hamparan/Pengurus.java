package hamparan.gapoktan.model.entities.hamparan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pengurus_hamparan")
public class Pengurus implements Serializable {

  @Id
  private int id;

  private String kd_anggota;

  private String kd_hasil;

  private String kd_harga;

  private String tanggal;

  private int total_pengurus;

  public Pengurus() {
  }

  public Pengurus(int id, String kd_anggota, String kd_hasil, String kd_harga, String tanggal, int total_pengurus) {
    this.id = id;
    this.kd_anggota = kd_anggota;
    this.kd_hasil = kd_hasil;
    this.kd_harga = kd_harga;
    this.tanggal = tanggal;
    this.total_pengurus = total_pengurus;
  }

  public Pengurus(String kd_anggota, String kd_hasil, String kd_harga, String tanggal, int total_pengurus) {
    this.kd_anggota = kd_anggota;
    this.kd_hasil = kd_hasil;
    this.kd_harga = kd_harga;
    this.tanggal = tanggal;
    this.total_pengurus = total_pengurus;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public int getTotal_pengurus() {
    return total_pengurus;
  }

  public void setTotal_pengurus(int total_pengurus) {
    this.total_pengurus = total_pengurus;
  }

}
