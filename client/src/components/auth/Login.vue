<template>
    <div class="login-container" v-if="shown">
        <div class="modal-title">Вход</div>
        <div class="fields-wrapper">
            <FieldInput :placeholder="'Email'" :value="user.email ?? ''" :setFieldValue="(event) => handleForm.setEmail(event.target, user)"/>
            <FieldInput :placeholder="'Пароль'" :type="'password'" :value="user.password ?? ''" :setFieldValue="(event) => handleForm.setFieldValue(event.target, user)"/>
        </div>
        <div class="modal-submit" @click="handleLogin()">Войти</div>
        <div class="switch-modal-button" @click="showRegistration()">Регистрация</div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import handleForm from '@/utils/handleForm'
import FieldInput from '../form/FieldInput.vue'
import {useStore} from "vuex"
import { useRouter } from 'vue-router'
import {ref} from 'vue'

export default defineComponent({
    name: 'Login',
    components: {
        FieldInput
    },
    props: {
        shown: {
            default: () => {}
        }
    },
    setup() {
        const store = useStore()
        const router = useRouter()

        let user = ref({})


        let handleLogin = () => {
            api.login(user.value.email, user.value.password)
            .then(res => {
                if(res.status === 200){
                    store.commit('setShownAuthModal', 'hide')
                    router.go()
                }
            })
        }

        let showRegistration = () => store.commit('setShownAuthModal', 'registration')

        return {
            user,
            handleForm,
            handleLogin,
            showRegistration
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
.modal-title{
    text-align: center;
    font-size: 24px;
    line-height: 34px;
    color: #273347;
    margin-bottom: 15px;
}
.login-container .fields-group__item{
    margin-bottom: 28px;
}

</style>