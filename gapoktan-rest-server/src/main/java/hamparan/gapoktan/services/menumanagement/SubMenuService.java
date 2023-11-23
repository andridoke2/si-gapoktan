package hamparan.gapoktan.services.menumanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.menumanagement.SubMenu;
import hamparan.gapoktan.model.repository.menumanagement.SubMenuRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SubMenuService {

  @Autowired
  private SubMenuRepository subMenuRepository;

  public SubMenu save(SubMenu subMenu) {
    return subMenuRepository.save(subMenu);
  }

  public List<SubMenu> findAll() {
    if (subMenuRepository.findAll().isEmpty()) {
      return null;
    } else {
      return subMenuRepository.findAll();
    }
  }

  public List<SubMenu> findAllSubMenu() {
    if (subMenuRepository.findAllSubMenu().isEmpty()) {
      return null;
    } else {
      return subMenuRepository.findAllSubMenu();
    }
  }

  public void removeOne(int id) {
    subMenuRepository.deleteById(id);
  }

  public SubMenu findSubMenuById(int id) {
    if (subMenuRepository.findSubMenuById(id).equals(null)) {
      return null;
    } else {
      return subMenuRepository.findSubMenuById(id);
    }
  }

  public SubMenu findSubMenuByTitle(String title) {
    if (subMenuRepository.findSubMenuByTitle(title).equals(null)) {
      return null;
    } else {
      return subMenuRepository.findSubMenuByTitle(title);
    }
  }

  public List<SubMenu> findAccessSubMenuByMenuId(int menu_id) {
    if (subMenuRepository.findAccessSubMenuByMenuId(menu_id).isEmpty()) {
      return null;
    } else {
      return subMenuRepository.findAccessSubMenuByMenuId(menu_id);
    }
  }

}
