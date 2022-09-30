<template>
    <div class="orders-history">
        <OrderSummary v-for="order in orders.reverse()" :key="order.id" :products="order.orderItems" :status="order.status" :payment="order.payment" :total="order.total" />
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import {computed} from 'vue'
import {useStore} from "vuex"
import OrderSummary from '../order/OrderSummary.vue'

export default defineComponent({
    name: 'OrdersHistory',
    components: {
        OrderSummary
    },
    setup() {
        const store = useStore()     
        
        api.getOrders()
        let orders = computed(() => store.getters.geOrders())

        return {
            orders
        }
    }
})
</script>

<style>
.orders-history{
    width: 100%;
    box-sizing: border-box;
}
.orders-history .order-success__summary{
    display: flex;
    width: 100%;
    justify-content: center;
}
.orders-history .order-success__summary:not(:last-child){
    padding-bottom: 30px;
}
.orders-history .order-success__summary .order-summary{
    flex-grow: 1;
}
.orders-history .order-success__summary .order-success__payment-status{
    max-height: 65px;
    margin-top: 0;
    margin-left: 40px;
}
</style>
