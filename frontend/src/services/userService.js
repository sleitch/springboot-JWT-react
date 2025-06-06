import axios from 'axios';
import { getCurrentUser } from './authService';

const API_URL = 'http://localhost:8080/api/users';

const updateProfile = (userData) => {
    const token = getCurrentUser()?.accessToken;
    return axios.put(`${API_URL}/profile`, userData, {
        withCredentials: true,
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    });
};

const getProfile = () => {
    const token = getCurrentUser()?.accessToken;
    return axios.get(`${API_URL}/profile`, {
        withCredentials: true,
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    });
};

export default {
    updateProfile,
    getProfile
};