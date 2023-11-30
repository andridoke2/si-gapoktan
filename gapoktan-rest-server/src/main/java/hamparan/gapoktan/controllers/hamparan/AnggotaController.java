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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hamparan.gapoktan.dto.ResponseData;
import hamparan.gapoktan.dto.SearchData;
import hamparan.gapoktan.dto.request.AnggotaRequest;
import hamparan.gapoktan.dto.response.AnggotaResponse;
import hamparan.gapoktan.dto.response.AnggotaResponsePagination;
import hamparan.gapoktan.model.entities.hamparan.Anggota;
import hamparan.gapoktan.services.hamparan.AnggotaService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import hamparan.gapoktan.utils.PaginationConfig;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/anggota")
public class AnggotaController {

  @Autowired
  private AnggotaService service;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * CREATE
   * Menambahkan anggota baru!
   * 
   * @param request // request data dari client
   * @param errors  // validation data
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseData<AnggotaResponse>> create(
      @Valid @RequestBody AnggotaRequest request,
      Errors errors) {

    ResponseData<AnggotaResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      /**
       * Response data client jika gagal!
       */
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Anggota anggota = modelMapper.map(request, Anggota.class);
      anggota = service.save(anggota);
      /**
       * Response data client jika berhasil!
       */
      response.setStatus(true);
      response.getMessage().add("Berhasil ditambahkan anggota baru!");
      response.setPayload(modelMapper.map(anggota, AnggotaResponse.class));
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
   * Mengubah data anggota!
   * 
   * @param request
   * @param errors
   * @return
   */
  @PutMapping
  public ResponseEntity<ResponseData<AnggotaResponse>> update(
      @Valid @RequestBody AnggotaRequest request,
      Errors errors) {

    ResponseData<AnggotaResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Anggota anggota = modelMapper.map(request, Anggota.class);
      response.setStatus(true);
      response.getMessage().add("Data anggota berhasil diubah!");
      response.setPayload(modelMapper.map(service.save(anggota), AnggotaResponse.class));
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
   * Mengambil semua data anggota
   * 
   * fungsi tanpa menggunakan pagination.
   * 
   * @return
   */

  /**
   * @GetMapping
   *             public ResponseEntity<ResponseData<List<AnggotaResponse>>>
   *             findAll() {
   * 
   *             ResponseData<List<AnggotaResponse>> response = new
   *             ResponseData<>();
   *             List<AnggotaResponse> listAnggota = new ArrayList<>();
   *             service.findAll().forEach(anggota -> {
   *             listAnggota.add(modelMapper.map(anggota, AnggotaResponse.class));
   *             });
   *             response.setStatus(true);
   *             response.setPayload(listAnggota);
   *             response.getMessage().add("Data anggota berhasil ditemukan!");
   *             return ResponseEntity.ok(response);
   *             }
   * 
   */

  /**
   * fungsi menggunakan pagination.
   * fungsi ini terdapat kekurangan yaitu, masih belum mengimplementasikan model
   * mapper dimana kegunaan dari model mapper yaitu untuk memfilter data yang akan
   * dikirim kepada client sesuai dengan kebutuhan saja.
   * 
   * @param page
   * @param size
   * @return
   * @GetMapping
   * 
   *             ini endpoint untuk find all menggunakan pagination basic.
   * 
   *             public Iterable<Anggota> findAll(
   * @RequestParam(defaultValue = PaginationConfig.DEFAULT_PAGE_NUMBER) int page,
   * @RequestParam(defaultValue = PaginationConfig.DEFAULT_SIZE_NUMBER) int size)
   *                            {
   *                            Pageable pageable = PageRequest.of(page, size);
   *                            return service.findAll(pageable);
   *                            }
   */

  /**
   * 
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @param sortDir
   * @return
   */
  @GetMapping
  public AnggotaResponsePagination findAll(
      @RequestParam(value = "pageNo", defaultValue = PaginationConfig.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = PaginationConfig.DEFAULT_SIZE_NUMBER, required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = PaginationConfig.DEFAULT_SORT_BY) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = PaginationConfig.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
    return service.findAll(pageNo, pageSize, sortBy, sortDir);
  }

  /**
   * GET BY KODE
   * ambil data anggota hamparan berdasarkan kode anggota
   * 
   * @param kodeAnggota
   * @return
   */
  @GetMapping("/{kodeAnggota}")
  public ResponseEntity<ResponseData<AnggotaResponse>> findOne(@PathVariable("kodeAnggota") String kodeAnggota) {

    ResponseData<AnggotaResponse> response = new ResponseData<>();

    if (service.findAnggotaByKode(kodeAnggota) == null) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add("Data anggota dengan kode : " + kodeAnggota + " tidak ditemukan!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } else {
      response.setStatus(true);
      response.getMessage().add("Data anggota berhasil ditemukan!");
      response.setPayload(modelMapper.map(service.findAnggotaByKode(kodeAnggota), AnggotaResponse.class));
      return ResponseEntity.ok(response);
    }
  }

  /**
   * DELETE BY KODE
   * hapus data anggota hamparan berdasarkan kode anggota
   */
  @DeleteMapping("/{kodeAnggota}")
  public void deleteOne(@PathVariable("kodeAnggota") String kodeAnggota) {
    service.removeOne(kodeAnggota);
  }

  /**
   * FIND BY NAME
   * cari data anggota hamparan berdasarkan nama anggota
   * 
   * @param searchData
   * @return
   */
  @PostMapping("/search/byname")
  public ResponseEntity<ResponseData<List<AnggotaResponse>>> getAnggotaByName(@RequestBody SearchData searchData) {

    ResponseData<List<AnggotaResponse>> response = new ResponseData<>();
    List<AnggotaResponse> listAnggota = new ArrayList<>();

    service.findAnggotaByName(searchData.getSearchKey()).forEach(anggota -> {
      listAnggota.add(modelMapper.map(anggota, AnggotaResponse.class));
    });

    response.setStatus(true);
    response.setPayload(listAnggota);
    response.getMessage().add("Data berhasil ditemukan!");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/getbyemail/{email}")
  public ResponseEntity<ResponseData<AnggotaResponse>> getOneAnggotaByEmail(@PathVariable("email") String email) {
    ResponseData<AnggotaResponse> response = new ResponseData<>();
    try {
      if (service.findAnggotaByEmail(email).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Anggota tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.setPayload(modelMapper.map(service.findAnggotaByEmail(email), AnggotaResponse.class));
        response.getMessage().add("Anggota berhasil ditemukan!");
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * GET BY GOLONGAN
   * cari data anggota hamparan berdasarkan golongan anggota
   * 
   * @param searchData
   * @param size
   * @param page
   * @return
   *         CATATAN : fungsi pagination hilang karena model mapper,
   *         gimana solusinya?
   */
  @PostMapping("/search/bygolongan/{size}/{page}")
  public ResponseEntity<ResponseData<Iterable<AnggotaResponse>>> getAnggotaByGolongan(
      @RequestBody SearchData searchData,
      @PathVariable("size") int size,
      @PathVariable("page") int page) {

    Pageable pageable = PageRequest.of(page, size);

    ResponseData<Iterable<AnggotaResponse>> response = new ResponseData<>();
    List<AnggotaResponse> listAnggota = new ArrayList<>();

    service.findAnggotaByGolongan(searchData.getSearchKey(),
        pageable).forEach(anggota -> {
          listAnggota.add(modelMapper.map(anggota, AnggotaResponse.class));
        });

    response.setStatus(true);
    response.setMessage(null);
    response.setPayload(listAnggota);

    return ResponseEntity.ok(response);
  }

  /**
   * GET BY GOLONGAN USE PAGINATION & SORT
   * cari data anggota hamparan berdasarkan golongan, pagination & sorting
   * berdasarkan kode anggota
   * 
   * @param searchData
   * @param size
   * @param page
   * @param sort
   * @return
   */
  @PostMapping("/search/bygolongan/{size}/{page}/{sort}")
  public Iterable<Anggota> getAnggotaByGolongan(
      @RequestBody SearchData searchData,
      @PathVariable("size") int size,
      @PathVariable("page") int page,
      @PathVariable("sort") String sort) {

    Pageable pageable = PageRequest.of(page, size, Sort.by("kd_anggota"));

    if (sort.equalsIgnoreCase("desc")) {
      pageable = PageRequest.of(page, size, Sort.by("kd_anggota").descending());
    }

    return service.findAnggotaByGolongan(searchData.getSearchKey(), pageable);
  }

  /**
   * GET BY KODE
   * cari data anggota berdasarkan kode anggota melalui endpoint atau url
   * 
   * @param kd_anggota
   * @return
   */
  @GetMapping("/search/bykode/{kd_anggota}")
  public ResponseEntity<ResponseData<AnggotaResponse>> findOneAnggotaByKode(
      @PathVariable("kd_anggota") String kd_anggota) {

    ResponseData<AnggotaResponse> response = new ResponseData<>();

    Anggota anggota = service.findOneAnggotaByKode(kd_anggota);

    if (anggota == null) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add("Data anggota dengan kode : " + kd_anggota + " tidak ditemukan!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } else {
      response.setMessage(null);
      response.setStatus(true);
      response.setPayload(modelMapper.map(anggota, AnggotaResponse.class));
      return ResponseEntity.ok(response);
    }
  }

}
