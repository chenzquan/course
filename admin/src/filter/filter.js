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

export default {
    optionKV
}
