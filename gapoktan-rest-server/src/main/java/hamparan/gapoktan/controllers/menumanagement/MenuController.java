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
import hamparan.gapoktan.dto.request.MenuRequest;
import hamparan.gapoktan.dto.response.MenuResponse;
import hamparan.gapoktan.model.entities.menumanagement.Menu;
import hamparan.gapoktan.services.menumanagement.MenuService;
import hamparan.gapoktan.utils.ClearDataIntoDatabase;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/menu")
public class MenuController {

  @Autowired
  private MenuService menuService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<MenuResponse>> create(@Valid @RequestBody MenuRequest menuRequest,
      Errors errors) {

    ResponseData<MenuResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Menu menu = modelMapper.map(menuRequest, Menu.class);
      ClearDataIntoDatabase clearData = new ClearDataIntoDatabase();

      menu.setMenu(clearData.capitalize(menu.getMenu()));

      menu = menuService.save(menu);

      response.setStatus(true);
      response.getMessage().add("Menu berhasil disimpan!");
      response.setPayload(modelMapper.map(menu, MenuResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PutMapping
  public ResponseEntity<ResponseData<MenuResponse>> update(@Valid @RequestBody MenuRequest menuRequest,
      Errors errors) {
    ResponseData<MenuResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Menu menu = modelMapper.map(menuRequest, Menu.class);
      ClearDataIntoDatabase clearData = new ClearDataIntoDatabase();

      menu.setMenu(clearData.capitalize(menu.getMenu()));

      menu = menuService.save(menu);

      response.setStatus(true);
      response.getMessage().add("Menu berhasil disimpan!");
      response.setPayload(modelMapper.map(menu, MenuResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping
  public ResponseEntity<ResponseData<List<MenuResponse>>> findAll() {
    ResponseData<List<MenuResponse>> response = new ResponseData<>();
    List<MenuResponse> listMenu = new ArrayList<>();
    try {
      if (menuService.findAll().isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data masih kosing!");
        return ResponseEntity.ok(response);
      } else {
        menuService.findAll().forEach(menu -> {
          listMenu.add(modelMapper.map(menu, MenuResponse.class));
        });
        response.setStatus(true);
        response.setPayload(listMenu);
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
    menuService.removeOne(id);
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<ResponseData<MenuResponse>> findMenuById(@PathVariable("id") int id) {
    ResponseData<MenuResponse> response = new ResponseData<>();
    try {
      if (menuService.findMenuById(id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.setPayload(modelMapper.map(menuService.findMenuById(id), MenuResponse.class));
        response.getMessage().add("Data berhasil ditemukan!");
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.getMessage().add(e.getMessage());
      response.setPayload(null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getbyname/{menu}")
  public ResponseEntity<ResponseData<MenuResponse>> findMenuByName(@PathVariable("menu") String menu) {
    ResponseData<MenuResponse> response = new ResponseData<>();
    try {
      if (menuService.findMenuByName(menu).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Data berhasil ditemukan!");
        response.setPayload(modelMapper.map(menuService.findMenuByName(menu), MenuResponse.class));
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getaccessmenubyroleid/{role_id}")
  public ResponseEntity<ResponseData<List<MenuResponse>>> getAccessMenuByRoleId(@PathVariable("role_id") int role_id) {
    ResponseData<List<MenuResponse>> response = new ResponseData<>();
    List<MenuResponse> listMenu = new ArrayList<>();
    try {
      if (menuService.findAccessMenuByRoleId(role_id).isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Menu masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        menuService.findAccessMenuByRoleId(role_id).forEach(menu -> {
          listMenu.add(modelMapper.map(menu, MenuResponse.class));
        });
        response.setStatus(true);
        response.setPayload(listMenu);
        response.getMessage().add("Menu berhasil ditemukan!");
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getroleaccessmenu")
  public ResponseEntity<ResponseData<List<MenuResponse>>> getRoleAccessMenu() {
    ResponseData<List<MenuResponse>> response = new ResponseData<>();
    List<MenuResponse> listMenu = new ArrayList<>();
    try {
      if (menuService.findRoleAccessMenu().isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Menu tidak ditemukan!");
        return ResponseEntity.ok(response);
      } else {
        menuService.findRoleAccessMenu().forEach(menu -> {
          listMenu.add(modelMapper.map(menu, MenuResponse.class));
        });
        response.setStatus(true);
        response.setPayload(listMenu);
        response.getMessage().add("Menu berhasil ditemukan!");
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
