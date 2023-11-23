<template>
  <div id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <Sidebar />
      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
          <Navbar />
          <!-- Begin Page Content -->
          <div class="container-fluid">
            <!-- Content Row -->
            <div class="row">
              <div class="col-md-12">
                <div class="row mb-3 float-right">
                  <a href="" class="btn btn-warning btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                      <i class="fas fa-file-csv"></i>
                    </span>
                    <span class="text">CSV</span>
                  </a>
                  <a href="" class="btn btn-danger btn-icon-split btn-sm mx-2">
                    <span class="icon text-white-50">
                      <i class="fas fa-file-pdf"></i>
                    </span>
                    <span class="text">PDF</span>
                  </a>
                  <a href="" class="btn btn-success btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                      <i class="fas fa-file-excel"></i>
                    </span>
                    <span class="text">Excel</span>
                  </a>
                </div>

                <!-- Table Data Hasil -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Kode Anggota</th>
                      <!-- jika anggota dengan gologan 'Petani', maka tampilkan data kode kebun -->
                      <th scope="col" v-if="user.golongan == 'Petani'">
                        Kode Kebun
                      </th>
                      <!-- jika anggota dengana gologan 'Mobil', maka tampilkan data kode mobil -->
                      <th scope="col" v-if="user.golongan == 'Mobil'">
                        Kode Mobil
                      </th>
                      <th scope="col">Kode Hasil</th>
                      <th scope="col">Status</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(penghasilan, index) in listPenghasilan"
                    :key="penghasilan.kd_hasil"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ penghasilan.kd_anggota }}</td>
                      <td v-if="user.golongan == 'Petani'">
                        {{ penghasilan.kd_kebun }}
                      </td>
                      <td v-if="user.golongan == 'Mobil'">
                        {{ penghasilan.kd_mobil }}
                      </td>
                      <td>{{ penghasilan.kd_hasil }}</td>
                      <td
                        :class="
                          checkStatus(penghasilan.status)
                            ? 'text-success'
                            : 'text-danger'
                        "
                      >
                        <i
                          :class="
                            checkStatus(penghasilan.status)
                              ? 'fas fa-check'
                              : 'fas fa-times'
                          "
                        ></i>
                      </td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-info"
                          data-toggle="modal"
                          data-target="#detailHasilModal"
                          @click="findDetailPenghasilanAnggota(penghasilan)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div v-if="listPenghasilan.length <= 0" class="text-center">
                  Data masih kosong!
                </div>
              </div>
            </div>
          </div>
          <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->
        <!-- Footer -->
        <Footer />
      </div>
      <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Modal Detail Penghasilan -->
    <div
      class="modal fade"
      id="detailHasilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="detailHasilLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="detailHasilLabel">
              Penghasilan {{ user.nama_anggota }}
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body small">
            <div class="row">
              <div class="col-md-6">
                <label for="nama_anggota">Nama Anggota</label>
              </div>
              <div class="col-md-6">
                <p>{{ user.nama_anggota }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="alamat">Alamat</label>
              </div>
              <div class="col-md-6">
                <p>{{ user.alamat }}</p>
              </div>
            </div>
            <div class="row" v-if="detailPenghasilan.kebun != null">
              <div class="col-md-6">
                <label for="lokasi">Lokasi</label>
              </div>
              <div class="col-md-6">
                <p>{{ detailPenghasilan.kebun.lokasi }}</p>
              </div>
            </div>
            <div class="row" v-if="detailPenghasilan.kebun != null">
              <div class="col-md-6">
                <label for="jenis_kebun">Jenis Kebun</label>
              </div>
              <div class="col-md-6">
                <p>{{ detailPenghasilan.kebun.jenis_kebun }}</p>
              </div>
            </div>
            <div class="row" v-if="detailPenghasilan.mobil != null">
              <div class="col-md-6">
                <label for="merk_mobil">Merk Mobil</label>
              </div>
              <div class="col-md-6">
                <p>{{ detailPenghasilan.mobil.merk_mobil }}</p>
              </div>
            </div>
            <div class="row" v-if="detailPenghasilan.mobil != null">
              <div class="col-md-6">
                <label for="nomor_plat_kendaraan">No. Plat Kendaraan</label>
              </div>
              <div class="col-md-6">
                <p>{{ detailPenghasilan.mobil.nomor_plat_kendaraan }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="no_telpon">No. Telepon</label>
              </div>
              <div class="col-md-6">
                <p>{{ user.no_telpon }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="golongan">Golongan</label>
              </div>
              <div class="col-md-6">
                <p>{{ user.golongan }}</p>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-md-6">
                <label for="sawit">Sawit</label>
              </div>
              <div class="col-md-6">
                <p>{{ formatRupiah(detailPenghasilan.harga.sawit) }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="mobil">Mobil</label>
              </div>
              <div class="col-md-6">
                <p>{{ formatRupiah(detailPenghasilan.harga.mobil) }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="pemuat">Pemuat</label>
              </div>
              <div class="col-md-6">
                <p>{{ formatRupiah(detailPenghasilan.harga.pemuat) }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="penimbang">Penimbang</label>
              </div>
              <div class="col-md-6">
                <p>{{ formatRupiah(detailPenghasilan.harga.penimbang) }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="pengurus">Pengurus</label>
              </div>
              <div class="col-md-6">
                <p>{{ formatRupiah(detailPenghasilan.harga.pengurus) }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="pajak">Pajak</label>
              </div>
              <div class="col-md-6">
                <p>{{ formatRupiah(detailPenghasilan.harga.pajak) }} /kg</p>
              </div>
            </div>
            <div v-if="user.golongan == 'Petani'">
              <hr />
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_mobil">Potongan Mobil</label>
                </div>
                <div class="col-md-6">
                  <p>
                    {{ formatRupiah(detailPenghasilan.hasil.potongan_mobil) }}
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_pemuat">Potongan Pemuat</label>
                </div>
                <div class="col-md-6">
                  <p>
                    {{ formatRupiah(detailPenghasilan.hasil.potongan_pemuat) }}
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_penimbang">Potongan Penimbang</label>
                </div>
                <div class="col-md-6">
                  <p>
                    {{
                      formatRupiah(detailPenghasilan.hasil.potongan_penimbang)
                    }}
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_pengurus">Potongan Pengurus</label>
                </div>
                <div class="col-md-6">
                  <p>
                    {{
                      formatRupiah(detailPenghasilan.hasil.potongan_pengurus)
                    }}
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_pajak">Potongan Pajak</label>
                </div>
                <div class="col-md-6">
                  <p>
                    {{ formatRupiah(detailPenghasilan.hasil.potongan_pajak) }}
                  </p>
                </div>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-md-6">
                <label for="tanggal">Tanggal Panen</label>
              </div>
              <div class="col-md-6">
                <p>{{ detailPenghasilan.hasil.tanggal }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="tonase">Tonase</label>
              </div>
              <div class="col-md-6">
                <p>{{ detailPenghasilan.hasil.tonase }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="total_potongan">Total Potongan</label>
              </div>
              <div class="col-md-6">
                <p>
                  {{ formatRupiah(detailPenghasilan.hasil.total_potongan) }}
                </p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="total_hasil" class="font-weight-bold"
                  >Hasil Bersih</label
                >
              </div>
              <div class="col-md-6">
                <p class="font-weight-bold">
                  {{ formatRupiah(detailPenghasilan.hasil.total_hasil) }}
                </p>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary btn-sm"
              data-dismiss="modal"
            >
              Kembali
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Akhir Modal Detail Penghasilan -->
  </div>
</template>

<script>
/** Libarry */
import axios from 'axios';

/** Component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

/** API Configuration */
const baseURL = '/api/hamparan';
const TOKEN = localStorage.getItem('token');
const EMAIL = localStorage.getItem('email');

const header = {
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
};

export default {
  name: 'PageOfPenghasilan',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listPenghasilan: [],
      user: {},
      detailPenghasilan: {
        anggota: {},
        kebun: {},
        mobil: {},
        hasil: {},
        harga: {},
      },
    };
  },

  async created() {
    await this.findUserByEmail(EMAIL);
    if (this.user) {
      await this.loadPenghasilanAnggota();
    }
  },

  methods: {
    /**
     * fungsi find anggota by email untuk user yang sedang login.
     *
     * @param {*} email
     */
    async findUserByEmail(email) {
      let berhasil = false;

      await axios
        .get(`${baseURL}/anggota/getbyemail/${email}`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.user = res.data.payload;
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            console.log(err.response.data.message);
          }
        })
        .finally(() => {
          if (berhasil) {
            console.log('(penghasilan) : berhasil get anggota by email...');
          }
        });
    },

    async loadPenghasilanAnggota() {
      let berhasil = false;

      const searchData = {
        searchKey: this.user.kd_anggota,
      };

      await axios
        .post(`${baseURL}/detailhasil/penghasilan/anggota`, searchData, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listPenghasilan = res.data.payload;
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            console.log(err.response.data.message);
          }
        })
        .finally(() => {
          if (berhasil) {
            console.log('find penghasilan by kode anggota berhasil...');
          }
        });
    },

    async findDetailPenghasilanAnggota(penghasilan) {
      let berhasil = false;

      const request = {
        kd_anggota: penghasilan.kd_anggota,
        kd_kebun: penghasilan.kd_kebun,
        kd_mobil: penghasilan.kd_mobil,
        kd_hasil: penghasilan.kd_hasil,
      };

      await axios
        .post(`${baseURL}/detailhasil/penghasilan`, request, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.detailPenghasilan.anggota = res.data.payload.anggota;
            this.detailPenghasilan.kebun = res.data.payload.kebun;
            this.detailPenghasilan.mobil = res.data.payload.mobil;
            this.detailPenghasilan.hasil = res.data.payload.hasil;
            this.detailPenghasilan.harga = res.data.payload.harga;
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            console.log(err.response.data.message);
          }
        })
        .finally(() => {
          if (berhasil) {
            console.log('detail penghasilan anggota berhasil ditemukan...');
          }
        });
    },

    checkStatus(status) {
      return status == 'Y' ? true : false;
    },

    /**
     * Build-in js library
     *
     * Intl.NumberFormat
     * JavaScript has a number formatter (part of the Internationalization API).
     *
     * NOTE : masih belum sesuai dengan keinginan.
     *
     */
    formatRupiah(value) {
      // Create our number formatter.
      const formatter = new Intl.NumberFormat('id-ID', {
        style: 'currency',
        currency: 'IDR',
        maximumFractionDigits: 3,
      });

      return formatter.format(value);
    },
  },
};
</script>
