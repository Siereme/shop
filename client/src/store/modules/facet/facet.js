export default {
    state: {
        query: '',
        rangePrice: {},
        price: {},
        options: []
    },
    getters: {
        getQuery: state => {
            return state.query
        },
        getRangePrice: state => {
            return state.rangePrice
        },
        getPrice: state => {
            return state.rangePrice
        },
        getOptions: state => {
            return state.options
        }
    },
    mutations: {
        setQuery: (state, query) => {
            state.query = query
        },
        setRangePrice: (state, rangePrice) => {
            state.rangePrice = rangePrice
        },
        setPrice: (state, price) => {
            state.price = price
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