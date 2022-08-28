
import api from './backend-api'
import axiosApi from "./api"


const setup = (store) => {
    axiosApi.interceptors.request.use(
      (config) => {
        var accessToken = store.getters.getAccessToken()
        if (accessToken && !config.url.includes('/refresh')) {
          config.headers['X-access-token'] = accessToken
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );
    axiosApi.interceptors.response.use((response) => {
        return response
      }, async function (error) {
        const originalRequest = error.config;
        if (error.response?.status === 401 && !originalRequest._retry) {
          originalRequest._retry = true;
          await Promise.resolve(api.refreshAccessToken())          
          return axiosApi(originalRequest);
        }
        return Promise.reject(error);
    });
  };

export default setup