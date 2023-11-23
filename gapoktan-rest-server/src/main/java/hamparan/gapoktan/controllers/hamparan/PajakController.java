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
import hamparan.gapoktan.dto.response.PajakResponse;
import hamparan.gapoktan.services.hamparan.PajakService;

@RestController
@RequestMapping("/api/hamparan/pajak")
public class PajakController {

  @Autowired
  private PajakService pajakService;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * Find All Pajak
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseData<List<PajakResponse>>> findAll() {
    ResponseData<List<PajakResponse>> response = new ResponseData<>();
    List<PajakResponse> listPajak = new ArrayList<>();

    pajakService.findAll().forEach(pajak -> {
      listPajak.add(modelMapper.map(pajak, PajakResponse.class));
    });

    response.setStatus(true);
    response.setPayload(listPajak);
    response.getMessage().add("Data pajak berhasil ditemukan!");
    return ResponseEntity.ok(response);
  }
}
