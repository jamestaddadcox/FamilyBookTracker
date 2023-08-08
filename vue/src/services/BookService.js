import axios from 'axios';


export default {

    getAll() {
        return axios.get('/book');
    },

    getByIsbn(isbn) {
        return axios.get(/stats/user/{user_id}/book/{isbn});
    }
}