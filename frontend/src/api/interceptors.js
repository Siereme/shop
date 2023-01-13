
import api from './backend-api'
import axiosApi from "./api"
import cookiesModule from '@/utils/cookies'
import store from "@/store"


const cookies = cookiesModule
let headerAccessToken = store.getters.getHeaderAccessToken()
let headerRefreshToken = store.getters.getHeaderRefreshToken()
let accessTokenExcludeUrls = ['/refresh', '/login-anonymous']

const setup = (store) => {
    axiosApi.interceptors.request.use(
      (config) => {
        var storeAccessToken = store.getters.getAccessToken()
        var accessToken =  storeAccessToken ? storeAccessToken : (() => {
          var token = cookies.getCookie(headerAccessToken)
          if(token) store.commit('setAccessToken', token)
          return token
        })()

        var storeRefreshToken = store.getters.getRefreshToken()
        var refreshToken = storeRefreshToken ? store.getters.getRefreshToken() : (() => {
          var token = cookies.getCookie(headerRefreshToken)
          if(token) store.commit('setRefreshToken', token)
          return token
        })()

        if (accessToken && accessTokenExcludeUrls.every(url => !config.url.includes(url))) {
          config.headers['Authorization'] = 'Bearer ' + accessToken
        }
        if(refreshToken && config.url.includes('/refresh')){
          config.headers['Authorization'] = 'Bearer ' + refreshToken
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
          api.refreshAccessToken()
          .catch(() => api.loginAnonymous())
          return axiosApi(originalRequest);
        }
        return Promise.reject(error);
    });
};

export default setup