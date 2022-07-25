export default {
    state: {
        products: [],
        popularProducts: []
    },
    getters: {
        getProducts: state => {
            return state.products
        },
        getPopularProducts: state => {
            return state.popularProducts
        }
    },
    mutations: {
        setProducts: (state, products) => {
            state.products = products
        },
        setPopularProducts: (state, products) => {
            state.popularProducts = products
        }
    }
}