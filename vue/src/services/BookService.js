import axios from 'axios';
//REFERENCE: book_user has userid, isbn, mins read, +++

export default {

    // getAll() {
    //     return axios.get('/book');
    // },

    getBookUsersByUserId(id){
        return axios.get(`/stats/user/${id}`);//list of bookuser(s) ??
    },

    getBookByIsbn(isbn){
        return axios.get(`/book/${isbn}`);
    },
}