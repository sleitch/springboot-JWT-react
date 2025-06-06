import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

// Configure axios defaults
axios.defaults.withCredentials = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const register = async (userData) => {
    try {
        console.log('Registering user:', JSON.stringify(userData));    
        const response = await axios.post(API_URL + 'signup', userData , {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        });
        
        console.log('Registration response:', response.data);
        return response.data;
    } catch (error) {
                console.log('Registration error:', error);

        throw error;
    }
};

const login = (username, password) => {
    return axios.post(API_URL + 'login', {
        username,
        pwd : password
    }, {
        withCredentials: true,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then(response => {
        if (response.data.accessToken) {
            localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
    });
};

const logout = () => {
    return axios.post(API_URL + 'logout', {}, { withCredentials: true });
};

const getCurrentUser = () => {

    console.log('getCurrentUser Current user:', JSON.parse(localStorage.getItem('user')));
    return JSON.parse(localStorage.getItem('user'));
};

const forgotPassword = (email) => {
    return axios.post(API_URL + 'forgot', { email }, {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });
};

const resetPassword = (token, password) => {
    return axios.post(API_URL + 'reset', {
        token,
        password
    }, {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });
};

export {
    register,
    login,
    logout,
    getCurrentUser,
    forgotPassword,
    resetPassword
};

export default {
    register,
    login,
    logout,
    getCurrentUser,
    forgotPassword,
    resetPassword
};