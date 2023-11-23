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
                <!-- Button Tambah Role -->
                <a
                  href=""
                  class="btn btn-primary mb-3 btn-icon-split btn-sm"
                  data-toggle="modal"
                  data-target="#newRoleModal"
                  @click="clearForm"
                >
                  <span class="icon text-white-50">
                    <i class="fas fa-plus"></i>
                  </span>
                  <span class="text">Tambah Role</span>
                </a>

                <!-- Alert Success Create -->
                <div v-if="status" :class="alertClass" role="alert">
                  <strong>{{ statusText }}</strong> {{ message }}
                  <button
                    type="button"
                    class="close"
                    data-dismiss="alert"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>

                <!-- Table Data Role -->
                <table class="table table-hover text-center">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">Role</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody v-for="(role, index) in listOfRole" :key="role.id">
                    <tr>
                      <th scope="row">{{ ++index }}</th>
                      <td>{{ role.role }}</td>
                      <td>
                        <router-link
                          :to="{
                            name: 'access',
                            params: { id: role.id, role: role.role },
                          }"
                          class="badge badge-pill badge-warning"
                          >Access <i class="fas fa-universal-access"></i
                        ></router-link>
                        <a
                          href=""
                          class="badge badge-pill badge-success mx-2"
                          data-toggle="modal"
                          data-target="#newRoleModalUpdate"
                          @click="getUpdateRole(role)"
                        >
                          Update <i class="fas fa-check"></i>
                        </a>
                        <a
                          href=""
                          @click="deleteRole(role.id)"
                          class="badge badge-pill badge-danger"
                          >Delete <i class="fas fa-trash"></i
                        ></a>
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

    <!-- Modal Tambah Role -->
    <div
      class="modal fade"
      id="newRoleModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newRoleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newRoleModalLabel">Tambah Role</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="createRole">
            <div class="modal-body">
              <div class="form-group">
                <input
                  type="text"
                  class="form-control"
                  id="role"
                  v-model="data.role"
                  placeholder="Role Name"
                />
              </div>
            </div>
            <div class="modal-footer">
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
    <!-- Akhir Modal Tambah Role -->

    <!-- Modal Update Role -->
    <div
      class="modal fade"
      id="newRoleModalUpdate"
      tabindex="-1"
      role="dialog"
      aria-labelledby="newRoleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="newRoleModalLabel">Update Role</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit="updateRole">
            <div class="modal-body">
              <div class="form-group">
                <input type="hidden" id="id" name="id" v-model="data.id" />
                <input
                  type="text"
                  class="form-control"
                  id="role"
                  name="role"
                  placeholder="Role Name"
                  v-model="data.role"
                />
              </div>
            </div>
            <div class="modal-footer">
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
    <!-- Akhir Modal Update Role -->
  </div>
</template>

<script>
import axios from 'axios';
import $ from 'jquery';

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
  name: 'PageRole',

  components: {
    Sidebar,
    Navbar,
    Footer,
  },

  data() {
    return {
      listOfRole: [],
      data: {
        id: '',
        role: '',
      },
      status: false,
      statusText: '',
      message: '',
      alertClass: '',
    };
  },

  async created() {
    await this.loadRole();
  },

  methods: {
    async loadRole() {
      let berhasil = false;

      await axios
        .get(`${baseURL}/role`, header)
        .then((res) => {
          if (res.data.status) {
            berhasil = true;
            this.listOfRole = res.data.payload;
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(() => {
          if (berhasil) {
            console.info('(role) berhasil load role...');
          } else {
            console.error('(role) gagal load role...');
          }
        });
    },

    async createRole(e) {
      e.preventDefault();

      let berhasil = false;

      const data = {
        id: this.data.id,
        role: this.data.role,
      };
      await axios
        .post(`${baseURL}/role`, data, header)
        .then(async (res) => {
          if (res.data.status) {
            berhasil = true;
            this.alertMessage(true, res.data.message[0], 'alert-success');
            this.clearForm();
            $('#newRoleModal .close').click();
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(async () => {
          if (berhasil) {
            await this.loadRole();
            console.info('(role) berhasil tambah role...');
          } else {
            console.error('(role) gagal tambah role...');
          }
        });
    },

    async deleteRole(id) {
      let berhasil = false;

      await axios
        .delete(`${baseURL}/role/${id}`, header)
        .then(async (res) => {
          if (res.data.status) {
            berhasil = true;
            this.alertMessage(true, 'Role berhasil dihapus!', 'alert-success');
          }
        })
        .catch((err) => {
          berhasil = false;
          console.log(err.response.data.message);
        })
        .finally(async () => {
          if (berhasil) {
            await this.loadRole();
            console.info('(role) berhasil hapus role...');
          } else {
            console.error('(role) gagal hapus role...');
          }
        });
    },

    getUpdateRole(role) {
      this.data.id = role.id;
      this.data.role = role.role;
    },

    async updateRole(e) {
      e.preventDefault();
      let berhasil = false;
      await axios
        .put(`${baseURL}/role`, this.data, header)
        .then(async (res) => {
          if (res.data.status) {
            berhasil = true;
            this.alertMessage(
              res.data.status,
              res.data.message[0],
              'alert-success'
            );
          }
        })
        .catch((err) => {
          console.log(err);
          berhasil = false;
        })
        .finally(async () => {
          if (berhasil) {
            $('#newRoleModalUpdate .close').click();
            this.clearForm();
            await this.getRole();
          }
        });
    },

    clearForm() {
      this.data.id = '';
      this.data.role = '';
    },

    alertMessage(status, message, alertClass) {
      if (status) {
        this.status = status;
        this.message = message;
        this.statusText = 'Success!';
        this.alertClass = `alert  ${alertClass} alert-dismissible fade show`;
      } else {
        this.status = status;
        this.message = message;
        this.statusText = 'Failed!';
        this.alertClass = `alert  ${alertClass} alert-dismissible fade show`;
      }
    },
  },
};

/**
 * lanjut buat access role, sub menu & anggota.
 */
</script>
