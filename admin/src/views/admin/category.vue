<template>
    <div>

        <div class="row">
            <div class="col-md-6">
                <p>
                    <button @click="add1()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-edit"></i>新增一级
                    </button>
                    &nbsp;
                    <button @click="all()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-refresh"></i>刷新
                    </button>
                </p>
                <table id="level1-table" class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
<!--                        <th>父id</th>-->
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>

                    <tr v-for="category in level1" :key="category.id" @click="onClickLevel1(category)" :class="{'active' : active.id === category.id}">
                        <td>{{category.id}}</td>
<!--                        <td>{{category.parent}}</td>-->
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>
                        <!--            <td>Feb 12</td>-->
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" @click="edit(category)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-danger" @click="deleteData(category.id)">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
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
            </div>


            <div class="col-md-6">
                <p>
                    <button @click="add2()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-edit"></i>新增二级
                    </button>
                    &nbsp;
                    <button @click="all()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-refresh"></i>刷新
                    </button>
                </p>
                <table id="level2-table" class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
<!--                        <th>父id</th>-->
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>

                    <tr v-for="category in level2" :key="category.id">
                        <td>{{category.id}}</td>
<!--                        <td>{{category.parent}}</td>-->
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>
                        <!--            <td>Feb 12</td>-->
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" @click="edit(category)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-danger" @click="deleteData(category.id)">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
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
            </div>
        </div>

<!--        <p>-->

<!--            <button @click="add()" class="btn btn-white btn-default btn-round">-->
<!--                <i class="ace-icon fa fa-edit"></i>新增-->
<!--            </button>-->
<!--            &nbsp;-->
<!--            <button @click="all()" class="btn btn-white btn-default btn-round">-->
<!--                <i class="ace-icon fa fa-refresh"></i>刷新-->
<!--            </button>-->
<!--        </p>-->
<!--        <table id="simple-table" class="table  table-bordered table-hover">-->
<!--            <thead>-->
<!--            <tr>-->
<!--                             <th>id</th>-->
<!--             <th>父id</th>-->
<!--             <th>名称</th>-->
<!--             <th>顺序</th>-->

<!--            <th>操作</th>-->


<!--            <th></th>-->
<!--            </tr>-->
<!--            </thead>-->

<!--            <tbody>-->

<!--            <tr v-for="category in categorys" :key="category.id">-->
<!--                <td>{{category.id}}</td>-->
<!--                <td>{{category.parent}}</td>-->
<!--                <td>{{category.name}}</td>-->
<!--                <td>{{category.sort}}</td>-->


<!--            &lt;!&ndash;            <td>Feb 12</td>&ndash;&gt;-->


<!--            <td>-->
<!--                <div class="hidden-sm hidden-xs btn-group">-->
<!--                    &lt;!&ndash;                        <button class="btn btn-xs btn-success">&ndash;&gt;-->
<!--                    &lt;!&ndash;                            <i class="ace-icon fa fa-check bigger-120"></i>&ndash;&gt;-->
<!--                    &lt;!&ndash;                        </button>&ndash;&gt;-->

<!--                    <button class="btn btn-xs btn-info" @click="edit(category)">-->
<!--                        <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--                    </button>-->

<!--                    <button class="btn btn-xs btn-danger" @click="deleteData(category.id)">-->
<!--                        <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--                    </button>-->

<!--                    &lt;!&ndash;                        <button class="btn btn-xs btn-warning">&ndash;&gt;-->
<!--                    &lt;!&ndash;                            <i class="ace-icon fa fa-flag bigger-120"></i>&ndash;&gt;-->
<!--                    &lt;!&ndash;                        </button>&ndash;&gt;-->
<!--                </div>-->

<!--                <div class="hidden-md hidden-lg">-->
<!--                    <div class="inline pos-rel">-->
<!--                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"-->
<!--                                data-position="auto">-->
<!--                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>-->
<!--                        </button>-->

<!--                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">-->
<!--                            <li>-->
<!--                                <a href="#" class="tooltip-info" data-rel="tooltip" title="View">-->
<!--                                                                    <span class="blue">-->
<!--                                                                        <i class="ace-icon fa fa-search-plus bigger-120"></i>-->
<!--                                                                    </span>-->
<!--                                </a>-->
<!--                            </li>-->

<!--                            <li>-->
<!--                                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">-->
<!--                                                                    <span class="green">-->
<!--                                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>-->
<!--                                                                    </span>-->
<!--                                </a>-->
<!--                            </li>-->

<!--                            <li>-->
<!--                                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">-->
<!--                                                                    <span class="red">-->
<!--                                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--                                                                    </span>-->
<!--                                </a>-->
<!--                            </li>-->
<!--                        </ul>-->
<!--                    </div>-->
<!--                </div>-->
<!--            </td>-->
<!--            </tr>-->

<!--            </tbody>-->
<!--        </table>-->

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
                                        <label  class="col-sm-2 control-label">父分类</label>
                                        <div class="col-sm-10">
<!--                                            <input v-model="category.parent" class="form-control"  placeholder="父id">-->
                                            <p class="form-control-static">{{active.name || "无"}}</p>
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


    // import Swal from 'sweetalert2';

    export default {
        name: "business-category",
        data() {
            return {
                categorys: [],
                category:{},
                level1:[],
                level2:[],
                active:{}
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("business-category-sidebar");
            _this.all();

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

            all() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response) => {
                    Loading.hide();
                    let res = response.data;
                    _this.categorys = res.content;
                    //将数据 变成树形 结构
                    _this.level1 = [];
                    for(let i=0; i<_this.categorys.length; i++){
                        let c = _this.categorys[i];
                        if(c.parent === '00000000'){
                            _this.level1.push(c);
                            for(let j=0; j<_this.categorys.length; j++){
                                let child = _this.categorys[j];
                                if(child.parent === c.id){
                                    if(Tool.isEmpty(c.children)){
                                        c.children = [];
                                    }
                                    c.children.push(child);
                                }
                            }
                        }
                    }

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
                            _this.all();
                            Toast.success("删除成功!")
                        }
                        // _this.categorys = response.data.list;
                        // _this.$refs.pagination.render(page, response.data.total);

                    });
                });
            },

            save(){
                let _this = this;
                //保存校验
                if (1!=1
                    || !Validator.request(_this.category.parent,"父id")
                    || !Validator.request(_this.category.name,"名称")
                    || !Validator.length(_this.category.name,"名称",1,50)){
                    return;
                }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save',
                    _this.category
                ).then((response) => {
                    let res = response.data;
                    console.log("jieguo add", response);
                    if(res.success){
                        $(".modal").modal("hide");
                        _this.all();
                        Toast.success("保存成功!");
                    }else{
                        Toast.warning(res.message);
                    }
                    // _this.categorys = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);
                });
            },

            onClickLevel1(category){
                let _this = this;
                _this.active = category;
                _this.level2 = category.children;
            },

            add1(){
                let _this = this;
                _this.active = {};
                _this.level2 = [];
                _this.category = {
                    parent:'00000000'
                };
                $(".modal").modal("show");
            },

            add2(){
                let _this = this;
                if(Tool.isEmpty(_this.active)){
                    Toast.warning("请先点击一级分类");
                    return;
                }

                _this.category = {
                    parent:_this.active.id
                };

                $(".modal").modal("show");

            }




        }


    }
</script>

<style scoped>
    .active td{
        background-color: #d6e9c6 !important;
    }
</style>


