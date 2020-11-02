<template>
    <div>
        <p>

            <button @click="add()" class="btn btn-white btn-default btn-round">
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
             <th>角色</th>
             <th>描述</th>

            <th>操作</th>


            <th></th>
            </tr>
            </thead>

            <tbody>

            <tr v-for="role in roles" :key="role.id">
                <td>{{role.id}}</td>
                <td>{{role.name}}</td>
                <td>{{role.desc}}</td>


            <!--            <td>Feb 12</td>-->


            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!--                        <button class="btn btn-xs btn-success">-->
                    <!--                            <i class="ace-icon fa fa-check bigger-120"></i>-->
                    <!--                        </button>-->

                    <button class="btn btn-xs btn-info" @click="editResource(role)">
                        <i class="ace-icon fa fa-list bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-info" @click="edit(role)">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" @click="deleteData(role.id)">
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

        <div class="modal fade" tabindex="-1" role="dialog" >
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
                                        <label  class="col-sm-2 control-label">角色</label>
                                        <div class="col-sm-10">
                                            <input v-model="role.name" class="form-control"  placeholder="角色">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">描述</label>
                                        <div class="col-sm-10">
                                            <input v-model="role.desc" class="form-control"  placeholder="描述">
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


        <!-- 角色资源关联配置 -->
        <div id="resource-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">角色资源关联配置</h4>
                    </div>
                    <div class="modal-body">
                        <ul id="tree" class="ztree"></ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
                            <i class="ace-icon fa fa-times"></i>
                            关闭
                        </button>
                        <button type="button" class="btn btn-white btn-info btn-round" v-on:click="saveResource()">
                            <i class="ace-icon fa fa-plus blue"></i>
                            保存
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
        name: "system-role",
        components: {
            Pagination
        },

        data() {
            return {
                roles: [],
                role:{},
                resources: [],
                zTree: {},
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("system-role-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/system/role/list').then((response)=>{
            //         console.log("jieguo",response);
            //     });
            //     // http.get()
            // }


            _this.list(1);

        },


        methods: {
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/role/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.roles = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.role = {};
                $(".modal").modal("show");
            },

            edit(role){
                let _this = this;
                $(".modal").modal("show");
                _this.role = $.extend({},role);
            },

            deleteData(id){
                let _this = this;
                Confirm.show("删除角色后不可恢复，确认删除？",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/role/delete/' + id).then((response) => {
                        Loading.hide();
                        let res = response.data;
                        console.log("jieguo add", response);
                        if(res.success){
                            // $(".modal").modal("hide");
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                        // _this.roles = response.data.list;
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
                //         _this.$ajax.delete('http://127.0.0.1:9000/system/admin/role/delete/' + id).then((response) => {
                //             Loading.hide();
                //             let res = response.data;
                //             console.log("jieguo add", response);
                //             if(res.success){
                //                 // $(".modal").modal("hide");
                //                 _this.list(1);
                //                 Toast.success("删除成功!")
                //             }
                //             // _this.roles = response.data.list;
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
                                   || !Validator.request(_this.role.name,"角色")
                   || !Validator.length(_this.role.name,"角色",1,50)
                                   || !Validator.request(_this.role.desc,"描述")
                   || !Validator.length(_this.role.desc,"描述",1,100)
                ){
                    return;
                }

                //保存校验
                // if (!Validator.request(_this.role.name,"名称")
                //     || !Validator.request(_this.role.courseId,"课程ID")
                //     || !Validator.length(_this.role.courseId,"课程ID",1,8)){
                //     return;
                // }


                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/role/save',
                    _this.role
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
                    // _this.roles = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },


            /**
             * 点击【编辑】
             */
            editResource(role) {
                let _this = this;
                _this.role = $.extend({}, role);
                _this.loadResource();
                $("#resource-modal").modal("show");
            },

            /**
             * 加载资源树
             */
            loadResource() {
                let _this = this;
                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/resource/load-tree').then((res)=>{
                    Loading.hide();
                    let response = res.data;
                    _this.resources = response.content;
                    // 初始化树
                    _this.initTree();
                    // _this.listRoleResource();
                })
            },


            /**
             * 初始资源树
             */
            initTree() {
                let _this = this;
                let setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            idKey: "id",
                            pIdKey: "parent",
                            rootPId: "",
                            enable: true
                        }
                    }
                };

                _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.resources);
                _this.zTree.expandAll(true);
            },





        }


    }
</script>


