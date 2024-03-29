import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import '../styles_global/variables.css';

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || { firstName: 'Bookworm' },
    modalFlag: false,

    familyId: currentUser ? currentUser.family_id : null, // Add familyId here
    // bookUser: [{userId: 1, isbn: 9780194229647},{userId: 1, isbn: 9783423092449}]
    activeFamilyMemberId: currentUser ? currentUser.username : 0,
  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      state.familyId = user ? user.family_id : null; // Set familyId here
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.familyId = null; // Reset familyId on logout
      axios.defaults.headers.common = {};
    },

    UPDATE_FAMILY_MEMBER_FILTER(state, username){
      state.activeFamilyMemberId = username;
    },
    UPDATE_FAMILY_MEMBER_FILTER_ID(state, id){
      state.activeFamilyMemberId = id;
    }

    //CHANGE_MODAL_FLAG(state, modalFlag) {
    //  this.modalFlag = !this.modalFlag;
    //}
  },
});
