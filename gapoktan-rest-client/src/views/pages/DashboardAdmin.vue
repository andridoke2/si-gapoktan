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
              <!-- Card Anggota -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-primary text-uppercase mb-1"
                        >
                          Anggota
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          {{ this.listAnggota.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-users fa-2x text-gray-300"></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-primary"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Kebun -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-success text-uppercase mb-1"
                        >
                          Kebun
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          {{ this.listKebun.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-tree fa-2x text-gray-300"></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-success"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Mobil -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-danger shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-danger text-uppercase mb-1"
                        >
                          Mobil
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          {{ this.listMobil.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-car fa-2x text-gray-300"></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-danger"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Hasil -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-info shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-info text-uppercase mb-1"
                        >
                          Hasil
                        </div>
                        <div class="row no-gutters align-items-center">
                          <div class="col-auto">
                            <div
                              class="h5 mb-0 mr-3 font-weight-bold text-gray-800"
                            >
                              {{ this.listHasil.length }}
                            </div>
                          </div>
                          <div class="col">
                            <div class="progress progress-sm mr-2">
                              <div
                                class="progress-bar bg-info"
                                role="progressbar"
                                style="width: 70%"
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
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-info"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Harga -->
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
                          {{ this.listHarga.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i
                          class="fas fa-money-bill-alt fa-2x text-gray-300"
                        ></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-warning"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Pajak -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-danger shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-danger text-uppercase mb-1"
                        >
                          Pajak
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          {{ this.listPajak.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-cut fa-2x text-gray-300"></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-danger"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Pengurus -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-success text-uppercase mb-1"
                        >
                          Pengurus
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          {{ this.listPengurus.length }}
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-user-tie fa-2x text-gray-300"></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-success"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Card Hamparan -->
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div
                          class="text-xs font-weight-bold text-primary text-uppercase mb-1"
                        >
                          Hamparan
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                          15
                        </div>
                      </div>
                      <div class="col-auto">
                        <i class="fas fa-stream fa-2x text-gray-300"></i>
                      </div>
                    </div>
                    <div class="row justify-content-center">
                      <a
                        href=""
                        class="badge badge-pill badge-primary"
                        @click="detailAnggota(anggota)"
                        >Visualisasi <i class="fas fa-poll"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Chart -->
            <div class="row">
              <div class="col-md-6">
                <BarChart />
              </div>
              <div class="col-md-6">
                <HasilChart />
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
import axios from 'axios';
import RAC from '@/config/RestAPIConfig';

/** import chartjs component */
import BarChart from '@/components/chart/Bar.vue';
import HasilChart from '@/components/chart/HasilChart.vue';

/** import content component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

export default {
  name: 'DashboardAdmin',

  components: {
    Sidebar,
    Navbar,
    Footer,
    BarChart,
    HasilChart,
  },

  data() {
    return {
      listAnggota: [],
      listKebun: [],
      listMobil: [],
      listHasil: [],
      listHarga: [],
      listPajak: [],
      listPengurus: [],
    };
  },

  async created() {
    await this.loadAnggota();
    await this.loadKebun();
    await this.loadMobil();
    await this.loadHasil();
    await this.loadHarga();
    await this.loadPajak();
    await this.loadPengurus();
  },

  methods: {
    async loadAnggota() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/anggota`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listAnggota = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashbard admin) berhasil load anggota...');
          } else {
            console.error('(dashboard admin) gagal load anggota...');
          }
        });
    },

    async loadKebun() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/kebun`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listKebun = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashboard admin) berhasil load kebun...');
          } else {
            console.error('(dashboard admin) gagal load kebun...');
          }
        });
    },

    async loadMobil() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/mobil`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listMobil = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashboard admin) berhasil load mobil...');
          } else {
            console.error('(dashboard admin) gagal load mobil...');
          }
        });
    },

    async loadHasil() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/hasil`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listHasil = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashboard admin) berhasil load hasil...');
          } else {
            console.error('(dashboard admin) gagal load hasil...');
          }
        });
    },

    async loadHarga() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/harga`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listHarga = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashboard admin) berhasil load harga...');
          } else {
            console.error('(dashboard admin) gagal load harga...');
          }
        });
    },

    async loadPajak() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/pajak`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listPajak = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashboard admin) berhasil load pajak...');
          } else {
            console.error('(dashboard admin) gagal load pajak...');
          }
        });
    },

    async loadPengurus() {
      let berhasil = false;

      await axios
        .get(`${RAC.BASE_URL}/pengurus`, RAC.HEADER)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listPengurus = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(dashboard admin) berhasil load pengurus...');
          } else {
            console.error('(dashboard admin) gagal load pengurus...');
          }
        });
    },

    printError(errorMessage) {
      for (let i = 0; i <= errorMessage.length; i++) {
        console.log(errorMessage[i]);
      }
    },
  },
};
</script>
