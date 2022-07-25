<template>
    <div class="checkout-main">
        <h1>Оформление заказа</h1>

        <div class="checkout-main-container">
            <div class="checkout-main-order">
                <div class="checkout__steps">
                    <div class="checkout__step">
                        <div class="checkout__step-header">
                            <div class="checkout__step-header-num">1</div>
                            <div class="checkout__step-header-content">
                                <div class="checkout__step-header-title">Получатель</div>
                                <div class="checkout__step-header-description">
                                    Укажите данные или <a href="#">авторизуйтесь</a>
                                </div>
                            </div>
                        </div>
                        <StepUserInfo :user="user" />
                    </div>
                    <div class="checkout__step">
                        <div class="checkout__step-header">
                            <div class="checkout__step-header-num">2</div>
                            <div class="checkout__step-header-content">
                                <div class="checkout__step-header-title">Способ оплаты</div>
                            </div>
                        </div>
                        <div class="checkout__step-body">
                            <div class="checkout__payments">
                                <div class="checkout__payments-item" v-for="item in payments" :key="item.id">
                                    <input v-model="payment" type="radio" id="OrderOplata_1" class="field-radio__input" :value="item">
                                    <label for="OrderOplata_1" class="field-radio__label">
                                        <div class="field-radio__label-content-heading">
                                            При получении
                                        </div>
                                        <div class="field-radio__label-content-description">
                                            Картой / наличными
                                        </div>
                                        <div class="field-radio__label-content-icons">
                                            
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="checkout__proceed">
                    <div class="checkout__proceed-action-total">
                        <span>Итого к оплате:</span>
                        <span class="checkout__proceed-action-total-price">{{orderTotal}} ₽</span>
                    </div>
                    <div class="checkout__proceed-action-item">
                        <div class="checkout__proceed-action-button" @click="createOrder()">Оформить</div>
                    </div>
                </div>
            </div>
            <CartModal />
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import CartModal from './cart/CartModal.vue'
import api from "@/api/backend-api"

// import ProductList from './product/ProductList.vue'
// import FacetsVue from './facets/Facets.vue'
import {computed, ref} from 'vue'
import {useStore} from "vuex"
// import {ref} from 'vue'
import { useRouter } from 'vue-router'
import StepUserInfo from './form/StepUserInfo.vue'

export default defineComponent({
    name: 'CheckoutPage',
    components: {
    CartModal,
    StepUserInfo
},
    setup() {
        const store = useStore()     
        const router = useRouter()

        const orderTotal = computed(() => {
            if(!store.getters.getIsLoading() && store.state.cart.cartTotal === 0){
                router.replace({name: 'Home'})
            }
            return store.state.cart.cartTotal
        })

        api.getPayments()

        let storeUser = computed(() => store.state.user.user)
        let user = ref(storeUser.value && storeUser.value.status !== 'ANONYMOUS' ? storeUser : {})
        let payments = computed(() => store.state.order.payments)
        let payment = ref({})

        let setUserData = () => {
            store.commit('setUserPhone', user.value.phone)
            store.commit('setUserEmail', user.value.email)
            store.commit('setUserSurname', user.value.surname)
            store.commit('setUserName', user.value.name)
            store.commit('setUserPatronymic', user.value.patronymic)
        }

        let createOrder = () => {
            setUserData()
            api.createOrder(storeUser.value, payment.value)
            .then(res => {
                if(res.status === 200){
                    store.commit('setIsLoading', true)
                    store.commit('setCartProducts', [])
                    router.replace({name: 'OrderPage'})
                }
            })
        }

        return {
            storeUser,
            user,
            orderTotal,
            payments,
            payment,
            setUserData,
            createOrder
        }
    }
})
</script>

