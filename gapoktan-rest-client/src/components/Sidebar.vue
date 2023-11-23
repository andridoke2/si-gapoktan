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

    <div v-html="sidebarMenuHtml"></div>

    <hr class="sidebar-divider mb-0" />
    <!-- Logout Button -->
    <li class="nav-item">
      <a class="nav-link" href="#" @click="logout">
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

/** API Configuration */
const baseURL = '/api/hamparan';
const TOKEN = localStorage.getItem('token');
const header = {
        headers: {
          Authorization: `Bearer ${TOKEN}`
        }
      };

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
    this.user = await this.getUser(localStorage.getItem('email'));
    this.role = await this.getRole(this.user.role);
    this.menu = await this.getMenu(this.role.id);
    await this.setSidebarMenu(this.menu); /** set sidebar menu from database */
  },

  methods: {
    async getUser(email){
      const response = await axios.get(`${baseURL}/anggota/getbyemail/${email}`, header);
      return response.data.payload;
    },

    async getRole(role){
      const response = await axios.get(`${baseURL}/role/getbyname/${role}`, header);
      return response.data.payload;
    },

    async getMenu(roleId){
      const response = await axios.get(`${baseURL}/menu/getaccessmenubyroleid/${roleId}`, header);
      return response.data.payload;
    },

    async getSubMenu(menuId){
      const response = await axios.get(`${baseURL}/submenu/getaccesssubmenubymenuid/${menuId}`, header);
      return response.data.payload;
    },

    async setSidebarMenu(menu){
      for(let i = 0; i < menu.length; i++){

        this.subMenu[i] = await this.getSubMenu(menu[i].id);

        this.sidebarMenuHtml += `<hr class="sidebar-divider" />
        <div class="sidebar-heading">${menu[i].menu}</div>`;

        for(let j = 0; j < this.subMenu[i].length; j++){
          this.sidebarMenuHtml += `<li class="nav-item">
          <a class="nav-link" href="${this.subMenu[i][j].url}">
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
