package hamparan.gapoktan.utils;

public class ClearDataIntoDatabase {

  public String cleanKode(String kode) {
    String cleanKode = "";
    String inisial = kode.substring(0, 3).toUpperCase();
    String digits = kode.substring(3, kode.length());
    return cleanKode.concat(inisial).concat(digits);
  }

  public String capitalize(String data) {
    String[] arrayOfData = data.split(" ");
    if (arrayOfData.length <= 1) {
      arrayOfData[0] = arrayOfData[0].substring(0, 1).toUpperCase() + arrayOfData[0].substring(1).toLowerCase();
      return arrayOfData[0].toString();
    } else {
      for (int i = 0; i <= arrayOfData.length - 1; i++) {
        arrayOfData[i] = arrayOfData[i].substring(0, 1).toUpperCase() + arrayOfData[i].substring(1).toLowerCase();
      }
      return String.join(" ", arrayOfData);
    }
  }

}
