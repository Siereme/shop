<template>
    <div class="user-info">
        <StepUserInfo :user="user" :isDisabled="isDisabled" />
        <button class="user-info__edit-button" v-if="isDisabled" @click="handleDisabled(false)">Редактировать</button>
        <button class="user-info__save-button" v-if="!isDisabled" @click="saveUser()">Сохранить</button>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import {computed, ref} from 'vue'
import {useStore} from "vuex"
import StepUserInfo from '../form/StepUserInfo.vue'

export default defineComponent({
    name: 'UserInfo',
    components: {
        StepUserInfo
    },
    setup() {
        const store = useStore()     
        
        let user = computed(() => store.getters.getUser())

        let isDisabled = ref(true)

        let handleDisabled = flag => isDisabled.value = flag

        let saveUser = () => {
            api.editUser(user.value)
            .then(res => {
                if(res.status === 200){
                    handleDisabled(true)
                }
            })
        }

        return {
            user,
            isDisabled,
            handleDisabled,
            saveUser
        }
    }
})
</script>

<style>
.user-info__edit-button,
.user-info__save-button{
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
.user-info .checkout__step-body:not(.is-disabled) .field-control__label {
    border-color: #36d146;
}
</style>
