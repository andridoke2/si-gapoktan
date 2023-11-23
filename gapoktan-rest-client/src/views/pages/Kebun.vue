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
                <!-- Button Tambah Kebun -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newKebunModal"
                  @click="clearPayload"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Kebun</span>
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

                <!-- Table Data Kebun -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Kode Kebun</th>
                      <th scope="col">Lokasi</th>
                      <th scope="col">Jenis Kebun</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(kebun, index) in listKebun"
                    :key="kebun.kd_kebun"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ kebun.kd_kebun }}</td>
                      <td>{{ kebun.lokasi }}</td>
                      <td>{{ kebun.jenis_kebun }}</td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-primary"
                          data-toggle="modal"
                          data-target="#detailKebunModal"
                          @click="detailKebun(kebun)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                        <a
                          href=""
                          class="badge badge-pill badge-success mx-2"
                          data-toggle="modal"
                          data-target="#updateKebunModal"
                          @click="setUpdatePayload(kebun)"
                        >
                          Update <i class="fas fa-check"></i>
                        </a>
                        <a
                          href=""
                          class="badge badge-pill badge-danger"
                          @click="deleteKebun(kebun.kd_kebun)"
                          >Delete <i class="fas fa-trash"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div v-if="listKebun.length <= 0" class="text-center">
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

    <!-- Modal Tambah Kebun -->
    <div
      class="modal fade"
      id="newKebunModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newKebunModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newKebunModalLabel">Tambah Kebun</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="createKebun">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <div class="row">
                    <div class="col-md-4">
                      <label for="kd_anggota">Kode Anggota</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Kode Anggota -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="kd_anggota"
                          name="kd_anggota"
                          v-model="payload.kd_anggota"
                          placeholder="Kode Anggota"
                          autofocus
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="lokasi">Lokasi Kebun</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Lokasi -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="lokasi"
                          name="lokasi"
                          v-model="payload.lokasi"
                          placeholder="Lokasi Kebun"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="jenis_kebun">Jenis Kebun</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Jenis Kebun -->
                      <div class="form-group">
                        <select
                          class="form-control form-control-sm custom-select"
                          id="jenis_kebun"
                          v-model="payload.jenis_kebun"
                          required
                        >
                          <option value="">Pilih Jenis Kebun</option>
                          <option value="Kavlingan">Kavlingan</option>
                          <option value="Pekarangan">Pekarangan</option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <small
                v-if="isValidForm"
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
    <!-- Akhir Modal Tambah Kebun -->

    <!-- Modal Update Kebun -->
    <div
      class="modal fade"
      id="updateKebunModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="updateKebunModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="updateKebunModalLabel">Update Kebun</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="updateKebun">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <input
                    type="hidden"
                    id="kd_kebun"
                    name="kd_kebun"
                    v-model="payload.kd_kebun"
                    readonly
                  />
                  <div class="row">
                    <div class="col-md-4">
                      <label for="kd_anggota">Kode Anggota</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Kode Anggota -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="kd_anggota"
                          name="kd_anggota"
                          v-model="payload.kd_anggota"
                          placeholder="Kode Anggota"
                          autofocus
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="lokasi">Lokasi Kebun</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Lokasi -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="lokasi"
                          name="lokasi"
                          v-model="payload.lokasi"
                          placeholder="Lokasi Kebun"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="jenis_kebun">Jenis Kebun</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Jenis Kebun -->
                      <div class="form-group">
                        <select
                          class="form-control form-control-sm custom-select"
                          id="jenis_kebun"
                          v-model="payload.jenis_kebun"
                          required
                        >
                          <option value="">Pilih Jenis Kebun</option>
                          <option value="Kavlingan">Kavlingan</option>
                          <option value="Pekarangan">Pekarangan</option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <small
                v-if="isValidForm"
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
    <!-- Akhir Modal Update Kebun -->

    <!-- Modal Detail Kebun -->
    <div
      class="modal fade"
      id="detailKebunModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="detailKebunModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <form>
            <div class="modal-body">
              <div class="row">
                <div class="container emp-profile">
                  <form method="post">
                    <div class="row">
                      <div class="col-md-4">
                        <div class="profile-img">
                          <img
                            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                            alt=""
                          />
                          <div class="file btn btn-lg btn-primary">
                            Change Photo
                            <input type="file" name="file" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="profile-head">
                          <h5 class="font-weight-bold">
                            {{ pemilik.nama_anggota }}
                          </h5>
                          <h6>{{ pemilik.golongan }}</h6>
                          <p class="proile-rating">
                            Bergabung sejak :
                            {{ timestamp(pemilik.date_created) }}
                          </p>
                          <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                              <a
                                class="nav-link active"
                                id="home-tab"
                                data-toggle="tab"
                                href="#home"
                                role="tab"
                                aria-controls="home"
                                aria-selected="true"
                                >Kebun</a
                              >
                            </li>
                            <li class="nav-item">
                              <a
                                class="nav-link"
                                id="profile-tab"
                                data-toggle="tab"
                                href="#profile"
                                role="tab"
                                aria-controls="profile"
                                aria-selected="false"
                                >Pemilik</a
                              >
                            </li>
                          </ul>
                        </div>
                      </div>
                      <div class="col-md-2">
                        <input
                          type="button"
                          class="profile-edit-btn btn-sm"
                          name="btnAddMore"
                          value="Keluar"
                          data-dismiss="modal"
                        />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-4">
                        <div class="profile-work">
                          <p>WORK LINK</p>
                          <a href="">Website Link</a><br />
                          <a href="">Bootsnipp Profile</a><br />
                          <a href="">Bootply Profile</a>
                          <p>SKILLS</p>
                          <a href="">Web Designer</a><br />
                          <a href="">Web Developer</a><br />
                          <a href="">WordPress</a><br />
                          <a href="">WooCommerce</a><br />
                          <a href="">PHP, .Net</a><br />
                        </div>
                      </div>
                      <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                          <div
                            class="tab-pane fade show active"
                            id="home"
                            role="tabpanel"
                            aria-labelledby="home-tab"
                          >
                            <div class="row">
                              <div class="col-md-6">
                                <label>Kode</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.kd_kebun }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Lokasi</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.lokasi }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Jenis</label>
                              </div>
                              <div class="col-md-6">
                                <p>
                                  {{ payload.jenis_kebun }}
                                </p>
                              </div>
                            </div>
                          </div>
                          <div
                            class="tab-pane fade"
                            id="profile"
                            role="tabpanel"
                            aria-labelledby="profile-tab"
                          >
                            <div class="row">
                              <div class="col-md-6">
                                <label>Kode</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ pemilik.kd_anggota }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Nama</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ pemilik.nama_anggota }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Alamat</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ pemilik.alamat }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Jenis Kelamin</label>
                              </div>
                              <div class="col-md-6">
                                <p>
                                  {{
                                    pemilik.jenis_kelamin == 'L'
                                      ? 'Laki-laki'
                                      : 'Perempuan'
                                  }}
                                </p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>No. Telepon</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ pemilik.no_telpon }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-12">
                                <label>Email</label><br />
                                <p>{{ pemilik.email }}</p>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- Akhir Modal Detail Kebun -->
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
  name: 'PageOfKebun',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listKebun: [],
      payload: {
        kd_kebun: '',
        kd_anggota: '',
        lokasi: '',
        jenis_kebun: '',
      },
      pemilik: {},
      isValidForm: false,
      formErrorMessage: '',
      alert: {
        icon: '',
        title: '',
        text: '',
      },
    };
  },

  async created() {
    await this.loadKebun();
  },

  methods: {
    async loadKebun() {
      let berhasil = false;
      await axios
        .get(`${baseURL}/kebun`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listKebun = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err);
        })
        .finally(() => {
          if (berhasil) {
            console.log('berhasil...');
          }
        });
    },

    async createKebun(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = {
        kd_anggota: this.payload.kd_anggota,
        lokasi: this.payload.lokasi,
        jenis_kebun: this.payload.jenis_kebun,
      };

      await axios
        .post(`${baseURL}/kebun`, payload, header)
        .then((res) => {
          if (res.data.status) {
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
            berhasil = true;
          }
        })
        .catch((err) => {
          console.log(err.response.data.message);
          if (err.response.data.status == false) {
            berhasil = false;
            this.isValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
        })
        .finally(async () => {
          if (berhasil) {
            $('#newKebunModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadKebun();
          this.clearPayload();
        });
    },

    setUpdatePayload(kebun) {
      this.payload.kd_kebun = kebun.kd_kebun;
      this.payload.kd_anggota = kebun.kd_anggota;
      this.payload.lokasi = kebun.lokasi;
      this.payload.jenis_kebun = kebun.jenis_kebun;
    },

    async updateKebun(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = this.payload;

      await axios
        .put(`${baseURL}/kebun`, payload, header)
        .then((res) => {
          berhasil = true;
          if (res.data.status) {
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
          }
        })
        .catch((err) => {
          console.log(err.response.data.message);
          berhasil = false;
          if (err.response.data.status == false) {
            this.isValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
        })
        .finally(async () => {
          if (berhasil) {
            $('#updateKebunModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadKebun();
          this.clearPayload();
        });
    },

    async detailKebun(kebun) {
      /** set data kebun untuk detail */
      this.payload.kd_kebun = kebun.kd_kebun;
      this.payload.kd_anggota = kebun.kd_anggota;
      this.payload.lokasi = kebun.lokasi;
      this.payload.jenis_kebun = kebun.jenis_kebun;

      /** set data anggota pemilik kebun untuk detail */
      await this.getPemilik(kebun.kd_anggota);
    },

    async getPemilik(kd_anggota) {
      await axios
        .get(`${baseURL}/anggota/${kd_anggota}`, header)
        .then((res) => {
          if (res.data.status) {
            this.pemilik = res.data.payload;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    async deleteKebun(kd_kebun) {
      let berhasil = false;
      await axios
        .delete(`${baseURL}/kebun/${kd_kebun}`, header)
        .then((res) => {
          console.log(res);
          berhasil = true;
        })
        .catch((err) => {
          console.log(err.response.data.message);
          berhasil = false;
        })
        .finally(async () => {
          if (berhasil) {
            await this.loadKebun();
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
      this.payload.kd_kebun = '';
      this.payload.kd_anggota = '';
      this.payload.lokasi = '';
      this.payload.jenis_kebun = '';
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
