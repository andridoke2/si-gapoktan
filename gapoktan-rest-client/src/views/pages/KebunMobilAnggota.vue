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

                <!-- Table Data Kebun -->
                <table
                  class="table table-hover text-center"
                  v-if="anggota.golongan == 'Petani' || 'Mobil'"
                >
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col" v-if="anggota.golongan == 'Petani'">
                        Kode Kebun
                      </th>
                      <th scope="col" v-if="anggota.golongan == 'Petani'">
                        Lokasi Kebun
                      </th>
                      <th scope="col" v-if="anggota.golongan == 'Mobil'">
                        Kode Mobil
                      </th>
                      <th scope="col" v-if="anggota.golongan == 'Mobil'">
                        Merk Mobil
                      </th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(data, index) in listData"
                    :key="
                      anggota.golongan == 'Petani' ? data.kd_kebun : kd_mobil
                    "
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>
                        {{
                          anggota.golongan == 'Petani'
                            ? data.kd_kebun
                            : data.kd_mobil
                        }}
                      </td>
                      <td>
                        {{
                          anggota.golongan == 'Petani'
                            ? data.lokasi
                            : data.merk_mobil
                        }}
                      </td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-info"
                          data-toggle="modal"
                          data-target="#detailKebunMobilModal"
                          @click="detail(data)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div v-if="listData.length <= 0" class="text-center">
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

    <!-- Modal Detail Data Kebun | Mobil -->
    <div
      class="modal fade"
      id="detailKebunMobilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="detailKebunMobilLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="detailKebunMobilLabel">
              Detail Kebun | Mobil
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
            <div class="row" v-if="data.kd_kebun">
              <div class="col-md-6">
                <label for="kd_kebun">Kode Kebun</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.kd_kebun }}</p>
              </div>
            </div>
            <div class="row" v-if="data.kd_mobil">
              <div class="col-md-6">
                <label for="kd_mobil">Kode Mobil</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.kd_mobil }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="kd_anggota">Kode Anggota</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.kd_anggota }}</p>
              </div>
            </div>
            <div class="row" v-if="data.lokasi">
              <div class="col-md-6">
                <label for="lokasi">Lokasi</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.lokasi }}</p>
              </div>
            </div>
            <div class="row" v-if="data.jenis_kebun">
              <div class="col-md-6">
                <label for="jenis_kebun">Jenis Kebun</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.jenis_kebun }}</p>
              </div>
            </div>
            <div class="row" v-if="data.merk_mobil">
              <div class="col-md-6">
                <label for="merk_mobil">Merk Mobil</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.merk_mobil }}</p>
              </div>
            </div>
            <div class="row" v-if="data.nomor_plat_kendaraan">
              <div class="col-md-6">
                <label for="nomor_plat_kendaraan">No. Plat Kendaraan</label>
              </div>
              <div class="col-md-6">
                <p>{{ data.nomor_plat_kendaraan }}</p>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <a type="button" class="btn btn-info btn-icon-split btn-sm">
              <span class="icon text-white-50">
                <i class="fas fa-info"></i>
              </span>
              <span class="text">Penghasilan</span>
            </a>
          </div>
        </div>
      </div>
    </div>
    <!-- Akhir Modal Detail Data Kebun | Mobil -->
  </div>
</template>

<script>
/** Libarry */
import axios from 'axios';
import RAC from '@/config/RestAPIConfig';

/** Component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

export default {
  name: 'PageOfKebunMobilAnggota',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listData: [],
      anggota: {},
      data: {},
    };
  },

  async created() {
    /** dapatkan data anggota dari server */
    await this.findAnggotaByEmail(RAC.EMAIL);
    /** cek golongan anggota */
    switch (this.anggota.golongan) {
      case 'Petani':
        /** jika anggota dengan golongan petani */
        /** maka load data kebun milik anggota tersebut. */
        await this.loadKebun();
        /** stop pengecekan. */
        break;
      case 'Mobil':
        /** jika anggota dengan gologan mobil */
        /** maka load data mobil milik anggota tersebut. */
        await this.loadMobil();
        /** stop pengecekan. */
        break;
      default:
        /** jika golongan anggota tidak dengan keduanya, maka redirect halaman */
        /** redirect halaman karena tidak memiliki kebun maupun mobil */
        this.$router.push('/forbidden');
        break;
    }
  },

  methods: {
    /**
     * fungsi find anggota by email untuk user yang sedang login.
     *
     * @param {*} email
     */
    async findAnggotaByEmail(email) {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/anggota/getbyemail/${email}`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.anggota = res.data.payload;
            console.log(this.anggota);
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
            console.log('berhasil get anggota by email...');
          }
        });
    },

    /**
     * fungsi load kebun digunakan untuk mendapatkan data
     * kebun jika user yang sedang login merupakan golongan 'Petani',
     * mobil jika user yang sedang login merupakan golongan 'Mobil' &
     * akses akan dilarang bagi anggota yang bukan golongan 'Petani' & 'Mobil'.
     */
    async loadKebun() {
      let berhasil = false;

      const searchData = {
        searchKey: this.anggota.kd_anggota,
      };

      await axios
        .post(
          `${RAC.BASE_URL}/kebun/search/bykodeanggota`,
          searchData,
          RAC.HEADER
        )
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listData = res.data.payload;
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
            console.log('find kebun by kode anggota berhasil...');
          }
        });
    },

    async loadMobil() {
      let berhasil = false;

      const searchData = {
        searchKey: this.anggota.kd_anggota,
      };

      /**
       * endpoint untuk find mobil by kode anggota belum ada di rest server.
       * untuk menjalankan fungsi ini maka harus dibuat terlebih dahulu fungsi
       * find mobil by kode anggota.
       */

      await axios
        .post(
          `${RAC.BASE_URL}/mobil/search/bykodeanggota`,
          searchData,
          RAC.HEADER
        )
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            console.log(res.data.payload);
            this.listData = res.data.payload;
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
            console.log('find mobil by kode anggota berhasil...');
          }
        });
    },

    detail(data) {
      this.data = { ...data };
    },
  },
};
</script>
