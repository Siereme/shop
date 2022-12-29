<template>
    <div class="login-container" v-if="shown">
        <div class="modal-title">Вход</div>
        <div class="fields-wrapper" ref="fields">
            <FieldInput :placeholder="'Email'" 
                :type="'email'" :value="userDTO.email ?? ''"
                :setFieldValue="(event) => handleForm.setEmail(event.target, userDTO)"
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
            <FieldInput :placeholder="'Пароль'" 
                :type="'password'" :value="userDTO.password ?? ''"
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, userDTO)"
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
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

        let userDTO = ref({})
        let messages = ref({})
        let fields = ref({})

        let handleLogin = () => {
            api.login(userDTO.value.email, userDTO.value.password)
            .then(res => {
                if(res.status === 200){
                    store.commit('setShownAuthModal', 'hide')
                    router.push("/")
                }
            }).catch(res => handleValidation(res.response.data))
        }

        let handleValidation = massages => {
            let fieldItems = Array.from(fields.value.children)
            .flatMap(item => Array.from(item.children).map(childrenItem => childrenItem))
            
            Object.keys(massages).forEach(field => {
                let currentField = fieldItems.find(item => field.includes(item.dataset.type))
                if(currentField){
                    currentField.classList.add('unvalid')
                    currentField.value = ''
                    currentField.placeholder = massages[field]
                }
            })
        }
        
        let showRegistration = () => store.commit('setShownAuthModal', 'registration')

        return {
            userDTO,
            handleForm,
            messages,
            fields,
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