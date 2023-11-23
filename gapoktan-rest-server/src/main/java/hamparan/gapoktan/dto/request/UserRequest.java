package hamparan.gapoktan.dto.request;

import hamparan.gapoktan.validator.PasswordEqualConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@PasswordEqualConstraint(message = "Retype password tidak sama!")
public class UserRequest {

  private String kd_anggota;

  @NotEmpty(message = "Nama harus diisi!")
  private String nama_anggota;

  @NotEmpty(message = "Alamat harus diisi!")
  private String alamat;

  private char jenis_kelamin;

  @NotEmpty(message = "Nomor telpon harus diisi!")
  private String no_telpon;

  @NotEmpty(message = "Email harus diisi!")
  @Email(message = "Email tidak valid!")
  private String email;

  @NotEmpty(message = "Password harus diisi!")
  private String password;

  @NotEmpty(message = "Retype password harus diisi!")
  private String password_retype;

  private int is_active;

  private String golongan;

  private String image;

  private String role;

  private String date_created;

  public String getKd_anggota() {
    return kd_anggota;
  }

  public void setKd_anggota(String kd_anggota) {
    this.kd_anggota = kd_anggota.trim();
  }

  public String getNama_anggota() {
    return nama_anggota;
  }

  public void setNama_anggota(String nama_anggota) {
    this.nama_anggota = nama_anggota.trim();
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat.trim();
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
    this.no_telpon = no_telpon.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email.trim();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password.trim();
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
    this.golongan = golongan.trim();
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image.trim();
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role.trim();
  }

  public String getDate_created() {
    return date_created;
  }

  public void setDate_created(String date_created) {
    this.date_created = date_created;
  }

  public String getPassword_retype() {
    return password_retype;
  }

  public void setPassword_retype(String password_retype) {
    this.password_retype = password_retype.trim();
  }

}