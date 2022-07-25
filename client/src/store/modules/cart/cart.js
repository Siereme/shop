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
        checkCartProduct: (state) => (productId) => {
            return state.cartProducts.find(cartItem => cartItem.product.id === productId)
        },
        getCartCount: (state) => () => {
            return state.cartCount
        },
        getCartTotal: (state) => () => {
            return state.cartProducts.reduce((total, cart) => total += cart.product.price * cart.count, 0)
        },
        getCartModalShown: (state) => () => {
            return state.cartModalShown
        }
    },
    mutations: {
        setCartProducts: (state, products) => {
            state.cartProducts = products.sort((a, b) => a.id > b.id ? 1 : -1)
            state.cartCount = state.cartProducts.length
            state.cartTotal = state.cartProducts.reduce((total, cart) => total += cart.product.price * cart.count, 0)
        },
        setCartProduct: (state, product) => {
            state.cartProducts.push(product)
            state.cartCount = state.cartProducts.length
            state.cartTotal = state.cartProducts.reduce((total, cart) => total += cart.product.price * cart.count, 0)
        },
        removeCartProduct: (state, productId) => {
            state.cartProducts = state.cartProducts.filter(cart => cart.product.id !== productId)
            state.cartCount = state.cartProducts.length
            state.cartTotal = state.cartProducts.reduce((total, cart) => total += cart.product.price * cart.count, 0)
        },
        setCartModalShown: (state, shown) => {
            state.cartModalShown = shown
        },
    }
}