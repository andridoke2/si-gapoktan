<template>
  <div class="container">
    <Bar
      v-if="loaded"
      id="hasil-chart"
      :options="chartOptions"
      :data="chartData"
    />
  </div>
</template>

<script>
import axios from 'axios';

/** API Configuration */
const baseURL = '/api/hamparan';
const TOKEN = localStorage.getItem('token');

const header = {
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
};

import { Bar } from 'vue-chartjs';
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from 'chart.js';

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: 'HasilChart',

  components: {
    Bar,
  },

  data() {
    return {
      chartData: {
        labels: [
          'Jan',
          'Feb',
          'Mar',
          'Apr',
          'Mei',
          'Jun',
          'Jul',
          'Agu',
          'Sep',
          'Okt',
          'Nov',
          'Des',
        ],
        datasets: [
          {
            data: [],
            lineTension: 0,
            backgroundColor: 'transparent',
            borderColor: '#007bff',
            borderWidth: 4,
            pointBackgroundColor: '#007bff',
          },
        ],
      },
      chartOptions: {
        responsive: true,
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: false,
              },
            },
          ],
        },
        plugins: {
          title: {
            display: true,
            text: 'Total Hasil',
            padding: {
              bottom: 0,
            },
          },
          legend: {
            display: true,
            labels: {
              color: '#007bff',
            },
          },
        },
      },
      loaded: false,
      listHasil: [],
      listTotalHasil: [],
    };
  },

  async mounted() {
    await this.loadHasil();
    if (this.loaded) {
      this.filterTotalHasil(this.listHasil);
      for (let i = 0; i < this.listTotalHasil.length; i++) {
        /**
         * sampai disini untuk me-load data kedalam chartjs
         */
        this.chartData.datasets[0].data.push(this.listTotalHasil[i]);
      }
    }
  },

  methods: {
    async loadHasil() {
      this.loaded = false;

      await axios
        .get(`${baseURL}/hasil`, header)
        .then((res) => {
          if (res.data.status) {
            this.loaded = true;
            this.listHasil = res.data.payload;
          }
        })
        .catch((err) => {
          if (!err.response.status) {
            this.loaded = false;
            this.listHasil = null;
          }
        })
        .finally(() => {
          if (this.loaded) {
            console.log('(Hasil Chart) berhasil load data hasil...');
          } else {
            console.log('(Hasil Chart) gagal load data hasil...');
          }
        });
    },
    filterTotalHasil(listHasil) {
      for (let i = 0; i < listHasil.length; i++) {
        this.listTotalHasil.push(listHasil[i].total_hasil);
      }
    },
  },
};
</script>
