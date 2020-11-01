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
             <th>名称</th>
             <th>页面</th>
             <th>请求</th>
             <th>父id</th>

            <th>操作</th>


            <th></th>
            </tr>
            </thead>

            <tbody>

            <tr v-for="resource in resources" :key="resource.id">
                <td>{{resource.id}}</td>
                <td>{{resource.name}}</td>
                <td>{{resource.page}}</td>
                <td>{{resource.request}}</td>
                <td>{{resource.parent}}</td>


            <!--            <td>Feb 12</td>-->


            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!--                        <button class="btn btn-xs btn-success">-->
                    <!--                            <i class="ace-icon fa fa-check bigger-120"></i>-->
                    <!--                        </button>-->

                    <button class="btn btn-xs btn-info" @click="edit(resource)">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" @click="deleteData(resource.id)">
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
                                        <label  class="col-sm-2 control-label">名称</label>
                                        <div class="col-sm-10">
                                            <input v-model="resource.name" class="form-control"  placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">页面</label>
                                        <div class="col-sm-10">
                                            <input v-model="resource.page" class="form-control"  placeholder="页面">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">请求</label>
                                        <div class="col-sm-10">
                                            <input v-model="resource.request" class="form-control"  placeholder="请求">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">父id</label>
                                        <div class="col-sm-10">
                                            <input v-model="resource.parent" class="form-control"  placeholder="父id">
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
        name: "system-resource",
        components: {
            Pagination
        },

        data() {
            return {
                resources: [],
                resource:{},
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("system-resource-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/system/resource/list').then((response)=>{
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.resources = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.resource = {};
                $(".modal").modal("show");
            },

            edit(resource){
                let _this = this;
                $(".modal").modal("show");
                _this.resource = $.extend({},resource);
            },

            deleteData(id){
                let _this = this;
                Confirm.show("删除资源后不可恢复，确认删除？",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/resource/delete/' + id).then((response) => {
                        Loading.hide();
                        let res = response.data;
                        console.log("jieguo add", response);
                        if(res.success){
                            // $(".modal").modal("hide");
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                        // _this.resources = response.data.list;
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
                //         _this.$ajax.delete('http://127.0.0.1:9000/system/admin/resource/delete/' + id).then((response) => {
                //             Loading.hide();
                //             let res = response.data;
                //             console.log("jieguo add", response);
                //             if(res.success){
                //                 // $(".modal").modal("hide");
                //                 _this.list(1);
                //                 Toast.success("删除成功!")
                //             }
                //             // _this.resources = response.data.list;
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
                                   || !Validator.request(_this.resource.name,"名称")
                   || !Validator.length(_this.resource.name,"名称",1,100)
                                   || !Validator.length(_this.resource.page,"页面",1,50)
                                   || !Validator.length(_this.resource.request,"请求",1,200)
                                ){
                    return;
                }

                //保存校验
                // if (!Validator.request(_this.resource.name,"名称")
                //     || !Validator.request(_this.resource.courseId,"课程ID")
                //     || !Validator.length(_this.resource.courseId,"课程ID",1,8)){
                //     return;
                // }


                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save',
                    _this.resource
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
                    // _this.resources = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },





        }


    }
</script>


