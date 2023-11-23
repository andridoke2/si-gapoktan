package hamparan.gapoktan.model.entities.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "anggota_hamparan")
public class User implements UserDetails {

  @Id
  private String kd_anggota;

  private String nama_anggota;

  private String alamat;

  private char jenis_kelamin;

  private String no_telpon;

  private String email;

  private String password;

  private int is_active;

  private String golongan;

  private String image;

  private String date_created;

  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDate_created() {
    return date_created;
  }

  public void setDate_created(String date_created) {
    this.date_created = date_created;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public void setRole(UserRoleEnum role) {
    this.role = role;
  }

}
