export default {
    state: {
        priceRange: {},
        options: []
    },
    getters: {
        getPriceRange: state => {
            return state.priceRange
        },
        getOptions: state => {
            return state.options
        }
    },
    mutations: {
        setPriceRange: (state, priceRange) => {
            state.priceRange = priceRange
        },
        setOptions: (state, options) => {
            state.options = options
        }
    }
}