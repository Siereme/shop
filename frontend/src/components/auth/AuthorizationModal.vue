<template>
    <div class="authorization-modal" v-if="shown">
        <div class="authorization-modal__overlay" @click="hideAuthModal()"></div>
        <div class="authorization-modal__container">
            <div class="authorization-modal__content">
                <CloseIcon @click="hideAuthModal()"/>
                <Login :shown="shownType === 'login'" />
                <Registration  :shown="shownType === 'registration'" />
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import Login from './Login.vue'
import Registration from './Registration.vue'
import CloseIcon from '../form/CloseIcon.vue'
import {useStore} from "vuex"
import {computed} from 'vue'

export default defineComponent({
    name: 'AuthorizationModal',
    components: {
        Login,
        Registration,
        CloseIcon
    },
    setup() {
        let store = useStore()

        let shownType = computed(() => store.getters.getShownAuthModal())

        let shown = computed(() => {
            return store.getters.getShownAuthModal() !== 'hide'
        })

        let hideAuthModal = () => store.commit('setShownAuthModal', 'hide')

        return {
            shown,
            shownType,
            hideAuthModal
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
  color: #0595e6;
  text-decoration: none;
}
.authorization-modal{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 9995;
    overflow-x: hidden;
    overflow-y: auto;
}
.authorization-modal__overlay{
    position: fixed;
    left: 0;
    top: 0;
    opacity: .6;
    width: 100%;
    height: 100%;
    background-color: #000;
}
.authorization-modal__container{
    min-height: 100%;
    max-width: 460px;
    padding: 20px 0;
    margin: 0 auto;
    display: flex;
    align-items: center;
}
.authorization-modal__content{
    border: none;
    border-radius: 9px;
    box-shadow: none;
    padding: 60px 42px;
    position: relative;
    display: flex;
    flex-direction: column;
    width: 100%;
    pointer-events: auto;
    background-color: #fff;
    background-clip: padding-box;
}
.modal-submit{
    font-size: 17px;
    line-height: 21px;
    height: auto;
    padding: 13px 25px;
    color: #fff;
    border-color: #0595e6;
    background-color: #0595e6;
    cursor: pointer;
    userDTO-select: none;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 6px;
    margin: 16px 8px 0;
}
.switch-modal-button{
    color: #0595e6;
    text-decoration: none;
    background-color: transparent;
    cursor: pointer;
    font-size: 13px;
    text-align: center;
    margin: 25px 8px 0;
}
</style>