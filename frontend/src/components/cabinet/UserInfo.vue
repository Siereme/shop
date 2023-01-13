<template>
    <div class="userDTO-info">
        <StepUserInfo :userDTO="userForm" :isDisabled="isDisabled" :errorMessages="messages" />
        <button class="userDTO-info__edit-button" v-if="isDisabled" @click="handleDisabled(false)">Редактировать</button>
        <button class="userDTO-info__save-button" v-if="!isDisabled" @click="saveUser()">Сохранить</button>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import { ref } from 'vue'
import {useStore} from "vuex"
import StepUserInfo from '../form/StepUserInfo.vue'

export default defineComponent({
    name: 'UserInfo',
    components: {
        StepUserInfo
    },
    setup() {
        const store = useStore()     
        
        let userForm = ref(Object.assign({}, store.getters.getUser()))
        let getUserForm = () => {
            let userSend = {}
            userSend.id = userForm.value.id
            userSend.name = userForm.value.name
            userSend.surname = userForm.value.surname
            userSend.patronymic = userForm.value.patronymic
            userSend.email = userForm.value.email
            userSend.lastEmail = store.getters.getUserEmail()
            userSend.password = userForm.value.password
            userSend.phone = userForm.value.phone
            userSend.role = userForm.value.role            
            userSend.status = userForm.value.status
            return userSend
        }

        let isDisabled = ref(true)
        let messages = ref({})

        let handleDisabled = flag => isDisabled.value = flag

        let saveUser = () => {
            api.updateUser(getUserForm())
            .then(res => {
                if(res.status === 200){
                    handleDisabled(true)
                }
            }).catch(res => messages.value = res.response.data)
        }

        return {
            userForm,
            isDisabled,
            messages,
            handleDisabled,
            saveUser
        }
    }
})
</script>

<style>
.userDTO-info__edit-button,
.userDTO-info__save-button{
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    max-width: 145px;
    font-size: 15px;
    font-weight: 700;
    width: 100%;
    padding: 6px;
    margin: 0 auto;
    color: #fff;
    border-color: #0595e6;
    background-color: #0595e6;
    border-radius: 6px;
    cursor: pointer;
    line-height: 1.4;
    box-sizing: border-box;
    border: 1px solid transparent;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.userDTO-info .checkout__step-body:not(.is-disabled) .field-control__label {
    border-color: #36d146;
}
</style>
