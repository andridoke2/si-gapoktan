import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue';
import Login from '@/views/auth/Login.vue';
import Register from '@/views/auth/Register.vue';
import Dashboard from '@/views/pages/DashboardAdmin.vue';
import DashboardUser from '@/views/pages/DashboardUser.vue';
import Forbidden from '@/components/Forbidden.vue';
import Role from '@/views/pages/Role.vue';
import Anggota from '@/views/pages/Anggota.vue';
import Access from '@/views/pages/Access.vue';
import SubMenu from '@/views/pages/SubMenu.vue';
import Kebun from '@/views/pages/Kebun.vue';
import Mobil from '@/views/pages/Mobil.vue';
import Hasil from '@/views/pages/Hasil.vue';
import Pajak from '@/views/pages/Pajak.vue';
import Pengurus from '@/views/pages/Pengurus.vue';
import Harga from '@/views/pages/Harga.vue';
import Hamparan from '@/views/pages/Hamparan.vue';
import JadwalHamparan from '@/views/pages/JadwalHamparan.vue';
import Penghasilan from '@/views/pages/Penghasilan.vue';
import KebunMobil from '@/views/pages/KebunMobilAnggota.vue';

Vue.use(VueRouter);

const routes = [
  /**
   * router for HOME page
   */
  {
    path: '/',
    component: Home,
  },

  /**
   * router for LOGIN page
   */
  {
    path: '/login',
    component: Login,
  },

  /**
   * router for REGISTER page
   */
  {
    path: '/register',
    component: Register,
  },

  /**
   * router for ADMIN/Pengurus DASHBOARD page
   */
  {
    path: '/dashboard',
    component: Dashboard,
    meta: {
      title: 'Dashboard',
    },
  },

  /**
   * router for USER/Petani, Pemuat, Penimbang, Mobil DASHBOARD page
   */
  {
    path: '/dashboarduser',
    component: DashboardUser,
    meta: {
      title: 'Dashboard',
    },
  },

  /**
   * router for FORBIDDEN page
   */
  {
    path: '/forbidden',
    component: Forbidden,
    meta: {
      title: 'Access Forbidden',
    },
  },

  /**
   * router for ROLE page
   */
  {
    path: '/role',
    component: Role,
    meta: {
      title: 'User Role',
    },
  },

  /**
   * router for ACCESS page
   */
  {
    path: '/access/:id',
    component: Access,
    name: 'access',
    meta: {
      title: 'Role Access',
    },
  },

  /**
   * router for SUB MENU page
   */
  {
    path: '/submenu',
    component: SubMenu,
    name: 'submenu',
    meta: {
      title: 'Sub Menu',
    },
  },

  /**
   * router for ANGGOTA page
   */
  {
    path: '/anggota',
    component: Anggota,
    meta: {
      title: 'Anggota',
    },
  },

  /**
   * router for KEBUN page
   */
  {
    path: '/kebun',
    component: Kebun,
    meta: {
      title: 'Kebun',
    },
  },

  /**
   * router for MOBIL page
   */
  {
    path: '/mobil',
    component: Mobil,
    meta: {
      title: 'Mobil',
    },
  },

  /**
   * router for HASIL page
   */
  {
    path: '/hasil',
    component: Hasil,
    meta: {
      title: 'Hasil',
    },
  },

  /**
   * router for PAJAK page
   */
  {
    path: '/pajak',
    component: Pajak,
    meta: {
      title: 'Pajak',
    },
  },

  /**
   * router for PENGURUS page
   */
  {
    path: '/pengurus',
    component: Pengurus,
    meta: {
      title: 'Pengurus',
    },
  },

  /**
   * router for HARGA page
   */
  {
    path: '/harga',
    component: Harga,
    meta: {
      title: 'Harga',
    },
  },

  /**
   * router for HAMPARAN page
   */
  {
    path: '/hamparan',
    component: Hamparan,
    meta: {
      title: 'Hamparan',
    },
  },

  /**
   * router for JADWAL HAMPARAN page
   */
  {
    path: '/jadwal',
    component: JadwalHamparan,
    meta: {
      title: 'Jadwal Hamparan',
    },
  },

  /**
   * router for PENGHASILAN page
   */
  {
    path: '/penghasilan',
    component: Penghasilan,
    meta: {
      title: 'Penghasilan',
    },
  },

  /**
   * router for KEBUN / MOBIL page
   */
  {
    path: '/kebunormobil',
    component: KebunMobil,
    meta: {
      title: 'Kebun | Mobil',
    },
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((toRoute, fromRoute, next) => {
  window.document.title =
    toRoute.meta && toRoute.meta.title ? toRoute.meta.title : 'SI-GAPOKTAN';

  next();
});

export default router;
