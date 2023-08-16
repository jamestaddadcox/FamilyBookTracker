import axios from 'axios';

export default {

    getPrizeById(id){
        return axios.get(`/prize/${id}`); // one single prize {prizeId}
    },

    getPrizesByFamilyId(id){
        return axios.get(`/prize/family/${id}`); // list of prizes per family {familyId}
    },

    getPrizesByWinnerUserId(id){
        return axios.get(`/prize/user/${id}`); // list of prizes per winner {userId}
    },

    addPrize(prize) {
        return axios.post('/prize', prize);
    },

    updatePrize(prize) {
        return axios.put(`/prize/${prize.id}`, [prize]);
    },

    // delete prize

}