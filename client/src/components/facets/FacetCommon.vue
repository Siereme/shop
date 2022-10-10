<template>
    <div class="facet-common">
      <div class="facet-header">
        <div class="facet-header__title">{{option.type}}</div>
      </div>
      <ul class="facet-common__container">
        <li class="facet-common__item" v-for="item in option.values" :key="item.value + Date.now()">
          <input type="checkbox" 
            :id="item.value" :value="item.value" 
            :checked="item.checked" v-model="checkedValues" 
            @click="handleOptionClick($event, option.id, item.value)"
          >
          <label :for="item.value">
            <span class="facet-common__checkmark"></span>
            <span class="facet-common__title">{{ item.value }}</span>
          </label>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { defineComponent, ref } from 'vue'
  import { useStore } from "vuex"
  
  export default defineComponent({
      name: 'FacetCommon',
      props: {
        option: Object,
        handleClick: Function
      },
      setup(props) {
        const store = useStore()

        let checkedValues = ref([])

        let handleOptionClick = (event, id, value) => {
          store.commit('setOption', {id: id, value: value, checked: event.target.checked})
          props.handleClick()
        }

        return {
          checkedValues,
          handleOptionClick
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
  .facet-common:not(:last-child){
    margin-bottom: 20px;
  }
  .facet-header{
    display: -webkit-flex;
    display: flex;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    -webkit-align-items: center;
    align-items: center;
    margin-bottom: 15px;
  }
  .facet-header__title{
    text-align: left;
    font-size: 16px;
    line-height: 18px;
  }
  .facet-common ul{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: flex-start;
  }
  .facet-common .facet-common__item{
    width: 100%;
    margin-bottom: 16px;
  }
  .facet-common .facet-common__item label{
    display: flex;
    -webkit-align-items: center;
    align-items: center;
    width: 100%;
    cursor: pointer;
  }
  .facet-common .facet-common__item input[type="checkbox"]{
    display: none;
  }
  .facet-common .facet-common__item .facet-common__checkmark{
    position: relative;
    display: flex;
    -webkit-flex: 1 0 20px;
    flex: 1 0 20px;
    width: 20px;
    height: 20px;
    margin-right: 10px;
    border: 1px solid #e6e6ee;
    border-radius: 3px;
  }
  .facet-common .facet-common__item input[type="checkbox"]:checked ~ label .facet-common__checkmark{
    border-color: #0595e6;
  }
  .facet-common .facet-common__item input[type="checkbox"]:checked ~ label .facet-common__checkmark::after{
    content: "";
    position: absolute;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%);
    display: block;
    width: 20px;
    height: 20px;
    background-repeat: no-repeat;
    background-size: auto;
    background-position: 50%;
    background-color: #fff;
    box-sizing: border-box;
    background-image: url("data:image/svg+xml;charset=utf8,%3Csvg width='10' height='9' viewBox='0 0 10 9' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M10 1.85145L4.69623 7.8216C4.35287 8.2081 3.7715 8.26916 3.35533 7.96243L0 5.48946L1.12661 3.86199L3.82056 5.84751L8.57118 0.5L10 1.85145Z' fill='%23608FF6'/%3E%3C/svg%3E");
  }
  .facet-common .facet-common__item .facet-common__title{
    flex-grow: 1;
    width: 100%;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    text-align: left;
    font-size: 15px;
  }
  .facet-common .facet-common__item label:hover .facet-common__title{
    color: #0595e6;
  }
  </style>