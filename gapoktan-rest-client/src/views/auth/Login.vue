<template>
  <div class="bg-gradient-secondary pb-5">
    <div class="container pb-4">
      <!-- Outer Row -->
      <div class="row justify-content-center pb-5">
        <div class="col-xl-5 col-lg-5 col-md-5">
          <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
              <!-- Nested Row within Card Body -->
              <div class="row justify-content-center">
                <div class="col-lg-10">
                  <div class="p-5">
                    <div class="text-center mb-2">
                      <h1 class="h4 text-gray-900 mb-4">Selamat Datang!</h1>
                      <span class="text-danger" v-if="!loggedin">{{
                        message
                      }}</span>
                    </div>
                    <form class="user" @submit="login">
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control form-control-user text-center"
                          id="exampleInputEmail"
                          aria-describedby="emailHelp"
                          placeholder="Masukan email anda..."
                          v-model="auth.email"
                        />
                      </div>
                      <div class="form-group">
                        <input
                          type="password"
                          class="form-control form-control-user text-center"
                          id="exampleInputPassword"
                          placeholder="Masukan password anda..."
                          v-model="auth.password"
                        />
                      </div>
                      <button
                        type="submit"
                        class="btn btn-primary btn-user btn-block"
                      >
                        Login
                      </button>
                    </form>
                    <hr />
                    <div class="text-center">
                      <router-link class="small" to="/register"
                        >Belum memiliki akun? Register!</router-link
                      >
                    </div>
                    <div class="text-center">
                      <router-link class="small" to="/"
                        >Batal Login!</router-link
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

/** configuration */
const url = '/api/auth/authenticate';

export default {
  name: 'AuthLogin',

  data() {
    return {
      auth: {
        email: '',
        password: '',
      },
      loggedin: false,
      message: '',
    };
  },

  methods: {
    async login(e) {
      e.preventDefault();

      /**
       * user role
       */
      const ADMIN_ROLE = 'ADMIN';
      const USER_ROLE = 'USER';

      const request = {
        email: this.auth.email,
        password: this.auth.password,
      };

      await axios
        .post(url, request)
        .then((res) => {
          if (res.data.status) {
            this.loggedin = true;
            localStorage.setItem('token', res.data.payload.token);
            localStorage.setItem('email', res.data.payload.email);
            localStorage.setItem('role', res.data.payload.role);
            /** butuh response role dari api server. */
          }
        })
        .catch((err) => {
          console.log(err);
          this.loggedin = false;
          this.message = 'Opsss!, Ada kesalahan!';
        })
        .finally(() => {
          if (this.loggedin) {
            const role = localStorage.getItem('role');

            if (role === ADMIN_ROLE) {
              this.$router.push('/dashboard');
            }

            if (role === USER_ROLE) {
              this.$router.push('/dashboarduser');
            }
          } else {
            console.log('Login gagal dilakukan!');
          }
        });

      /**
       * masih ada yang salah!, ketika user dengan role USER (bukan admin)
       * halaman yang akan dirender tetap ke halaman dashboard admin.
       * oleh karena itu disini harus dilakukan pengecekan user yang login dengan role apa?
       * jika admin maka langsung ke halaman dashboard admin & jika user maka render ke halaman dashboard khusus user
       * kesimpulannya buat dua halaman dashboard, untuk admin dan user.
       * dan dilakukan pengecekan terlebih dahulu disini.
       *
       * untuk dapat melakukan itu semua diatas, kita perlu memberikan response user role
       * dari api server pada controller authenticate. tidak hanya token & email saja yang
       * dikembalikan oleh api server, tapi dengan data role user.
       * dengan kata lain yang harus diperbaiki dahulu yaitu pada response api servernya.
       */
    },
  },
};
</script>
