<template>
    <div class="order-receipt">
        <OrderReceiptGroups ref="receiptGroupsWrapper" :shownError="shownError" :handleClick="(type) => {handleSelect(type); this.handleError();}"/>
        <Pickup ref="pickupWrapper" v-if="shown === 'Самовывоз'" :errorMessages="errorMessages"/>
        <Delivery ref="deliveryWrapper" v-if="shown === 'Доставка'" :errorMessages="errorMessages"/>
    </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import OrderReceiptGroups from './OrderReceiptGroups.vue'
import Delivery from './delivery/Delivery.vue'
import Pickup from './Pickup.vue'
export default defineComponent({
    name: 'OrderReceipt',
    components: {
      OrderReceiptGroups,
      Delivery,
      Pickup
  },
    props: {
      errorMessages: Array,
      shownError: Boolean,
      handleError: Function
    },
    setup(){      
      let shown = ref(false)
      let receiptGroupsWrapper = ref(null)
      let deliveryWrapper = ref(null)
      let pickupWrapper = ref(null)
      
      let handleSelect = type => shown.value = type

      let getAddress = () => shown.value === 'Самовывоз' 
            ? pickupWrapper.value ? pickupWrapper.value.getShopAddress() : {} 
            : deliveryWrapper.value ? deliveryWrapper.value.getAddress() : {}

      let getDate = () => shown.value === 'Самовывоз' 
            ? pickupWrapper.value ? pickupWrapper.value.getDate() : ''
            : deliveryWrapper.value ? deliveryWrapper.value.getDate() : ''

      let getReceiptDetail = () => ({
        'receipt': Object.assign({}, receiptGroupsWrapper.value.getReceipt()),
        'address': getAddress(),
        'date': getDate()
      })

      return{
        shown,
        receiptGroupsWrapper,
        deliveryWrapper,
        pickupWrapper,
        handleSelect,
        getReceiptDetail
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
</style>