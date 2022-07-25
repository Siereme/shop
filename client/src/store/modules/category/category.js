export default {
    state: {
        mainCategory: {},
        currentCategory: {},
        categories: []
    },
    getters: {
        getMainCategory(state) {
            return state.mainCategory
        },
        getCurrentCategory(state) {
            return state.currentCategory
        },
        getCategories(state) {
            return state.categories && state.categories.length
        },
        getCategoryById: (state) => (id) => {
            return  state.categories.find(category => +category.id === +id)
        }
    },
    mutations: {
        setMainCategory: (state, category) => {
            state.mainCategory = category
        },
        setCurrentCategory: (state, category) => {
            state.currentCategory = category
        },
        setCategories: (state, categories) => {
            state.categories = categories
        }
    }
}