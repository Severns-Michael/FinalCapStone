import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import AdminPageView from '../views/AdminPageView.vue';
import SetCriteriaView from "@/views/SetCriteriaView.vue";
import UserDashboardView from '../views/UserDashboardView.vue';
import SwipingView from '../views/SwipingView.vue';
import TestView from "@/views/TestSwiperView.vue";
import FlopsView from "../views/FlopsView.vue"
import CleanView from "@/views/CleanView.vue";

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/adminpage",
    name: "adminpage",
    component: AdminPageView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/setcriteria",
    name: "setcriteria",
    component: SetCriteriaView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/userdashboard",
    name: "userdashboard",
    component: UserDashboardView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/swiping",
    name: "swipingview",
    component: SwipingView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/test",
    name: "Testview",
    component: TestView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/flops",
    name: "flopsview",
    component:FlopsView,
    meta:{
      requiresAuth: true
    }
  },
  {
    path: "/clean",
    name: "cleanview",
    component: CleanView,
    meta: {
      requiresAuth: true
    }
  },
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
