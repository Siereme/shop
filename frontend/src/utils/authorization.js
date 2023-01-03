import api from "@/api/backend-api"
import cookies from "./cookies.js"
import store from "@/store"

let headerAccessToken = store.getters.getHeaderAccessToken()

const handleAuth = async () => {
    let token = cookies.getCookie(headerAccessToken)
    if(!token){
        return await Promise.resolve(api.loginAnonymous())
    } 
    // else {
    //     return await Promise.resolve(api.authInfo(token))
    // }
}

const handleAuthInit = () => handleAuth().then(() => {
    api.loadMain()
    api.loadCartProducts()
})

export default {
    handleAuthInit
}