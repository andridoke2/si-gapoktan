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
                <!-- Button Tambah Anggota -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newAnggotaModal"
                  @click="clearPayload"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Anggota</span>
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

                <!-- Table Data Anggota -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Nama Anggota</th>
                      <th scope="col">Golongan</th>
                      <th scope="col">Role</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(anggota, index) in listAnggota"
                    :key="anggota.kd_anggota"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ anggota.nama_anggota }}</td>
                      <td>{{ anggota.golongan }}</td>
                      <td
                        :class="
                          anggota.role == 'ADMIN'
                            ? 'text-success'
                            : 'text-primary'
                        "
                      >
                        <i
                          :class="
                            anggota.role == 'ADMIN' ? 'fa fa-key' : 'fa fa-user'
                          "
                        ></i>
                      </td>
                      <td>
                        <a
                          href=""
                          class="badge badge-pill badge-primary"
                          data-toggle="modal"
                          data-target="#detailAnggotaModal"
                          @click="detailAnggota(anggota)"
                          >Detail <i class="fas fa-universal-access"></i
                        ></a>
                        <a
                          href=""
                          class="badge badge-pill badge-success mx-2"
                          data-toggle="modal"
                          data-target="#updateAnggotaModal"
                          @click="setUpdatePayload(anggota)"
                        >
                          Update <i class="fas fa-check"></i>
                        </a>
                        <a
                          href=""
                          @click="deleteAnggota(anggota.kd_anggota)"
                          class="badge badge-pill badge-danger"
                          >Delete <i class="fas fa-trash"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <!-- jika data masih kosong! -->
                <div v-if="listAnggota.length <= 0" class="text-center">
                  Data masih kosong!
                </div>

                <!-- Reuseable Pagination -->
                <pagination
                  :totalPages="pagination.response.totalPage"
                  :perPage="pagination.response.pageSize"
                  :currentPage="pagination.currentPage"
                  @pagechanged="
                    onPageChange(
                      pagination.request.pageNo,
                      pagination.request.pageSize,
                      pagination.request.sortBy,
                      pagination.request.sortDir
                    )
                  "
                />
                <!-- Reuseable Pagination -->
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

    <!-- Modal Tambah Anggota -->
    <div
      class="modal fade"
      id="newAnggotaModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newAnggotaModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newAnggotaModalLabel">
              Tambah Anggota
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
          <form @submit="createAnggota">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8">
                  <!-- div.row>div.col-md-4>label : lanjut disini untuk buat label field -->
                  <div class="row">
                    <div class="col-md-4">
                      <label for="nama_anggota">Nama Anggota</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Nama -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="nama_anggota"
                          name="nama_anggota"
                          v-model="payload.nama_anggota"
                          placeholder="Nama Anggota"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="alamat">Alamat</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Alamat -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="alamat"
                          name="alamat"
                          v-model="payload.alamat"
                          placeholder="Alamat"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="jenis_kelamin">Jenis Kelamin</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Jenis Kelamin -->
                      <div class="form-group">
                        <div
                          class="custom-control custom-radio custom-control-inline"
                        >
                          <input
                            type="radio"
                            id="customRadioInline1"
                            name="customRadioInline1"
                            value="L"
                            v-model="payload.jenis_kelamin"
                            class="custom-control-input"
                          />
                          <label
                            class="custom-control-label"
                            for="customRadioInline1"
                            >Laki-laki</label
                          >
                        </div>
                        <div
                          class="custom-control custom-radio custom-control-inline"
                        >
                          <input
                            type="radio"
                            id="customRadioInline2"
                            name="customRadioInline1"
                            value="P"
                            v-model="payload.jenis_kelamin"
                            class="custom-control-input"
                          />
                          <label
                            class="custom-control-label"
                            for="customRadioInline2"
                            >Perempuan</label
                          >
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="no_telpon">No. Telepon</label>
                    </div>
                    <div class="col-md-8">
                      <!-- No Telpon -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="no_telpon"
                          name="no_telpon"
                          v-model="payload.no_telpon"
                          placeholder="No Telepon"
                          required
                        />
                      </div>
                    </div>
                  </div>
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
                  <div class="row">
                    <div class="col-md-4"><label for="email">Email</label></div>
                    <div class="col-md-8">
                      <!-- Email -->
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control form-control-sm"
                          id="email"
                          name="email"
                          v-model="payload.email"
                          placeholder="Email"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4"><label for="image">Image</label></div>
                    <div class="col-md-8">
                      <!-- Image -->
                      <div class="form-group">
                        <input
                          type="file"
                          class="form-control form-control-sm"
                          id="input_image"
                          @change="onFileChange"
                        />
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div id="image" v-if="url">
                    <div id="preview">
                      <img v-if="url" :src="url" class="img-fluid" />
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
    <!-- Akhir Modal Tambah Anggota -->

    <!-- Modal Update Anggota -->
    <div
      class="modal fade"
      id="updateAnggotaModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="updateAnggotaModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="updateAnggotaModalLabel">
              Update Anggota
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
          <form @submit="updateAnggota">
            <div class="modal-body">
              <div class="row">
                <div class="col-md-8">
                  <input
                    type="hidden"
                    id="kd_anggota"
                    name="kd_anggota"
                    v-model="payload.kd_anggota"
                    readonly
                  />
                  <div class="row">
                    <div class="col-md-4">
                      <label for="nama_anggota">Nama Anggota</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Nama -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="nama_anggota"
                          name="nama_anggota"
                          v-model="payload.nama_anggota"
                          placeholder="Nama Anggota"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="alamat">Alamat</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Alamat -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="alamat"
                          name="alamat"
                          v-model="payload.alamat"
                          placeholder="Alamat"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="jenis_kelamin">Jenis Kelamin</label>
                    </div>
                    <div class="col-md-8">
                      <!-- Jenis Kelamin -->
                      <div class="form-group">
                        <div
                          class="custom-control custom-radio custom-control-inline"
                        >
                          <input
                            type="radio"
                            id="customRadioInline1"
                            name="customRadioInline1"
                            value="L"
                            v-model="payload.jenis_kelamin"
                            class="custom-control-input"
                          />
                          <label
                            class="custom-control-label"
                            for="customRadioInline1"
                            >Laki-laki</label
                          >
                        </div>
                        <div
                          class="custom-control custom-radio custom-control-inline"
                        >
                          <input
                            type="radio"
                            id="customRadioInline2"
                            name="customRadioInline1"
                            value="P"
                            v-model="payload.jenis_kelamin"
                            class="custom-control-input"
                          />
                          <label
                            class="custom-control-label"
                            for="customRadioInline2"
                            >Perempuan</label
                          >
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <label for="no_telpon">No. Telepon</label>
                    </div>
                    <div class="col-md-8">
                      <!-- No Telpon -->
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control form-control-sm"
                          id="no_telpon"
                          name="no_telpon"
                          v-model="payload.no_telpon"
                          placeholder="No Telepon"
                          required
                        />
                      </div>
                    </div>
                  </div>
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
                  <div class="row">
                    <div class="col-md-4"><label for="email">Email</label></div>
                    <div class="col-md-8">
                      <!-- Email -->
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control form-control-sm"
                          id="email"
                          name="email"
                          v-model="payload.email"
                          placeholder="Email"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4"><label for="image">Image</label></div>
                    <div class="col-md-8">
                      <!-- Image -->
                      <div class="form-group">
                        <input
                          type="file"
                          class="form-control form-control-sm"
                          id="input_image"
                          @change="onFileChange"
                        />
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div id="image" v-if="url">
                    <div id="preview">
                      <img v-if="url" :src="url" class="img-fluid" />
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
    <!-- Akhir Modal Update Anggota -->

    <!-- Modal Detail Anggota -->
    <div
      class="modal fade"
      id="detailAnggotaModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="detailAnggotaModalLabel"
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
                            {{ payload.nama_anggota }}
                          </h5>
                          <h6>{{ payload.golongan }}</h6>
                          <p class="proile-rating">
                            Bergabung sejak :
                            {{ timestamp(payload.date_created) }}
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
                                >About</a
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
                                >Timeline</a
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
                          <p>KEBUN</p>
                          <a href="">Kebun A</a><br />
                          <a href="">Kebun B</a><br />
                          <a href="">Kebun C</a><br />
                          <a href="">Kebun D</a><br />
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
                                <p>{{ payload.kd_anggota }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Nama</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.nama_anggota }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Alamat</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.alamat }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Jenis Kelamin</label>
                              </div>
                              <div class="col-md-6">
                                <p>
                                  {{
                                    payload.jenis_kelamin == 'L'
                                      ? 'Laki-laki'
                                      : 'Perempuan'
                                  }}
                                </p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>No. Telpon</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.no_telpon }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Email</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.email }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Golongan</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.golongan }}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Role</label>
                              </div>
                              <div class="col-md-6">
                                <p>{{ payload.role }}</p>
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
                                <label>Experience</label>
                              </div>
                              <div class="col-md-6">
                                <p>Expert</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Hourly Rate</label>
                              </div>
                              <div class="col-md-6">
                                <p>10$/hr</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Total Projects</label>
                              </div>
                              <div class="col-md-6">
                                <p>230</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>English Level</label>
                              </div>
                              <div class="col-md-6">
                                <p>Expert</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <label>Availability</label>
                              </div>
                              <div class="col-md-6">
                                <p>6 months</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-12">
                                <label>Your Bio</label><br />
                                <p>Your detail description</p>
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
    <!-- Akhir Modal Detail Anggota -->
  </div>
