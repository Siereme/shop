export default {
    state: {
        query: '',
        priceRange: {},
        options: []
    },
    getters: {
        getQuery: state => {
            return state.query
        },
        getPriceRange: state => {
            return state.priceRange
        },
        getOptions: state => {
            return state.options
        }
    },
    mutations: {
        setQuery: (state, query) => {
            state.query = query
        },
        setPriceRange: (state, priceRange) => {
            state.priceRange = priceRange
        },
        setOptions: (state, options) => {
            state.options = options
        },
        setOption: (state, option) => {
            let targetOption = state.options.find(item => item.id === option.id)
            let targetValue =  targetOption?.values.find(item => item.value === option.value)
            targetValue.checked = option.checked
        }
    },

}