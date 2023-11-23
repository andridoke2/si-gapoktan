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
import hamparan.gapoktan.dto.request.HargaRequest;
import hamparan.gapoktan.dto.response.HargaResponse;
import hamparan.gapoktan.model.entities.hamparan.Harga;
import hamparan.gapoktan.services.hamparan.HargaService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/harga")
public class HargaController {

  @Autowired
  private HargaService service;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * CREATE HARGA BARU
   * 
   * @param request
   * @param errors
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseData<HargaResponse>> create(@Valid @RequestBody HargaRequest request, Errors errors) {
    ResponseData<HargaResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Harga harga = modelMapper.map(request, Harga.class);
      harga = service.save(harga);

      response.setStatus(true);
      response.getMessage().add("Harga berhasil ditambahkan!");
      response.setPayload(modelMapper.map(harga, HargaResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * UPDATE HARGA
   * 
   * @param request
   * @param errors
   * @return
   */
  @PutMapping
  public ResponseEntity<ResponseData<HargaResponse>> update(@Valid @RequestBody HargaRequest request, Errors errors) {
    ResponseData<HargaResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Harga harga = modelMapper.map(request, Harga.class);
      response.setStatus(true);
      response.getMessage().add("Data harga berhasil diubah!");
      response.setPayload(modelMapper.map(service.save(harga), HargaResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  /**
   * Find All Harga
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<HargaResponse>>> findAll() {
    ResponseData<List<HargaResponse>> response = new ResponseData<>();
    List<HargaResponse> listHarga = new ArrayList<>();

    try {
      service.findAll().forEach(harga -> {
        listHarga.add(modelMapper.map(harga, HargaResponse.class));
      });
      response.setStatus(true);
      response.setPayload(listHarga);
      response.getMessage().add("Data harga berhasil ditemukan!");
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @DeleteMapping("/{kodeHarga}")
  public void deleteOne(@PathVariable("kodeHarga") String kodeHarga) {
    service.removeOne(kodeHarga);
  }

  /**
   * Find Last Harga
   * Last harga adalah perubahan harga yang terakhir atau terbaru.
   * 
   * @return
   */
  @GetMapping("/terbaru")
  public ResponseEntity<ResponseData<HargaResponse>> terbaru() {
    ResponseData<HargaResponse> response = new ResponseData<>();
    try {
      if (service.findLastHarga() == null) {
        response.setStatus(false);
        response.setPayload(null);
        response.getMessage().add("Data harga tidak ditemukan!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Data harga berhasil ditemukan!");
        response.setPayload(modelMapper.map(service.findLastHarga(), HargaResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}
