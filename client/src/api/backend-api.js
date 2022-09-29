import axiosApi from "./api"
import store from "@/store"
import cookiesModule from "@/utils/cookies/"


//variables
const cookies = cookiesModule
let headerAccessToken = store.getters.getHeaderAccessToken()
let headerRefreshToken = store.getters.getHeaderRefreshToken()

//methods
let login = (email, password) => {
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
        store.commit('setRefreshToken', res.data.refreshToken)
        cookies.setCookie(headerAccessToken, res.data.accessToken)
        cookies.setCookie(headerRefreshToken, res.data.refreshToken)
        return res
    })
}

let loginAnonymous = () => {
    return axiosApi({
        method: 'post',
        url: '/auth/login-anonymous'
    })
    .then(res => {
        store.commit('setUser', res.data.user)
        store.commit('setAccessToken', res.data.accessToken)
        store.commit('setRefreshToken', res.data.refreshToken)
        cookies.setCookie(headerAccessToken, res.data.accessToken)
        cookies.setCookie(headerRefreshToken, res.data.refreshToken)
        return res
    })
}

let refreshAccessToken = () => {
    return axiosApi({
        method: 'post',
        url: '/auth/refresh'
    })
    .then(res => {
        store.commit('setUser', res.data.user)
        store.commit('setAccessToken', res.data.accessToken)
        store.commit('setRefreshToken', res.data.refreshToken)
        cookies.setCookie(headerAccessToken, res.data.accessToken)
        cookies.setCookie(headerRefreshToken, res.data.refreshToken)
        return res
    })
    .catch(() => loginAnonymous())
}

let logout = () => {
    return axiosApi({
        method: 'post',
        url: '/auth/logout'
    })
    .then(res => {
        store.commit('setUser', {})
        store.commit('setAccessToken', '')
        store.commit('setRefreshToken', '')
        cookies.deleteCookie(headerAccessToken)
        cookies.deleteCookie(headerRefreshToken)
        return res
    })
}

let authInfo = (token) => {
    return axiosApi({
        method: 'get',
        url: '/auth/info',
        headers: {
            [headerAccessToken]: token
        }
    })
    .then(res => {
        store.commit('setAccessToken', res.data.accessToken)
        store.commit('setUser', res.data.user)
        return res
    })
}

let loadMain = () => {
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
}

let createUser = (user) => {
    return axiosApi({
        method: 'post',
        url: '/user/add',
        data: user
    })
}

let updateUser = (user) => {
    return axiosApi({
        method: 'post',
        url: '/user/update',
        data: user
    })
    .then(res => {
        store.commit('setUser', res.data.user)
        store.commit('setAccessToken', res.data.accessToken)
        store.commit('setRefreshToken', res.data.refreshToken)
        cookies.setCookie(headerAccessToken, res.data.accessToken)
        cookies.setCookie(headerRefreshToken, res.data.refreshToken)
        return res
    })
}

let loadCategory = (id, withParent, withProducts) => {
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
        if(res.data.category !== null){
            store.commit('setCurrentCategory', res.data.category)
        }
        if(res.data.parentCategory !== null){
            store.commit('setMainCategory', res.data.parentCategory)
        }
        if(res.data.products !== null){
            store.commit('setProducts', res.data.products)
        }
        return res
    })
}

let loadProducts = () =>  {
    return axiosApi({
        method: 'get',
        url: '/product/all'
    })
    .then(res => {
        store.commit('setProducts', res.data)
        return res
    })
}

let loadPopularProducts = () =>  {
    return axiosApi({
        method: 'get',
        url: '/product/popular'
    })
    .then(res => {
        store.commit('setPopularProducts', res.data)
        return res
    })
}

let loadCartProducts = () =>  {
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
}

let addCartProduct = (productId, count) =>  {
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
}

let removeCartProduct = (productId) =>  {
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
}

let getOrders = () => {
    const userId = store.getters.getUserId()
    return axiosApi({
        method: 'get',
        url: `/order/user-id/${userId}`
    })
    .then(res => {
        store.commit('setOrders', [...res.data])
        return res
    })
}

let createOrder = (order) => {
    return axiosApi({
        method: 'post',
        url: `/order/add`,
        data : order
    })
    .then(res => {
        store.commit('setOrder', res.data.order)
        store.commit('clearCart')
        return res
    })
}

let getPayments = () => {
    return axiosApi({
        method: 'get',
        url: `/payment/all`
    })
    .then(res => {
        store.commit('setPayments', res.data)
        return res
    })       
}

let getReceipts = () => {
    return axiosApi({
        method: 'get',
        url: `/receipt/all`
    })
    .then(res => {
        store.commit('setReceipts', res.data)
        return res
    })       
}

let getShopAddress = () => {
    return axiosApi({
        method: 'get',
        url: `/shop-address/all`
    })
    .then(res => {
        store.commit('setShopAddress', res.data)
        return res
    })       
}

export default {
    login,
    loginAnonymous,
    refreshAccessToken,
    logout,
    authInfo,
    loadMain,
    createUser,
    updateUser,
    loadCategory,
    loadProducts,
    loadPopularProducts,
    loadCartProducts,
    addCartProduct,
    removeCartProduct,
    getOrders,
    createOrder,
    getPayments,
    getReceipts,
    getShopAddress
}
