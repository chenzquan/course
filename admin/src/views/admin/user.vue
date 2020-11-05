<template>
    <div>
        <p>

            <button v-show="hasResource('010101')" @click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>新增
            </button>
            &nbsp;
            <button @click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>刷新
            </button>
        </p>
        <Pagination ref="pagination" :list="list"></Pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                             <th>id</th>
             <th>登录名</th>
             <th>呢称</th>
             <th>密码</th>

            <th>操作</th>


            <th></th>
            </tr>
            </thead>

            <tbody>

            <tr v-for="user in users" :key="user.id">
                <td>{{user.id}}</td>
                <td>{{user.loginName}}</td>
                <td>{{user.name}}</td>
                <td>{{user.password}}</td>


            <!--            <td>Feb 12</td>-->


            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!--                        <button class="btn btn-xs btn-success">-->
                    <!--                            <i class="ace-icon fa fa-check bigger-120"></i>-->
                    <!--                        </button>-->

                    <button v-show="hasResource('010103')" class="btn btn-xs btn-info" @click="edit(user)">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button v-show="hasResource('010101')" class="btn btn-xs btn-info" @click="editPassword(user)">
                        <i class="ace-icon fa fa-key bigger-120"></i>
                    </button>

                    <button v-show="hasResource('010102')" class="btn btn-xs btn-danger" @click="deleteData(user.id)">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                    <!--                        <button class="btn btn-xs btn-warning">-->
                    <!--                            <i class="ace-icon fa fa-flag bigger-120"></i>-->
                    <!--                        </button>-->
                </div>

                <div class="hidden-md hidden-lg">
                    <div class="inline pos-rel">
                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                data-position="auto">
                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                        </button>

                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                            <li>
                                <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                                                    <span class="blue">
                                                                        <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                                    </span>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                                    <span class="green">
                                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                    </span>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                                    <span class="red">
                                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                    </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </td>
            </tr>

            </tbody>
        </table>

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <!--                    <p>One fine body&hellip;</p>-->
                        <form class="form-horizontal">

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">登录名</label>
                                        <div class="col-sm-10">
                                            <input v-model="user.loginName"  :disabled="user.id" class="form-control"  placeholder="登录名">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">呢称</label>
                                        <div class="col-sm-10">
                                            <input v-model="user.name" class="form-control"  placeholder="呢称">
                                        </div>
                                    </div>
                                    <div v-show="!user.id" class="form-group">
                                        <label  class="col-sm-2 control-label">密码</label>
                                        <div class="col-sm-10">
                                            <input v-model="user.password" class="form-control" type="password"  placeholder="密码">
                                        </div>
                                    </div>



                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" @click="save()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div id="edit-password-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">修改密码</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-2">密码</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="password" v-model="user.password" name="password">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
                            <i class="ace-icon fa fa-times"></i>
                            取消
                        </button>
                        <button type="button" class="btn btn-white btn-info btn-round" v-on:click="savePassword()">
                            <i class="ace-icon fa fa-plus blue"></i>
                            保存密码
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>

</template>

<script>

    import Pagination from '../../components/pagination.vue';
    // import Swal from 'sweetalert2';

    export default {
        name: "system-user",
        components: {
            Pagination
        },

        data() {
            return {
                users: [],
                user:{},
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("system-user-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/system/user/list').then((response)=>{
            //         console.log("jieguo",response);
            //     });
            //     // http.get()
            // }


            _this.list(1);

        },


        methods: {

            hasResource(id){
                return Tool.hasResource(id);
            },


            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.users = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.user = {};
                $("#form-modal").modal("show");
            },

            edit(user){
                let _this = this;
                $("#form-modal").modal("show");
                _this.user = $.extend({},user);
            },

            editPassword(user){
                let _this = this;
                $("#edit-password-modal").modal("show");
                _this.user = $.extend({},user);
                _this.user.password = null;
            },

            deleteData(id){
                let _this = this;
                Confirm.show("删除用户后不可恢复，确认删除？",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/user/delete/' + id).then((response) => {
                        Loading.hide();
                        let res = response.data;
                        console.log("jieguo add", response);
                        if(res.success){
                            // $(".modal").modal("hide");
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                        // _this.users = response.data.list;
                        // _this.$refs.pagination.render(page, response.data.total);

                    });
                });

                // Swal.fire({
                //     title: '确认删除?',
                //     text: '删除后不可恢复，确认删除',
                //     icon: 'warning',
                //     showCancelButton: true,
                //     confirmButtonText: '确认',
                //     cancelButtonText: '取消'
                // }).then((result) => {
                //     if (result.value) {
                //         Loading.show();
                //         _this.$ajax.delete('http://127.0.0.1:9000/system/admin/user/delete/' + id).then((response) => {
                //             Loading.hide();
                //             let res = response.data;
                //             console.log("jieguo add", response);
                //             if(res.success){
                //                 // $(".modal").modal("hide");
                //                 _this.list(1);
                //                 Toast.success("删除成功!")
                //             }
                //             // _this.users = response.data.list;
                //             // _this.$refs.pagination.render(page, response.data.total);
                //
                //         });
                //     }
                //     // else if (result.dismiss === Swal.DismissReason.cancel) {
                //     //     Swal.fire(
                //     //         'Cancelled',
                //     //         'Your imaginary file is safe :)',
                //     //         'error'
                //     //     )
                //     // }
                // });


            },

            save(){
                let _this = this;

                if (1!=1
                                   || !Validator.request(_this.user.loginName,"登录名")
                   || !Validator.length(_this.user.loginName,"登录名",1,50)
                                   || !Validator.length(_this.user.name,"呢称",1,50)
                                   || !Validator.request(_this.user.password,"密码")
                ){
                    return;
                }

                //保存校验
                // if (!Validator.request(_this.user.name,"名称")
                //     || !Validator.request(_this.user.courseId,"课程ID")
                //     || !Validator.length(_this.user.courseId,"课程ID",1,8)){
                //     return;
                // }

                _this.user.password = hex_md5(_this.user.password + KEY);



                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/save',
                    _this.user
                ).then((response) => {
                    let res = response.data;
                    // console.log("jieguo add", response);
                    if(res.success){
                        $(".modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功!");
                    }else{
                        Toast.warning(res.message);
                    }
                    // _this.users = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },

            savePassword(){
                let _this = this;



                //保存校验
                // if (!Validator.request(_this.user.name,"名称")
                //     || !Validator.request(_this.user.courseId,"课程ID")
                //     || !Validator.length(_this.user.courseId,"课程ID",1,8)){
                //     return;
                // }

                _this.user.password = hex_md5(_this.user.password + KEY);



                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/save-password',
                    _this.user
                ).then((response) => {
                    let res = response.data;
                    // console.log("jieguo add", response);
                    if(res.success){
                        $("#edit-password-modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功!");
                    }else{
                        Toast.warning(res.message);
                    }
                    // _this.users = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            }





        }


    }
</script>


