package hamparan.gapoktan.controllers.hamparan;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import hamparan.gapoktan.dto.request.KebunRequest;
import hamparan.gapoktan.dto.response.KebunResponse;
import hamparan.gapoktan.model.entities.hamparan.Kebun;
import hamparan.gapoktan.services.hamparan.KebunService;
import hamparan.gapoktan.utils.ClearDataIntoDatabase;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/hamparan/kebun")
public class KebunController {

  @Autowired
  private KebunService service;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * CREATE KEBUN
   * 
   * @param kebunRequest
   * @param errors
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseData<KebunResponse>> create(
      @Valid @RequestBody KebunRequest kebunRequest,
      Errors errors) {

    ResponseData<KebunResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Kebun kebun = modelMapper.map(kebunRequest, Kebun.class);

      ClearDataIntoDatabase clearData = new ClearDataIntoDatabase();

      kebun.setKd_anggota(clearData.cleanKode(kebun.getKd_anggota()));
      kebun.setLokasi(clearData.capitalize(kebun.getLokasi()));
      kebun.setJenis_kebun(clearData.capitalize(kebun.getJenis_kebun()));

      System.out.println("Create data kebun...");
      kebun = service.save(kebun); /** create data setelah clean */

      response.setStatus(true);
      response.getMessage().add("Kebun berhasil disimpan!");
      response.setPayload(modelMapper.map(kebun, KebunResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * UPDATE KEBUN
   * 
   * @param kebunRequest
   * @param errors
   * @return
   */
  @PutMapping
  public ResponseEntity<ResponseData<KebunResponse>> update(@Valid @RequestBody KebunRequest kebunRequest,
      Errors errors) {

    ResponseData<KebunResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Kebun kebun = modelMapper.map(kebunRequest, Kebun.class);

      ClearDataIntoDatabase clearData = new ClearDataIntoDatabase();

      kebun.setKd_kebun(clearData.cleanKode(kebun.getKd_kebun()));
      kebun.setKd_anggota(clearData.cleanKode(kebun.getKd_anggota()));
      kebun.setLokasi(clearData.capitalize(kebun.getLokasi()));
      kebun.setJenis_kebun(clearData.capitalize(kebun.getJenis_kebun()));

      System.out.println("Update data kebun...");
      kebun = service.save(kebun); /** update data kebun */

      response.setStatus(true);
      response.getMessage().add("Kebun berhasil diubah!");
      response.setPayload(modelMapper.map(kebun, KebunResponse.class));

      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * GET ALL KEBUN
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<KebunResponse>>> findAll() {
    ResponseData<List<KebunResponse>> response = new ResponseData<>();
    List<KebunResponse> listKebun = new ArrayList<>();
    if (service.findAll().isEmpty()) {
      response.setStatus(true);
      response.setPayload(null);
      response.getMessage().add("Data masih kosing!");
      return ResponseEntity.ok(response);
    } else {
      service.findAll().forEach(kebun -> {
        listKebun.add(modelMapper.map(kebun, KebunResponse.class));
      });
      response.setStatus(true);
      response.setPayload(listKebun);
      return ResponseEntity.ok(response);
    }
  }

  /**
   * GET KEBUN BY KODE KEBUN
   * 
   * @param kodeKebun
   * @return
   */
  @GetMapping("/{kodeKebun}")
  public ResponseEntity<ResponseData<KebunResponse>> findOne(@PathVariable("kodeKebun") String kodeKebun) {
    ResponseData<KebunResponse> response = new ResponseData<>();

    if (service.findKebunByKode(kodeKebun) == null) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add("Data kebun dengan kode : " + kodeKebun + " tidak ditemukan!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } else {
      response.setStatus(true);
      response.getMessage().add("Data kebun dengan kode : " + kodeKebun + " berhasil ditemukan!");
      response.setPayload(modelMapper.map(service.findKebunByKode(kodeKebun), KebunResponse.class));
      return ResponseEntity.ok(response);
    }
  }

  /**
   * DELETE KEBUN BY KODE KEBUN
   * 
   * @param kodeKebun
   */
  @DeleteMapping("/{kodeKebun}")
  public void deleteOne(@PathVariable("kodeKebun") String kodeKebun) {
    service.removeOne(kodeKebun);
  }

  /**
   * SEARCH KEBUN BY KODE ANGGOTA
   * 
   * @param searchData
   * @return
   */
  @PostMapping("/search/bykodeanggota")
  public ResponseEntity<ResponseData<List<KebunResponse>>> getKebunByKodeAnggota(@RequestBody SearchData searchData) {

    ResponseData<List<KebunResponse>> response = new ResponseData<>();

    try {
      List<KebunResponse> listKebun = new ArrayList<>();

      service.findKebunByKodeAnggota(searchData.getSearchKey()).forEach(kebun -> {
        listKebun.add(modelMapper.map(kebun, KebunResponse.class));
      });

      if (listKebun.isEmpty()) {
        response.getMessage().add("kebun tidak ditemukan dengan kode anggota : " + searchData.getSearchKey());
        response.setPayload(null);
      } else {
        response.setMessage(null);
        response.setPayload(listKebun);
      }
      response.setStatus(true);

      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * SEARCH BY JENIS KEBUN
   * 
   * @param searchData
   * @param size
   * @param page
   * @return
   */
  @PostMapping("/search/byjeniskebun/{size}/{page}")
  public ResponseEntity<ResponseData<Iterable<KebunResponse>>> getKebunByJenisKebun(
      @RequestBody SearchData searchData,
      @PathVariable("size") int size,
      @PathVariable("page") int page) {

    Pageable pageable = PageRequest.of(page, size);
    ResponseData<Iterable<KebunResponse>> response = new ResponseData<>();
    List<KebunResponse> listKebun = new ArrayList<>();

    service.findKebunByJenisKebun(searchData.getSearchKey(), pageable).forEach(kebun -> {
      listKebun.add(modelMapper.map(kebun, KebunResponse.class));
    });

    response.setStatus(true);
    response.getMessage().add("Data kebun dengan jenis : " + searchData.getSearchKey() + " berhasil ditemukan!");
    response.setPayload(listKebun);
    return ResponseEntity.ok(response);
  }

  /**
   * SEARCH BY JENIS KEBUN WITH PAGINATION & SORT
   * 
   * @param searchData
   * @param size
   * @param page
   * @param sort
   * @return
   */
  @PostMapping("/search/byjeniskebun/{size}/{page}/{sort}")
  public Iterable<Kebun> getKebunByJenisKebun(
      @RequestBody SearchData searchData,
      @PathVariable("size") int size,
      @PathVariable("page") int page,
      @PathVariable("sort") String sort) {

    Pageable pageable = PageRequest.of(page, size, Sort.by("kd_kebun"));

    if (sort.equalsIgnoreCase("desc")) {
      pageable = PageRequest.of(page, size, Sort.by("kd_kebun").descending());
    }

    return service.findKebunByJenisKebun(searchData.getSearchKey(), pageable);
  }

  /**
   * SEARCH BY KODE KEBUN
   * 
   * @param kd_kebun
   * @return
   */
  @GetMapping("/search/bykode/{kd_kebun}")
  public ResponseEntity<ResponseData<KebunResponse>> findOneKebunByKode(@PathVariable("kd_kebun") String kd_kebun) {

    ResponseData<KebunResponse> response = new ResponseData<>();

    Kebun kebun = service.findOneKebunByKode(kd_kebun);

    if (kebun == null) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add("Data kebun dengan kode : " + kd_kebun + " tidak ditemukan!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } else {
      response.setStatus(true);
      response.getMessage().add("Data kebun dengan kode : " + kd_kebun + " berhasil ditemukan!");
      response.setPayload(modelMapper.map(kebun, KebunResponse.class));
      return ResponseEntity.ok(response);
    }
  }

}
