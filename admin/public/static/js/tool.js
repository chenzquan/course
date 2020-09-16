Tool = {

    /**
     * 空校验 null 或 "" 都返回true
     * @param obj
     * @returns {boolean}
     */

    isEmpty:function (obj) {
        if((typeof obj == 'string')){
          return !obj || obj.replace(/\s+/g,"") == "";
        }else {
            return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
        }
    },


    isNotEmpty:function (obj) {
        return !this.isEmpty(obj);
    },

    isLength:function (str,min,max) {
        return $.trim(str).length >= min && $.trim(str).length <= max;
    },


    /**
     * 移除对象数组中的对象
     * @param array
     * @param obj
     * @returns {number}
     */
    removeObj: function (array, obj) {
        let index = -1;
        for (let i = 0; i < array.length; i++) {
            if (array[i] === obj) {
                array.splice(i, 1);
                index = i;
                break;
            }
        }
        return index;
    },



    // dateFormat: function (format,date) {
    //     let result;
    //     if(!date){
    //         date = new Date();
    //     }
    //
    //     const option = {
    //         "y+":date.getFullYear().toString(),
    //         "M+":(date.getMonth() + 1).toString(),
    //         "d+":date.getDate().toString(),
    //         "h+":date.getHours().toString(),
    //         "m+":date.getMinutes().toString(),
    //         "s+":date.getSeconds().toString()
    //     };
    //
    //
    //     for (let i in option){
    //         result = new RegExp("(" + i + ")").exec(format);
    //         if(result){
    //             format = format.replace(result[1],(result[1].length == 1) ? (option[i]) : (option[i].pack()))
    //         }
    //     }
    //
    //     return format;
    // }




};
