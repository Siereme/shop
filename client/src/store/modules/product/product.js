export default {
    state: {
        products: [],
        popularProducts: [],
        pagesCount: 0
    },
    getters: {
        getProducts: state => {
            return state.products
        },
        getPopularProducts: state => {
            return state.popularProducts
        },
        getPagesCount: state => {
            return state.pagesCount
        }
    },
    mutations: {
        setProducts: (state, products) => {
            state.products = products
        },
        setPopularProducts: (state, products) => {
            state.popularProducts = products
        },
        setPagesCount: (state, pagesCount) => {
            state.pagesCount = pagesCount
        }
    }
}