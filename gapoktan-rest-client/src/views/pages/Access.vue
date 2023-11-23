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
              <div class="col-md-8">
                <div v-if="status" class="alert alert-success" role="alert">
                  Access Changed!
                </div>
                <h5>Role : {{ roleNameParam }}</h5>

                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Menu</th>
                      <th scope="col" class="text-center">Access</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      v-for="accessMenu in listOfMenu[0]"
                      :key="accessMenu.id"
                    >
                      <th scope="row"></th>
                      <td>{{ accessMenu.menu }}</td>
                      <td class="text-center">
                        <div v-html="inputCheckbox"></div>
                      </td>
                    </tr>
                  </tbody>
                </table>
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
import axios from 'axios';
// import $ from 'jquery';

import Navbar from '@/components/Navbar.vue';
import Sidebar from '@/components/Sidebar.vue';
import Footer from '@/components/Footer.vue';

/** API Configuration */
const baseURL = '/api/hamparan';
const TOKEN = localStorage.getItem('token');
const header = {
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
};

/**
 * endpoint check_access : "/getaccessbyroleidmenuid/{role_id}/{menu_id}"
 * endpoint looping data access menu : /getroleaccessmenu/{id}
 * if exists access menu : /existsAccessMenu/{role_id}/{menu_id}
 */

export default {
  name: 'PageAccess',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listOfMenu: [],
      listOfAccessMenu: [],
      userRole: null,
      roleNameParam: '',
      roleIdParam: '',
      inputCheckbox: '',
      status: false,
    };
  },

  async created() {
    await this.getAccessMenu();

    for (let i = 0; i < this.listOfMenu[0].length; i++) {
      this.checkboxAccessMenu(this.roleIdParam, this.listOfMenu[0][i].id);
    }
  },

  methods: {
    async getAccessMenu() {
      this.roleIdParam = this.$route.params.id;
      this.roleNameParam = this.$route.params.role;
      await this.getRoleById(this.roleIdParam);
      await axios
        .get(`${baseURL}/menu/getroleaccessmenu`, header)
        .then(async (res) => {
          if (res.data.status) {
            this.listOfMenu.push(res.data.payload);
            for (let i = 0; i < res.data.payload; i++) {
              await this.checkboxAccessMenu(
                this.roleIdParam,
                this.res.data.payload[i].id
              );
              console.log(this.res.data.payload[i].id);
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    async getRoleById(id) {
      await axios
        .get(`${baseURL}/role/getbyid/${id}`, header)
        .then((res) => {
          if (res.data.status) {
            this.userRole = res.data.payload;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    async checkboxAccessMenu(roleId, menuId) {
      await axios
        .get(
          `${baseURL}/accessmenu/getaccessbyroleidmenuid/${roleId}/${menuId}`,
          header
        )
        .then((res) => {
          this.listOfAccessMenu.push(res.data.payload);
          this.inputCheckbox = `
              <div class="form-group">
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    checked="checked"
                    data-role="${roleId}"
                    data-menu="${menuId}"
                  />
                </div>
              </div>
            `;
        })
        .catch((err) => {
          console.error(err);
          this.inputCheckbox = `
              <div class="form-group">
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    data-role="${roleId}"
                    data-menu="${menuId}"
                  />
                </div>
              </div>
            `;
        });
    },
  },
};
</script>
