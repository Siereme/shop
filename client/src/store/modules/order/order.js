export default {
    state: {
        orders: [],
        payments: [],
        receipts: [],
        shopAddress: []
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
        },
        getReceipts: (state) => () => {
            return state.receipts
        },
        getShopAddress: (state) => () => {
            return state.shopAddress
        }
    },
    mutations: {
        setOrder: (state, order) => {
            state.orders.push(order)
        },
        setOrders: (state, orders) => {
            state.orders = orders
        },
        setPayments: (state, payments) => {
            state.payments = payments
        },
        setReceipts: (state, receipts) => {
            state.receipts = receipts
        },
        setShopAddress: (state, shopAddress) => {
            state.shopAddress = shopAddress
        }
    }
}