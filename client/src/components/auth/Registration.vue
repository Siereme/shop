<template>
    <div class="registration-container" v-if="shown">
        <div class="modal-title">Регистрация</div>
        <StepUserInfo :user="user" :shownPassword="true" />
        <div class="modal-submit" @click="handleRegistration()">Зарегестрироваться</div>
        <div class="switch-modal-button" @click="showLogin()">Войти</div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import StepUserInfo from '../form/StepUserInfo.vue'
import {useStore} from "vuex"
import {ref} from 'vue'

export default defineComponent({
    name: 'Registration',
    components: {
        StepUserInfo
    },
    props: {
        shown: {
            default: () => {}
        }
    },
    setup() {
        const store = useStore()

        let showLogin = () => store.commit('setShownAuthModal', 'login')

        let user = ref({})

        let handleRegistration = () => {
            api.createUser(user.value)
            .then(res => {
                if(res.status === 200){
                    store.commit('setShownAuthModal', 'login')
                }
            })
        }

        return {
            user,
            showLogin,
            handleRegistration
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
.registration-container .checkout__step-body{
    margin: 0;
}
.registration-container .fields-group{
    flex-wrap: wrap;
    margin: 0;
}
.registration-container .fields-group:not(:last-child){
    margin-bottom: 20px;
}
</style>    