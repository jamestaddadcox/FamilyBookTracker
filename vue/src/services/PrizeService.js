import axios from 'axios';

export default {
    
    getPrizeById(id) {
        return axios.get(`/prize/${id}`);
    },

    getPrizesByFamilyId(familyId) {
        return axios.get(`/prize/family/${familyId}`);
    },

    getPrizesByWinnerUserId(id) {
        return axios.get(`/prize/user/${id}`);
    },

    createPrize(prize) {
        return axios.post('/prize', prize);
    },

    deletePrizeById(id) {
        return axios.delete(`/prize/${id}`);
    },

    editPrize(id, prize) {
        return axios.put(`/prize/${id}`, prize)
    }

    
}