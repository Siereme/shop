<template>
    <div class="personal-cabinet">
        <h1>Личный кабинет</h1>
        <div class="personal-cabinet-container">
            <div class="cabinet-menu">
                <div class="cabinet-menu-list">
                    <router-link class="cabinet-menu-item" to="/cabinet/userDTO">Личные данные</router-link>
                    <router-link class="cabinet-menu-item" to="/cabinet/orders-history">История заказов</router-link>
                    <div class="cabinet-menu-item" @click="handleLogout()">Выйти</div>
                </div>
            </div>
            <div class="cabinet-content">
                <router-view name="CabinetContainer"></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import auth from "@/utils/authorization"
import {computed} from 'vue'
import {useStore} from "vuex"
import { useRouter } from 'vue-router'

export default defineComponent({
    name: 'PersonalCabinet',
    setup() {
        const store = useStore()     
        const router = useRouter()
        
        store.commit('setIsLoading', false)
        
        let order = computed(() => store.getters.getLastOrder())

        let handleLogout = () => {
            api.logout()
            .then(res => {
                if(res.status === 200){
                    router.push("/")
                    auth.handleAuthInit()
                }
            })
        }

        return {
            order,
            handleLogout
        }
    }
})
</script>

<style>
.personal-cabinet{
    padding: 0 30px 60px;
}
h1 {
    color: #273347;
    line-height: 30px;
    font-size: 26px;
}
.personal-cabinet-container{
    display: flex;
}
.cabinet-menu{
    flex: 0 0 auto;
    width: 226px;
    margin-right: 40px;
}
.cabinet-menu-list{
    display: flex;
    flex-direction: column;
    padding: 20px 25px;
    border-radius: 10px;
    background-color: #fafafa;
    box-sizing: border-box;
    justify-content: center;
    align-items: flex-start;
}
.cabinet-menu-item{
    color: #273347;
    font-size: 14px;
    line-height: 18px;
    margin: 10px 0;
    transition: .2s ease;
    cursor: pointer;
}
.cabinet-menu-item:hover{
    color: #06a4fd;
}
.cabinet-content{
    flex: 1 1 auto;
    max-width: 1000px;
}
.personal-cabinet .checkout__step-body{
    margin-left: 0;
}
</style>
