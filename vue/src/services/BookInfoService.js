import axios from 'axios';

const axiosInstance = axios.create({baseURL: 'https://openlibrary.org', timeout: 1000, headers: { "accept": "application/json" } });
// let authorId = "";

// const config = {
//     headers:{
//         "accept": "application/json"
//     }
// }

export default {
    getBookInfoByIsbn(isbn) {
        return axiosInstance.get(`/isbn/${isbn}.json`);

    },
    getAuthorByAuthorId(id) {
        return axiosInstance.get(`/authors/${id}.json`);
    }

}