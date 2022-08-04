import axiosApi from "./api"
import store from "@/store"
import cookies from "@/utils/cookies/"
import { computed } from "vue"

let refreshToken = computed(() => store.getters.getRefreshToken())

export default {
    login: (email, password) => {
        return axiosApi({
            method: 'post',
            url: '/auth/login',
            data : {
                "email": email,
                "password": password
            }
        })
        .then(res => {
            store.commit('setUser', res.data.user)
            store.commit('setAccessToken', res.data.accessToken)
            cookies.setCookie('Access_Authorization', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('Refresh_Authorization', res.data.refreshToken)
            return res
        })
    },
    loginAnonymous: () => {
        return axiosApi({
            method: 'post',
            url: '/auth/login-anonymous'
        })
        .then(res => {
            store.commit('setUser', res.data.user)
            store.commit('setAccessToken', res.data.accessToken)
            cookies.setCookie('Access_Authorization', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('Refresh_Authorization', res.data.refreshToken)
            return res
        })
    },
    refreshAccessToken: () => {
        return axiosApi({
            method: 'post',
            url: '/auth/refresh',
            headers: {
                'Refresh_Authorization': refreshToken.value
            }
        })
        .then(res => {
            store.commit('setUser', res.data.user)
            store.commit('setAccessToken', res.data.accessToken)
            cookies.setCookie('Access_Authorization', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('Refresh_Authorization', res.data.refreshToken)
            return res
        })
    },
    logout: () => {
        return axiosApi({
            method: 'post',
            url: '/auth/logout'
        })
        .then(res => {
            cookies.deleteCookie('Access_Authorization')
            cookies.deleteCookie('Refresh_Authorization')
            store.commit('setUser', {})
            store.commit('setAccessToken', '')
            store.commit('setRefreshToken', '')
            return res
        })
    },
    authInfo: (token) => {
        return axiosApi({
            method: 'get',
            url: '/auth/info',
            headers: {
                'Access_Authorization': token
            }
        })
        .then(res => {
            store.commit('setAccessToken', res.data.accessToken)
            store.commit('setUser', res.data.user)
            return res
        })
    },
    createUser: (user) => {
        return axiosApi({
            method: 'post',
            url: '/user/add',
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
            url: '/category/all'
        })
        .then(res => {
            store.commit('setCategories', res.data)
            return res
        })
    },
    loadCategoryById: (id) =>  {
        return axiosApi({
            method: 'get',
            url: '/category/id/' + id
        })
        .then(res => {
            store.commit('setCurrentCategory', res.data)
            return res
        })
    },
    loadCategoriesByDepth: (depth) =>  {
        return axiosApi({
            method: 'get',
            url: '/category/depth?depth=' + depth
        })
        .then(res => {
            store.commit('setCategories', res.data)
            return res
        })
    },
    loadCategoriesByLineageAndDepth: (lineage, depth) =>  {
        return axiosApi({
            method: 'get',
            url: '/category/lineage-depth?lineage=' + lineage + '&depth=' + depth
        })
        .then(res => {
            store.commit('setMainCategory', res.data[0])
            return res
        })
    },
    loadProducts: () =>  {
        return axiosApi({
            method: 'get',
            url: '/product/all'
        })
        .then(res => {
            store.commit('setProducts', res.data)
            return res
        })
    },
    loadProductsByCategoryId: (categoryId) =>  {
        return axiosApi({
            method: 'get',
            url: '/product/category_id/' + categoryId
        })
        .then(res => {
            store.commit('setProducts', res.data)
            return res
        })
    },
    loadPopularProducts: () =>  {
        return axiosApi({
            method: 'get',
            url: '/product/popular'
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
            url: '/shopping-cart/user-id/' + userId
        })
        .then(res => {
            store.commit('setCartProducts', res.data.cartItems ?? [])
            store.commit('setCartCount', res.data.count ?? 0)
            store.commit('setCartTotal', res.data.total ?? 0)
            return res
        })
    },
    addCartProduct: (productId, count) =>  {
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'post',
            url: `/shopping-cart/add?userId=${userId}&productId=${productId}&count=${count}`
        })
    },
    removeCartProduct: (productId) =>  {
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'post',
            url: `/shopping-cart/remove?userId=${userId}&productId=${productId}`
        })
    },
    getOrders: function(){
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'get',
            url: `/order/user-id/${userId}`
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
            data : {
                user,
                payment
            }
        })
        .then(res => {
            store.commit('setOrder', res.data.order)
            store.commit('clearCart')
            return res
        })
    },
    getPayments: function(){
        return axiosApi({
            method: 'get',
            url: `/payment/all`
        })
        .then(res => {
            store.commit('setPayments', res.data)
            return res
        })       
    }
}