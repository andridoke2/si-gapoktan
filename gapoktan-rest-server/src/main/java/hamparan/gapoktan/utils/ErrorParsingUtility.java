package hamparan.gapoktan.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ErrorParsingUtility {

  public static List<String> parseErrors(Errors errors) {
    List<String> message = new ArrayList<>();
    for (ObjectError error : errors.getAllErrors()) {
      message.add(error.getDefaultMessage());
    }
    return message;
  }

}
