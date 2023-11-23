package hamparan.gapoktan.controllers.hamparan;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hamparan.gapoktan.dto.ResponseData;
import hamparan.gapoktan.dto.response.PengurusResponse;
import hamparan.gapoktan.services.hamparan.PengurusService;

@RestController
@RequestMapping("/api/hamparan/pengurus")
public class PengurusController {

  @Autowired
  private PengurusService pengurusService;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * Find All Pengurus
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<PengurusResponse>>> findAll() {
    ResponseData<List<PengurusResponse>> response = new ResponseData<>();
    List<PengurusResponse> listPengurus = new ArrayList<>();

    pengurusService.findAll().forEach(pengurus -> {
      listPengurus.add(modelMapper.map(pengurus, PengurusResponse.class));
    });

    response.setStatus(true);
    response.setPayload(listPengurus);
    response.getMessage().add("Data pengurus berhasil ditemukan!");
    return ResponseEntity.ok(response);
  }

}
