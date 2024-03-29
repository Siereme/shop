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
        url: `/auth/login?email=${email}&password=${password}`,
    })
    .then(res => {
        authInfo()
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
        url: '/auth/login/anonymous'
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

let authInfo = () => {
    return axiosApi({
        method: 'get',
        url: '/customer/info',
    })
    .then(res => {
        store.commit('setUser', res.data)
        return res
    })
}

let loadMain = () => {
    return axiosApi({
        method: 'post',
        url: '/product-catalog/main/main-page',
        data: {
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
    })
}

let createUser = (user) => {
    return axiosApi({
        method: 'post',
        url: '/auth/registration',
        data: user
    })
}

let updateUser = (user) => {
    return axiosApi({
        method: 'put',
        url: '/auth/login/update',
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

let searchCategory = (request) => {
    return axiosApi({
        method: 'post',
        url: '/product-catalog/search/category',
        data: request
    })
    .then(res => {
        if(res.data.category !== null){
            store.commit('setCurrentCategory', res.data.category)
        }
        if(res.data.parentCategory){
            store.commit('setMainCategory', res.data.parentCategory)
        }
        if(res.data.products !== null){
            store.commit('setProducts', res.data.products)
        }
        if(res.data.rangePrice !== null){
            store.commit('setRangePrice', res.data.rangePrice)
        }
        if(res.data.options !== null){
            store.commit('setOptions', res.data.options)
        }     
        if(res.data.pagesCount !== null){
            store.commit('setPagesCount', res.data.pagesCount)
        }
        return res
    })
}

let searchCategoryByOptions = (options) => {
    return axiosApi({
        method: 'post',
        url: '/product-catalog/search/category/options',
        data: options
    })
    .then(res => {
        if(res.data.products !== null){
            store.commit('setProducts', res.data.products)
        }
        if(res.data.rangePrice !== null){
            store.commit('setRangePrice', res.data.rangePrice)
        }
        if(res.data.options !== null){
            store.commit('setOptions', res.data.options)
        }
        if(res.data.pagesCount !== null){
            store.commit('setPagesCount', res.data.pagesCount)
        }
        return res
    })
}

let search = (request) => {
    return axiosApi({
        method: 'post',
        url: '/product-catalog/search/',
        data: request
    })
    .then(res => {
        store.commit('setQuery', request.query)
        if(res.data.products !== null){
            store.commit('setProducts', res.data.products)
        }
        if(res.data.rangePrice !== null){
            store.commit('setRangePrice', res.data.rangePrice)
        }
        if(res.data.options !== null){
            store.commit('setOptions', res.data.options)
        }
        if(res.data.pagesCount !== null){
            store.commit('setPagesCount', res.data.pagesCount)
        }
        return res
    })
}

let searchByOptions = (options) => {
    return axiosApi({
        method: 'post',
        url: '/product-catalog/search/options',
        data: options
    })
    .then(res => {
        if(res.data.products !== null){
            store.commit('setProducts', res.data.products)
        }
        if(res.data.rangePrice !== null){
            store.commit('setRangePrice', res.data.rangePrice)
        }
        if(res.data.options !== null){
            store.commit('setOptions', res.data.options)
        }
        if(res.data.pagesCount !== null){
            store.commit('setPagesCount', res.data.pagesCount)
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
        store.commit('setCartProducts', res.data.cartLineItems.lineItems ?? [])
        store.commit('setCartCount', res.data.count ?? 0)
        store.commit('setCartTotal', res.data.total ?? 0)
        return res
    })
}

let addCartProduct = (sku, count) =>  {
    const userId = store.getters.getUserId()
    return axiosApi({
        method: 'post',
        url: `/shopping-cart/add?customerId=${userId}&sku=${sku}&count=${count}`
    })
    .then(res => {
        store.commit('setCartProducts', res.data.cartLineItems.lineItems ?? [])
        store.commit('setCartCount', res.data.count ?? 0)
        store.commit('setCartTotal', res.data.total ?? 0)
        return res
    })
}

let removeCartProduct = (sku) =>  {
    const userId = store.getters.getUserId()
    return axiosApi({
        method: 'post',
        url: `/shopping-cart/delete?customerId=${userId}&sku=${sku}`
    })
    .then(res => {
        store.commit('setCartProducts', res.data.cartLineItems.lineItems ?? [])
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
        url: `/order/payment/all`
    })
    .then(res => {
        store.commit('setPayments', res.data)
        return res
    })       
}

let getReceipts = () => {
    return axiosApi({
        method: 'get',
        url: `/order/receipt/all`
    })
    .then(res => {
        store.commit('setReceipts', res.data)
        return res
    })       
}

let getShopAddress = () => {
    return axiosApi({
        method: 'get',
        url: `/order/shop-address/all`
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
    searchCategory,
    searchCategoryByOptions,
    search,
    searchByOptions,
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
