import axios from 'axios'
import store from "@/store";
import cookies from "@/utils/cookies/"
import { computed } from "vue"

let token = computed(() => store.getters.getToken())

const axiosApi = axios.create({
    baseURL: '/api/v1',
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' }
});

export default {
    login: (email, password) => {
        return axiosApi({
            method: 'post',
            url: '/auth/login',
            headers: {},
            data : {
                "email": email,
                "password": password
            }
        })
        .then(res => {
            store.commit('setUser', res.data.user)
            store.commit('setToken', res.data.token)
            cookies.setCookie('Authorization', res.data.token)
            return res
        })
    },
    logout: () => {
        return axiosApi({
            method: 'post',
            url: '/auth/logout',
            headers: {
                "Authorization": token.value
            },
        })
        .then(res => {
            cookies.deleteCookie('Authorization')
            store.commit('setUser', {})
            store.commit('setToken', '')
            return res
        })
    },
    authInfo: (token) => {
        return axiosApi({
            method: 'get',
            url: '/auth/info',
            headers: {
                "Authorization": token
            },
        })
        .then(res => {
            store.commit('setToken', res.data.token)
            store.commit('setUser', res.data.user)
            return res
        })
    },
    createUser: (user) => {
        return axiosApi({
            method: 'post',
            url: '/user/add',
            headers: {
                "Authorization": token.value
            },
            data: user
        })
    },
    createAnonymousUser: () => {
        return axiosApi({
            method: 'post',
            url: '/user/add/anonymous'
        })
        .then(res => {
            store.commit('setUser', res.data)
            return res
        })
    },
    editUser: (user) => {
        return axiosApi({
            method: 'post',
            url: '/user/edit',
            headers: {
                "Authorization": token.value
            },
            data: user
        })
        .then(res => {
            store.commit('setUser', res.data)
            return res
        })
    },
    loadAllCategories: () =>  {
        return axiosApi({
            method: 'get',
            url: '/category/all',
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setCategories', res.data)
            return res
        })
    },
    loadCategoryById: (id) =>  {
        return axiosApi({
            method: 'get',
            url: '/category/id/' + id,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setCurrentCategory', res.data)
            return res
        })
    },
    loadCategoriesByDepth: (depth) =>  {
        return axiosApi({
            method: 'get',
            url: '/category/depth?depth=' + depth,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setCategories', res.data)
            return res
        })
    },
    loadCategoryByIdAndDepth: (id, depth) =>  {
        return axiosApi({
            method: 'get',
            url: `/category/id-depth?id=${id}&depth=${depth}`,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setMainCategory', res.data)
            return res
        })
    },
    loadCategoriesByLineageAndDepth: (lineage, depth) =>  {
        return axiosApi({
            method: 'get',
            url: '/category/lineage-depth?lineage=' + lineage + '&depth=' + depth,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
    },
    loadProducts: () =>  {
        return axiosApi({
            method: 'get',
            url: '/product/all',
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setProducts', res.data)
            return res
        })
    },
    loadProductsByCategoryId: (categoryId) =>  {
        return axiosApi({
            method: 'get',
            url: '/product/category_id/' + categoryId,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setProducts', res.data)
            return res
        })
    },
    loadPopularProducts: () =>  {
        return axiosApi({
            method: 'get',
            url: '/product/popular',
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setPopularProducts', res.data)
            return res
        })
    },
    loadCartProducts: () =>  {
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'get',
            url: '/shopping-cart/user-id/' + userId,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
        .then(res => {
            store.commit('setCartProducts', res.data.cartItems)
            return res
        })
    },
    addCartProduct: (productId, count) =>  {
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'post',
            url: `/shopping-cart/add?userId=${userId}&productId=${productId}&count=${count}`,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
    },
    removeCartProduct: (productId) =>  {
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'post',
            url: `/shopping-cart/remove?userId=${userId}&productId=${productId}`,
            headers: {
                "Authorization": token.value
            },
            data : {}
        })
    },
    getOrders: function(){
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'get',
            url: `/order/user-id/${userId}`,
            headers: {
                "Authorization": token.value
            }
        })
        .then(res => {
            store.commit('setOrders', [...res.data])
            return res
        })       
    },
    createOrder: function(user, payment){
        return axiosApi({
            method: 'post',
            url: `/order/add`,
            headers: {
                "Authorization": token.value
            },
            data : {
                user,
                payment
            }
        })
        .then(res => {
            if(res.data.token){
                store.commit('setToken', res.data.token)
                cookies.setCookie('Authorization', res.data.token)
            }
            if(res.data.user){
                store.commit('setUser', res.data.user)
            }
            if(res.data.order){
                store.commit('setOrder', res.data.order)
            }
            return res
        })
    },
    getPayments: function(){
        return axiosApi({
            method: 'get',
            url: `/payment/all`,
            headers: {
                "Authorization": token.value
            }
        })
        .then(res => {
            store.commit('setPayments', res.data)
            return res
        })       
    }
}