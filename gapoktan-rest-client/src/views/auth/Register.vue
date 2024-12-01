<template>
  <div class="bg-gradient-secondary">
    <div class="container pb-4">
      <!-- Outer Row -->
      <div class="row justify-content-center pb-5">
        <div class="col-xl-5 col-lg-5 col-md-5">
          <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
              <!-- Nested Row within Card Body -->
              <div class="row justify-content-center">
                <div class="col-xl-12 col-lg-12 col-md-12">
                  <div class="p-5">
                    <div class="text-center">
                      <h1 class="h4 text-gray-900 mb-4">Register Akun!</h1>
                    </div>
                    <form class="user">
                      <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                          <input
                            type="text"
                            class="form-control form-control-user"
                            id="exampleFirstName"
                            placeholder="First Name"
                          />
                        </div>
                        <div class="col-sm-6">
                          <input
                            type="text"
                            class="form-control form-control-user"
                            id="exampleLastName"
                            placeholder="Last Name"
                          />
                        </div>
                      </div>
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control form-control-user"
                          id="exampleInputEmail"
                          placeholder="Email Address"
                        />
                      </div>
                      <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                          <input
                            type="password"
                            class="form-control form-control-user"
                            id="exampleInputPassword"
                            placeholder="Password"
                          />
                        </div>
                        <div class="col-sm-6">
                          <input
                            type="password"
                            class="form-control form-control-user"
                            id="exampleRepeatPassword"
                            placeholder="Repeat Password"
                          />
                        </div>
                      </div>
                      <button
                        type="submit"
                        class="btn btn-primary btn-user btn-block"
                      >
                        Register Account
                      </button>
                    </form>
                    <hr />
                    <div class="text-center">
                      <router-link class="small" to="/login"
                        >Already have an account? Login!</router-link
                      >
                    </div>
                    <div class="text-center">
                      <router-link class="small" to="/"
                        >Batal Register!</router-link
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
/**
 * fitur register belum jalan.
 */

export default {
  name: 'AuthRegister',
};
</script>
