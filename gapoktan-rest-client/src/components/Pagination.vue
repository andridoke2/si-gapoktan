<template>
  <div class="row">
    <div class="col">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm justify-content-center">
          <li class="page-item">
            <button
              type="button"
              class="btn btn-sm"
              @click="onClickFirstPage"
              :disabled="isInFirstPage"
              aria-label="First"
            >
              First
            </button>
          </li>
          <li class="page-item">
            <button
              type="button"
              class="btn btn-sm"
              @click="onClickPreviousPage"
              :disabled="isInFirstPage"
              aria-label="Previous"
            >
              <span aria-hidden="true">&laquo;</span>
            </button>
          </li>

          <!-- Visible Buttons Start -->

          <li v-for="page in pages" class="page-item" :key="page.name">
            <button
              type="button"
              class="btn btn-sm"
              @click="onClickPage(page.name)"
              :disabled="page.isDisabled"
              :class="{ active: isPageActive(page.name) }"
            >
              {{ page.name }}
            </button>
          </li>

          <!-- Visible Buttons End -->

          <li class="page-item">
            <button
              type="button"
              class="btn btn-sm"
              @click="onClickNextPage"
              :disabled="isInLastPage"
              aria-label="Next"
            >
              <span aria-hidden="true">&raquo;</span>
            </button>
          </li>
          <li class="page-item">
            <button
              type="button"
              class="btn btn-sm"
              @click="onClickLastPage"
              :disabled="isInLastPage"
              aria-label="Last"
            >
              Last
            </button>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  name: 'paginationComponent',

  props: {
    maxVisibleButtons: {
      type: Number,
      require: false,
      default: 3,
    },
    totalPages: {
      type: Number,
      require: true,
    },
    perPage: {
      type: Number,
      require: true,
    },
    currentPage: {
      type: Number,
      require: true,
    },
  },

  computed: {
    startPage() {
      // when on the start page
      if (this.currentPage == 0) {
        return 1;
      }

      // when on the last page
      if (this.currentPage == this.totalPages) {
        const start = this.totalPages - (this.maxVisibleButtons - 1);

        if (start === 0) {
          return 1;
        } else {
          return start;
        }
      }

      // when in between
      return this.currentPage;
    },

    pages() {
      const range = [];

      for (
        let i = this.startPage;
        i <=
        Math.min(this.startPage + this.maxVisibleButtons - 1, this.totalPages);
        i++
      ) {
        range.push({
          name: i,
          isDisabled: i === this.currentPage,
        });
      }

      return range;
    },

    isInFirstPage() {
      return this.currentPage === 1;
    },
    isInLastPage() {
      return this.currentPage === this.totalPages;
    },
  },

  methods: {
    onClickFirstPage() {
      this.$emit('pagechanged', 1);
    },
    onClickPreviousPage() {
      this.$emit('pagechanged', this.currentPage - 1);
    },
    onClickPage(page) {
      this.$emit('pagechanged', page);
    },
    onClickNextPage() {
      this.$emit('pagechanged', this.currentPage + 1);
    },
    onClickLastPage() {
      this.$emit('pagechanged', this.totalPages);
    },

    isPageActive(page) {
      return this.currentPage == page;
    },
  },
};
</script>

<style>
.pagination {
  list-style-type: none;
}

.pagination-item {
  display: inline-block;
}

.active {
  background-color: rgb(18, 76, 235);
  color: #ffffff;
}
</style>
