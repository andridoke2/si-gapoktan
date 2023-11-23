package hamparan.gapoktan.helpers;

public class GenerateKode {

  private int angkaKode = 0;

  private String kode = "";

  public String generateKodeAnggota(String golongan, String maxKode) {
    String[] inisial = { "PTI", "PMB", "PMT", "PNB", "PRS" };
    if (maxKode == null) {
      this.angkaKode = 1000;
      this.angkaKode += 1;
      switch (golongan) {
        case "Petani":
          this.kode = String.valueOf(inisial[0] + this.angkaKode);
          break;
        case "Mobil":
          this.kode = String.valueOf(inisial[1] + this.angkaKode);
          break;
        case "Pemuat":
          this.kode = String.valueOf(inisial[2] + this.angkaKode);
          break;
        case "Penimbang":
          this.kode = String.valueOf(inisial[3] + this.angkaKode);
          break;
        case "Pengurus":
          this.kode = String.valueOf(inisial[4] + this.angkaKode);
          break;
        default:
          System.out.println("Golongan tidak terdaftar didalam anggota!");
          break;
      }
    } else {
      this.angkaKode = Integer.parseInt(maxKode.substring(3, 7));
      this.angkaKode += 1;
      switch (golongan) {
        case "Petani":
          this.kode = String.valueOf(inisial[0] + this.angkaKode);
          break;
        case "Mobil":
          this.kode = String.valueOf(inisial[1] + this.angkaKode);
          break;
        case "Pemuat":
          this.kode = String.valueOf(inisial[2] + this.angkaKode);
          break;
        case "Penimbang":
          this.kode = String.valueOf(inisial[3] + this.angkaKode);
          break;
        case "Pengurus":
          this.kode = String.valueOf(inisial[4] + this.angkaKode);
          break;
        default:
          System.out.println("Golongan tidak ditemukan pada anggota!");
          break;
      }
    }
    return this.kode;
  }

  public String generateKodeHasil(String golongan, String maxKode) {
    String[] inisial = { "H-PTI", "H-PMB", "H-PMT", "H-PNB" };
    if (maxKode == null) {
      this.angkaKode = 1000;
      this.angkaKode += 1;
      switch (golongan) {
        case "Petani":
          this.kode = String.valueOf(inisial[0] + this.angkaKode);
          break;
        case "Mobil":
          this.kode = String.valueOf(inisial[1] + this.angkaKode);
          break;
        case "Pemuat":
          this.kode = String.valueOf(inisial[2] + this.angkaKode);
          break;
        case "Penimbang":
          this.kode = String.valueOf(inisial[3] + this.angkaKode);
          break;
        default:
          System.out.println("Golongan tidak terdaftar didalam anggota!");
          break;
      }
    } else {
      this.angkaKode = Integer.parseInt(maxKode.substring(5, 9));
      this.angkaKode += 1;
      switch (golongan) {
        case "Petani":
          this.kode = String.valueOf(inisial[0] + this.angkaKode);
          break;
        case "Mobil":
          this.kode = String.valueOf(inisial[1] + this.angkaKode);
          break;
        case "Pemuat":
          this.kode = String.valueOf(inisial[2] + this.angkaKode);
          break;
        case "Penimbang":
          this.kode = String.valueOf(inisial[3] + this.angkaKode);
          break;
        default:
          System.out.println("Golongan tidak ditemukan pada anggota!");
          break;
      }
    }
    return this.kode;
  }

  /**
   * untuk generate kode kebun, mobil, hasil & gaji sebenarnya bisa dijadikan satu
   * method.
   * 
   * @param maxKode
   * @return
   */

  public String generateKodeKebun(String maxKode) {
    String inisial = "KBN";
    if (maxKode == null) {
      this.angkaKode = 1000;
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    } else {
      this.angkaKode = Integer.parseInt(maxKode.substring(3, 7));
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    }
    return this.kode;
  }

  public String generateKodeMobil(String maxKode) {
    String inisial = "MBL";
    if (maxKode == null) {
      this.angkaKode = 1000;
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    } else {
      this.angkaKode = Integer.parseInt(maxKode.substring(3, 7));
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    }
    return this.kode;
  }

  public String generateKodeHarga(String maxKode) {
    String inisial = "HRG";
    if (maxKode == null) {
      this.angkaKode = 1000;
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    } else {
      this.angkaKode = Integer.parseInt(maxKode.substring(3, 7));
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    }
    return this.kode;
  }

  public String generateKodeGaji(String maxKode) {
    String inisial = "GJI";
    if (maxKode == null) {
      this.angkaKode = 1000;
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    } else {
      this.angkaKode = Integer.parseInt(maxKode.substring(3, 7));
      this.angkaKode += 1;
      this.kode = String.valueOf(inisial + "" + this.angkaKode);
    }
    return this.kode;
  }
}
