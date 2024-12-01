<template>
  <div id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Sidebar -->
      <Sidebar />
      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
          <!-- Navbar -->
          <Navbar />
          <!-- Begin Page Content -->
          <div class="container-fluid">
            <!-- Content Row -->
            <div class="row">
              <div class="col-md-12">
                <!-- Button Tambah Sub Menu -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newAnggotaModal"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Sub Menu</span>
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

                <!-- Table Data Sub Menu -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Title</th>
                      <th scope="col">URL</th>
                      <th scope="col">Icon</th>
                      <th scope="col">Is Active</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(submenu, index) in listSubMenu"
                    :key="submenu.id"
                  >
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ submenu.title }}</td>
                      <td>{{ submenu.url }}</td>
                      <td>{{ submenu.icon }}</td>
                      <td
                        :class="
                          isActive(submenu.is_active)
                            ? 'text-success'
                            : 'text-danger'
                        "
                      >
                        <i
                          :class="
                            isActive(submenu.is_active)
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
                <div v-if="listSubMenu.length <= 0" class="text-center">
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
  </div>
</template>

<script>
/** library */
import axios from 'axios';
import RAC from '@/config/RestAPIConfig';

/** component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

export default {
  name: 'PageOfDashboard',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listSubMenu: [],
    };
  },

  async created() {
    await this.loadSubMenu();

    /**
     * masih error, cek dulu dari server baru kembali kesini.
     */
  },

  methods: {
    async loadSubMenu() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/submenu`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listSubMenu = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          this.printError(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(sub menu) berhasil load submenu...');
          } else {
            console.error('(sub menu) gagal load submenu...');
          }
        });
    },

    isActive(isActive) {
      return isActive == '1' ? true : false;
    },

    printError(message) {
      for (let i = 0; i < message.length; i++) {
        console.error(message[i]);
      }
    },
  },
};
</script>
