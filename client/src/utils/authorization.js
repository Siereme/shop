import api from "@/api/backend-api"
import store from "@/store";
import cookies from "./cookies.js"


const handleAuth = async () => {
    let token = cookies.getCookie('Authorization')

    if(!token){
        return await api.createAnonymousUser()
        .then(async () => {
            let user = store.getters.getUser()
            return await Promise.resolve(api.login(user.email, user.email))
        })
    } else {
        return await Promise.resolve(api.authInfo(token))
    }
}

const handleAuthInit = () => {
    handleAuth()
    .then(() => {
      api.loadCategoriesByDepth(1)
      api.loadPopularProducts()
      api.loadCartProducts()
      api.getOrders()
    })
}

export default {
    handleAuthInit
}