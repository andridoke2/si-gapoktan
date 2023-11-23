package hamparan.gapoktan.controllers.hamparan;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hamparan.gapoktan.dto.ResponseData;
import hamparan.gapoktan.dto.SearchData;
import hamparan.gapoktan.dto.request.DetailHasilRequest;
import hamparan.gapoktan.dto.response.AnggotaResponse;
import hamparan.gapoktan.dto.response.DetailHasilObjectResponse;
import hamparan.gapoktan.dto.response.DetailHasilResponse;
import hamparan.gapoktan.dto.response.HargaResponse;
import hamparan.gapoktan.dto.response.HasilResponse;
import hamparan.gapoktan.dto.response.KebunResponse;
import hamparan.gapoktan.dto.response.MobilResponse;
import hamparan.gapoktan.model.entities.hamparan.Anggota;
import hamparan.gapoktan.model.entities.hamparan.DetailHasil;
import hamparan.gapoktan.model.entities.hamparan.Harga;
import hamparan.gapoktan.model.entities.hamparan.Hasil;
import hamparan.gapoktan.model.entities.hamparan.Kebun;
import hamparan.gapoktan.model.entities.hamparan.Mobil;
import hamparan.gapoktan.services.hamparan.AnggotaService;
import hamparan.gapoktan.services.hamparan.DetailHasilService;
import hamparan.gapoktan.services.hamparan.HargaService;
import hamparan.gapoktan.services.hamparan.HasilService;
import hamparan.gapoktan.services.hamparan.KebunService;
import hamparan.gapoktan.services.hamparan.MobilService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/detailhasil")
public class DetailHasilController {

  @Autowired
  private DetailHasilService detailHasilService;

  @Autowired
  private AnggotaService anggotaService;

  @Autowired
  private KebunService kebunService;

  @Autowired
  private MobilService mobilService;

  @Autowired
  private HasilService hasilService;

  @Autowired
  private HargaService hargaService;

  @Autowired
  private ModelMapper modelMapper;

  private Anggota anggota;
  private Kebun kebun;
  private Mobil mobil;
  private Hasil hasil;
  private Harga harga;

