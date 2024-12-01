<template>
  <!-- Sidebar -->
  <ul
    class="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion"
    id="accordionSidebar"
  >
    <!-- Sidebar - Brand -->
    <router-link
      class="sidebar-brand d-flex align-items-center justify-content-center"
      to="/dashboard"
    >
      <div class="sidebar-brand-icon rotate-n-15">
        <i class="fas fa-tree"></i>
      </div>
      <div class="sidebar-brand-text mx-3">SI-GAPOKTAN</div>
    </router-link>

    <!-- menampilkan menu pada sidebar dari database -->
    <div v-html="sidebarMenuHtml"></div>

    <hr class="sidebar-divider mb-0" />
    <!-- Logout Button -->
    <li class="nav-item">
      <a class="nav-link pb-0 pt-0" href="#" @click="logout">
        <i class="fas fa-fw fa-sign-out-alt"></i>
        <span>Logout</span></a
      >
    </li>
    <hr class="sidebar-divider mt-0" />
    <!-- End of Logout Button -->
  </ul>
  <!-- End of Sidebar -->
</template>
<script lang="js">
import axios from 'axios';
import RAC from '@/config/RestAPIConfig';

export default {
  name: 'SidebarComponent',

  data(){
    return {
      user: {},
      role: {},
      menu: [],
      subMenu: [],
      sidebarMenuHtml: '',
    }
  },

  async created(){
    await this.getUser(RAC.EMAIL);
    await this.getRole(this.user.role);
    await this.getMenu(this.role.id);
    await this.setSidebarMenu(this.menu); /** set sidebar menu from database */
  },

  methods: {
    async getUser(email){
      await axios.get(`${RAC.BASE_URL}/anggota/getbyemail/${email}`, RAC.HEADER)
      .then(res => {
        if(res.data.status){
          this.user = res.data.payload;
        }
      })
      .catch(err => {
        console.log(err.response.data.message);
      })
    },

    async getRole(role){
      await axios.get(`${RAC.BASE_URL}/role/getbyname/${role}`, RAC.HEADER)
      .then(res => {
        if(res.data.status){
          this.role = res.data.payload;
        }
      })
      .catch(err => {
        console.log(err.response.data.message);
      })
    },

    async getMenu(roleId){
      await axios.get(`${RAC.BASE_URL}/menu/getaccessmenubyroleid/${roleId}`, RAC.HEADER)
      .then(res => {
        if(res.data.status){
          this.menu = res.data.payload;
        }
      })
      .catch(err => {
        console.log(err.response.data.message);
      });
    },

    async getSubMenu(menuId){
      const response = await axios.get(`${RAC.BASE_URL}/submenu/getaccesssubmenubymenuid/${menuId}`, RAC.HEADER);
      return response.data.payload;
    },

    async setSidebarMenu(menu){
      for(let i = 0; i < menu.length; i++){

        this.subMenu[i] = await this.getSubMenu(menu[i].id);

        this.sidebarMenuHtml += `<hr class="sidebar-divider mt-2" />
        <div class="sidebar-heading">${menu[i].menu}</div>`;

        for(let j = 0; j < this.subMenu[i].length; j++){
          this.sidebarMenuHtml += `<li class="nav-item">
          <a class="nav-link pb-0" href="${this.subMenu[i][j].url}">
          <i class="${this.subMenu[i][j].icon}"></i>
          <span>${this.subMenu[i][j].title}</span></a>
          </li>`;
        }

      }
    },

    logout(){
      localStorage.removeItem('token');
      localStorage.removeItem('email');
      this.$router.push('/');
    }
  }
}
</script>
