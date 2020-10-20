//localStorage 是本地缓存，浏览器关闭后，下次再打开还能读到

LOCAL_KEY_REMEMBER_USER = "LOCAL_KEY_REMEMBER_USER";

LocalStorage = {
    get : function (key) {
        let v = localStorage.getItem(key);
        if(v && typeof(v) !== "undefined" && v !== "undefined"){
            return JSON.parse(v);
        }
    },

    set: function (key,data) {

        localStorage.setItem(key,JSON.stringify(data));
    },

    remove: function (key) {
        localStorage.removeItem(key);
    },

    clearAll: function () {
        localStorage.clear();
    }

};