<style>
.checkout-main{
    padding: 0 30px;
}
h1 {
    color: #273347;
    line-height: 32px;
    font-size: 28px;
}
.checkout-main-container{
    display: flex;
    margin-bottom: 40px;
}
.checkout-main-order,
.checkout__steps,
.checkout__step-header,
.checkout__step-header-content{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.checkout-main-order{
    padding-right: 60px;
}
.checkout__step{
    width: 100%;
    margin-bottom: 32px;
    box-sizing: border-box;
}
.checkout__step-header{
    margin-bottom: 20px;
    flex-direction: row;
}
.checkout__step-header-num{
    margin-right: 56px;
    font-size: 16px;
    font-weight: 700;
    position: relative;
    display: flex;
    align-items: center;
    flex: 0 0 auto;
    justify-content: center;
    width: 24px;
    height: 24px;
    color: #fff;
    border-radius: 50%;
    background: #aedcf6;
}
.checkout__step-header-title,
.checkout__step-header-description{
    font-size: 20px;
    line-height: 1.09091;
    flex: 0 0 auto;
    margin-right: 20px;
    color: #273347;
    font-weight: 400;
}
.checkout__step-header-description{
    font-size: 16px;
    margin-top: 10px;
    color: #464b51;
}
.checkout__step-header-description a{
    transition: all .15s ease-in-out;
    text-decoration: none;
    color: #0595e6;
    font-weight: bolder;
}
.checkout__step-body{
    margin-left: 80px;
}
.fields-group{
    margin-right: -8px;
    margin-left: -8px;
    display: flex;
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
.checkout__payments-item{
    min-width: 200px;
    min-height: 90px;
    width: fit-content;
    display: flex;
    flex: 0 0 auto;
    padding: 5px;
    box-sizing: border-box;
}
.field-radio__label{
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
    cursor: pointer;
    color: #7d858c;
    border-color: #eee;
    font-weight: 400;
    padding: 13px;
    border: 1px solid #ced8e1;
    border-radius: 6px;
    transition: all .15s ease-in-out;
}
.field-radio__label div{
    color: #7d858c;
}
.field-radio__label .field-radio__label-content-heading{
    font-size: 15px;
    line-height: 18px;
}
.field-radio__label .field-radio__label-content-description{
    font-weight: 400;
    font-size: 12px;
    line-height: 16px;
    margin-top: 2px;
}
.field-radio__input{
    position: absolute;
    width: 1px;
    height: 1px;
    opacity: 0;
}
.field-radio__input:checked ~ .field-radio__label{
    border-color: #06a4fd;
}
.field-radio__input:checked ~ .field-radio__label div{
    color: #353d4a;
    font-weight: 600;
}
.field-radio__input:checked ~ .field-radio__label .field-radio__label-content-heading{
    font-size: 14px;
}
.field-radio__input:checked ~ .field-radio__label .field-radio__label-content-description{
    font-size: 11px;
}
.checkout-main .cart-modal-container{
    position: relative;
    padding: 0;
}
.checkout-main .cart-modal{
    width: 365px;
}
.checkout-main .cart-modal-buttons{
    display: none;
}
.checkout__proceed{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: calc(100% - 80px);
    margin-left: 80px;
    padding-top: 20px;
    border-top: 1px solid #ced8e1;
}
@media (max-width: 1100px) {
    .checkout__proceed{
        flex-direction: column;
    }
    .checkout__proceed-action-total{
        margin-bottom: 20px;
    }
}
.checkout__proceed-action-total{
    display: flex;
    font-size: 24px;
    margin-right: 20px;
}
.checkout__proceed-action-total span{
    font-weight: 400;
    white-space: nowrap;
}
.checkout__proceed-action-total .checkout__proceed-action-total-price{
    margin-left: 30px;
    font-weight: 600;
}
.checkout__proceed-action-button{
    color: #fff;
    background-color: #0595e6;
    cursor: pointer;
    width: 230px;
    white-space: nowrap;
    font-size: 17px;
    min-height: 48px;
    font-weight: 700;
    line-height: 1;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 34px;
    padding: 0 20px 1px;
    user-select: none;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out;
    text-align: center;
    vertical-align: middle;
    border: 1px solid transparent;
    border-radius: 6px;
    padding-right: 40px;
    padding-left: 40px;
    box-sizing: border-box;
}
.checkout__proceed-action-button:hover{
    background-color: #06a4fd;
}
</style>
