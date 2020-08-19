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
                <th>ID</th>
                <th>名称</th>
                <th>课程ID</th>
                <th>操作</th>


                <th></th>
            </tr>
            </thead>

            <tbody>

            <tr v-for="chapter in chapters" :key="chapter.id">


                <td>{{chapter.id}}</td>

                <td>{{chapter.name}}</td>
                <td>{{chapter.courseId}}</td>

                <!--            <td>Feb 12</td>-->


                <td>
                    <div class="hidden-sm hidden-xs btn-group">
<!--                        <button class="btn btn-xs btn-success">-->
<!--                            <i class="ace-icon fa fa-check bigger-120"></i>-->
<!--                        </button>-->

                        <button class="btn btn-xs btn-info" @click="edit(chapter)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" @click="deleteData(chapter.id)">
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
                                    <input v-model="chapter.name" class="form-control"  placeholder="名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label  class="col-sm-2 control-label">课程ID</label>
                                <div class="col-sm-10">
                                    <input v-model="chapter.courseId" class="form-control"  placeholder="课程ID">
                                </div>
                            </div>
<!--                            <div class="form-group">-->
<!--                                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
<!--                                <div class="col-sm-10">-->
<!--                                    <input type="password" class="form-control" id="inputPassword3"-->
<!--                                           placeholder="Password">-->
<!--                                </div>-->
<!--                            </div>-->
<!--                            <div class="form-group">-->
<!--                                <div class="col-sm-offset-2 col-sm-10">-->
<!--                                    <div class="checkbox">-->
<!--                                        <label>-->
<!--                                            <input type="checkbox"> Remember me-->
<!--                                        </label>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </div>-->
<!--                            <div class="form-group">-->
<!--                                <div class="col-sm-offset-2 col-sm-10">-->
<!--                                    <button type="submit" class="btn btn-default">Sign in</button>-->
<!--                                </div>-->
<!--                            </div>-->
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

    export default {
        name: "chapter",
        components: {
            Pagination
        },

        data() {
            return {
                chapters: [],
                chapter:{}
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("business-chapter-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/business/chapter/list').then((response)=>{
            //         console.log("jieguo",response);
            //     });
            //     // http.get()
            // }


            _this.list(1);

        },


        methods: {
            list(page) {
                let _this = this;
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    console.log("jieguo", response);
                    let res = response.data;
                    _this.chapters = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.chapter = {};
                $(".modal").modal("show");
            },

            edit(chapter){
                let _this = this;
                $(".modal").modal("show");
                _this.chapter = $.extend({},chapter);
            },

            deleteData(id){
                let _this = this;
                _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id).then((response) => {
                    let res = response.data;
                    console.log("jieguo add", response);
                    if(res.success){
                        // $(".modal").modal("hide");
                        _this.list(1);
                    }
                    // _this.chapters = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },

            save(){
                let _this = this;
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',
                    _this.chapter
                ).then((response) => {
                    let res = response.data;
                    console.log("jieguo add", response);
                    if(res.success){
                        $(".modal").modal("hide");
                        _this.list(1);
                    }
                    // _this.chapters = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },





        }


    }
</script>


