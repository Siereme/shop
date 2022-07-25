let isActualValue = value => value !== undefined && value !== null

let validateEmail = (email) => isActualValue(email) && /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email) ? email : ''
let setEmail = (event, user) => validateEmail(event.value) ? user.email = event.value : user.email = ''

let validateNumber = (number) => {
    var validNumber = isActualValue(number) ? number.replace(/\D/g, '').match(/(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,4})/) : ''
    return !validNumber[2] ? validNumber[1] : validNumber[1] + ' (' + validNumber[2] + ') ' + validNumber[3] + (validNumber[4] ? '-' + validNumber[4] : '');

}
let setNumber = (number, user) => {
    var validNumber = validateNumber(number.value)
    return user.phone = validNumber ?? ''
}

let getFieldValue = (event, user) => {
    var name = event.dataset.type
    return isActualValue(name) ? user[name] : ''
}
let setFieldValue = (event, user) => {
    var name = event.dataset.type
    var value = event.value
    return isActualValue(name) && isActualValue(value) ? user[name] = value : user[name] = ''
}


export default {
    validateEmail,
    setEmail,
    validateNumber,
    setNumber,
    getFieldValue,
    setFieldValue
}