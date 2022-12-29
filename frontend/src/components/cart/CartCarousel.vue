<template>
  <div class="cart-carousel">
    <div class="cart-carousel_container" @click.stop="handleCart(-1)">
        <div class="cart-carousel_decrement" :class="{'cart-carousel_button-disable': productQuantity <= 1}">-</div>
        <input type="text" class="cart-carousel_field" :value="productQuantity" @click.stop="" @input.stop="handleFieldInput($event)">
        <div class="cart-carousel_increment" @click.stop="handleCart(1)">+</div>
    </div>
    <div class="cart-carousel_delete" @click="deleteProduct()">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#ced8e1" class="bi bi-trash" viewBox="0 0 16 16">
            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
        </svg>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import {useStore} from "vuex"
import {ref} from 'vue'

export default defineComponent({
    name: 'CartCarousel',
    props: {
      product: {
            default: () => {}
      }
    },
    setup(props) {
      const store = useStore();
      
      let productQuantity = ref(props.product.quantity)

      let handleCart = (quantity) => {
          if(+productQuantity.value + +quantity >= 1){
              productQuantity.value = +productQuantity.value + +quantity
              api.addCartProduct(props.product.article, productQuantity.value)
          }
      }

      let handleFieldInput = (event) => {
        var quantity = event.target.value
        if(!isNaN(quantity) && +quantity >= 1){
            productQuantity.value = quantity
            api.addCartProduct(props.product.article, +quantity)
        } else {
            productQuantity.value = 1
            api.addCartProduct(props.product.article, 1)
        }
      }

      let deleteProduct = () => {
        store.commit('removeCartProduct', props.product.article)
        store.commit('setCartModalShown', store.getters.getCartCount() > 0)
        api.removeCartProduct(props.product.article)
      }

      return {
        productQuantity,
        handleCart,
        handleFieldInput,
        deleteProduct
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
.cart-carousel{
    width: 130px;
    height: 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-left: 10px;
    cursor: default;
}
.cart-carousel_container{
    display: flex;
    box-sizing: border-box;
    height: 30px;
    border: 1px solid #ced8e1;
    border-radius: 4px;
    max-width: calc(100% - 30px);
}
.cart-carousel_decrement,
.cart-carousel_increment{
    font-family: monospace;
    font-size: 18px;
    font-weight: 700;
    line-height: 1;
    display: flex;
    align-items: center;
    flex: 0 0 auto;
    justify-content: center;
    box-sizing: border-box!important;
    width: 30px;
    height: 30px;
    margin: -1px;
    padding: 0 0 2px;
    cursor: pointer;
    color: #353d4a;
    border: 1px solid #ced8e1;
    border-radius: 4px;
    background-color: transparent;
    userDTO-select: none;
}
.cart-carousel_field{
    flex: 1 1 auto;
    max-width: calc(100% - 60px);
    font-size: 14px;
    font-weight: 700;
    line-height: 1.28571;
    height: 100%;
    text-align: center;
    border: 0;
    outline: 0 none;
    background: 0 0;
}
.cart-carousel_delete{
    display: block;
    width: 20px;
    height: 20px;
    margin-left: 10px;
    cursor: pointer;
}
</style>