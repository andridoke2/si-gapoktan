package hamparan.gapoktan.model.entities.hamparan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "anggota_hamparan")
public class Anggota implements Serializable {

  @Id
  private String kd_anggota;

  @NotEmpty(message = "Nama harus diisi.")
  private String nama_anggota;

  @NotEmpty(message = "Alamat harus diisi.")
  private String alamat;

  private char jenis_kelamin;

  @NotEmpty(message = "No telepon harus diisi.")
  private String no_telpon;

  private int is_active;

  @NotEmpty(message = "Golongan harus diisi.")
  private String golongan;

  @NotEmpty(message = "Email harus diisi.")
  private String email;

  @NotEmpty(message = "Password harus diisi.")
  private String password;

  @NotEmpty(message = "Image harus diisi.")
  private String image;

  private String role;

  @NotEmpty(message = "Date created harus diisi.")
  private String date_created;

  public Anggota() {
  }

  public Anggota(
      String kd_anggota,
      String nama_anggota,
      String alamat,
      char jenis_kelamin,
      String no_telpon,
      int is_active,
      String golongan,
      String email,
      String password,
      String image,
      String role,
      String date_created) {
    this.kd_anggota = kd_anggota;
    this.nama_anggota = nama_anggota;
    this.alamat = alamat;
    this.jenis_kelamin = jenis_kelamin;
    this.no_telpon = no_telpon;
    this.is_active = is_active;
    this.golongan = golongan;
    this.email = email;
    this.password = password;
    this.image = image;
    this.role = role;
    this.date_created = date_created;
  }

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota;
  }

  public String getNama_anggota() {
    return nama_anggota;
  }

  public void setNama_anggota(String nama_anggota) {
    this.nama_anggota = nama_anggota;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public char getJenis_kelamin() {
    return jenis_kelamin;
  }

  public void setJenis_kelamin(char jenis_kelamin) {
    this.jenis_kelamin = jenis_kelamin;
  }

  public String getNo_telpon() {
    return no_telpon;
  }

  public void setNo_telpon(String no_telpon) {
    this.no_telpon = no_telpon;
  }

  public int getIs_active() {
    return is_active;
  }

  public void setIs_active(int is_active) {
    this.is_active = is_active;
  }

  public String getGolongan() {
    return golongan;
  }

  public void setGolongan(String golongan) {
    this.golongan = golongan;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getDate_created() {
    return date_created;
  }

  public void setDate_created(String date_created) {
    this.date_created = date_created;
  }

}