  /**
   * Find All Detail Hasil
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<DetailHasilResponse>>> findAll() {
    ResponseData<List<DetailHasilResponse>> response = new ResponseData<>();
    List<DetailHasilResponse> listDetailHasil = new ArrayList<>();

    detailHasilService.findAll().forEach(detailHasil -> {
      listDetailHasil.add(modelMapper.map(detailHasil, DetailHasilResponse.class));
    });

    response.setStatus(true);
    response.setPayload(listDetailHasil);
    response.getMessage().add("Data detail hasil berhasil ditemukan!");
    return ResponseEntity.ok(response);
  }

  /**
   * Endpoint ini berfungsi untuk mendapatkan data penghasilan anggota hamparan.
   * Diimplementasikan khusus ketika anggota login dan ingin melihat laporan
   * penghasilan yang dimiliki.
   * 
   * @param searchData
   * @param errors
   * @return
   */
  @PostMapping("/penghasilan/anggota")
  public ResponseEntity<ResponseData<List<DetailHasilResponse>>> findByKodeAnggota(
      @Valid @RequestBody SearchData searchData, Errors errors) {
    ResponseData<List<DetailHasilResponse>> response = new ResponseData<>();
    List<DetailHasilResponse> listDetailHasil = new ArrayList<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      detailHasilService.findDetailHasilByKodeAnggota(searchData.getSearchKey()).forEach(detailHasil -> {
        listDetailHasil.add(modelMapper.map(detailHasil, DetailHasilResponse.class));
      });

      response.setStatus(true);
      response.setPayload(listDetailHasil);
      response.getMessage().add("Data detail hasil : " + searchData.getSearchKey() + " berhasil ditemukan!");
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * FIND DETAIL HASIL ANGGOTA (PENGHASILAN)
   * Note:
   * fungsi ini belum menggunakan perintah join sql. Karena belum menggunakan join
   * sql, ketika user salah meng-inputkan kode namun data akan tetap
   * diberikan oleh sistem. Dengan kata lain tidak ada validasi disini.
   * 
   * Perkiraan:
   * jika ingin menggunakan perintah sql join table,
   * maka buat dua entity & repository untuk detail hasil ini.
   * 
   * @param request
   * @param errors
   * @return
   */
  @PostMapping("/penghasilan")
  public ResponseEntity<ResponseData<DetailHasilObjectResponse>> findDetailHasil(
      @Valid @RequestBody DetailHasilRequest request, Errors errors) {

    ResponseData<DetailHasilObjectResponse> response = new ResponseData<>();

    DetailHasilObjectResponse objectResponse = new DetailHasilObjectResponse();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {

      switch (anggotaService.findOneAnggotaByKode(request.getKd_anggota()).getGolongan()) {
        case "Petani":
          anggota = anggotaService.findOneAnggotaByKode(request.getKd_anggota());
          kebun = kebunService.findOneKebunByKode(request.getKd_kebun());
          hasil = hasilService.findOneHasilByKode(request.getKd_hasil());
          harga = hargaService.findOneHargaByKode(hasil.getKd_harga());

          objectResponse = new DetailHasilObjectResponse(
              modelMapper.map(anggota, AnggotaResponse.class),
              modelMapper.map(kebun, KebunResponse.class),
              modelMapper.map(hasil, HasilResponse.class),
              modelMapper.map(harga, HargaResponse.class));
          break;
        case "Mobil":
          anggota = anggotaService.findOneAnggotaByKode(request.getKd_anggota());
          hasil = hasilService.findOneHasilByKode(request.getKd_hasil());
          harga = hargaService.findOneHargaByKode(hasil.getKd_harga());
          mobil = mobilService.findOneMobilByKode(request.getKd_mobil());

          objectResponse = new DetailHasilObjectResponse(
              modelMapper.map(anggota, AnggotaResponse.class),
              modelMapper.map(mobil, MobilResponse.class),
              modelMapper.map(hasil, HasilResponse.class),
              modelMapper.map(harga, HargaResponse.class));
          break;
        case "Pemuat":
          anggota = anggotaService.findOneAnggotaByKode(request.getKd_anggota());
          hasil = hasilService.findOneHasilByKode(request.getKd_hasil());
          harga = hargaService.findOneHargaByKode(hasil.getKd_harga());

          objectResponse = new DetailHasilObjectResponse(
              modelMapper.map(anggota, AnggotaResponse.class),
              modelMapper.map(hasil, HasilResponse.class),
              modelMapper.map(harga, HargaResponse.class));
          break;
        case "Penimbang":
          anggota = anggotaService.findOneAnggotaByKode(request.getKd_anggota());
          hasil = hasilService.findOneHasilByKode(request.getKd_hasil());
          harga = hargaService.findOneHargaByKode(hasil.getKd_harga());

          objectResponse = new DetailHasilObjectResponse(
              modelMapper.map(anggota, AnggotaResponse.class),
              modelMapper.map(hasil, HasilResponse.class),
              modelMapper.map(harga, HargaResponse.class));
          break;
      }

      response.setStatus(true);
      response.getMessage().add("Data berhasil ditemukan!");
      response.setPayload(objectResponse);
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PostMapping("/konfirmasi")
  public ResponseEntity<ResponseData<DetailHasilResponse>> konfirmasi(@Valid @RequestBody DetailHasilRequest request,
      Errors errors) {
    ResponseData<DetailHasilResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      DetailHasil detailHasil = modelMapper.map(request, DetailHasil.class);
      detailHasil = detailHasilService.save(detailHasil);

      response.setStatus(true);
      response.getMessage().add("Penghasilan berhasil dikonfirmasi!");
      response.setPayload(modelMapper.map(detailHasil, DetailHasilResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}
