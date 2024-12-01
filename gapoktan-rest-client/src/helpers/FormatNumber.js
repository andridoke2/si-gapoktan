export default {
  /**
   * Build-in js library
   *
   * Intl.NumberFormat
   * JavaScript has a number formatter (part of the Internationalization API).
   *
   * NOTE : masih belum sesuai dengan keinginan.
   *
   */

  /**
   * fungsi untuk mengubah angka menjadi format rupiah.
   * @param {*} value
   * @returns
   */

  formatRupiah(value) {
    // Create our number formatter.
    const formatter = Intl.NumberFormat('id-ID', {
      style: 'currency',
      currency: 'IDR',
      maximumFractionDigits: 3,
    });

    return formatter.format(value);
  },
};
