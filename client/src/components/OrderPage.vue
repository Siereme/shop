<template>
    <div class="order-success" v-if="order">
        <div class="order-success-main-container">
            <div class="order-success__confirm">
                <div class="order-success__tatus-title">Готово!</div>
                <div class="order-success__status-num">Заказ №{{order.id}}</div>
                <div class="order-success__status-description">Ваш заказ успешно оформлен и принят в обработку.</div>
                <div class="order-success__status-contacts">{{order.userDetails.phone}}, {{order.userDetails.email}}</div>
            </div>
            <OrderSummary :products="order.orderItems" :payment="order.payment" :total="order.total" />
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import {computed} from 'vue'
import {useStore} from "vuex"
import OrderSummary from './order/OrderSummary.vue'

export default defineComponent({
    name: 'OrderPage',
    components: {
    OrderSummary
},
    setup() {
        const store = useStore()     
        
        store.commit('setIsLoading', false)

        let order = computed(() => store.getters.getLastOrder())

        return {
            order
        }
    }
})
</script>

<style>
.checkout-main{
    padding: 0 30px;
}
h1 {
    color: #273347;
    line-height: 32px;
    font-size: 28px;
}
.order-success{
    padding: 40px 30px 80px;
}
.order-success-main-container{
    display: flex;
    justify-content: space-between;
}
.order-success__confirm{
    display: flex;
    flex-grow: 1;
    margin-right: 100px;
    max-height: 150px;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    padding: 25px;
    border-radius: 9px;
    background: #fff;
    box-shadow: 0 0 27px -6px rgb(0 0 0 / 20%);
    font-size: 15px;
    line-height: 1.26667;
}
.order-success__tatus-title{
    font-size: 32px;
    font-weight: 700;
    line-height: 1.28571;
    margin-bottom: 20px;
    color: #0595e6;
}
.order-success__status-num{
    font-size: 18px;
    font-weight: 700;
    margin-bottom: 10px;
    color: #2a62a0;
}
.order-success__status-description{
    font-size: 16px;
}
.order-success__status-contacts{
    font-weight: 700;
    margin-top: 10px;
}
.order-success__summary{
    width: 365px;
}
.order-success__payment-status{
    border-radius: 9px;
    background: #fff;
    padding: 25px;
    margin-top: 10px;
    color: #7d858c;
    box-shadow: 0 0 27px -6px rgb(0 0 0 / 20%);
}
.order-success__payment-status-header{
    text-align: left;
    font-size: 16px;
    font-weight: 700;
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    color: #273347;
}
.order-success__payment-status-header svg{
    margin-left: 10px;
    color: #36d146;
}
.order-success__payment-status-method{
    color: #7d858c;
    font-size: 14px;
    text-align: left;
}
</style>
