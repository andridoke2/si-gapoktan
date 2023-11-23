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
import hamparan.gapoktan.dto.request.SubMenuRequest;
import hamparan.gapoktan.dto.response.SubMenuJoinMenuResponse;
import hamparan.gapoktan.dto.response.SubMenuResponse;
import hamparan.gapoktan.model.entities.menumanagement.SubMenu;
import hamparan.gapoktan.services.menumanagement.SubMenuService;
import hamparan.gapoktan.utils.ClearDataIntoDatabase;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/submenu")
public class SubMenuController {

  @Autowired
  private SubMenuService subMenuService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<SubMenuResponse>> create(@Valid @RequestBody SubMenuRequest request,
      Errors errors) {
    ResponseData<SubMenuResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      SubMenu subMenu = modelMapper.map(request, SubMenu.class);
      ClearDataIntoDatabase clear = new ClearDataIntoDatabase();

      subMenu.setTitle(clear.capitalize(subMenu.getTitle()));

      subMenu = subMenuService.save(subMenu);

      response.setStatus(true);
      response.setPayload(modelMapper.map(subMenu, SubMenuResponse.class));
      response.getMessage().add("Data Sub Menu berhasil disimpan!");
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PutMapping
  public ResponseEntity<ResponseData<SubMenuResponse>> update(@Valid @RequestBody SubMenuRequest request,
      Errors errors) {
    ResponseData<SubMenuResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      SubMenu subMenu = modelMapper.map(request, SubMenu.class);
      ClearDataIntoDatabase clear = new ClearDataIntoDatabase();

      subMenu.setTitle(clear.capitalize(subMenu.getTitle()));

      subMenu = subMenuService.save(subMenu);

      response.setStatus(true);
      response.getMessage().add("Sub Menu berhasil diubah!");
      response.setPayload(modelMapper.map(subMenu, SubMenuResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping
  public ResponseEntity<ResponseData<List<SubMenuResponse>>> findAll() {
    ResponseData<List<SubMenuResponse>> response = new ResponseData<>();
    List<SubMenuResponse> listSubMenu = new ArrayList<>();

    try {
      if (subMenuService.findAll().isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data Sub Menu masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        subMenuService.findAll().forEach(subMenu -> {
          listSubMenu.add(modelMapper.map(subMenu, SubMenuResponse.class));
        });
        response.setStatus(true);
        response.setPayload(listSubMenu);
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
    subMenuService.removeOne(id);
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<ResponseData<SubMenuResponse>> findSubMenuById(@PathVariable("id") int id) {
    ResponseData<SubMenuResponse> response = new ResponseData<>();

    try {
      if (subMenuService.findSubMenuById(id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data Sub Menu masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.setPayload(modelMapper.map(subMenuService.findSubMenuById(id), SubMenuResponse.class));
        response.getMessage().add("Sub menu ID: " + id + " berhasil ditemukan!");
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getbytitle/{title}")
  public ResponseEntity<ResponseData<SubMenuResponse>> findSubMenuByTitle(@PathVariable("title") String title) {
    ResponseData<SubMenuResponse> response = new ResponseData<>();

    try {
      if (subMenuService.findSubMenuByTitle(title).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Sub Menu masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.setPayload(modelMapper.map(subMenuService.findSubMenuByTitle(title), SubMenuResponse.class));
        response.getMessage().add("Sub Menu ID: " + title + " berhasil ditemukan!");
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getaccesssubmenubymenuid/{menu_id}")
  public ResponseEntity<ResponseData<List<SubMenuJoinMenuResponse>>> getAccessSubMenuByMenuId(
      @PathVariable("menu_id") int menu_id) {
    ResponseData<List<SubMenuJoinMenuResponse>> response = new ResponseData<>();
    List<SubMenuJoinMenuResponse> listSubMenu = new ArrayList<>();
    try {
      if (subMenuService.findAccessSubMenuByMenuId(menu_id).isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Sub menu masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        subMenuService.findAccessSubMenuByMenuId(menu_id).forEach(subMenu -> {
          listSubMenu.add(modelMapper.map(subMenu, SubMenuJoinMenuResponse.class));
        });

        response.setStatus(true);
        response.setPayload(listSubMenu);
        response.getMessage().add("Sub menu berhasil ditemukan!");
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
