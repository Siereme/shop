import { createStore } from "vuex";
import main from "./modules/main/main.js"
import user from './modules/user/user.js'
import product from './modules/product/product.js'
import category from './modules/category/category.js'
import cart from './modules/cart/cart.js'
import order from "./modules/order/order.js";

export default createStore({
  modules: {
    main,
    user,
    product,
    category,
    cart,
    order
  }
})
