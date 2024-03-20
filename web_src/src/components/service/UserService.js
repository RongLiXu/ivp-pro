
export default {

    /**
     * 存储用户信息
     * @param username
     * @param token
     */
    setUser(user) {
        localStorage.setItem("ivp-user", JSON.stringify(user));
    },

    /**
     * 获取用户
     */
    getUser() {
        return JSON.parse(localStorage.getItem("ivp-user"));
    },


    /**
     * 获取登录token
     */
    getToken() {
        return localStorage.getItem("ivp-token");
    },

    /**
     * 清理用户信息
     */
    clearUserInfo() {
        localStorage.removeItem("ivp-user");
        localStorage.removeItem("ivp-token");
    },
    /**
     * 更新token
     * @param header
     */
    setToken(token) {
        localStorage.setItem("ivp-token", token);
    }
}
