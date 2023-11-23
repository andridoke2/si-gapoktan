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
                <!-- Button Tambah Hasil -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newHasilModal"
                  @click="clearPayload"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Hasil</span>
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

                <!-- Table Data Hasil -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Kode Anggota</th>
                      <th scope="col">Kode Kebun</th>
                      <th scope="col">Kode Mobil</th>
                      <th scope="col">Kode Hasil</th>
                      <th scope="col">Status</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(detailHasil, index) in listDetailHasil"
                    :key="detailHasil.kd_hasil"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ detailHasil.kd_anggota }}</td>
                      <td>{{ detailHasil.kd_kebun }}</td>
                      <td>{{ detailHasil.kd_mobil }}</td>
                      <td>{{ detailHasil.kd_hasil }}</td>
                      <td
                        :class="
                          checkStatus(detailHasil.status)
                            ? 'text-success'
                            : 'text-danger'
                        "
                      >
                        <i
                          :class="
                            checkStatus(detailHasil.status)
                              ? 'fas fa-check'
                              : 'fas fa-times'
                          "
                        ></i>
                      </td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-primary"
                          data-toggle="modal"
                          data-target="#detailHasilModal"
                          @click="findDetailHasil(detailHasil)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                        <a
                          href=""
                          class="badge badge-pill badge-success mx-2"
                          data-toggle="modal"
                          data-target="#updateHasilModal"
                          @click="setUpdatePayload(detailHasil)"
                        >
                          Update <i class="fas fa-check"></i>
                        </a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div v-if="listDetailHasil.length <= 0" class="text-center">
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

    <!-- Modal Tambah Hasil -->
    <div
      class="modal fade"
      id="newHasilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newHasilModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newHasilModalLabel">Tambah Hasil</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="createHasil">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <div class="row">
                    <div class="col-md-4">
                      <label for="golongan">Golongan</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Golongan -->
                      <div class="form-group">
                        <select
                          class="form-control form-control-sm custom-select"
                          id="golongan"
                          v-model="payload.golongan"
                          required
                        >
                          <option value="">Pilih Golongan</option>
                          <option value="Petani">Petani</option>
                          <option value="Mobil">Mobil</option>
                          <option value="Pemuat">Pemuat</option>
                          <option value="Penimbang">Penimbang</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan != ''">
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
                  <div class="row" v-if="payload.golongan == 'Petani'">
                    <div class="col-md-4">
                      <label for="kd_kebun">Kode Kebun</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Kode Kebun -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="kd_kebun"
                          name="kd_kebun"
                          v-model="payload.kd_kebun"
                          placeholder="Kode Kebun"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan == 'Mobil'">
                    <div class="col-md-4">
                      <label for="kd_mobil">Kode Mobil</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Kode Mobil -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="kd_mobil"
                          name="kd_mobil"
                          v-model="payload.kd_mobil"
                          placeholder="Kode Mobil"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan != ''">
                    <div class="col-md-4">
                      <label for="tanggal">Tanggal</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Tanggal -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="tanggal"
                          name="tanggal"
                          v-model="payload.tanggal"
                          placeholder="Tanggal"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan != ''">
                    <div class="col-md-4">
                      <label for="tonase">Tonase</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Tonase -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="tonase"
                          name="tonase"
                          v-model="payload.tonase"
                          placeholder="Tonase"
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
    <!-- Akhir Modal Tambah Hasil -->

    <!-- Modal Detail Hasil -->
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
              Penghasilan Anggota
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
              <div class="col-md-12 text-uppercase">
                <p class="font-weight-bold text-center">Anggota</p>
              </div>
              <div class="col-md-6">
                <label for="nama_anggota">Nama Anggota</label>
              </div>
              <div class="col-md-6">
                <p>{{ anggota.nama_anggota }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="alamat">Alamat</label>
              </div>
              <div class="col-md-6">
                <p>{{ anggota.alamat }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="no_telpon">No. Telepon</label>
              </div>
              <div class="col-md-6">
                <p>{{ anggota.no_telpon }}</p>
              </div>
            </div>
            <div class="row" v-if="kebun != null">
              <div class="col-md-6">
                <label for="lokasi">Lokasi</label>
              </div>
              <div class="col-md-6">
                <p>{{ kebun.lokasi }}</p>
              </div>
            </div>
            <div class="row" v-if="kebun != null">
              <div class="col-md-6">
                <label for="jenis_kebun">Jenis Kebun</label>
              </div>
              <div class="col-md-6">
                <p>{{ kebun.jenis_kebun }}</p>
              </div>
            </div>
            <div class="row" v-if="mobil != null">
              <div class="col-md-6">
                <label for="merk_mobil">Merk Mobil</label>
              </div>
              <div class="col-md-6">
                <p>{{ mobil.merk_mobil }}</p>
              </div>
            </div>
            <div class="row" v-if="mobil != null">
              <div class="col-md-6">
                <label for="nomor_plat_kendaraan">No. Plat Kendaraan</label>
              </div>
              <div class="col-md-6">
                <p>{{ mobil.nomor_plat_kendaraan }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="golongan">Golongan</label>
              </div>
              <div class="col-md-6">
                <p>{{ anggota.golongan }}</p>
              </div>
            </div>
            <hr />
            <div class="col-md-12 text-uppercase">
              <p class="font-weight-bold text-center">Harga</p>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="sawit">Sawit</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ harga.sawit }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="mobil">Mobil</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ harga.mobil }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="pemuat">Pemuat</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ harga.pemuat }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="penimbang">Penimbang</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ harga.penimbang }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="pengurus">Pengurus</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ harga.pengurus }} /kg</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="pajak">Pajak</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ harga.pajak }} /kg</p>
              </div>
            </div>
            <div v-if="anggota.golongan == 'Petani'">
              <hr />
              <div class="col-md-12 text-uppercase">
                <p class="font-weight-bold text-center">Potongan</p>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_mobil">Potongan Mobil</label>
                </div>
                <div class="col-md-6">
                  <p>Rp. {{ hasil.potongan_mobil }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_pemuat">Potongan Pemuat</label>
                </div>
                <div class="col-md-6">
                  <p>Rp. {{ hasil.potongan_pemuat }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_penimbang">Potongan Penimbang</label>
                </div>
                <div class="col-md-6">
                  <p>Rp. {{ hasil.potongan_penimbang }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_pengurus">Potongan Pengurus</label>
                </div>
                <div class="col-md-6">
                  <p>Rp. {{ hasil.potongan_pengurus }}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label for="potongan_pajak">Potongan Pajak</label>
                </div>
                <div class="col-md-6">
                  <p>Rp. {{ hasil.potongan_pajak }}</p>
                </div>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-md-12 text-uppercase">
                <p class="font-weight-bold text-center">Hasil</p>
              </div>
              <div class="col-md-6">
                <label for="tanggal">Tanggal Panen</label>
              </div>
              <div class="col-md-6">
                <p>{{ hasil.tanggal }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="tonase">Tonase</label>
              </div>
              <div class="col-md-6">
                <p>{{ hasil.tonase }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="total_potongan">Total Potongan</label>
              </div>
              <div class="col-md-6">
                <p>Rp. {{ hasil.total_potongan }}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="total_hasil" class="font-weight-bold"
                  >Hasil Bersih</label
                >
              </div>
              <div class="col-md-6">
                <p class="font-weight-bold">Rp. {{ hasil.total_hasil }}</p>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <a
              type="button"
              class="btn btn-info btn-sm justify-content-center"
              v-if="!checkStatus(konfirmasiData.status)"
              @click="konfirmasi(konfirmasiData)"
            >
              Konfirmasi <i class="fas fa-handshake"></i>
            </a>
          </div>
        </div>
      </div>
    </div>
    <!-- Akhir Modal Detail Hasil -->

    <!-- Modal Update Hasil -->
    <div
      class="modal fade"
      id="updateHasilModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="updateHasilModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="updateHasilModalLabel">Update Hasil</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="updateHasil">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8 mx-auto">
                  <div class="row">
                    <div class="col-md-4">
                      <label for="golongan">Golongan</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Golongan -->
                      <div class="form-group">
                        <select
                          class="form-control form-control-sm custom-select"
                          id="golongan"
                          v-model="payload.golongan"
                          required
                          disabled
                        >
                          <option value="">Pilih Golongan</option>
                          <option value="Petani">Petani</option>
                          <option value="Mobil">Mobil</option>
                          <option value="Pemuat">Pemuat</option>
                          <option value="Penimbang">Penimbang</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan != ''">
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
                          required
                          readonly
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan == 'Petani'">
                    <div class="col-md-4">
                      <label for="kd_kebun">Kode Kebun</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Kode Kebun -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="kd_kebun"
                          name="kd_kebun"
                          v-model="payload.kd_kebun"
                          placeholder="Kode Kebun"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan == 'Mobil'">
                    <div class="col-md-4">
                      <label for="kd_mobil">Kode Mobil</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Kode Mobil -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="kd_mobil"
                          name="kd_mobil"
                          v-model="payload.kd_mobil"
                          placeholder="Kode Mobil"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan != ''">
                    <div class="col-md-4">
                      <label for="tanggal">Tanggal</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Tanggal -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="tanggal"
                          name="tanggal"
                          v-model="payload.tanggal"
                          placeholder="Tanggal"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row" v-if="payload.golongan != ''">
                    <div class="col-md-4">
                      <label for="tonase">Tonase</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Tonase -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="tonase"
                          name="tonase"
                          v-model="payload.tonase"
                          placeholder="Tonase"
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
    <!-- Akhir Modal Update Hasil -->
  </div>
</template>

<script>
/**
 * Rencana (Rest Server): FIX
 * Menu gaji akan dihapus & semua akan dihandle dari menu hasil ini.
 * Yang akan ditampilkan pada table hasil adalah data dari database table detail hasil.
 * Ketika user ingin melihat detail maka akan diberikan detail dari hasil tersebut.
 * Dan pada table detail hasil akan ditambahkan field status
 * (apakah anggota tersebut sudah melakukan pengambilan hasil panen tersebut).
 *
 * untuk memvalidasi pengambilan hasil panen anggota khususnya petani
 * dilakukan dengan cara meng-update status dari table detail hasil tersebut.
 */

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
  name: 'PageOfHasil',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listDetailHasil: [],
      payload: {
        kd_hasil: '',
        kd_anggota: '',
        kd_kebun: '',
        kd_mobil: '',
        tanggal: '',
        tonase: '',
        golongan: '',
        status: '',
      },
      anggota: {},
      kebun: {},
      mobil: {},
      hasil: {},
      harga: {},
      konfirmasiData: {
        kd_hasil: '',
        kd_anggota: '',
        kd_kebun: '',
        kd_mobil: '',
        status: '',
      },
      dataAnggota: {},
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
    /**
     * 1. sebaiknya detail hasil diurutkan berdasarkan status belum dikonfirmasi (status = 'N')
     */
    await this.loadDetailHasil();
  },

  methods: {
    async loadDetailHasil() {
      let berhasil = false;

      await axios
        .get(`${baseURL}/detailhasil`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listDetailHasil = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.log('berhasil...');
          }
        });
    },

    async createHasil(e) {
      e.preventDefault();

      let berhasil = false;

      const request = {
        kd_anggota: this.payload.kd_anggota,
        kd_kebun: this.payload.kd_kebun,
        kd_mobil: this.payload.kd_mobil,
        tanggal: this.payload.tanggal,
        tonase: this.payload.tonase,
        golongan: this.payload.golongan,
      };

      await axios
        .post(`${baseURL}/hasil`, request, header)
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
            $('#newHasilModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadDetailHasil();
          this.clearPayload();
        });
    },

    async findDetailHasil(detailHasil) {
      let berhasil = false;

      this.konfirmasiData = { ...detailHasil };

      const request = {
        kd_anggota: detailHasil.kd_anggota,
        kd_kebun: detailHasil.kd_kebun,
        kd_mobil: detailHasil.kd_mobil,
        kd_hasil: detailHasil.kd_hasil,
      };

      await axios
        .post(`${baseURL}/detailhasil/penghasilan`, request, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.anggota = res.data.payload.anggota;
            this.kebun = res.data.payload.kebun;
            this.mobil = res.data.payload.mobil;
            this.hasil = res.data.payload.hasil;
            this.harga = res.data.payload.harga;
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
            console.log('detail hasil berhasil ditemukan...');
          }
        });
    },

    async konfirmasi(konfirmasiData) {
      let berhasil = false;

      if (konfirmasiData.status == 'N') {
        konfirmasiData.status = 'Y';
      }

      const request = {
        kd_hasil: konfirmasiData.kd_hasil,
        kd_anggota: konfirmasiData.kd_anggota,
        kd_kebun: konfirmasiData.kd_kebun,
        kd_mobil: konfirmasiData.kd_mobil,
        status: konfirmasiData.status,
      };

      await axios
        .post(`${baseURL}/detailhasil/konfirmasi`, request, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            console.log(res.data.message[0]);
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            console.log(err.response.data.message[0]);
          }
        })
        .finally(async () => {
          if (berhasil) {
            await this.loadDetailHasil();
            $('#detailHasilModal .close').click();
          }
        });
    },

    async setUpdatePayload(detailHasil) {
      await this.findDetailHasil(detailHasil);

      this.payload.kd_hasil = this.hasil.kd_hasil;
      this.payload.kd_anggota = this.anggota.kd_anggota;

      switch (this.hasil.golongan) {
        case 'Petani':
          this.payload.kd_kebun = this.kebun.kd_kebun;
          break;
        case 'Mobil':
          this.payload.kd_mobil = this.mobil.kd_mobil;
          break;
      }

      this.payload.tanggal = this.hasil.tanggal;
      this.payload.tonase = this.hasil.tonase;
      this.payload.golongan = this.hasil.golongan;
    },

    async updateHasil(e) {
      e.preventDefault();

      let berhasil = false;

      await axios
        .put(`${baseURL}/hasil`, this.payload, header)
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
            $('#updateHasilModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadDetailHasil();
          this.clearPayload();
        });
    },

    /**
     * fungsi find anggota ini digunakan untuk menampilkan nama anggota pada table
     * berdasarkan kode anggota yang ada pada table detail hasil.
     * next jalankan fungsi ini pada table hasil anggota.
     */
    async findAnggotaByKode(kd_anggota) {
      let berhasil = false;

      await axios
        .get(`${baseURL}/anggota/${kd_anggota}`, header)
        .then((res) => {
          if (res.data.status) {
            this.dataAnggota = res.data.payload;
            berhasil = true;
          }
        })
        .catch((err) => {
          if (!err.response.data.status) {
            berhasil = false;
            console.log(err.response.data.message[0]);
          }
        })
        .finally(() => {
          if (berhasil) {
            console.log('find anggota by kode berhasil...');
          }
        });
    },

    checkStatus(status) {
      return status == 'Y' ? true : false;
    },

    clearPayload() {
      this.payload.kd_hasil = '';
      this.payload.kd_anggota = '';
      this.payload.kd_kebun = '';
      this.payload.kd_mobil = '';
      this.payload.tanggal = '';
      this.payload.tonase = '';
      this.payload.golongan = '';
      this.payload.status = '';
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
