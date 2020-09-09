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
        name: "file",
        props:{
            text:{
                default:"上传文件"
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
            }

        },
        data:function () {
            return{

            }
        },

        methods:{
            uploadFile(){
                let _this = this;
                let formData = new window.FormData();

                let file = _this.$refs.file.files[0];

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


                // formData.append('file',document.querySelector('#file-upload-input').files[0]);
                formData.append('file',file);
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload',formData).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    let image = resp.content;
                    // console.log("image",image);
                    // _this.teacher.image = image;
                    _this.afterUpload(resp);
                    $("#" + _this.inputId + "-input").val("");
                });
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
