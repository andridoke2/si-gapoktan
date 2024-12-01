/**
 * contoh export varibale
 */
const EXAMPLE = 'Andri';
export default {
  /**
   * sweetalert merupakan library js yang digunakan untuk menampilkan alert menjadi
   * lebih menarik.
   * @param {*} icon
   * @param {*} title
   * @param {*} text
   */
  showAlert(icon, title, text) {
    this.$swal({
      icon: icon,
      title: title,
      text: text,
    });
  },
  EXAMPLE,
};
