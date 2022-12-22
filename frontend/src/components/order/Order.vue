<template>
    <div class="order-summary" v-if="products.length">
        <div class="order-summary-container">
            <div class="order-summary__cart">
                <CartModalProduct v-for="product in products" :key="product.id" :cart="product" />
            </div>
            <div class="order-summary__info">
                <div class="order-summary__info-row">
                    <span>{{products.length}} товара</span>
                    <span><b>{{total}} ₽</b></span>
                </div>
                <div class="order-summary__info-row-name">
                    <span><b>Способ оплаты:</b></span>
                    <span>{{ payment.description }}</span>
                </div>
            </div>
        </div>
        <div class="order-summary__total">
            <div class="order-summary__total-name">Итого</div>
            <div class="order-summary__total-value">{{total}} ₽</div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import CartModalProductVue from "@/components/product/CartModalProduct.vue"
// import {useStore} from "vuex"
// import {computed} from 'vue'

export default defineComponent({
    name: 'Order',
    components: {
        CartModalProduct: CartModalProductVue
    },
    props: {
        products: {
            default: () => {}
        },
        payment: {
            default: () => {}
        },
        total: {
            default: () => {}
        }
    }
})
</script>

<style>
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
.order-summary{
    border-radius: 9px;
    background: #fff;
    padding: 25px;
    color: #7d858c;
    box-shadow: 0 0 27px -6px rgb(0 0 0 / 20%);
}
.order-summary__cart{
    max-height: 192px;
    overflow-y: auto;
    padding-right: 15px;
    margin-bottom: 15px;
}
.order-summary__cart::-webkit-scrollbar{
    width: 4px;
}
.order-summary__cart::-webkit-scrollbar-thumb{
    border-radius: 4px;
    background-color: #cccccc;
}
.order-summary__cart .cart-carousel{
    display: none;
}
.order-summary__info-row,
.order-summary__info-row-name,
.order-summary__total{
    display: flex;
    justify-content: space-between;
    margin-bottom: 8px;
}
.order-summary__info-row-name{
    justify-content: flex-start;
}
.order-summary__info-row span,
.order-summary__info-row-name span,
.order-summary__total-name,
.order-summary__total-value{
    font-size: 12px;
    line-height: 14px;
    color: #464b51;
    font-weight: 400;

}
.order-summary__info-row span b{
    font-weight: 700;
}
.order-summary__info-row-name span:last-child{
    color: #0595e6;
    margin-left: 5px;
}
.order-summary__total{
    margin-top: 18px;
    padding-top: 18px;
    border-top: 1px solid #ced8e1;
}
.order-summary__total-value{
    font-size: 20px;
    font-weight: 700;
}
</style>