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
             <th>父id</th>
             <th>名称</th>
             <th>顺序</th>

            <th>操作</th>


            <th></th>
            </tr>
            </thead>

            <tbody>

            <tr v-for="category in categorys" :key="category.id">
                <td>{{category.id}}</td>
                <td>{{category.parent}}</td>
                <td>{{category.name}}</td>
                <td>{{category.sort}}</td>


            <!--            <td>Feb 12</td>-->


            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!--                        <button class="btn btn-xs btn-success">-->
                    <!--                            <i class="ace-icon fa fa-check bigger-120"></i>-->
                    <!--                        </button>-->

                    <button class="btn btn-xs btn-info" @click="edit(category)">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" @click="deleteData(category.id)">
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
                                        <label  class="col-sm-2 control-label">父id</label>
                                        <div class="col-sm-10">
                                            <input v-model="category.parent" class="form-control"  placeholder="父id">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">名称</label>
                                        <div class="col-sm-10">
                                            <input v-model="category.name" class="form-control"  placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">顺序</label>
                                        <div class="col-sm-10">
                                            <input v-model="category.sort" class="form-control"  placeholder="顺序">
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
        name: "business-category",
        components: {
            Pagination
        },

        data() {
            return {
                categorys: [],
                category:{},
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("business-category-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/business/category/list').then((response)=>{
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.categorys = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.category = {};
                $(".modal").modal("show");
            },

            edit(category){
                let _this = this;
                $(".modal").modal("show");
                _this.category = $.extend({},category);
            },

            deleteData(id){
                let _this = this;
                Confirm.show("删除分类后不可恢复，确认删除？",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id).then((response) => {
                        Loading.hide();
                        let res = response.data;
                        console.log("jieguo add", response);
                        if(res.success){
                            // $(".modal").modal("hide");
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                        // _this.categorys = response.data.list;
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
                //         _this.$ajax.delete('http://127.0.0.1:9000/business/admin/category/delete/' + id).then((response) => {
                //             Loading.hide();
                //             let res = response.data;
                //             console.log("jieguo add", response);
                //             if(res.success){
                //                 // $(".modal").modal("hide");
                //                 _this.list(1);
                //                 Toast.success("删除成功!")
                //             }
                //             // _this.categorys = response.data.list;
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
                                   || !Validator.request(_this.category.parent,"父id")
                                   || !Validator.request(_this.category.name,"名称")
                   || !Validator.length(_this.category.name,"名称",1,50)
                                ){
                    return;
                }

                //保存校验
                // if (!Validator.request(_this.category.name,"名称")
                //     || !Validator.request(_this.category.courseId,"课程ID")
                //     || !Validator.length(_this.category.courseId,"课程ID",1,8)){
                //     return;
                // }


                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save',
                    _this.category
                ).then((response) => {
                    let res = response.data;
                    console.log("jieguo add", response);
                    if(res.success){
                        $(".modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功!");
                    }else{
                        Toast.warning(res.message);
                    }
                    // _this.categorys = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },





        }


    }
</script>


