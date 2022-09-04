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
            cookies.setCookie('X-access-token', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('X-refresh-token', res.data.refreshToken)
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
            cookies.setCookie('X-access-token', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('X-refresh-token', res.data.refreshToken)
            return res
        })
    },
    refreshAccessToken: () => {
        return axiosApi({
            method: 'post',
            url: '/auth/refresh',
            headers: {
                'X-refresh-token': refreshToken.value
            }
        })
        .then(res => {
            store.commit('setUser', res.data.user)
            store.commit('setAccessToken', res.data.accessToken)
            cookies.setCookie('X-access-token', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('X-refresh-token', res.data.refreshToken)
            return res
        })
    },
    logout: () => {
        return axiosApi({
            method: 'post',
            url: '/auth/logout'
        })
        .then(res => {
            cookies.setCookie('X-access-token', '')
            cookies.setCookie('X-refresh-token', '')
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
                'X-access-token': token
            }
        })
        .then(res => {
            store.commit('setAccessToken', res.data.accessToken)
            store.commit('setUser', res.data.user)
            return res
        })
    },
    loadMain: () => {
        return axiosApi({
            method: 'post',
            url: '/main/main-page',
            data: {
                'userId': store.getters.getUserId(),
                'withCategories': true,
                'categoryLevel': 1,
                'withShoppingCart': true,
                'withOrders': true,
                'withProductsPopular': true,
            }
        })
        .then(res => {
            store.commit('setCategories', res.data.categories)
            store.commit('setPopularProducts', res.data.productsPopular)
            store.commit('setCart', res.data.shoppingCart)
            store.commit('setOrders', [...res.data.orders])
        })
    },
    createUser: (user) => {
        return axiosApi({
            method: 'post',
            url: '/user/add',
            data: user
        })
    },
    updateUser: (user) => {
        return axiosApi({
            method: 'post',
            url: '/user/update',
            data: user
        })
        .then(res => {
            store.commit('setUser', res.data.user)
            store.commit('setAccessToken', res.data.accessToken)
            cookies.setCookie('X-access-token', res.data.accessToken)
            store.commit('setRefreshToken', res.data.refreshToken)
            cookies.setCookie('X-refresh-token', res.data.refreshToken)
            return res
        })
    },
    loadCategory: (id, withParent, withProducts) => {
        return axiosApi({
            method: 'post',
            url: '/main/category-page',
            data: {
                "id": id, 
                "withParent": withParent,
                "withProducts": withProducts
            }
        })
        .then(res => {
            store.commit('setCurrentCategory', res.data.category)
            store.commit('setMainCategory', res.data.parentCategory ?? {})
            store.commit('setProducts', res.data.products ?? [])
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
        .then(res => {
            store.commit('setCartProducts', res.data.cartItems ?? [])
            store.commit('setCartCount', res.data.count ?? 0)
            store.commit('setCartTotal', res.data.total ?? 0)
            return res
        })
    },
    removeCartProduct: (productId) =>  {
        const userId = store.getters.getUserId()
        return axiosApi({
            method: 'post',
            url: `/shopping-cart/delete?userId=${userId}&productId=${productId}`
        })
        .then(res => {
            store.commit('setCartProducts', res.data.cartItems ?? [])
            store.commit('setCartCount', res.data.count ?? 0)
            store.commit('setCartTotal', res.data.total ?? 0)
            return res
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