<template>
    <div class="checkout__payments" :class="{'unchecked': shownError}">
        <div class="checkout__payments-item" v-for="item in payments" :key="item.id" @click="this.handleError">
            <input v-model="payment" type="radio" :id="'payment_' + item.id" class="field-radio__input" :value="item">
            <label :for="'payment_' + item.id" class="field-radio__label">
                <div class="field-radio__label-content-heading">
                    {{ item.type }}
                </div>
                <div class="field-radio__label-content-description">
                    {{ item.description }}
                </div>
                <div class="field-radio__label-content-icons">
                    
                </div>
            </label>
        </div>
    </div>
</template>

<script>
import { defineComponent, ref, computed } from 'vue'
import api from "@/api/backend-api"
import {useStore} from "vuex"

export default defineComponent({
    name: 'Payments',
    props: {
        shownError: Boolean,
        handleError: Function
    },
    setup() {
        const store = useStore()   

        api.getPayments()

        let payments = computed(() => store.state.order.payments)
        let payment = ref(null)

        let getPayment = () => Object.assign({}, payment.value)
    

        return {
            payments,
            payment,
            getPayment,
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
  text-decoration: none;
}
.checkout__payments {
    display: flex;
}
.checkout__payments.unchecked .field-radio__label{
    border-color: red;
}
.checkout__payments-item{
    min-width: 200px;
    min-height: 90px;
    width: fit-content;
    display: flex;
    flex: 0 0 auto;
    padding: 5px;
    box-sizing: border-box;
}
.field-radio__label{
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    width: 100%;
    cursor: pointer;
    color: #7d858c;
    border-color: #eee;
    font-weight: 400;
    padding: 13px;
    border: 1px solid #ced8e1;
    border-radius: 6px;
    transition: all .15s ease-in-out;
}
.field-radio__label div{
    color: #7d858c;
}
.field-radio__label .field-radio__label-content-heading{
    font-size: 15px;
    line-height: 18px;
}
.field-radio__label .field-radio__label-content-description{
    font-weight: 400;
    font-size: 12px;
    line-height: 16px;
    margin-top: 2px;
}
.field-radio__input{
    position: absolute;
    width: 1px;
    height: 1px;
    opacity: 0;
}
.field-radio__input:checked ~ .field-radio__label{
    border-color: #06a4fd;
}
.field-radio__input:checked ~ .field-radio__label div{
    color: #353d4a;
    font-weight: 600;
}
.field-radio__input:checked ~ .field-radio__label .field-radio__label-content-heading{
    font-size: 14px;
}
.field-radio__input:checked ~ .field-radio__label .field-radio__label-content-description{
    font-size: 11px;
}
</style>