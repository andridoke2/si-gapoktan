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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hamparan.gapoktan.dto.ResponseData;
import hamparan.gapoktan.dto.request.HasilRequest;
import hamparan.gapoktan.dto.response.HasilResponse;
import hamparan.gapoktan.model.entities.hamparan.DetailHasil;
import hamparan.gapoktan.model.entities.hamparan.Hasil;
import hamparan.gapoktan.model.entities.hamparan.Pajak;
import hamparan.gapoktan.model.entities.hamparan.Pengurus;
import hamparan.gapoktan.services.hamparan.DetailHasilService;
import hamparan.gapoktan.services.hamparan.HasilService;
import hamparan.gapoktan.services.hamparan.PajakService;
import hamparan.gapoktan.services.hamparan.PengurusService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/hasil")
public class HasilController {

  @Autowired
  private HasilService hasilService;

  @Autowired
  private PengurusService pengurusService;

  @Autowired
  private PajakService pajakService;

  @Autowired
  private DetailHasilService detailHasilService;

  @Autowired
  private ModelMapper modelMapper;

  private Pajak pajak;
  private Pengurus pengurus;
  private DetailHasil detailHasil;

  /**
   * CREATE HASIL
   * 
   * @param request
   * @param errors
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseData<HasilResponse>> create(@Valid @RequestBody HasilRequest request, Errors errors) {
    ResponseData<HasilResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Hasil hasil = modelMapper.map(request, Hasil.class);
      /** save hasil anggota */
      hasil = hasilService.save(hasil);
      System.out.println("Save hasil anggota...");

      if (request.getStatus() == '\u0000') {
        request.setStatus('N');
      }

      switch (hasil.getGolongan()) {
        case "Petani":
          /** potongan pengurus petani */
          pengurus = new Pengurus(
              request.getKd_anggota(),
              hasil.getKd_hasil(),
              hasil.getKd_harga(),
              request.getTanggal(),
              hasil.getPotongan_pengurus());

          /** potongan pajak petani */
          pajak = new Pajak(
              request.getKd_anggota(),
              hasil.getKd_hasil(),
              hasil.getKd_harga(),
              request.getTanggal(),
              hasil.getPotongan_pajak());

          /** set detail hasil petani */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              request.getKd_kebun(),
              null,
              request.getStatus());

          /** save potongan pajak & pengurus petani */
          pajakService.save(pajak);
          pengurusService.save(pengurus);
          System.out.println("Save potongan pajak & pengurus...");
          break;
        case "Mobil":
          /** set detail hasil mobil */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              null,
              request.getKd_mobil(),
              request.getStatus());
          break;
        case "Pemuat":
          /** set detail hasil pemuat */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              null,
              null,
              request.getStatus());
          break;
        case "Penimbang":
          /** set detail hasil penimbang */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              null,
              null,
              request.getStatus());
          break;
        default:
          throw new Exception("Golongan tidak ditemukan");
      }

      /** save detail hasil petani, mobil, pemuat & penimbang to database */
      detailHasilService.save(detailHasil);
      System.out.println("Save detail hasil...");

      response.setStatus(true);
      response.getMessage().add("Hasil berhasil disimpan!");
      response.setPayload(modelMapper.map(hasil, HasilResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * UPDATE HASIL
   * 
   * @param request
   * @param errors
   * @return
   */
  @PutMapping
  public ResponseEntity<ResponseData<HasilResponse>> update(@Valid @RequestBody HasilRequest request, Errors errors) {
    ResponseData<HasilResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Hasil hasil = modelMapper.map(request, Hasil.class);
      /** save hasil anggota */
      hasil = hasilService.save(hasil);

      /** current detail hasil */
      DetailHasil currentDetailHasil = detailHasilService.findDetailHasilByKodeHasil(request.getKd_hasil());

      /**
       * current status detail hasil
       * jika status tidak dikirim, maka gunakan data status yang sudah ada.
       */
      if (request.getStatus() == '\u0000') {
        request.setStatus(currentDetailHasil.getStatus());
      }

      switch (hasil.getGolongan()) {
        case "Petani":
          /** get current potongan pengurus petani */
          Pengurus currentPengurus = pengurusService.findPengurusByKodeHasil(hasil.getKd_hasil());

          /** potongan pengurus petani */
          pengurus = new Pengurus(
              currentPengurus.getId(),
              request.getKd_anggota(),
              hasil.getKd_hasil(),
              hasil.getKd_harga(),
              request.getTanggal(),
              hasil.getPotongan_pengurus());

          /** get current potongan pajak petani */
          Pajak currentPajak = pajakService.findPajakByKodeHasil(hasil.getKd_hasil());

          /** potongan pajak petani */
          pajak = new Pajak(
              currentPajak.getId(),
              request.getKd_anggota(),
              hasil.getKd_hasil(),
              hasil.getKd_harga(),
              request.getTanggal(),
              hasil.getPotongan_pajak());

          /** set detail hasil petani */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              request.getKd_kebun(),
              null,
              request.getStatus());

          /** save potongan pajak & pengurus petani */
          pajakService.save(pajak);
          pengurusService.save(pengurus);
          break;
        case "Mobil":
          /** set detail hasil mobil */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              null,
              request.getKd_mobil(),
              request.getStatus());
          break;
        case "Pemuat":
          /** set detail hasil pemuat */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              null,
              null,
              request.getStatus());
          break;
        case "Penimbang":
          /** set detail hasil penimbang */
          detailHasil = new DetailHasil(
              hasil.getKd_hasil(),
              request.getKd_anggota(),
              null,
              null,
              request.getStatus());
          break;
        default:
          throw new Exception("Golongan tidak ditemukan");
      }

      /** save detail hasil petani, mobil, pemuat & penimbang */
      detailHasilService.save(detailHasil);

      response.setStatus(true);
      response.getMessage().add("Hasil berhasil diubah!");
      response.setPayload(modelMapper.map(hasil, HasilResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * FIND ALL HASIL
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<HasilResponse>>> findAll() {
    ResponseData<List<HasilResponse>> response = new ResponseData<>();
    List<HasilResponse> listHasil = new ArrayList<>();

    hasilService.findAll().forEach(hasil -> {
      listHasil.add(modelMapper.map(hasil, HasilResponse.class));
    });

    response.setStatus(true);
    response.setPayload(listHasil);
    response.getMessage().add("Data hasil berhasil ditemukan!");
    return ResponseEntity.ok(response);
  }

}