</template>

<script>
/** library */
import axios from 'axios';
import $ from 'jquery';

/** component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

/** pagination */
import Pagination from '@/components/Pagination.vue';

/** API Configuration */
const baseURL = '/api/hamparan';
const TOKEN = localStorage.getItem('token');
const header = {
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
};

/**
 * Fitur yang kurang untuk Halaman Anggota sejauh ini yaitu :
 * 1. Pagination
 * 2. Search Data
 * 3. Export Data
 */

export default {
  name: 'PageOfAnggota',

  components: {
    Sidebar,
    Navbar,
    Footer,
    Pagination,
  },

  data() {
    return {
      listAnggota: [], // variabel untuk menampung list data anggota
      payload: {
        // untuk menangkap data form anggota (tambah, ubah dan detail)
        kd_anggota: '',
        nama_anggota: '',
        alamat: '',
        jenis_kelamin: '',
        no_telpon: '',
        is_active: '',
        golongan: '',
        email: '',
        password: '',
        image: '',
        role: '',
        date_created: '',
      },
      url: null,
      alert: {
        // variable untuk set alert (success dan error)
        icon: '',
        title: '',
        text: '',
      },
      isValidForm: false, // variable untuk validasi form
      formErrorMessage: '', // variable untuk menyimpan pesan error form
      pagination: {
        // variable untuk menyimpan informasi pagination
        request: {
          pageNo: 0,
          pageSize: 7,
          sortBy: 'golongan',
          sortDir: 'asc',
        },
        response: {
          pageNumber: 0,
          pageSize: 0,
          totalElements: 0,
          totalPage: 0,
          lastPage: false,
        },
        currentPage: 1,
      },
    };
  },

  /**
   * sampai di pagination masih belum jalan.
   */

  /**
   * fungsi untuk me-load data saat halaman pertama kali diakses.
   */
  async created() {
    this.pagination.request = {
      pageNo: this.pagination.currentPage,
      pageSize: 7,
      sortBy: 'golongan',
      sortDir: 'desc',
    };

    /**
     * load data anggota ketika pertama kali halaman anggota dibuka.
     */
    await this.loadAnggota(
      this.pagination.request.pageNo,
      this.pagination.request.pageSize,
      this.pagination.request.sortBy,
      this.pagination.request.sortDir
    );
  },

  methods: {
    /**
     * fungsi untuk me-load data anggota dari rest server.
     *
     * @param {*} pageNo
     * @param {*} pageSize
     * @param {*} sortBy
     * @param {*} sortDir
     */
    async loadAnggota(pageNo, pageSize, sortBy, sortDir) {
      let berhasil = false;

      await axios
        .get(
          `${baseURL}/anggota?pageNo=${
            pageNo - 1
          }&pageSize=${pageSize}&sortBy=${sortBy}&sortDir=${sortDir}`,
          header
        )
        .then((res) => {
          berhasil = true;
          this.listAnggota = res.data.content;
          this.pagination.response = {
            pageNumber: res.data.pageNumber,
            pageSize: res.data.pageSize,
            totalElements: res.data.totalElements,
            totalPage: res.data.totalPage,
            lastPage: res.data.lastPage,
          };
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(anggota) berhasil load anggota...');
            console.log(this.listAnggota);
            console.log(this.pagination.response);
          } else {
            console.error('(anggota) gagal load anggota...');
          }
        });
    },

    /**
     * Create Anggota
     * method ini digunakan untuk melakukan request ke rest server
     * yang berfungsi untuk membuat anggota baru.
     * @param {*} e
     */
    async createAnggota(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = {
        nama_anggota: this.payload.nama_anggota,
        alamat: this.payload.alamat,
        jenis_kelamin: this.payload.jenis_kelamin,
        no_telpon: this.payload.no_telpon,
        golongan: this.payload.golongan,
        email: this.payload.email,
      };

      await axios
        .post(`${baseURL}/anggota`, payload, header)
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
            $('#newAnggotaModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadAnggota();
          this.clearPayload();
        });
    },

    /**
     * set update payload
     * fingsi ini digunakan untuk mengambil object data anggota dari array
     * dilooping pada halaman daftar anggota.
     *
     * @param {*} anggota
     */
    setUpdatePayload(anggota) {
      this.payload.kd_anggota = anggota.kd_anggota;
      this.payload.nama_anggota = anggota.nama_anggota;
      this.payload.alamat = anggota.alamat;
      this.payload.jenis_kelamin = anggota.jenis_kelamin;
      this.payload.no_telpon = anggota.no_telpon;
      this.payload.golongan = anggota.golongan;
      this.payload.email = anggota.email;
    },

    /**
     * Update Anggota
     * method ini melakukan request ke rest server berfungsi untuk melakukan
     * perubahan pada data anggota.
     *
     * @param {*} e
     */
    async updateAnggota(e) {
      e.preventDefault();

      let berhasil = false;

      const payload = this.payload;
      await axios
        .put(`${baseURL}/anggota`, payload, header)
        .then((res) => {
          if (res.data.status) {
            this.alert.icon = 'success';
            this.alert.title = 'Success!';
            this.alert.text = res.data.message[0];
          }
          berhasil = true;
        })
        .catch((err) => {
          console.log(err.response.data.message);
          if (err.response.data.status == false) {
            this.isValidForm = true;
            this.formErrorMessage = err.response.data.message[0];
          }
          berhasil = false;
        })
        .finally(async () => {
          if (berhasil) {
            $('#updateAnggotaModal .close').click();
            this.showAlert(this.alert.icon, this.alert.title, this.alert.text);
          }
          await this.loadAnggota();
          this.clearPayload();
        });
    },

    /**
     * delete anggota digunakan untuk menghapus data anggota.
     * @param {*} kd_anggota
     */
    async deleteAnggota(kd_anggota) {
      let berhasil = false;
      await axios
        .delete(`${baseURL}/anggota/${kd_anggota}`, header)
        .then((res) => {
          console.log(res);
          berhasil = true;
        })
        .catch((err) => {
          console.log(err.response.data.message);
          berhasil = false;
        })
        .finally(async () => {
          /**
           * alasan kenapa sweetalert tidak muncul itu karena halaman melakukan reload!
           * solusi :
           * ganti button delete menggunakan button,
           * jangan lagi menggunakan tag <a href=""></a>
           */
          if (berhasil) {
            await this.loadAnggota();
            /**
             * baru ditambahkan... belum dilakukan percobaan.
             */
            this.showAlert('success', 'Success!', 'Data berhasil dihapus!');
          } else {
            /**
             * baru ditambahkan... belum dilakukan percobaan.
             */
            this.showAlert('error', 'Failed!', 'Data gagal dihapus!');
          }
        });
    },

    /**
     * fungsi detail anggota digunakan untuk menampilkan detail data anggota.
     * Detail data anggota tidak perlu lagi melakukan request ke server karena,
     * data tersebut dapat diambil dari object anggota yang sudah dilooping pada halaman
     * daftar anggota.
     * @param {*} anggota
     */
    detailAnggota(anggota) {
      this.payload.kd_anggota = anggota.kd_anggota;
      this.payload.nama_anggota = anggota.nama_anggota;
      this.payload.alamat = anggota.alamat;
      this.payload.jenis_kelamin = anggota.jenis_kelamin;
      this.payload.no_telpon = anggota.no_telpon;
      this.payload.is_active = anggota.is_active;
      this.payload.golongan = anggota.golongan;
      this.payload.email = anggota.email;
      this.payload.password = anggota.password;
      this.payload.image = anggota.image;
      this.payload.role = anggota.role;
      this.payload.date_created = anggota.date_created;
    },

    /**
     * clear payload digunakan untuk me-reset data.
     * kebutuhan dari fungsi ini terdapat pada halaman tambah, ubah & detail.
     */
    clearPayload() {
      this.payload.kd_anggota = '';
      this.payload.nama_anggota = '';
      this.payload.alamat = '';
      this.payload.jenis_kelamin = '';
      this.payload.no_telpon = '';
      this.payload.is_active = '';
      this.payload.golongan = '';
      this.payload.email = '';
      this.payload.password = '';
      this.payload.image = '';
      this.payload.role = '';
      this.payload.date_created = '';
    },

    /**
     * on file change digunakan untuk menampilkan file pada halaman form tambah anggota.
     * @param {*} e
     */
    onFileChange(e) {
      const file = e.target.files[0];
      this.url = URL.createObjectURL(file);
    },

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

    /**
     * fungsi untuk mengubah date created menjadi format waktu yang dapat dibaca manusia.
     * salah satu implementasi dari fungsi ini yaitu pada halaman detail anggota.
     *
     * @param {*} timestamp
     */
    timestamp(timestamp) {
      const date = new Date(parseInt(timestamp));
      return `${date.getDate()}/${
        date.getMonth() + 1
      }/${date.getFullYear()} | ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
    },

    async onPageChange(pageNo, pageSize, sortBy, sortDir) {
      console.log(pageNo);

      await this.loadAnggota(pageNo, pageSize, sortBy, sortDir);
      this.pagination.currentPage = pageNo;
    },
  },
};
</script>

<style>
/**
 * image preview modal tambah anggota & modal update anggta
 */
#image {
  background-color: #e2e2e2;
  padding: 5px;
  border-radius: 5px;
}

#preview {
  display: flex;
  justify-content: center;
  align-items: center;
}

#preview img {
  max-width: 100%;
  max-height: 300px;
}

/**
 * modal detail anggota style css
 */
.emp-profile {
  padding: 3%;
  margin-top: 3%;
  margin-bottom: 3%;
  border-radius: 0.5rem;
  background: #fff;
}
.profile-img {
  text-align: center;
}
.profile-img img {
  width: 70%;
  height: 100%;
}
.profile-img .file {
  position: relative;
  overflow: hidden;
  margin-top: -20%;
  width: 70%;
  border: none;
  border-radius: 0;
  font-size: 15px;
  background: #212529b8;
}
.profile-img .file input {
  position: absolute;
  opacity: 0;
  right: 0;
  top: 0;
}
.profile-head h5 {
  color: #333;
}
.profile-head h6 {
  color: #06f857;
}
.profile-edit-btn {
  border: none;
  border-radius: 1.5rem;
  width: 70%;
  padding: 2%;
  font-weight: 600;
  color: #6c757d;
  cursor: pointer;
}
.proile-rating {
  font-size: 12px;
  color: #818182;
  margin-top: 5%;
}
.proile-rating span {
  color: #495057;
  font-size: 15px;
  font-weight: 600;
}
.profile-head .nav-tabs {
  margin-bottom: 5%;
}
.profile-head .nav-tabs .nav-link {
  font-weight: 600;
  border: none;
}
.profile-head .nav-tabs .nav-link.active {
  border: none;
  border-bottom: 2px solid #06f857;
}
.profile-work {
  padding: 14%;
  margin-top: -15%;
}
.profile-work p {
  font-size: 12px;
  color: #818182;
  font-weight: 600;
  margin-top: 10%;
}
.profile-work a {
  text-decoration: none;
  color: #495057;
  font-weight: 600;
  font-size: 14px;
}
.profile-work ul {
  list-style: none;
}
.profile-tab label {
  font-weight: 600;
}
.profile-tab p {
  font-weight: 600;
  color: #1c1d1d;
}
</style>
