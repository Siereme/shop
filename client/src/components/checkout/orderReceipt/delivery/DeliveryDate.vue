<template>
    <div class="delivery-date" ref="receiptDateWrapper" >
      <h4 class="delivery-address__title">Выберите дату</h4>
      <div class="delivery-date-container">
        <div class="delivery-date__item" v-for="item in days" :key="item" @click="handleClick(false)">
            <input v-model="day" type="radio" :id="'day_' + item" class="field-radio__input" :value="item">
            <label :for="'day_' + item" class="field-radio__label">
                <div class="field-radio__label-content-heading">
                    {{ moment(item).date() }}
                </div>
                <div class="field-radio__label-content-heading">
                    {{ moment(item).locale('ru').format('D MMMM').split(' ')[1] }}
                </div>
            </label>
        </div>
      </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import moment from "moment";
import { ref, watch } from 'vue'

export default defineComponent({
    name: 'DeliveryDate',
    props: {
      errorMessages: Array
    },
    setup(props) {
        let today = moment().date()
        let days = ref([])
        let day = ref(null)
        
        for(var i = 1; i < 8; i++) days.value[i - 1] = moment().date(today + i)
        
        let getDate = () => day.value ? moment(day.value) : ''
        
        watch(
            () => props.errorMessages,
            massages => handleValidation(massages)
            )
            
            
        let receiptDateWrapper = ref(null)

        let handleValidation = massages => Object.keys(massages).some(field => field.includes('receiptDetail.date')) 
                    ? receiptDateWrapper.value.classList.add('unchecked') 
                    : null

        let handleClick = flag => {
          if(flag) receiptDateWrapper.value.classList.add('unchecked')
          else receiptDateWrapper.value.classList.remove('unchecked')
        }

        return { 
            receiptDateWrapper,
            days, 
            day, 
            getDate,
            handleClick,
            moment
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
.delivery-date.unchecked .field-radio__label{
    border-color: red;
}
.delivery-address__title{
    text-align: left;
    font-size: 18px;
    line-height: 1.09091;
    flex: 0 0 auto;
    margin-right: 20px;
    color: #273347;
    font-weight: 400;
}
.delivery-date-container {
  display: flex;
  margin-right: -8px;
  margin-left: -8px;
}
.delivery-date__item{
    min-height: 46px;
    width: fit-content;
    display: flex;
    flex: 1 1 auto;
    padding: 5px;
    box-sizing: border-box;
}
.delivery-date .field-radio__label{
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
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
.field-radio__label .field-radio__label-content-heading:first-child{
    margin-bottom: 2px;
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