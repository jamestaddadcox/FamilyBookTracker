import axios from 'axios';


export default {
    //get list of users by family id
    getListOfFamilyMembers(familyId){
        return axios.get(`/family/${familyId}/user`);
    },
    //get user by id
    getUserById(id){
        return axios.get(`/user/${id}`);
    },

    //add user

    //edit user (display name?)

    
}
