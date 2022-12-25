export default {
    state: {
        cartProducts: [],
        cartCount: 0,
        cartTotal: 0,
        cartModalShown: false
    },
    getters: {
        getCartProducts: (state) => () => {
            return state.cartProducts
        },
        checkCartProduct: (state) => (article) => {
            return state.cartProducts.find(product => product.article === article)
        },
        getCartCount: (state) => () => {
            return state.cartCount
        },
        getCartTotal: (state) => () => {
            return state.cartProducts.reduce((total, cart) => total += cart.price * cart.count, 0)
        },
        getCartModalShown: (state) => () => {
            return state.cartModalShown
        }
    },
    mutations: {
        setCartProducts: (state, products) => {
            state.cartProducts = products.sort((a, b) => a.id > b.id ? 1 : -1)
        },
        setCartProduct: (state, product) => {
            state.cartProducts.push(product)
        },
        removeCartProduct: (state, article) => {
            state.cartCount--
            state.cartProducts = state.cartProducts.filter(product => product.article !== article)
        },
        setCartModalShown: (state, shown) => {
            state.cartModalShown = shown
        },
        setCartCount: (state, count) => {
            state.cartCount = count
        },
        setCartTotal: (state, total) => {
            state.cartTotal = total
        },
        setCart: (state, cart) => {
            state.cartProducts = cart.cartItems
            state.cartCount = cart.count
            state.cartTotal = cart.total
        },
        clearCart: (state) => {
            state.cartProducts = []
            state.cartCount = 0
            state.cartTotal = 0
            state.cartModalShown = false
        }
    }
}