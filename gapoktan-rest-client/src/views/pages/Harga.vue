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
                <!-- Button Tambah Harga -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newHargaModal"
                  @click="clearPayload"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Harga</span>
                </a>

                <a
                  href=""
                  class="btn btn-warning btn-icon-split btn-sm float-right"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-file-csv"></i>
                  </span>
                  <span class="text">CSV</span>
                </a>
                <a
                  href=""
                  class="btn btn-danger btn-icon-split btn-sm float-right mx-2"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-file-pdf"></i>
                  </span>
                  <span class="text">PDF</span>
                </a>
                <a
                  href=""
                  class="btn btn-success btn-icon-split btn-sm float-right"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-file-excel"></i>
                  </span>
                  <span class="text">Excel</span>
                </a>

                <!-- Table Data Harga -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Sawit</th>
                      <th scope="col">Mobil</th>
                      <th scope="col">Pemuat</th>
                      <th scope="col">Penimbang</th>
                      <th scope="col">Pengurus</th>
                      <th scope="col">Pajak</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(harga, index) in listHarga"
                    :key="harga.kd_harga"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ harga.sawit }}</td>
                      <td>{{ harga.mobil }}</td>
                      <td>{{ harga.pemuat }}</td>
                      <td>{{ harga.penimbang }}</td>
                      <td>{{ harga.pengurus }}</td>
                      <td>{{ harga.pajak }}</td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-primary"
                          data-toggle="modal"
                          data-target="#detailHargaModal"
                          @click="detailHarga(harga)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                        <a
                          href=""
                          class="badge badge-pill badge-success mx-2"
                          data-toggle="modal"
                          data-target="#updateHargaModal"
                          @click="setUpdatePayload(harga)"
                        >
                          Update <i class="fas fa-check"></i>
                        </a>
                        <a
                          href=""
                          class="badge badge-pill badge-danger"
                          @click="deleteHarga(harga.kd_harga)"
                          >Delete <i class="fas fa-trash"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div v-if="listHarga.length <= 0" class="text-center">
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

    <!-- Modal Tambah Harga -->
    <div
      class="modal fade"
      id="newHargaModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newHargaModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newHargaModalLabel">Tambah Harga</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="createHarga">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <div class="row">
                    <div class="col-md-4">
                      <label for="sawit">Sawit</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Sawit -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="sawit"
                          name="sawit"
                          v-model="payload.sawit"
                          placeholder="Sawit"
                          autofocus
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="mobil">Mobil</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Mobil -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="mobil"
                          name="mobil"
                          v-model="payload.mobil"
                          placeholder="Mobil"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="pemuat">Pemuat</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Pemuat -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="pemuat"
                          name="pemuat"
                          v-model="payload.pemuat"
                          placeholder="Pemuat"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="penimbang">Penimbang</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Penimbang -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="penimbang"
                          name="penimbang"
                          v-model="payload.penimbang"
                          placeholder="Penimbang"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="pengurus">Pengurus</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Pengurus -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="pengurus"
                          name="pengurus"
                          v-model="payload.pengurus"
                          placeholder="Pengurus"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4"><label for="pajak">Pajak</label></div>
                    <div class="col-md-8">
                      <!-- Pajak -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="pajak"
                          name="pajak"
                          v-model="payload.pajak"
                          placeholder="Pajak"
                          required
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <small
                v-if="isNotValidForm"
                class="text-danger font-italic font-weight-light mx-auto"
                >{{ formErrorMessage }}</small
              >
              <button
                type="button"
                class="btn btn-danger btn-sm"
                data-dismiss="modal"
              >
                Batal
              </button>
              <button type="submit" class="btn btn-success btn-sm">
                Tambah <i class="fas fa-download"></i>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- Akhir Modal Tambah Harga -->

    <!-- Modal Update Harga -->
    <div
      class="modal fade"
      id="updateHargaModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="updateHargaModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="updateHargaModalLabel">Update Harga</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="updateHarga">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <input
                    type="hidden"
                    id="kd_harga"
                    name="kd_harga"
                    v-model="payload.kd_harga"
                    readonly
                  />
                  <div class="row">
                    <div class="col-md-4"><label for="sawit">Sawit</label></div>
                    <div class="col-md-8">
                      <!-- Sawit -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="sawit"
                          name="sawit"
                          v-model="payload.sawit"
                          placeholder="Sawit"
                          autofocus
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4"><label for="mobil">Mobil</label></div>
                    <div class="col-md-8">
                      <!-- Mobil -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="mobil"
                          name="mobil"
                          v-model="payload.mobil"
                          placeholder="Mobil"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="pemuat">Pemuat</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Pemuat -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="pemuat"
                          name="pemuat"
                          v-model="payload.pemuat"
                          placeholder="Pemuat"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="penimbang">Penimbang</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Penimbang -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="penimbang"
                          name="penimbang"
                          v-model="payload.penimbang"
                          placeholder="Penimbang"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="pengurus">Pengurus</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Pengurus -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="pengurus"
                          name="pengurus"
                          v-model="payload.pengurus"
                          placeholder="Pengurus"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4"><label for="pajak">Pajak</label></div>
                    <div class="col-md-8">
                      <!-- Pajak -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="pajak"
                          name="pajak"
                          v-model="payload.pajak"
                          placeholder="Pajak"
                          required
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <small
                v-if="isNotValidForm"
                class="text-danger font-italic font-weight-light mx-auto"
                >{{ formErrorMessage }}</small
              >
              <button
                type="button"
                class="btn btn-danger btn-sm"
                data-dismiss="modal"
              >
                Batal
              </button>
              <button type="submit" class="btn btn-success btn-sm">
                Update <i class="fas fa-download"></i>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- Akhir Modal Update Harga -->
  </div>
