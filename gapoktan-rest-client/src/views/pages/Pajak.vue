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
                <!-- Button Tambah Pajak -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newPajakModal"
                  @click="clearPayload"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Pajak</span>
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
              </div>
            </div>
          </div>
        </div>
        <Footer />
      </div>
    </div>
  </div>
</template>

<script>
/** libarry */
// import axios from 'axios';
// import $ from 'jquery';

/** Component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

export default {
  name: 'PageOfPajak',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listPajak: [],
    };
  },

  created() {
    console.log('Pajak Page...');
  },
};
</script>
