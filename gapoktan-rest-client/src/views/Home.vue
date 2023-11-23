<template>
  <div>
    <!-- Cover Style CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../assets/css/cover.css" />
    <div class="text-center">
      <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
        <header class="masthead mb-5">
          <div class="inner">
            <h3 class="masthead-brand">SI-GAPOKTAN</h3>
            <nav class="nav nav-masthead justify-content-center">
              <router-link class="nav-link active" to="/">Home</router-link>
              <router-link class="nav-link" to="/login">Login</router-link>
              <router-link class="nav-link" to="/register"
                >Register</router-link
              >
            </nav>
          </div>
        </header>

        <main role="main" class="inner cover mt-5 mb-5">
          <h2 class="cover-heading">
            Sistem Infromasi Gabungan Kelompok Tani (Kelapa Sawit)
          </h2>
          <p class="lead">
            Gabungan Kelompok Tani (Gapoktan) merupakan kumpulan beberapa
            kelompok tani yang bergabung dan bekerjasama untuk meningkatkan
            skala ekonomi dan efisiensi usaha.
          </p>
          <p class="lead">
            <a href="#" class="btn btn-lg btn-secondary">Lanjut baca.</a>
          </p>
        </main>

        <footer class="mastfoot mt-5">
          <div class="inner">
            <p>
              Copyright&copy;
              <a href="https://stiki.ac.id">STIKI Malang</a>, by
              <a href="@andridoke2">andridoke2@gmail.com</a>.
            </p>
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>

<script lang="js">

export default {
  name: 'HomePage'
}

/**
 * Halaman ini jadi landing page
 */
</script>
