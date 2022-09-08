
import api from './backend-api'
import axiosApi from "./api"
import cookies from '@/utils/cookies'
import store from "@/store"

let headerAccessToken = store.getters.getHeaderAccessToken()
let headerRefreshToken = store.getters.getHeaderRefreshToken()

const setup = (store) => {
    axiosApi.interceptors.request.use(
      (config) => {
        var accessToken = store.getters.getAccessToken() ?? cookies.getCookie(headerAccessToken)
        var refreshToken = store.getters.getRefreshToken() ?? cookies.getCookie(headerRefreshToken)
        if (accessToken && !config.url.includes('/refresh')) {
          config.headers[headerAccessToken] = accessToken
        }
        if(refreshToken && config.url.includes('/refresh')){
          config.headers[headerRefreshToken] = refreshToken
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