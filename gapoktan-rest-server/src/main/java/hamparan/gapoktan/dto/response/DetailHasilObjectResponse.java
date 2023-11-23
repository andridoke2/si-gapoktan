package hamparan.gapoktan.dto.response;

public class DetailHasilObjectResponse {

  private AnggotaResponse anggota;

  private KebunResponse kebun;

  private MobilResponse mobil;

  private HasilResponse hasil;

  private HargaResponse harga;

  public DetailHasilObjectResponse() {
  }

  public DetailHasilObjectResponse(AnggotaResponse anggota, HasilResponse hasil, HargaResponse harga) {
    this.anggota = anggota;
    this.hasil = hasil;
    this.harga = harga;
  }

  public DetailHasilObjectResponse(AnggotaResponse anggota, MobilResponse mobil, HasilResponse hasil,
      HargaResponse harga) {
    this.anggota = anggota;
    this.mobil = mobil;
    this.hasil = hasil;
    this.harga = harga;
  }

  public DetailHasilObjectResponse(AnggotaResponse anggota, KebunResponse kebun, HasilResponse hasil,
      HargaResponse harga) {
    this.anggota = anggota;
    this.kebun = kebun;
    this.hasil = hasil;
    this.harga = harga;
  }

  public AnggotaResponse getAnggota() {
    return anggota;
  }

  public void setAnggota(AnggotaResponse anggota) {
    this.anggota = anggota;
  }

  public KebunResponse getKebun() {
    return kebun;
  }

  public void setKebun(KebunResponse kebun) {
    this.kebun = kebun;
  }

  public MobilResponse getMobil() {
    return mobil;
  }

  public void setMobil(MobilResponse mobil) {
    this.mobil = mobil;
  }

  public HasilResponse getHasil() {
    return hasil;
  }

  public void setHasil(HasilResponse hasil) {
    this.hasil = hasil;
  }

  public void setHarga(HargaResponse harga) {
    this.harga = harga;
  }

  public HargaResponse getHarga() {
    return harga;
  }

}
