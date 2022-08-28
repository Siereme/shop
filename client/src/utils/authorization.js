import api from "@/api/backend-api"
import cookies from "./cookies.js"


const handleAuth = async () => {
    let token = cookies.getCookie('X-access-token')
    if(!token){
        return await Promise.resolve(api.loginAnonymous())
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