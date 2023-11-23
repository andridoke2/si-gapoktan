package hamparan.gapoktan.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hamparan.gapoktan.dto.ResponseData;
import hamparan.gapoktan.dto.request.AuthenticationRequest;
import hamparan.gapoktan.dto.request.UserRequest;
import hamparan.gapoktan.dto.response.AuthenticationResponse;
import hamparan.gapoktan.services.auth.AuthenticationService;
import hamparan.gapoktan.utils.ErrorParsingUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<AuthenticationResponse>> register(@Valid @RequestBody UserRequest request,
      Errors errors) {
    ResponseData<AuthenticationResponse> response = new ResponseData<>();
    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    response.setStatus(true);
    response.setPayload(authenticationService.register(request));
    response.getMessage().add("Berhasil register!");
    return ResponseEntity.ok(response);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<ResponseData<AuthenticationResponse>> authenticate(
      @Valid @RequestBody AuthenticationRequest request, Errors errors) {
    ResponseData<AuthenticationResponse> response = new ResponseData<>();
    if (errors.hasErrors()) {
      response.setStatus(false);
      response.setPayload(null);
      response.setMessage(ErrorParsingUtility.parseErrors(errors));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    response.setStatus(true);
    response.setPayload(authenticationService.authenticate(request));
    response.getMessage().add("Berhasil login!");
    return ResponseEntity.ok(response);
  }
}
