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
             <th>手机号</th>
             <th>密码</th>
             <th>呢称</th>
             <th>头像url</th>
             <th>注册时间</th>

            <th>操作</th>


            <th></th>
            </tr>
            </thead>

            <tbody>

            <tr v-for="member in members" :key="member.id">
                <td>{{member.id}}</td>
                <td>{{member.mobile}}</td>
                <td>{{member.password}}</td>
                <td>{{member.name}}</td>
                <td>{{member.photo}}</td>
                <td>{{member.registerTime}}</td>


            <!--            <td>Feb 12</td>-->


            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!--                        <button class="btn btn-xs btn-success">-->
                    <!--                            <i class="ace-icon fa fa-check bigger-120"></i>-->
                    <!--                        </button>-->

                    <button class="btn btn-xs btn-info" @click="edit(member)">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" @click="deleteData(member.id)">
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

        <div class="modal fade" tabindex="-1" role="dialog">
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
                                        <label  class="col-sm-2 control-label">手机号</label>
                                        <div class="col-sm-10">
                                            <input v-model="member.mobile" class="form-control"  placeholder="手机号">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">密码</label>
                                        <div class="col-sm-10">
                                            <input v-model="member.password" class="form-control"  placeholder="密码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">呢称</label>
                                        <div class="col-sm-10">
                                            <input v-model="member.name" class="form-control"  placeholder="呢称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">头像url</label>
                                        <div class="col-sm-10">
                                            <input v-model="member.photo" class="form-control"  placeholder="头像url">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">注册时间</label>
                                        <div class="col-sm-10">
                                            <input v-model="member.registerTime" class="form-control"  placeholder="注册时间">
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

    </div>

</template>

<script>

    import Pagination from '../../components/pagination.vue';
    // import Swal from 'sweetalert2';

    export default {
        name: "business-member",
        components: {
            Pagination
        },

        data() {
            return {
                members: [],
                member:{},
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("business-member-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/business/member/list').then((response)=>{
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/member/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.members = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.member = {};
                $(".modal").modal("show");
            },

            edit(member){
                let _this = this;
                $(".modal").modal("show");
                _this.member = $.extend({},member);
            },

            deleteData(id){
                let _this = this;
                Confirm.show("删除会员后不可恢复，确认删除？",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/member/delete/' + id).then((response) => {
                        Loading.hide();
                        let res = response.data;
                        console.log("jieguo add", response);
                        if(res.success){
                            // $(".modal").modal("hide");
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                        // _this.members = response.data.list;
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
                //         _this.$ajax.delete('http://127.0.0.1:9000/business/admin/member/delete/' + id).then((response) => {
                //             Loading.hide();
                //             let res = response.data;
                //             console.log("jieguo add", response);
                //             if(res.success){
                //                 // $(".modal").modal("hide");
                //                 _this.list(1);
                //                 Toast.success("删除成功!")
                //             }
                //             // _this.members = response.data.list;
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
                                   || !Validator.length(_this.member.mobile,"手机号",1,11)
                                   || !Validator.request(_this.member.password,"密码")
                                   || !Validator.length(_this.member.name,"呢称",1,50)
                                   || !Validator.length(_this.member.photo,"头像url",1,200)
                                ){
                    return;
                }

                //保存校验
                // if (!Validator.request(_this.member.name,"名称")
                //     || !Validator.request(_this.member.courseId,"课程ID")
                //     || !Validator.length(_this.member.courseId,"课程ID",1,8)){
                //     return;
                // }


                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/member/save',
                    _this.member
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
                    // _this.members = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },





        }


    }
</script>


