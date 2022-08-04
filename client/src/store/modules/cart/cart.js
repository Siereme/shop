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
        },
        setCartProduct: (state, product) => {
            state.cartProducts.push(product)
        },
        removeCartProduct: (state, productId) => {
            state.cartProducts = state.cartProducts.filter(cart => cart.product.id !== productId)
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
        clearCart: (state) => {
            state.cartProducts = []
            state.cartCount = 0
            state.cartTotal = 0
            state.cartModalShown = false
        }
    }
}