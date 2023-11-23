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
import hamparan.gapoktan.dto.request.RoleRequest;
import hamparan.gapoktan.dto.response.RoleResponse;
import hamparan.gapoktan.model.entities.menumanagement.Role;
import hamparan.gapoktan.services.menumanagement.RoleService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hamparan/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<RoleResponse>> create(@Valid @RequestBody RoleRequest request,
      Errors errors) {

    ResponseData<RoleResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Role role = modelMapper.map(request, Role.class);

      role = roleService.save(role); /** save role into database... */
      System.out.println("Save role into database...");

      response.setStatus(true);
      response.getMessage().add("Role berhasil disimpan!");
      response.setPayload(modelMapper.map(role, RoleResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @PutMapping
  public ResponseEntity<ResponseData<RoleResponse>> update(@Valid @RequestBody RoleRequest request,
      Errors errors) {

    ResponseData<RoleResponse> response = new ResponseData<>();

    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Role role = modelMapper.map(request, Role.class);

      role = roleService.save(role);

      response.setStatus(true);
      response.getMessage().add("Role berhasil diubah!");
      response.setPayload(modelMapper.map(role, RoleResponse.class));
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable("id") int id) {
    roleService.removeOne(id);
  }

  @GetMapping
  public ResponseEntity<ResponseData<List<RoleResponse>>> findAll() {
    ResponseData<List<RoleResponse>> response = new ResponseData<>();
    List<RoleResponse> listRole = new ArrayList<>();
    try {
      if (roleService.findAll().isEmpty()) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data masih kosing!");
        return ResponseEntity.ok(response);
      } else {
        roleService.findAll().forEach(role -> {
          listRole.add(modelMapper.map(role, RoleResponse.class));
        });
        response.setStatus(true);
        response.setPayload(listRole);
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<ResponseData<RoleResponse>> getRoleById(@PathVariable("id") int id) {
    ResponseData<RoleResponse> response = new ResponseData<>();
    try {
      if (roleService.findRoleById(id).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.setPayload(modelMapper.map(roleService.findRoleById(id), RoleResponse.class));
        response.getMessage().add("Data berhasil ditemukan!");
        return ResponseEntity.ok(response);
      }
    } catch (Exception e) {
      response.setStatus(false);
      response.setPayload(null);
      response.getMessage().add(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/getbyname/{role}")
  public ResponseEntity<ResponseData<RoleResponse>> getRoleByName(@PathVariable("role") String role) {
    ResponseData<RoleResponse> response = new ResponseData<>();
    try {
      if (roleService.findRoleByName(role).equals(null)) {
        response.setStatus(true);
        response.setPayload(null);
        response.getMessage().add("Data masih kosong!");
        return ResponseEntity.ok(response);
      } else {
        response.setStatus(true);
        response.getMessage().add("Data berhasil ditemukan!");
        response.setPayload(modelMapper.map(roleService.findRoleByName(role), RoleResponse.class));
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
