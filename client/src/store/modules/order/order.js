export default {
    state: {
        orders: [],
        payments: [],
    },
    getters: {
        geOrders: (state) => () => {
            return state.orders
        },
        getLastOrder: (state) => () => {
            return state.orders[state.orders.length - 1]
        },
        getPayments: (state) => () => {
            return state.payments
        }
    },
    mutations: {
        setOrder: (state, order) => {
            state.orders.shift(order)
        },
        setOrders: (state, orders) => {
            state.orders = orders
        },
        setPayments: (state, payments) => {
            state.payments = payments
        }
    }
}