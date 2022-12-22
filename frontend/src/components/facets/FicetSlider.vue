<template>
    <div class="facet-slider">
      <div class="facet-header">
        <div class="facet-header__title">Цена</div>
        <button class="facet-header__clear" v-if="showClear" @click="handleClear()">Очистить</button>
      </div>
      <div class="facet-slider-container">
        <div class="facet-slider__fields">
          <div class="facet-slider__field">
            <span class="facet-slider__label">от</span>
            <input class="facet-slider__input" type="tel" :value="price[0]" />
          </div>
          <div class="facet-slider__separator"></div>
          <div class="facet-slider__field">
            <span class="facet-slider__label">до</span>
            <input class="facet-slider__input" type="tel" :value="price[1]" />
          </div>
        </div>
        <Slider class="facet-slider__carousel" v-model="price" :min="rangeMin" :max="rangeMax"/>
      </div>
    </div>
  </template>
  
  <script>
  import { defineComponent, ref, computed, onMounted, watch } from 'vue'
  import Slider from '@vueform/slider'
  import "@vueform/slider/themes/default.css"
  import { useStore } from 'vuex'

  export default defineComponent({
      name: 'FacetSlider',
      components: {
        Slider
      },
      props: {
        handleClick: Function
      },
      setup(props) {
        const store = useStore()

        let price = ref([0, 100])
        let rangeMin = ref(0)
        let rangeMax = ref(1000)
        
        let setrangePrice = (rangePrice) => {
          price.value[0] = rangePrice.priceMin
          price.value[1] = rangePrice.priceMax
          
          rangeMin.value = rangePrice.rangeMin
          rangeMax.value = rangePrice.rangeMax

          store.commit('setPrice', {rangeMin: rangeMin.value, rangeMax: rangeMax.value, priceMin: price.value[0], priceMax: price.value[1]})
        }

        let handlePriceChange = () => {
          store.commit('setPrice', {rangeMin: rangeMin.value, rangeMax: rangeMax.value, priceMin: price.value[0], priceMax: price.value[1]})
          props.handleClick()
        }

        let handleClear = () => {
          store.commit('setPrice', null)
          props.handleClick()
        }

        let showClear = computed(() => price.value[0] !== rangeMin.value || price.value[1] !== rangeMax.value)


        onMounted(() => setrangePrice(store.state.facet.rangePrice))
        watch(
            () => store.state.facet.rangePrice,
            rangePrice => setrangePrice(rangePrice)
        )
        watch(
            () => price.value,
            () => handlePriceChange()
        )
        
        return {
          price,
          rangeMin,
          rangeMax,
          showClear,
          handleClear
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
    margin: 0;
  }
  li {
    display: inline-block;
    margin: 0;
  }
  a {
    color: #0595e6;
  }
  .facet-header{
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .facet-header__title{
    text-align: left;
    font-size: 16px;
    line-height: 18px;
  }
  .facet-header__clear{
    font-size: 12px;
    line-height: 13px;
    color: #AAAAAA;
    background: none;
    border: none;
    outline: none;
    cursor: pointer;
  }
  .facet-header__clear:hover{
    color: #0595e6;
  }
  .facet-slider__carousel{
    --slider-connect-bg: #0595e6;
    --slider-tooltip-bg: transparent;
    --slider-handle-ring-color: #3B82F630;
  }
  .facet-slider {
    border: 1px solid #b8ced9;
    border-radius: 15px;
    padding: 20px 15px;
    margin-bottom: 30px;
  }
  .facet-slider__fields{
    display: -webkit-flex;
    display: flex;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    -webkit-align-items: center;
    align-items: center;
    margin-bottom: 20px;
  }
  .facet-slider__field {
    position: relative;
    -webkit-flex-grow: 1;
    flex-grow: 1;
  }
  .facet-slider__label{
    position: absolute;
    top: 50%;
    left: 10px;
    -webkit-transform: translateY(-50%);
    transform: translateY(-50%);
    color: var(--color-font-second);
    font-size: 12px;
    line-height: 15px;
  }
  .facet-slider__input{
    height: 36px;
    font-size: 16px;
    line-height: 16px;
    width: 100%;
    padding: 0 15px 0 30px;
    border: 1px solid #D5D5D5;
    border-radius: 2px;
    color: #353d4a;
    font-weight: 400;
    box-sizing: border-box;
  }
  .facet-slider__separator{
    display: block;
    -webkit-flex: 1 1 15px;
    flex: 1 1 15px;
    margin: 0 10px;
    height: 1px;
    background-color: #D5D5D5;
  }
  </style>