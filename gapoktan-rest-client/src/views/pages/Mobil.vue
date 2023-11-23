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
                <!-- Button Tambah Mobil -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newMobilModal"
                  @click="clearPayload"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Mobil</span>
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
                      <th scope="col">Kode Mobil</th>
                      <th scope="col">Merk Mobil</th>
                      <th scope="col">No. Plat Kendaraan</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(mobil, index) in listMobil"
                    :key="mobil.kd_mobil"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ mobil.kd_mobil }}</td>
                      <td>{{ mobil.merk_mobil }}</td>
                      <td>{{ mobil.nomor_plat_kendaraan }}</td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-primary"
                          data-toggle="modal"
                          data-target="#detailMobilModal"
                          @click="detailMobil(mobil)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                        <a
                          href=""
                          class="badge badge-pill badge-success mx-2"
                          data-toggle="modal"
                          data-target="#updateMobilModal"
                          @click="setUpdatePayload(mobil)"
                        >
                          Update <i class="fas fa-check"></i>
                        </a>
                        <a
                          href=""
                          class="badge badge-pill badge-danger"
                          @click="deleteMobil(mobil.kd_mobil)"
                          >Delete <i class="fas fa-trash"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div v-if="listMobil.length <= 0" class="text-center">
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

    <!-- Modal Tambah Mobil -->
    <div
      class="modal fade"
      id="newMobilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newMobilModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newMobilModalLabel">Tambah Mobil</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="createMobil">
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
                      <label for="merk_mobil">Merk Mobil</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Merk Mobil -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="merk_mobil"
                          name="merk_mobil"
                          v-model="payload.merk_mobil"
                          placeholder="Merk Mobil"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="nomor_plat_kendaraan"
                        >No. Plat Kendaraan</label
                      >
                    </div>
                    <div class="col-md-8">
                      <!-- No Plat Kendaraan -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="nomor_plat_kendaraan"
                          name="nomor_plat_kendaraan"
                          v-model="payload.nomor_plat_kendaraan"
                          placeholder="No. Plat Kendaraan"
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
    <!-- Akhir Modal Tambah Mobil -->

    <!-- Modal Update Mobil -->
    <div
      class="modal fade"
      id="updateMobilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="updateMobilModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="updateMobilModalLabel">Update Mobil</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="updateMobil">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <input
                    type="hidden"
                    id="kd_mobil"
                    name="kd_mobil"
                    v-model="payload.kd_mobil"
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
                      <label for="merk_mobil">Merk Mobil</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Merk Mobil -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="merk_mobil"
                          name="merk_mobil"
                          v-model="payload.merk_mobil"
                          placeholder="Merk Mobil"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="nomor_plat_kendaraan"
                        >No. Plat Kendaraan</label
                      >
                    </div>
                    <div class="col-md-8">
                      <!-- No Plat Kendaraan -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="nomor_plat_kendaraan"
                          name="nomor_plat_kendaraan"
                          v-model="payload.nomor_plat_kendaraan"
                          placeholder="No. Plat Kendaraan"
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
    <!-- Akhir Modal Update Mobil -->

    <!-- Modal Detail Mobil -->
    <div
      class="modal fade"
      id="detailMobilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="detailMobilModalLabel"
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
                                >Mobil</a
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
                                <p>{{ payload.kd_mobil }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Merk</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.merk_mobil }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>No. Plat Kendaraan</label>
                              </div>
                              <div class="col-md-6">
                                <p>
                                  {{ payload.nomor_plat_kendaraan }}
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
    <!-- Akhir Modal Detail Mobil -->
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
  name: 'PageOfMobil',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listMobil: [],
      payload: {
        kd_mobil: '',
        kd_anggota: '',
        merk_mobil: '',
        nomor_plat_kendaraan: '',
      },
      pemilik: {},
      isNotValidForm: false,
      formErrorMessage: '',
      alert: {
        icon: '',
        title: '',
        text: '',
      },
    };
  },

  async created() {
    await this.loadMobil();
  },

  methods: {
    async loadMobil() {
      let berhasil = false;
      await axios
        .get(`${baseURL}/mobil`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listMobil = res.data.payload;
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

    async createMobil(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = {
        kd_anggota: this.payload.kd_anggota,
        merk_mobil: this.payload.merk_mobil,
        nomor_plat_kendaraan: this.payload.nomor_plat_kendaraan,
      };

      await axios
        .post(`${baseURL}/mobil`, payload, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
          }
        })
        .catch((err) => {
          console.log(err.response.data.message);
          if (!err.response.data.status) {
            berhasil = false;
            this.isNotValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
        })
        .finally(async () => {
          if (berhasil) {
            $('#newMobilModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadMobil();
          this.clearPayload();
        });
    },

    setUpdatePayload(mobil) {
      this.payload = { ...mobil };
    },

    async updateMobil(e) {
      e.preventDefault();

      let berhasil = false;
      const payload = this.payload;

      await axios
        .put(`${baseURL}/mobil`, payload, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
          }
        })
        .catch((err) => {
          console.log(err.response.data.message);
          if (!err.response.data.status) {
            berhasil = false;
            this.isNotValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
        })
        .finally(async () => {
          if (berhasil) {
            $('#updateMobilModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadMobil();
          this.clearPayload();
        });
    },

    async detailMobil(mobil) {
      this.payload = { ...mobil };
      await this.getPemilik(mobil.kd_anggota);
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

    async deleteMobil(kd_mobil) {
      let berhasil = false;
      await axios
        .delete(`${baseURL}/mobil/${kd_mobil}`, header)
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
            await this.loadMobil();
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
      this.payload.kd_mobil = '';
      this.payload.kd_anggota = '';
      this.payload.merk_mobil = '';
      this.payload.nomor_plat_kendaraan = '';
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
