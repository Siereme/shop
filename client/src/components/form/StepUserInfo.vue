<template>
    <div class="checkout__step-body" :class="{'is-disabled': isDisabled}">
        <div class="fields-group">
            <FieldInput :placeholder="'Телефон'" :isDisabled="isDisabled" :value="user.phone ?? ''" :setFieldValue="(event) => handleForm.setNumber(event.target, user)"/>
            <FieldInput :placeholder="'Email'" :isDisabled="isDisabled" :value="user.email ?? ''" :setFieldValue="(event) => handleForm.setEmail(event.target, user)"/>
        </div>
        <div class="fields-group">
            <FieldInput :placeholder="'Фамилия'" :isDisabled="isDisabled" :type="'surname'" :value="user.surname ?? ''" :setFieldValue="(event) => handleForm.setFieldValue(event.target, user)"/>
            <FieldInput :placeholder="'Имя'" :isDisabled="isDisabled" :type="'name'" :value="user.name ?? ''" :setFieldValue="(event) => handleForm.setFieldValue(event.target, user)"/>
            <FieldInput :placeholder="'Отчество'" :isDisabled="isDisabled" :type="'patronymic'" :value="user.patronymic ?? ''" :setFieldValue="(event) => handleForm.setFieldValue(event.target, user)"/>
        </div>
        <div class="fields-group" v-if="shownPassword">
            <FieldInput :placeholder="'Пароль'" :isDisabled="isDisabled" :type="'password'" :value="user.password ?? ''" :setFieldValue="(event) => handleForm.setFieldValue(event.target, user)"/>
            <FieldInput :placeholder="'Повторите пароль'" :isDisabled="isDisabled" :type="'repeatPassword'" :value="user.repeatPassword ?? ''" :setFieldValue="(event) => handleForm.setFieldValue(event.target, user)"/>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import handleForm from '@/utils/handleForm'
import FieldInput from './FieldInput.vue'
// import {useStore} from "vuex"
// import {computed} from 'vue'

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
        }
    },
    setup() {
        return {
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