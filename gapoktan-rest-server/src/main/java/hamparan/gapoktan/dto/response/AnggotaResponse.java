package hamparan.gapoktan.dto.response;

public class AnggotaResponse {

  private String kd_anggota;

  private String nama_anggota;

  private String alamat;

  private char jenis_kelamin;

  private String no_telpon;

  private int is_active;

  private String golongan;

  private String email;

  private String image;

  private String role;

  private String date_created;

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
