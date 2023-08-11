import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
// import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
// import Register from '../views/Register.vue'
import store from '../store/index'
import Welcome from '../views/Welcome.vue'
import Error404 from '../views/Error404.vue'
import Family from '../views/Family.vue'
import Prizes from '../views/Prizes.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/welcome',
      name: 'welcome',
      component: Welcome,
      meta: {
        requiresAuth: false 
      }
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true 
      }
    },
    // We're no longer using this guy:
    // {
    //   path: "/login",
    //   name: "login",
    //   component: Login,
    //   meta: {
    //     requiresAuth: false
    //   }
    // },
    // Logout, however, remains necessary:
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    // {
    //   path: "/register",
    //   name: "register",
    //   component: Register,
    //   meta: {
    //     requiresAuth: false
    //   }
    // },
    {
      path: '*',
      component: Error404, // eventually we'll want this to be an error page
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/family',
      name: "family",
      component: Family, // eventually we'll want this to be an error page
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/prizes',
      name: "prizes",
      component: Prizes, // eventually we'll want this to be an error page
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/welcome"
  if (requiresAuth && store.state.token === '') {
    return next("/welcome");
  } else if (to.name === 'welcome' && store.state.token !== '') { // unsure why this isn't working yet
    return next('/');
  } 
    // Else let them go to their next destination
    return next();
  
});

export default router;

