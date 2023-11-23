package hamparan.gapoktan.services.menumanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.model.entities.menumanagement.Role;
import hamparan.gapoktan.model.repository.menumanagement.RoleRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public Role save(Role role) {
    return roleRepository.save(role);
  }

  public List<Role> findAll() {
    if (roleRepository.findAll().isEmpty()) {
      return null;
    } else {
      return roleRepository.findAll();
    }
  }

  public void removeOne(int id) {
    roleRepository.deleteById(id);
  }

  public Role findRoleById(int id) {
    if (roleRepository.findRoleById(id).equals(null)) {
      return null;
    } else {
      return roleRepository.findRoleById(id);
    }
  }

  public Role findRoleByName(String role) {
    if (roleRepository.findRoleByName(role).equals(null)) {
      return null;
    } else {
      return roleRepository.findRoleByName(role);
    }
  }

}
