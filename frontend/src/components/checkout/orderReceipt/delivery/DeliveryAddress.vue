<template>
    <div class="delivery-address" :class="{'is-disabled': isDisabled}" ref="addressWrapper">
        <h4 class="delivery-address__title">Укажите адрес</h4>
        <div class="fields-group">
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Страна'" :type="'country'" :value="address.country ?? ''"
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, address)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Город'"  :type="'city'" :value="address.city ?? ''"
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, address)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
        </div>
        <div class="fields-group">
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Улица'"  :type="'street'" :value="address.street ?? ''" 
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, address)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
            <div class="fields-group__block">
                <FieldInput :isDisabled="isDisabled" 
                    :placeholder="'Дом'" :type="'building'" :value="address.building ?? ''" 
                    :setFieldValue="(event) => handleForm.setFieldValue(event.target, address)" 
                    :clearValidation="(event) => handleForm.clearValidationError(event.target)"
                />
                <FieldInput :isDisabled="isDisabled"
                    :placeholder="'Квартира'"  :type="'flat'" :value="address.flat ?? ''" 
                    :setFieldValue="(event) => handleForm.setFieldValue(event.target, address)" 
                    :clearValidation="(event) => handleForm.clearValidationError(event.target)"
                />
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import FieldInput from '@/components/form/FieldInput.vue'
import { watch, ref } from 'vue'
import handleForm from '@/utils/handleForm'

export default defineComponent({
    name: 'AddressInfo',
    components: {
        FieldInput
    },
    props: {
        isDisabled: {
            default: () => {}
        },
        errorMessages: {
            default: () => {}
        }
    },
    setup(props) {

        let address = ref({})
        let addressWrapper = ref(null)

        let getAddress = () => Object.assign({}, address.value)

        watch(
            () => props.errorMessages,
            massages => handleValidation(massages)
        )

        let handleValidation = massages => {
            let fields = Array.from(addressWrapper.value.querySelectorAll('.field-control__label'))
            
            Object.keys(massages).forEach(field => {
                let currentField = fields.find(item => field.includes(item.dataset.type))
                if(currentField){
                    address.value[currentField.dataset.type] = ''
                    currentField.classList.add('unvalid')
                    currentField.placeholder = massages[field]
                }
            })
        }

        return {
            address,
            addressWrapper,
            handleForm,
            getAddress
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
  text-decoration: none;
}
.fields-group__block{
    display: flex;
    width: 50%;
}
.delivery-address__title{
    text-align: left;
    font-size: 18px;
    line-height: 1.09091;
    flex: 0 0 auto;
    margin-right: 20px;
    color: #273347;
    font-weight: 400;
}
</style>