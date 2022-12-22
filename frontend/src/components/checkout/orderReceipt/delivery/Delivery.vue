<template>
    <div class="delivery-info">
        <DeliveryAddress ref="addressWrapper" :errorMessages="errorMessages"/>
        <DeliveryDate  ref="dateWrapper" :errorMessages="errorMessages"/>
    </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import DeliveryAddress from './DeliveryAddress.vue'
import DeliveryDate from './DeliveryDate.vue'
export default defineComponent({
    name: 'Delivery',
    components: {
        DeliveryAddress,
        DeliveryDate
    },
    props: {
        errorMessages: Array
    },
    setup() {
      let addressWrapper = ref(null)
      let dateWrapper = ref(null)

      let getAddress = () => addressWrapper.value.getAddress()
      let getDate = () => dateWrapper.value.getDate()

      return {
        addressWrapper,
        dateWrapper,
        getAddress,
        getDate
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