</template>

<script>
/** Libarry */
import axios from 'axios';
import $ from 'jquery';

/** Component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

/** API Configuration */
const baseURL = '/api/hamparan';
const TOKEN = localStorage.getItem('token');

const header = {
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
};

export default {
  name: 'PageOfHarga',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listHarga: [],
      payload: {
        kd_harga: '',
        sawit: '',
        mobil: '',
        pemuat: '',
        penimbang: '',
        pengurus: '',
        pajak: '',
      },
      isNotValidForm: false,
      formErrorMessage: '',
      alert: {
        icon: '',
        text: '',
        title: '',
      },
    };
  },

  async created() {
    await this.loadHarga();
  },

  methods: {
    async loadHarga() {
      let berhasil = false;
      await axios
        .get(`${baseURL}/harga`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listHarga = res.data.payload;
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
            console.log('load harga berhasil...');
          }
        });
    },

    async createHarga(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = {
        sawit: this.payload.sawit,
        mobil: this.payload.mobil,
        pemuat: this.payload.pemuat,
        penimbang: this.payload.penimbang,
        pengurus: this.payload.pengurus,
        pajak: this.payload.pajak,
      };

      await axios
        .post(`${baseURL}/harga`, payload, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            this.isNotValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
        })
        .finally(async () => {
          if (berhasil) {
            $('#newHargaModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadHarga();
          this.clearPayload();
        });
    },

    /**
     * detail harga belum jalan
     */
    detailHarga(harga) {
      this.payload = { ...harga };
    },

    setUpdatePayload(harga) {
      this.payload = { ...harga };
    },

    async updateHarga(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = { ...this.payload };

      await axios
        .put(`${baseURL}/harga`, payload, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            this.isNotValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
        })
        .finally(async () => {
          if (berhasil) {
            $('#updateHargaModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          this.loadHarga();
          this.clearPayload();
        });
    },

    async deleteHarga(kd_harga) {
      let berhasil = false;

      await axios
        .delete(`${baseURL}/harga/${kd_harga}`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            console.log(res);
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            console.log(err.response.data.message[0]);
          }
        })
        .finally(async () => {
          if (berhasil) {
            await this.loadHarga();
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = 'Data berhasil dihapus!';
          } else {
            this.alert.icon = 'warning';
            this.alert.title = 'Failed!';
            this.alert.text = 'Data gagal dihapus!';
          }
        });
    },

    clearPayload() {
      this.payload.kd_harga = '';
      this.payload.sawit = '';
      this.payload.mobil = '';
      this.payload.pemuat = '';
      this.payload.penimbang = '';
      this.payload.pengurus = '';
      this.payload.pajak = '';
    },

    showAlert(icon, title, text) {
      this.$swal({
        icon: icon,
        title: title,
        text: text,
      });
    },

    timestamp(timestamp) {
      const date = new Date(parseInt(timestamp));
      return `${date.getDate()}/${
        date.getMonth() + 1
      }/${date.getFullYear()} | ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
    },
  },
};
</script>
