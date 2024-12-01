import Vue from 'vue';
import App from './App.vue';
import router from './router';
import '@/config/axios';

/**
 * rest api configuration
 */
import '@/config/RestAPIConfig';

/**
 * sweetalert configuration
 */
import '@/utils/sweetalert';

/**
 * import sweetalert librarry.
 */
import VueSweetalert2 from 'vue-sweetalert2';

Vue.config.productionTip = false;

Vue.use(VueSweetalert2);

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
