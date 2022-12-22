<template>
  <div class="cart-button" :class="{'in-cart': isCartProduct}" @click="addToCart($event)">
    <span class="to-cart">В корзину</span>
    <span class="in-cart">В корзине</span>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import {useStore} from "vuex"
import {computed} from 'vue'

export default defineComponent({
    name: 'CartButton',
    props: {
        productId: {
            default: () => {}
        }
    },
    setup(props) {
      const store = useStore();

      let isCartProduct = computed(() => checkCartProduct())

      let checkCartProduct = () => store.getters.checkCartProduct(props.productId)

      let addToCart = (event) => {
        var button = event.target.closest('.cart-button')
        if(button && !button.classList.contains('in-cart')){
          button.classList.add('in-cart')
          api.addCartProduct(props.productId, 1)
        }
      }

      return {
        isCartProduct,
        checkCartProduct,
        addToCart
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
.cart-button{
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    max-width: 145px;
    font-size: 15px;
    font-weight: 700;
    width: 100%;
    padding: 6px;
    margin: 0 auto;
    color: #fff;
    border-color: #0595e6;
    background-color: #0595e6;
    border-radius: 6px;
    cursor: pointer;
    line-height: 1.4;
    box-sizing: border-box;
    border: 1px solid transparent;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.cart-button.in-cart{
    color: #353d4a;
    border-color: #0595e6;
    background-color: transparent;
}
.cart-button:not(.in-cart):hover{
  color: #fff;
  border-color: #06a4fd;
  background-color: #06a4fd;
}
.cart-button.in-cart:hover{
    color: #06a4fd;
}
.cart-button span{
  display: none;
}
.cart-button:not(.in-cart) span.to-cart{
  display: block;
}
.cart-button.in-cart span.in-cart{
  display: block;
}
</style>