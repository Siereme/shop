import axios from 'axios'

const axiosApi = axios.create({
    baseURL: '/api/v1',
    timeout: 1000,
    headers: { 
        'Content-Type': 'application/json'
    }
});

export default axiosApi