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
                        <div class="checkout__step-body">
                            <StepUserInfo :user="userForm" :errorMessages="errorMessages" />
                        </div>
                    </div>
                    <div class="checkout__step">
                        <div class="checkout__step-header">
                            <div class="checkout__step-header-num">3</div>
                            <div class="checkout__step-header-content checkout__step-header-row">
                                <div class="checkout__step-header-title">Способ получения</div>
                                <div class="checkout__step-header-error" v-if="shownReceiptError">Выберите способ получения</div>
                            </div>
                        </div>
                        <div class="checkout__step-body">
                            <OrderReceipt ref="receiptWrapper" :errorMessages="errorMessages" :shownError="shownReceiptError" :handleError="() => handleReceiptError(false)"/>
                        </div>
                    </div>
                    <div class="checkout__step">
                        <div class="checkout__step-header">
                            <div class="checkout__step-header-num">3</div>
                            <div class="checkout__step-header-content checkout__step-header-row">
                                <div class="checkout__step-header-title">Способ оплаты</div>
                                <div class="checkout__step-header-error" v-if="shownPaymentError">Выберите способ оплаты</div>
                            </div>
                        </div>
                        <div class="checkout__step-body">
                            <Payments ref="paymentWrapper" :shownError="shownPaymentError" :handleError="() => handlePaymentError(false)"/>
                        </div>
                    </div>
                </div>
                <div class="checkout__proceed">
                    <div class="checkout__proceed-action-total">
                        <span>Итого к оплате:</span>
                        <span class="checkout__proceed-action-total-price">{{orderTotal}} ₽</span>
                    </div>
                    <div class="checkout__proceed-action-item">
                        <div class="checkout__proceed-action-button" @click="sendOrder()">Оформить</div>
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
import {computed, ref} from 'vue'
import {useStore} from "vuex"
import { useRouter } from 'vue-router'
import StepUserInfo from './form/StepUserInfo.vue'
import Payments from './checkout/Payments.vue'
import OrderReceipt from './checkout/orderReceipt/OrderReceipt.vue'

export default defineComponent({
    name: 'CheckoutPage',
    components: {
        CartModal,
        StepUserInfo,
        Payments,
        OrderReceipt
    },
    setup() {
        const store = useStore()     
        const router = useRouter()

        const orderTotal = computed(() => {
            if(!store.getters.getIsLoading() && !store.state.cart.cartTotal){
                router.replace({name: 'Home'})
            }
            return store.state.cart.cartTotal
        })

        let user = computed(() => store.state.user.user)
        let userForm = ref(user.value && user.value.status !== 'ANONYMOUS' ? Object.assign({}, user.value) : {})
        let userSend = ref(user.value)
        
        let receiptWrapper = ref({})
        let paymentWrapper = ref({})

        let shownPaymentError = ref(false)
        let shownReceiptError = ref(false)
        let errorMessages = ref({})


        let getUserData = () => {
            userSend.value.phone = userForm.value.phone
            userSend.value.email = userForm.value.email
            userSend.value.surname = userForm.value.surname
            userSend.value.name = userForm.value.name
            userSend.value.patronymic = userForm.value.patronymic
            return Object.assign({}, userSend.value)
        }

        let createOrder = () => ({
            'user': getUserData(),
            'receiptDetail': receiptWrapper.value.getReceiptDetail(),
            'payment': paymentWrapper.value.getPayment()
        })

        let sendOrder = () => {
            store.commit('setIsLoading', true)
            api.createOrder(createOrder())
            .then(res => {
                if(res.status === 200){
                    router.replace({name: 'OrderPage'})
                }
                paymentWrapper.value.handleError(false)
            }).catch(res => {
                handleErrorMessages(res.response.data)
                store.commit('setIsLoading', false)
            })
        }

        let handleErrorMessages = messages => {
            errorMessages.value = messages
            Object.keys(messages).find(message => message.includes('payment.')) ? handlePaymentError(true) : null
            Object.keys(messages).find(message => message.includes('receipt.')) ? handleReceiptError(true) : null
        }
        let handlePaymentError = flag => shownPaymentError.value = flag ?? false
        let handleReceiptError = flag => shownReceiptError.value = flag ?? false

        return {
            user,
            userForm,
            orderTotal,
            paymentWrapper,
            receiptWrapper,
            errorMessages,
            shownPaymentError,
            shownReceiptError,
            sendOrder,
            handlePaymentError,
            handleReceiptError
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
.checkout__step-header-row{
    flex-direction: row;
    align-items: center;
}
.checkout__step-header-error{
    font-size: 16px;
    line-height: 1.09091;
    flex: 0 0 auto;
    margin-right: 20px;
    color: red;
    font-weight: 400;
}
</style>
