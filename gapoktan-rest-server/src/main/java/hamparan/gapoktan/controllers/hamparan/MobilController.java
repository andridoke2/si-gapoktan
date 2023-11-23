package hamparan.gapoktan.controllers.hamparan;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hamparan.gapoktan.dto.ResponseData;
import hamparan.gapoktan.dto.SearchData;
import hamparan.gapoktan.dto.request.MobilRequest;
import hamparan.gapoktan.dto.response.MobilResponse;
import hamparan.gapoktan.model.entities.hamparan.Mobil;
import hamparan.gapoktan.services.hamparan.MobilService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/mobil")
public class MobilController {

  @Autowired
  private MobilService service;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * CREATE MOBIL
   * Method : POST
   * 
   * @param request
   * @param errors
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseData<MobilResponse>> create(@Valid @RequestBody MobilRequest request, Errors errors) {
    ResponseData<MobilResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Mobil mobil = modelMapper.map(request, Mobil.class);
      mobil = service.save(mobil);

      response.setStatus(true);
      response.getMessage().add("Mobil berhasil ditambahkan!");
      response.setPayload(modelMapper.map(mobil, MobilResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * UPDATE
   * Endpoint ubah data kebun!
   * 
   * @param request
   * @param errors
   * @return
   */
  @PutMapping
  public ResponseEntity<ResponseData<MobilResponse>> update(@Valid @RequestBody MobilRequest request, Errors errors) {
    ResponseData<MobilResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Mobil mobil = modelMapper.map(request, Mobil.class);
      response.setStatus(true);
      response.getMessage().add("Data mobil berhasil diubah!");
      response.setPayload(modelMapper.map(service.save(mobil), MobilResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * GET ALL
   * Mengambil semua data mobil!
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<MobilResponse>>> findAll() {
    ResponseData<List<MobilResponse>> response = new ResponseData<>();
    List<MobilResponse> listMobil = new ArrayList<>();
    service.findAll().forEach(mobil -> {
      listMobil.add(modelMapper.map(mobil, MobilResponse.class));
    });
    response.setStatus(true);
    response.setPayload(listMobil);
    response.getMessage().add("Data mobil berhasil ditemukan!");
    return ResponseEntity.ok(response);
  }

  /**
   * GET BY KODE MOBIL
   * Mengambil data mobil hamparan berdasarkan kode mobil!
   * 
   * @param kodeMobil
   * @return
   */
  @GetMapping("/{kodeMobil}")
  public ResponseEntity<ResponseData<MobilResponse>> findOne(@PathVariable("kodeMobil") String kodeMobil) {
    ResponseData<MobilResponse> response = new ResponseData<>();

    if (service.findByKode(kodeMobil) == null) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add("Data mobil dengan kode : " + kodeMobil + " tidak ditemukan!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } else {
      response.setStatus(true);
      response.getMessage().add("Data mobil berhasil ditemukan!");
      response.setPayload(modelMapper.map(service.findByKode(kodeMobil), MobilResponse.class));
      return ResponseEntity.ok(response);
    }
  }

  /**
   * DELETE BY KODE
   * Menghapus data mobil hamparan berdasarkan kode mobil!
   * 
   * @param kodeMobil
   */
  @DeleteMapping("/{kodeMobil}")
  public void deleteOne(@PathVariable("kodeMobil") String kodeMobil) {
    service.removeOne(kodeMobil);
  }

  /**
   * FIND BY KODE MOBIL & KODE ANGGOTA
   * 
   * @param kd_mobil
   * @param kd_anggota
   * @return
   */
  @GetMapping("/findmobil/{kodeMobil}/{kodeAnggota}")
  public ResponseEntity<ResponseData<MobilResponse>> getMobilByKodeAnggota(
      @PathVariable("kodeMobil") String kd_mobil, @PathVariable("kodeAnggota") String kd_anggota) {
    ResponseData<MobilResponse> response = new ResponseData<>();

    try {
      Mobil mobil = service.findMobilByKodeMobilAndKodeAnggota(kd_mobil, kd_anggota);
      response.setStatus(true);
      response.setPayload(modelMapper.map(mobil, MobilResponse.class));
      response.getMessage().add("Data berhasil ditemukan!");
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * FIND BY MERK MOBIL
   * 
   * @param searchData
   * @return
   */
  @PostMapping("/search/bymerk")
  public ResponseEntity<ResponseData<List<MobilResponse>>> findByMerk(@RequestBody SearchData searchData) {
    ResponseData<List<MobilResponse>> response = new ResponseData<>();
    List<MobilResponse> listMobil = new ArrayList<>();

    try {
      service.findMobilByMerk(searchData.getSearchKey()).forEach(mobil -> {
        listMobil.add(modelMapper.map(mobil, MobilResponse.class));
      });

      response.setStatus(true);
      response.setPayload(listMobil);
      response.getMessage().add("Data berhasil ditemukan!");
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * FIND BY KODE ANGGOTA
   * 
   * @param searchData
   * @return
   */
  @PostMapping("/search/bykodeanggota")
  public ResponseEntity<ResponseData<List<MobilResponse>>> findByKodeAnggota(@RequestBody SearchData searchData) {
    ResponseData<List<MobilResponse>> response = new ResponseData<>();
    List<MobilResponse> listMobil = new ArrayList<>();

    try {
      service.findMobilByKodeAnggota(searchData.getSearchKey()).forEach(mobil -> {
        listMobil.add(modelMapper.map(mobil, MobilResponse.class));
      });

      response.setStatus(true);
      response.setPayload(listMobil);
      response.getMessage().add("Data berhasil ditemukan!");
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * FIND BY NO PLAT KENDARAAN
   * 
   * @param searchData
   * @return
   */
  @PostMapping("/search/bynomorplat")
  public ResponseEntity<ResponseData<List<MobilResponse>>> findByNomorPlat(@RequestBody SearchData searchData) {
    ResponseData<List<MobilResponse>> response = new ResponseData<>();
    List<MobilResponse> listMobil = new ArrayList<>();

    try {
      service.findMobilByNomorPlat(searchData.getSearchKey()).forEach(mobil -> {
        listMobil.add(modelMapper.map(mobil, MobilResponse.class));
      });

      response.setStatus(true);
      response.setPayload(listMobil);
      response.getMessage().add("Data berhasil ditemukan!");
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}
