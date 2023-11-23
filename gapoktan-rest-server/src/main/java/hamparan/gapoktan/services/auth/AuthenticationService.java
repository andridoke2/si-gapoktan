package hamparan.gapoktan.services.auth;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hamparan.gapoktan.config.jwt.JwtService;
import hamparan.gapoktan.dto.request.AuthenticationRequest;
import hamparan.gapoktan.dto.request.UserRequest;
import hamparan.gapoktan.dto.response.AuthenticationResponse;
import hamparan.gapoktan.helpers.DefaultData;
import hamparan.gapoktan.helpers.GenerateKode;
import hamparan.gapoktan.model.entities.auth.User;
import hamparan.gapoktan.model.entities.auth.UserRoleEnum;
import hamparan.gapoktan.model.entities.hamparan.Anggota;
import hamparan.gapoktan.model.repository.auth.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private GenerateKode generateKode = new GenerateKode();

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  @Autowired
  private ModelMapper modelMapper;

  public AuthenticationResponse register(UserRequest request) {
    /** Auto Generate Kode Anggota */
    String maxKodeAnggota = userRepository.findKodeByGolongan(request.getGolongan());
    request.setKd_anggota(generateKode.generateKodeAnggota(request.getGolongan(), maxKodeAnggota));

    DefaultData defaultData = new DefaultData();

    /** Encrypt Password */
    String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
    request.setPassword(encodedPassword);

    request.setDate_created(defaultData.defaultDateCreated(modelMapper.map(request, Anggota.class)));

    request.setImage(defaultData.defaultImage(modelMapper.map(request, Anggota.class)));

    if (request.getGolongan().equals("Pengurus")) {
      request.setRole(UserRoleEnum.ADMIN.name());
    } else {
      request.setRole(UserRoleEnum.USER.name());
    }

    request.setIs_active(defaultData.defaultActive(modelMapper.map(request, Anggota.class)));

    System.out.println("Register User..."); // log
    userRepository.save(modelMapper.map(request, User.class));

    var jwtToken = jwtService.generateToken(modelMapper.map(request, User.class));
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .email(request.getEmail())
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));

    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

    var jwtToken = jwtService.generateToken(modelMapper.map(user, User.class));

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
  }

}
