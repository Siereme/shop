<template>
    <div class="order-receipt__groups" :class="{'unchecked': shownError}">
        <div class="order-receipt__groups-item" v-for="item in receipts" :key="item.id" @click="this.handleClick(item.type)">
            <input v-model="receipt" type="radio" :id="'receipt_' + item.id" class="field-radio__input" :value="item">
            <label :for="'receipt_' + item.id" class="field-radio__label">
                <div class="field-radio__label-content-heading">
                    {{ item.type }}
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
    name: 'OrderReceiptGroups',
    props: {
        handleClick: Function,
        shownError: Boolean
    },
    setup() {
        const store = useStore()   

        api.getReceipts()

        let receipts = computed(() => store.state.order.receipts)
        let receipt = ref(null)

        let getReceipt = () => receipt.value

        return {
            receipts,
            receipt,
            getReceipt
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
.order-receipt__groups{
    display: flex;
}
.order-receipt__groups.unchecked .field-radio__label{
    border-color: red;
}
.order-receipt__groups-item{
    min-width: 200px;
    min-height: 70px;
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