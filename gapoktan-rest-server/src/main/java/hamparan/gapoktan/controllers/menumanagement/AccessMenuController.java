package hamparan.gapoktan.controllers.menumanagement;

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
import hamparan.gapoktan.dto.request.AccessMenuRequest;
import hamparan.gapoktan.dto.response.AccessMenuResponse;
import hamparan.gapoktan.model.entities.menumanagement.AccessMenu;
import hamparan.gapoktan.services.menumanagement.AccessMenuService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/accessmenu")
public class AccessMenuController {

  @Autowired
  private AccessMenuService accessMenuService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<AccessMenuResponse>> create(@Valid @RequestBody AccessMenuRequest request,
      Errors errors) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      AccessMenu accessMenu = modelMapper.map(request, AccessMenu.class);

      accessMenu = accessMenuService.save(accessMenu);

      response.setStatus(true);
      response.setPayload(modelMapper.map(accessMenu, AccessMenuResponse.class));
      response.getMessage().add("Access menu berhasil disimpan!");
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PutMapping
  public ResponseEntity<ResponseData<AccessMenuResponse>> update(@Valid @RequestBody AccessMenuRequest request,
      Errors errors) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      AccessMenu accessMenu = modelMapper.map(request, AccessMenu.class);

      accessMenu = accessMenuService.save(accessMenu);

      response.setStatus(true);
      response.getMessage().add("Access menu berhasil disimpan!");
      response.setPayload(modelMapper.map(accessMenu, AccessMenuResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping
  public ResponseEntity<ResponseData<List<AccessMenuResponse>>> findAll() {
    ResponseData<List<AccessMenuResponse>> response = new ResponseData<>();
    List<AccessMenuResponse> listAccessMenu = new ArrayList<>();

    try {
      if (accessMenuService.findAll().isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Access menu masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        accessMenuService.findAll().forEach(accessMenu -> {
          listAccessMenu.add(modelMapper.map(accessMenu, AccessMenuResponse.class));
        });
        response.setStatus(true);
        response.setPayload(listAccessMenu);
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable("id") int id) {
    accessMenuService.removeOne(id);
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<ResponseData<AccessMenuResponse>> findAccessMenuById(@PathVariable("id") int id) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();
    try {
      if (accessMenuService.findAccessMenuById(id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Access menu tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Access menu berhasil ditemukan!");
        response.setPayload(modelMapper.map(accessMenuService.findAccessMenuById(id), AccessMenuResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getbyroleid/{role_id}")
  public ResponseEntity<ResponseData<AccessMenuResponse>> findAccessMenuByRoleId(@PathVariable("role_id") int role_id) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();
    try {
      if (accessMenuService.findAccessMenuByRoleId(role_id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Access menu tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Access menu berhasil ditemukan!");
        response
            .setPayload(modelMapper.map(accessMenuService.findAccessMenuByRoleId(role_id), AccessMenuResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getbymenuid/{menu_id}")
  public ResponseEntity<ResponseData<AccessMenuResponse>> findAccessMenuByMenuId(@PathVariable("menu_id") int menu_id) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();
    try {
      if (accessMenuService.findAccessMenuByMenuId(menu_id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Access menu tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Access menu berhasil ditemukan!");
        response
            .setPayload(modelMapper.map(accessMenuService.findAccessMenuByMenuId(menu_id), AccessMenuResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getaccessbyroleidmenuid/{role_id}/{menu_id}")
  public ResponseEntity<ResponseData<AccessMenuResponse>> getAccessByRoleIdMenuId(@PathVariable("role_id") int role_id,
      @PathVariable("menu_id") int menu_id) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();
    try {
      if (accessMenuService.findAccessMenuByRoleIdMenuId(role_id, menu_id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Access tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Access berhasil ditemukan!");
        response.setPayload(modelMapper.map(accessMenuService.findAccessMenuByRoleIdMenuId(role_id, menu_id),
            AccessMenuResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/existsAccessMenu/{role_id}/{menu_id}")
  public ResponseEntity<ResponseData<AccessMenuResponse>> getExistsAccessMenu(@PathVariable("role_id") int role_id,
      @PathVariable("menu_id") int menu_id) {
    ResponseData<AccessMenuResponse> response = new ResponseData<>();
    try {
      if (accessMenuService.existsAccessMenu(role_id, menu_id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Access menu tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Access menu berhasil ditemukan!");
        response.setPayload(
            modelMapper.map(accessMenuService.existsAccessMenu(role_id, menu_id), AccessMenuResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}
