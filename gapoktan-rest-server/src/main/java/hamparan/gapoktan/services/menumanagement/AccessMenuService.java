package hamparan.gapoktan.services.menumanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.menumanagement.AccessMenu;
import hamparan.gapoktan.model.repository.menumanagement.AccessMenuRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccessMenuService {

  @Autowired
  private AccessMenuRepository accessMenuRepository;

  /** save */
  public AccessMenu save(AccessMenu accessMenu) {
    return accessMenuRepository.save(accessMenu);
  }

  /** find all */
  public List<AccessMenu> findAll() {
    if (accessMenuRepository.findAll().isEmpty()) {
      return null;
    } else {
      return accessMenuRepository.findAll();
    }
  }

  /** remove one */
  public void removeOne(int id) {
    accessMenuRepository.deleteById(id);
  }

  /** find access menu by id */
  public AccessMenu findAccessMenuById(int id) {
    if (accessMenuRepository.findAccessMenuById(id).equals(null)) {
      return null;
    } else {
      return accessMenuRepository.findAccessMenuById(id);
    }
  }

  /** find access menu by role id */
  public AccessMenu findAccessMenuByRoleId(int role_id) {
    if (accessMenuRepository.findAccessMenuByRoleId(role_id).equals(null)) {
      return null;
    } else {
      return accessMenuRepository.findAccessMenuByRoleId(role_id);
    }
  }

  /** find access menu by menu id */
  public AccessMenu findAccessMenuByMenuId(int menu_id) {
    if (accessMenuRepository.findAccessMenuByMenuId(menu_id).equals(null)) {
      return null;
    } else {
      return accessMenuRepository.findAccessMenuByMenuId(menu_id);
    }
  }

  /** find access menu by menu id and role id */
  public AccessMenu findAccessMenuByRoleIdMenuId(int role_id, int menu_id) {
    if (accessMenuRepository.findAccessMenuByRoleIdMenuId(role_id, menu_id).equals(null)) {
      return null;
    } else {
      return accessMenuRepository.findAccessMenuByRoleIdMenuId(role_id, menu_id);
    }
  }

  /** check exists access menu */
  public AccessMenu existsAccessMenu(int role_id, int menu_id) {
    return accessMenuRepository.existsAccessMenu(role_id, menu_id);
  }

  public String existsAccessMenuCount(int role_id, int menu_id) {
    return accessMenuRepository.existsAccessMenuCount(role_id, menu_id);
  }

}
