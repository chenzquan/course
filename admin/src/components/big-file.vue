<template>
    <div>
        <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-upload "></i>
            {{text}}
        </button>
        <input class="hidden" type="file" ref="file" :id="inputId + '-input'" @change="uploadFile()">
        <!--                  <big-file v-bind:input-id="'image-upload'"-->
        <!--                        v-bind:text="'上传头像'"-->
        <!--                        v-bind:suffixs="['jpg', 'jpeg', 'png']"-->
        <!--                        v-bind:use="FILE_USE.TEACHER.key"-->
        <!--                        v-bind:after-upload="afterUpload">-->
        <!--                    -->
        <!--                  </big-file>-->

    </div>
</template>

<script>
    export default {
        name: "big-file",
        props:{
            text:{
                default:"上传大文件"
            },

            afterUpload:{
                type:Function,
                default: null
            },

            inputId:{
                default:"file-upload"
            },

            suffixs:{
                default:[]
            },
            use:{
                default:""
            }

        },
        data:function () {
            return{

            }
        },

        methods:{
            uploadFile(){
                let _this = this;
                // let formData = new window.FormData();

                let file = _this.$refs.file.files[0];

                let key = hex_md5(file);
                let key10 = parseInt(key,16);
                let key62 = Tool._10to62(key10);


                //判断 文件格式

                let suffixs = _this.suffixs;
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length).toLowerCase();
                let validateSuffix = false;
                for(let i=0; i<suffixs.length; i++){
                    if(suffixs[i].toLowerCase() === suffix){
                        validateSuffix = true;
                        break;
                    }
                }

                if(!validateSuffix){
                    Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
                    $("#" + _this.inputId + "-input").val("");
                    return;
                }


                //文件分片
                let shardSize = 20 * 1024 * 1024;  //以20MB为一个分片

                let shardIndex = 1;  // 1 表示 第一个分片
                let start = shardSize * (shardIndex - 1); //当前分片启始 位置
                let end = Math.min(file.size,start+shardSize);//当前分片结束位置
                let fileShard  = file.slice(start,end);


                let size = file.size;

                let shardToal = Math.ceil(size / shardSize);


                // formData.append('file',document.querySelector('#file-upload-input').files[0]);
                // formData.append('shard',fileShard);
                // formData.append("shardIndex",shardIndex);
                // formData.append("shardSize",shardSize);
                // formData.append("shardTotal",shardToal);
                // formData.append("use",_this.use);
                //
                // formData.append("name",file.name);
                // formData.append("suffix",suffix);
                // formData.append("size",size);
                // formData.append("key",key62);

                let fileReader = new FileReader();

                fileReader.onload = function(e){
                    let base64 = e.target.result;
                    console.log("base64: ",base64);

                    let param = {
                        'shard':base64,
                        'shardIndex':shardIndex,
                        'shardSize':shardSize,
                        'shardTotal':shardToal,
                        'use':_this.use,
                        'name':file.name,
                        'suffix':suffix,
                        'size':file.size,
                        'key':key62
                    };

                    Loading.show();
                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload',param).then((response)=>{
                        Loading.hide();
                        let resp = response.data;
                        let image = resp.content;
                        // console.log("image",image);
                        // _this.teacher.image = image;
                        _this.afterUpload(resp);
                        $("#" + _this.inputId + "-input").val("");
                    });

                };
                fileReader.readAsDataURL(fileShard);

            },

            selectFile(){
                let _this = this;
                // $("#file-upload-input").trigger("click");
                $("#" + _this.inputId + "-input").trigger("click");
            }

        }


    }
</script>

<style scoped>

</style>
