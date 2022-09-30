export default {
    state: {
        products: [],
        popularProducts: [],
        pageCount: 0
    },
    getters: {
        getProducts: state => {
            return state.products
        },
        getPopularProducts: state => {
            return state.popularProducts
        },
        getPageCount: state => {
            return state.pageCount
        }
    },
    mutations: {
        setProducts: (state, products) => {
            state.products = products
        },
        setPopularProducts: (state, products) => {
            state.popularProducts = products
        },
        setPageCount: (state, pageCount) => {
            state.pageCount = pageCount - 1
        }
    }
}