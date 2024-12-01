/** Rest API Configuration */
/**
 * Base URL:
 * url utama rest server api.
 */
const BASE_URL = '/api/hamparan';

/**
 * authentication url:
 * url login & register.
 */
const AUTHENTICATION_URL = '/api/auth/authenticate';

/**
 * jwt token:
 */
const TOKEN = localStorage.getItem('token');

/**
 * email:
 */
const EMAIL = localStorage.getItem('email');

/**
 * role:
 */
const ROLE = localStorage.getItem('role');

/**
 * header:
 */
const HEADER = {
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
};

const ADMIN_ROLE = 'ADMIN';
const USER_ROLE = 'USER';

export default {
  BASE_URL,
  AUTHENTICATION_URL,
  TOKEN,
  EMAIL,
  ROLE,
  HEADER,
  ADMIN_ROLE,
  USER_ROLE,
};
