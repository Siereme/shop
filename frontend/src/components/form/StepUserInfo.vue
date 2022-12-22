<template>
    <div class="step-user-info" :class="{'is-disabled': isDisabled}">
        <div class="fields-group" ref="contacts">
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Телефон'"  :type="'phone'" :value="userData.phone ?? ''"
                :setFieldValue="(event) => handleForm.setNumber(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Email'" :type="'email'" :value="userData.email ?? ''"
                :setFieldValue="(event) => handleForm.setEmail(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
        </div>
        <div class="fields-group" ref="fullName">
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Фамилия'"  :type="'surname'" :value="userData.surname ?? ''" 
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />

            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Имя'"  :type="'name'" :value="userData.name ?? ''" 
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
            <FieldInput :isDisabled="isDisabled" 
                :placeholder="'Отчество'" :type="'patronymic'" :value="userData.patronymic ?? ''" 
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
        </div>
        <div class="fields-group" ref="passwords" v-if="shownPassword">
            <FieldInput :isDisabled="isDisabled" 
                :placeholder="'Пароль'" :type="'password'" :value="userData.password ?? ''" 
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
            <FieldInput :isDisabled="isDisabled"
                :placeholder="'Повторите пароль'"  :type="'repeatPassword'" :value="userData.repeatPassword ?? ''" 
                :setFieldValue="(event) => handleForm.setFieldValue(event.target, userData)" 
                :clearValidation="(event) => handleForm.clearValidationError(event.target)"
            />
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import handleForm from '@/utils/handleForm'
import FieldInput from './FieldInput.vue'
import { watch, ref } from 'vue'

export default defineComponent({
    name: 'StepUserInfo',
    components: {
        FieldInput
    },
    props: {
        isDisabled: {
            default: () => {}
        },
        shownPassword: {
            default: () => {}
        },
        user: {
            default: () => {}
        },
        errorMessages: {
            default: () => {}
        }
    },
    setup(props) {

        let contacts = ref(null)
        let fullName = ref(null)
        let passwords = ref(null)
        let userData = ref(props.user)

        watch(
            () => props.user,
            user => userData.value = user
        )

        watch(
            () => props.errorMessages,
            massages => handleValidation(massages)
        )

        let handleValidation = massages => {
            let fields = Array.from([contacts.value, fullName.value, passwords.value])
            .filter(item => item)
            .flatMap(item => Array.from(item.children).map(childrenItem => childrenItem.childNodes[0]))
            
            Object.keys(massages).forEach(field => {
                let currentField = fields.find(item => field.includes(item.dataset.type))
                if(currentField){
                    userData.value[currentField.dataset.type] = ''
                    currentField.classList.add('unvalid')
                    currentField.placeholder = massages[field]
                }
            })
        }

        return {
            contacts,
            fullName,
            passwords,
            userData,
            handleForm
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

</style>