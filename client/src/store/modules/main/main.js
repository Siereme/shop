export default {
    state: {
        shownAuthModal: 'hide',
        isLoading: false,
    },
    getters: {
        getShownAuthModal: (state) => () => {
            return state.shownAuthModal
        },
        getIsLoading: (state) => () => {
            return state.isLoading
        },
    },
    mutations: {
        setShownAuthModal: (state, shown) => {
            state.shownAuthModal = shown
        },
        setIsLoading: (state, flag) => {
            state.isLoading = flag
        },
    }
}