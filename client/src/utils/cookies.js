import { useCookies, globalCookiesConfig  } from "vue3-cookies";

globalCookiesConfig ({
    expireTimes: "30d",
    path: "/",
    domain: "",
    secure: true,
    sameSite: "None",
})

const { cookies } = useCookies();

export default {
    getCookie: (name) => cookies.get(name),
    deleteCookie: name => cookies.remove(name),
    setCookie: (name, value) => {
        cookies.remove(name)
        cookies.set(name, value)
    },
}