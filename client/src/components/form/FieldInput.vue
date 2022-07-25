<template>
    <div class="fields-group__item">
        <input type="tel" class="field-control__label" :disabled="isDisabled" :placeholder="placeholder" :data-type="type" :value="value" @change="this.setFieldValue">
    </div>
</template>

<script>
import { defineComponent } from 'vue'
// import {useStore} from "vuex"
// import {computed} from 'vue'

export default defineComponent({
    name: 'Registration',
    props: {
        isDisabled: {
            default: () => {}
        },
        placeholder: {
            default: () => {}
        },
        type: {
            default: () => {}
        },
        value: {
            default: () => {}
        },
        setFieldValue: { type: Function },
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
.fields-group__item{
    flex: 1 1 auto;
    padding: 0 8px;
    margin-bottom: 20px;
}
.fields-group__item:last-child{
    margin-bottom: 0;
}
.fields-group__item input{
    font-size: 15px;
    line-height: 1;
    display: block;
    width: 100%;
    height: 46px;
    padding: 0 16px;
    transition: border-color .15s ease-in-out;
    color: #464b51;
    border: 1px solid #ced8e1;
    border-radius: 6px;
    outline: 0 none;
    background-color: transparent;
    background-repeat: no-repeat;
    box-sizing: border-box;
}
.fields-group__item input::placeholder{
    color: #adadad;
}
</style>