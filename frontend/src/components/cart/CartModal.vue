<template>
    <div class="cart-modal-container" v-if="carts.length">
          <div class="cart-modal">
            <div class="cart-modal-products">
                <CartModalProduct v-for="cart in carts" :key="cart.id" :cart="cart" />
            </div>
            <div class="cart-modal-bottom">
                <div class="cart-modal-total">
                    <span>Итого:</span>
                    <span class="cart-modal_total-price">{{cartTotal}} ₽</span>
                </div>
                <div class="cart-modal-buttons">
                    <div class="cart-modal_order-button" @click.stop="redirectToCheckout()">Оформить заказ</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import CartModalProductVue from '../product/CartModalProduct.vue'
import { useRouter } from 'vue-router'
import {useStore} from "vuex"
import {computed} from 'vue'

export default defineComponent({
    name: 'CartModal',
    components: {
        CartModalProduct: CartModalProductVue
    },
    setup() {
        const store = useStore()
        const router = useRouter()

        let cartTotal = computed(() => store.state.cart.cartTotal)

        let carts = computed(() => store.state.cart.cartProducts)

        let redirectToCheckout = () => {
            store.commit('setCartModalShown', false)
            router.replace({name: 'CheckoutPage'})
        }

        return {
            cartTotal,
            carts,
            redirectToCheckout
        }
    }
})
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #0595e6;
  text-decoration: none;
}
.cart-modal-container{
    position: absolute;
    top: 100%;
    right: 0;
    left: auto;
    padding-top: 15px;
}
.cart-modal{
    overflow: visible;
    width: 420px;
    padding-top: 6px;
    display: flex;
    flex-direction: column;
    z-index: 9996;
    border: 0;
    border-radius: 9px;
    background-color: #fff;
    box-shadow: 0 0 27px -6px rgb(0 0 0 / 20%);
    font-size: 15px;
    line-height: 1.33333;
    padding: 20px;
    box-sizing: border-box;
}
.cart-modal > div{
    width: 100%;
}
.cart-modal-products{
    display: flex;
    flex-direction: column;
    position: relative;
    padding-bottom: 20px;
    border-bottom: 1px solid #ced8e1;
}
.cart-modal-bottom{
    padding-top: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.cart-modal-total span{
    font-size: 16px;
    font-weight: 700;
}
.cart-modal-total .cart-modal_total-price{
    margin-left: 15px;
}
.cart-modal_order-button{
    color: #fff;
    background-color: #0595e6;
    font-size: 14px;
    height: 32px;
    padding: 0 12px;
    font-weight: 700;
    line-height: 1;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out;
    text-align: center;
    vertical-align: middle;
    border-radius: 6px;
}
.cart-modal_order-button:hover{
    background-color: #06a4fd;
}
</style>