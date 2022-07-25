export default {
    state: {
        user: {
            id: 1,
            email: "admin@mail.com",
            password: "admin",
        },
        token: ""
    },
    getters: {
        getUser: (state) => () => {
            return state.user
        },
        getUserId: (state) => () => {
            return state.user.id
        },
        getUserEmail: (state) => () => {
            return state.user.email
        },
        getUserPhone: (state) => () => {
            return state.user.phone
        },
        getUserPassword: (state) => () => {
            return state.user.password
        },
        getUserName: (state) => () => {
            return state.user.name
        },
        getUserStatus: (state) => () => {
            return state.user?.status
        },
        getToken: (state) => () => {
            return state.token
        },
    },
    mutations: {
        setUser: (state, user) => {
            state.user = user
        },
        setUserId: (state, id) => {
            state.user.id = id
        },
        setUserEmail: (state, email) => {
            state.user.email = email
        },
        setUserPhone: (state, phone) => {
            state.user.phone = phone
        },
        setUserSurname: (state, surname) => {
            state.user.surname = surname
        },
        setUserName: (state, name) => {
            state.user.name = name
        },
        setUserPatronymic: (state, patronymic) => {
            state.user.patronymic = patronymic
        },
        setToken: (state, token) => {
            state.token = token
        }
    }
}