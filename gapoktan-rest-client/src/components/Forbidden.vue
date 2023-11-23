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
            <!-- 403 Error Text -->
            <div class="text-center">
              <div class="error mx-auto" data-text="403">403</div>
              <p class="lead text-gray-800 mb-5">Access Forbidden</p>
              <p class="text-gray-500 mb-0">
                Anda tidak memiliki access halaman ini!
              </p>
              <a href="" @click="redirect(role)">&larr; Back to Dashboard</a>
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
/** Component */
import Sidebar from '@/components/Sidebar.vue';
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';

/**
 * user login role
 */
const ROLE = localStorage.getItem('role');

export default {
  name: 'PageOfForbidden',
  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      role: ROLE,
    };
  },

  methods: {
    /**
     * redirect halaman berdasarkan role user yang sedang login.
     * @param {*} role
     */
    redirect(role) {
      switch (role) {
        case 'ADMIN':
          this.$router.push('/dashboard');
          break;
        case 'USER':
          this.$router.push('/profile');
          break;
      }
    },
  },
};
</script>
