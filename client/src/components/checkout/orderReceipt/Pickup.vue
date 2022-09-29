<template>
    <div class="pickup-info">
        <select class="pickup-select" ref="pickupSelectWrapper" v-model="selected" @click="handleClick(false)">
            <option disabled value="">Выберите магазин</option>
            <option v-for="address in shopAddress" :value="address" :key="address.id">
            {{ Object.values(address).slice(1).join(', ') }}
            </option>
        </select>
        <DeliveryDate  ref="pickupDateWrapper" :errorMessages="errorMessages"/>
    </div>
</template>

<script>
import { defineComponent, computed, ref, watch } from 'vue'
import api from "@/api/backend-api"
import {useStore} from "vuex"
import DeliveryDate from './delivery/DeliveryDate.vue'
export default defineComponent({
    name: 'Pickup',
    components: {
        DeliveryDate
    },
    props: {
        errorMessages: Array
    },
    setup(props) {
        const store = useStore()    
        
        api.getShopAddress()

        let shopAddress = computed(() => store.getters.getShopAddress())

        let selected = ref('')
        let pickupDateWrapper = ref(null)
        let pickupSelectWrapper = ref(null)

        let getShopAddress = () => Object.assign({}, selected.value)
        let getDate = () => pickupDateWrapper.value.getDate()

        watch(
            () => props.errorMessages,
            massages => handleValidation(massages)
        )

        let handleValidation = massages => {
            if(Object.keys(massages).some(field => field.includes('receiptDetail.'))){
              pickupSelectWrapper.value.classList.add('unchecked')
              pickupSelectWrapper.value.value = ''
            }
        }

        let handleClick = flag => {
          if(flag) pickupSelectWrapper.value.classList.add('unchecked')
          else pickupSelectWrapper.value.classList.remove('unchecked')
        }

        return {
            shopAddress,
            selected,
            pickupDateWrapper,
            pickupSelectWrapper,
            handleClick,
            getShopAddress,
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
.pickup-info{
  padding: 20px 5px 5px;
}
.pickup-select {
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
  background: white;
}
.pickup-select.unchecked{
  border-color: red;
}
</style>