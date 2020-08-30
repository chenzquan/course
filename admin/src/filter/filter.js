/**
 *      {{CHARGE | optionKV(section.charge)}}
 * @param list   [{key:"C",value:"收费"},{key:"F",value:"免费"}]
 * @param key C
 * @returns {string}  收费
 */
// let optionKV = (list,key)=>{
//     if(!list || !key){
//         return "";
//     }else{
//         let result = "";
//         for(let i=0; i<list.length; i++){
//             if(key === list[i]["key"]){
//                 result = list[i]["value"];
//             }
//         }
//         return result;
//
//     }
// };

/**
 *        {{SECTION_CHARGE | optionKV(section.charge)}}
 * @param object  {CHARGE:{key:"C",value:"收费"},FREE:{key:"F",value:"免费"}}
 * @param key
 * @returns {string}
 */
let optionKV = (object,key)=>{
    if(!object || !key){
        return "";
    }else{
        let result = "";
        for(let enums in object){
            if(key === object[enums]["key"]){
                result = object[enums]["value"];
            }
        }

        return result;

    }
};

/**
 * 时间格式化  36000
 * @param value  10:00:00
 */
let formatSecond = (value) =>{
    value = value || 0;
    let second = parseInt(value,10);
    let minute = 0; //分
    let hour = 0;
    if(second > 60){
        minute = Math.floor(second / 60);
        second = Math.floor(second % 60);

        if(minute > 60){
            hour = Math.floor(minute / 60);
            minute = Math.floor(minute % 60);
        }

    }

    let result = "" + PrefixInteger(second,2) + "";
    result = "" + PrefixInteger(minute,2) + ":" + result;
    result = "" + PrefixInteger(hour,2) + ":" + result;
    return result;
};

function PrefixInteger(num,length) {
    return (Array(length).join('0') + num).slice(-length);
}


export default {
    optionKV,
    formatSecond
}
