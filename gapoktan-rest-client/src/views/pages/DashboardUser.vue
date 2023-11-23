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
              <!-- Earnings (Monthly) Card Example -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-primary text-uppercase mb-1"
                        >
                          Kebun
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          {{ this.listKebun.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Earnings (Monthly) Card Example -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-success text-uppercase mb-1"
                        >
                          Mobil
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          0
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Earnings (Monthly) Card Example -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-info shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-info text-uppercase mb-1"
                        >
                          Penghasilan
                        </div>
                        <div class="row no-gutters align-items-center">
                          <div class="col-auto">
                            <div
                              class="h5 mb-0 mr-3 font-weight-bold text-gray-800"
                            >
                              {{ this.listPenghasilan.length }}
                            </div>
                          </div>
                          <div class="col">
                            <div class="progress progress-sm mr-2">
                              <div
                                class="progress-bar bg-info"
                                role="progressbar"
                                style="width: 50%"
                                aria-valuenow="50"
                                aria-valuemin="0"
                                aria-valuemax="100"
                              ></div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-auto">
                        <i
                          class="fas fa-clipboard-list fa-2x text-gray-300"
                        ></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Pending Requests Card Example -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-warning shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-warning text-uppercase mb-1"
                        >
                          Harga
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          18
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-comments fa-2x text-gray-300"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Chart -->
            <div class="row">
              <div class="col-md-6">
                <PenghasilanChart />
              </div>
            </div>
            <!-- End of Chart -->
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

/** import chartjs component */
import PenghasilanChart from '@/components/chart/PenghasilanChart.vue';

/** import content component */
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
  name: 'DashboardUser',

  components: {
    Sidebar,
    Navbar,
    Footer,
    PenghasilanChart,
  },

  data() {
    return {
      listKebun: [],
      listPenghasilan: [],
      user: {},
    };
  },

  async created() {
    await this.findUserByEmail(EMAIL);
    if (this.user) {
      await this.findKebunByKodeAnggota(this.user.kd_anggota);
      await this.findPenghasilanByKodeAnggota(this.user.kd_anggota);
    }
  },

  methods: {
    /**
     * find user by email user login.
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
            console.log('(dashboard user) : berhasil get anggota by email...');
          }
        });
    },

    async findKebunByKodeAnggota(kodeAnggota) {
      let berhasil = false;

      const searchData = {
        searchKey: kodeAnggota,
      };

      await axios
        .post(`${baseURL}/kebun/search/bykodeanggota`, searchData, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listKebun = res.data.payload;
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
            console.log('(dashboard user) : berhasil find kebun anggota...');
          }
        });
    },

    async findPenghasilanByKodeAnggota(kodeAnggota) {
      let berhasil = false;

      const searchData = {
        searchKey: kodeAnggota,
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
            console.log(
              '(dashboard user) : berhasil find penghasilan anggota...'
            );
          }
        });
    },
  },
};
</script>
