package hamparan.gapoktan.helpers;

import java.sql.Timestamp;

import hamparan.gapoktan.model.entities.hamparan.Anggota;

public class DefaultData {

  public String setDefaultPassword(String password, String kode) {
    int kodeDigit = Integer.parseInt(kode.substring(3, 7));
    String defaultPassword = "";
    if (password == null || password == "") {
      defaultPassword = kode + "" + (kodeDigit);
    } else {
      defaultPassword = password;
    }
    return defaultPassword;
  }

  public String defaultDateCreated(Anggota anggota) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String dateCreated;
    if (anggota.getDate_created() == null) {
      dateCreated = String.valueOf(timestamp.getTime());
    } else {
      dateCreated = anggota.getDate_created();
    }
    return dateCreated;
  }

  public int defaultActive(Anggota anggota) {
    int isActive;
    if (anggota.getIs_active() == 0) {
      isActive = 1;
    } else {
      isActive = anggota.getIs_active();
    }
    return isActive;
  }

  public String defaultImage(Anggota anggota) {
    String image;
    if (anggota.getImage() == null) {
      image = "default.png";
    } else {
      image = anggota.getImage();
    }
    return image;
  }

}
