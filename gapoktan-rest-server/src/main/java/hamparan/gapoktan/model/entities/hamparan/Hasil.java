package hamparan.gapoktan.model.entities.hamparan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hasil_hamparan")
public class Hasil implements Serializable {

  @Id
  private String kd_hasil;

  private String kd_harga;

  private int tonase;

  private String tanggal;

  private int total_hasil;

  private int potongan_mobil;

  private int potongan_pemuat;

  private int potongan_penimbang;

  private int potongan_pengurus;

  private int potongan_pajak;

  private int total_potongan;

  private String golongan;

  public Hasil() {
  }

  public Hasil(String kd_hasil, String kd_harga, int tonase, String tanggal, int total_hasil, int potongan_mobil,
      int potongan_pemuat, int potongan_penimbang, int potongan_pengurus, int potongan_pajak, int total_potongan,
      String golongan) {
    this.kd_hasil = kd_hasil;
    this.kd_harga = kd_harga;
    this.tonase = tonase;
    this.tanggal = tanggal;
    this.total_hasil = total_hasil;
    this.potongan_mobil = potongan_mobil;
    this.potongan_pemuat = potongan_pemuat;
    this.potongan_penimbang = potongan_penimbang;
    this.potongan_pengurus = potongan_pengurus;
    this.potongan_pajak = potongan_pajak;
    this.total_potongan = total_potongan;
    this.golongan = golongan;
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

  public int getTonase() {
    return tonase;
  }

  public void setTonase(int tonase) {
    this.tonase = tonase;
  }

  public String getTanggal() {
    return tanggal;
  }

  public void setTanggal(String tanggal) {
    this.tanggal = tanggal;
  }

  public int getTotal_hasil() {
    return total_hasil;
  }

  public void setTotal_hasil(int total_hasil) {
    this.total_hasil = total_hasil;
  }

  public int getPotongan_mobil() {
    return potongan_mobil;
  }

  public void setPotongan_mobil(int potongan_mobil) {
    this.potongan_mobil = potongan_mobil;
  }

  public int getPotongan_pemuat() {
    return potongan_pemuat;
  }

  public void setPotongan_pemuat(int potongan_pemuat) {
    this.potongan_pemuat = potongan_pemuat;
  }

  public int getPotongan_penimbang() {
    return potongan_penimbang;
  }

  public void setPotongan_penimbang(int potongan_penimbang) {
    this.potongan_penimbang = potongan_penimbang;
  }

  public int getPotongan_pengurus() {
    return potongan_pengurus;
  }

  public void setPotongan_pengurus(int potongan_pengurus) {
    this.potongan_pengurus = potongan_pengurus;
  }

  public int getPotongan_pajak() {
    return potongan_pajak;
  }

  public void setPotongan_pajak(int potongan_pajak) {
    this.potongan_pajak = potongan_pajak;
  }

  public int getTotal_potongan() {
    return total_potongan;
  }

  public void setTotal_potongan(int total_potongan) {
    this.total_potongan = total_potongan;
  }

  public void setGolongan(String golongan) {
    this.golongan = golongan;
  }

  public String getGolongan() {
    return golongan;
  }

}
