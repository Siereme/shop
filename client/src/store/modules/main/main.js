import CONFIG from "@/config.json"

export default {
    state: {
        accessToken: "",
        refreshToken: "",
        headerAccessToken: CONFIG.headerAccessToken,
        headerRefreshToken: CONFIG.headerRefreshToken,
        shownAuthModal: 'hide',
        isLoading: false,
    },
    getters: {
        getAccessToken: (state) => () => {
            return state.accessToken
        },
        getRefreshToken: (state) => () => {
            return state.refreshToken
        },
        getHeaderAccessToken: (state) => () => {
            return state.headerAccessToken
        },
        getHeaderRefreshToken: (state) => () => {
            return state.headerRefreshToken
        },
        getShownAuthModal: (state) => () => {
            return state.shownAuthModal
        },
        getIsLoading: (state) => () => {
            return state.isLoading
        }
    },
    mutations: {
        setAccessToken: (state, accessToken) => {
            state.accessToken = accessToken
        },
        setRefreshToken: (state, refreshToken) => {
            state.refreshToken = refreshToken
        },
        setShownAuthModal: (state, shown) => {
            state.shownAuthModal = shown
        },
        setIsLoading: (state, flag) => {
            state.isLoading = flag
        },
    }
}