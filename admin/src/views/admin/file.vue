<template>
    <div>
        <p>

<!--            <button @click="add()" class="btn btn-white btn-default btn-round">-->
<!--                <i class="ace-icon fa fa-edit"></i>新增-->
<!--            </button>-->
<!--            &nbsp;-->
            <button @click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>刷新
            </button>
        </p>
        <Pagination ref="pagination" :list="list"></Pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                             <th>id</th>
             <th>相对路径</th>
             <th>文件名</th>
             <th>后缀</th>
             <th>大小</th>
             <th>用途</th>

<!--            <th>操作</th>-->



            </tr>
            </thead>

            <tbody>

            <tr v-for="file in files" :key="file.id">
                <td>{{file.id}}</td>
                <td>{{file.path}}</td>
                <td>{{file.name}}</td>
                <td>{{file.suffix}}</td>
                <td>{{file.size | formatFileSize}}</td>
                <td>{{FILE_USE | optionKV(file.use)}}</td>
            </tr>

            </tbody>
        </table>



    </div>

</template>

<script>

    import Pagination from '../../components/pagination.vue';
    // import Swal from 'sweetalert2';

    export default {
        name: "file-file",
        components: {
            Pagination
        },

        data() {
            return {
                files: [],
                file:{},
                FILE_USE:FILE_USE,
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("file-file-sidebar");

            _this.list(1);

        },


        methods: {
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.files = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            // add() {
            //     let _this = this;// eslint-disable-line no-unused-vars
            //     _this.file = {};
            //     $(".modal").modal("show");
            // },
            //
            // edit(file){
            //     let _this = this;
            //     $(".modal").modal("show");
            //     _this.file = $.extend({},file);
            // },

            // deleteData(id){
            //     let _this = this;
            //     Confirm.show("删除文件后不可恢复，确认删除？",function () {
            //         Loading.show();
            //         _this.$ajax.delete(process.env.VUE_APP_SERVER + '/file/admin/file/delete/' + id).then((response) => {
            //             Loading.hide();
            //             let res = response.data;
            //             console.log("jieguo add", response);
            //             if(res.success){
            //                 // $(".modal").modal("hide");
            //                 _this.list(1);
            //                 Toast.success("删除成功!")
            //             }
            //             // _this.files = response.data.list;
            //             // _this.$refs.pagination.render(page, response.data.total);
            //
            //         });
            //     });
            //
            // },

            // save(){
            //     let _this = this;
            //
            //     if (1!=1
            //                        || !Validator.request(_this.file.path,"相对路径")
            //        || !Validator.length(_this.file.path,"相对路径",1,100)
            //                        || !Validator.length(_this.file.name,"文件名",1,100)
            //                        || !Validator.length(_this.file.suffix,"后缀",1,10)
            //                                        || !Validator.length(_this.file.use,"用途",1,50)
            //                                     ){
            //         return;
            //     }
            //
            //     //保存校验
            //     // if (!Validator.request(_this.file.name,"名称")
            //     //     || !Validator.request(_this.file.courseId,"课程ID")
            //     //     || !Validator.length(_this.file.courseId,"课程ID",1,8)){
            //     //     return;
            //     // }
            //
            //
            //     _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/save',
            //         _this.file
            //     ).then((response) => {
            //         let res = response.data;
            //         console.log("jieguo add", response);
            //         if(res.success){
            //             $(".modal").modal("hide");
            //             _this.list(1);
            //             Toast.success("保存成功!");
            //         }else{
            //             Toast.warning(res.message);
            //         }
            //         // _this.files = response.data.list;
            //         // _this.$refs.pagination.render(page, response.data.total);
            //
            //     });
            // },





        }


    }
</script>


