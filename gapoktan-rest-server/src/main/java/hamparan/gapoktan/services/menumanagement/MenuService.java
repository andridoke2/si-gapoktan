package hamparan.gapoktan.services.menumanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.menumanagement.Menu;
import hamparan.gapoktan.model.repository.menumanagement.MenuRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuService {

  @Autowired
  private MenuRepository menuRepository;

  public Menu save(Menu userMenu) {
    return menuRepository.save(userMenu);
  }

  public List<Menu> findAll() {
    if (menuRepository.findAll().isEmpty()) {
      return null;
    } else {
      return menuRepository.findAll();
    }
  }

  public void removeOne(int id) {
    menuRepository.deleteById(id);
  }

  public Menu findMenuById(int id) {
    if (menuRepository.findMenuById(id).equals(null)) {
      return null;
    } else {
      return menuRepository.findMenuById(id);
    }
  }

  public Menu findMenuByName(String menu) {
    if (menuRepository.findMenuByName(menu).equals(null)) {
      return null;
    } else {
      return menuRepository.findMenuByName(menu);
    }
  }

  public List<Menu> findAccessMenuByRoleId(int role_id) {
    if (menuRepository.findAccessMenuByRoleId(role_id).isEmpty()) {
      return null;
    } else {
      return menuRepository.findAccessMenuByRoleId(role_id);
    }
  }

  public List<Menu> findRoleAccessMenu() {
    if (menuRepository.findRoleAccessMenu().isEmpty()) {
      return null;
    } else {
      return menuRepository.findRoleAccessMenu();
    }
  }

}
