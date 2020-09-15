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

        <div class="row">
            <div v-for="course in courses" :key="course.id" class="col-md-2">
                <div class="thumbnail search-thumbnail">
                    <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg"/>
                    <img v-show="course.image" class="media-object" :src="course.image"/>
                    <div class="caption">
                        <div class="clearfix">
                            <span class="pull-right label label-primary info-label">{{COURSE_LEVEL | optionKV(course.level)}}</span>
                            <span class="pull-right label label-primary info-label">{{COURSE_CHARGE | optionKV(course.charge)}}</span>
                            <span class="pull-right label label-primary info-label">{{COURSE_STATUS | optionKV(course.status)}}</span>
                        </div>

                        <h3 class="search-title">
                            <a href="#" class="blue">{{course.name}}</a>
                        </h3>

                        <div v-for="teacher in teachers.filter(t=>{return t.id===course.teacherId})" class="profile-activity clearfix">
                            <div>
                                <img v-show="!teacher.image" class="pull-left"  src="/ace/assets/images/avatars/avatar5.png">
                                <img v-show="teacher.image" class="pull-left"  :src="teacher.image">
                                <a class="user" href="#">{{teacher.name}}</a>
                                <br>
                                {{teacher.position}}
                            </div>
                        </div>


                        <p>
                            <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>
                        </p>
                        <p>{{course.summary}}</p>

                        <div class="clearfix">
                            <span class="badge badge-info">{{course.id}}</span>
                            <span class="badge badge-info">排序：{{course.sort}}</span>
                            <span class="badge badge-info">时长：{{course.time  | formatSecond}}</span>
                            <!--                            <span class="pull-right label label-primary info-label">{{COURSE_CHARGE | optionKV(course.charge)}}</span>-->
                            <!--                            <span class="pull-right label label-primary info-label">{{COURSE_STATUS | optionKV(course.status)}}</span>-->
                        </div>
                        <p>

                            <button class="btn btn-white btn-xs btn-info btn-round" @click="toChapter(course)">
                                <!--                                <i class="ace-icon fa fa-pencil bigger-120"></i>-->
                                大章
                            </button>
                            &nbsp;
                            <button class="btn btn-white btn-xs btn-info btn-round" @click="editContent(course)">
                                <!--                                <i class="ace-icon fa fa-pencil bigger-120"></i>-->
                                内容
                            </button>
                            &nbsp;

                            <button class="btn btn-white btn-xs btn-info btn-round" @click="openSortModal(course)">
                                <!--                                <i class="ace-icon fa fa-pencil bigger-120"></i>-->
                                排序
                            </button>
                            <button class="btn btn-white btn-xs btn-info btn-round" @click="edit(course)">
                                <!--                                <i class="ace-icon fa fa-pencil bigger-120"></i>-->
                                编辑
                            </button>
                            &nbsp;
                            <button class="btn btn-white btn-xs btn-warning btn-round" @click="deleteData(course.id)">
                                <!--                                <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
                                删除
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div id="course-form" class="modal fade" tabindex="-1" role="dialog">
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
                                <label class="col-sm-2 control-label">
                                    分类
                                </label>
                                <div class="col-sm-10">
                                    <ul id="tree" class="ztree"></ul>
                                </div>

                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input v-model="course.name" class="form-control" placeholder="名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">讲师</label>
                                <div class="col-sm-10">
                                    <select v-model="course.teacherId" class="form-control">
                                        <option v-for="o in teachers" :value="o.id">{{o.name}}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">概述</label>
                                <div class="col-sm-10">
                                    <input v-model="course.summary" class="form-control" placeholder="概述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">时长</label>
                                <div class="col-sm-10">
                                    <input v-model="course.time" class="form-control" placeholder="时长">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">价格(元)</label>
                                <div class="col-sm-10">
                                    <input v-model="course.price" class="form-control" placeholder="价格(元)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">封面</label>
                                <div class="col-sm-10">
                                    <file :text="'上传封面'"
                                          :after-upload="afterUpload"
                                          :input-id="'image-upload'"
                                          :suffixs="['jpg','jpeg','png']"
                                          :use="FILE_USE.COURSE.key">
                                    </file>
                                    <div v-show="course.image" class="row">
                                        <div class="col-md-6">
                                            <img v-bind:src="course.image" class="img-responsive">
                                        </div>
                                    </div>
<!--                                    <input v-model="course.image" class="form-control" placeholder="封面">-->
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">级别</label>
                                <div class="col-sm-10">
                                    <select v-model="course.level" class="form-control">
                                        <option v-for="o in COURSE_LEVEL" :value="o.key">{{o.value}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">收费</label>
                                <div class="col-sm-10">
                                    <select v-model="course.charge" class="form-control">
                                        <option v-for="o in COURSE_CHARGE" :value="o.key">{{o.value}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-10">
                                    <select v-model="course.status" class="form-control">
                                        <option v-for="o in COURSE_STATUS" :value="o.key">{{o.value}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">报名数</label>
                                <div class="col-sm-10">
                                    <input v-model="course.enroll" class="form-control" placeholder="报名数">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <input v-model="course.sort" class="form-control disabled" placeholder="顺序" >
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


        <div id="course-content-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">内容编辑</h4>
                    </div>
                    <div class="modal-body">
                        <!--                    <p>One fine body&hellip;</p>-->
                        <form class="form-horizontal">

                            <div class="form-group">

                              <div class="col-lg-12">
                                  <div id="content"></div>
                              </div>

                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" @click="saveContent()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div id="course-sort-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">排序</h4>
                    </div>
                    <div class="modal-body">
                        <!--                    <p>One fine body&hellip;</p>-->
                        <form class="form-horizontal">

                            <div class="form-group">


                                <label class="control-label col-lg-3">
                                    当前排序
                                </label>

                                <div class="col-lg-9">
                                    <input class="form-control" v-model="sort.oldSort" name="oldSort" disabled>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-lg-3">
                                    新排序
                                </label>

                                <div class="col-lg-9">
                                    <input class="form-control" v-model="sort.newSort" name="newSort">
                                </div>
                            </div>


                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" @click="updateSort()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>

</template>

<script>

    import Pagination from '../../components/pagination.vue';
    import File from "../../components/file";
    // import Swal from 'sweetalert2';

    export default {
        name: "business-course",
        components: {
            Pagination,
            File
        },

        data() {
            return {
                courses: [],
                course: {},
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_CHARGE: COURSE_CHARGE,
                COURSE_STATUS: COURSE_STATUS,
                FILE_USE:FILE_USE,
                categorys:[],
                tree:{},
                sort:{
                    id:"",
                    oldSort:0,
                    newSort:0
                },
                teachers:[]
            }
        },
        mounted() {
            let _this = this;
            _this.$parent.activeSidebar("business-course-sidebar");
            // list(){
            //     let _this = this;
            //     _this.$ajax.get('http:127.0.0.1:9002/business/course/list').then((response)=>{
            //         console.log("jieguo",response);
            //     });
            //     // http.get()
            // }

            _this.allCategory();
            _this.allTeacher();
            _this.list(1);

        },


        methods: {
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("jieguo", response);
                    let res = response.data;
                    _this.courses = res.content.list;
                    _this.$refs.pagination.render(page, res.content.total);

                });
            },

            add() {
                let _this = this;// eslint-disable-line no-unused-vars
                _this.course = {
                    sort:_this.$refs.pagination.total + 1
                };
                _this.tree.checkAllNodes(false);
                $("#course-form").modal("show");
            },

            edit(course) {
                let _this = this;
                $("#course-form").modal("show");
                _this.listCategory(course.id);
                _this.course = $.extend({}, course);
            },

            deleteData(id) {
                let _this = this;
                Confirm.show("删除课程表后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id).then((response) => {
                        Loading.hide();
                        let res = response.data;
                        console.log("jieguo add", response);
                        if (res.success) {
                            // $(".modal").modal("hide");
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                        // _this.courses = response.data.list;
                        // _this.$refs.pagination.render(page, response.data.total);

                    });
                });

            },

            save() {
                let _this = this;
                //保存校验
                if (1 != 1
                    || !Validator.request(_this.course.name, "名称")
                    || !Validator.length(_this.course.name, "名称", 1, 50)
                    || !Validator.length(_this.course.summary, "概述", 1, 2000)
                    || !Validator.length(_this.course.image, "封面", 1, 100)) {
                    return;
                }

                let categorys = _this.tree.getCheckedNodes();
                if(Tool.isEmpty(categorys)){
                    Toast.warning("请选择分类！");
                    return;
                }
                _this.course.categorys = categorys;

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save',
                    _this.course
                ).then((response) => {
                    Loading.hide();
                    let res = response.data;
                    if (res.success) {
                        $("#course-form").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功!");
                    } else {
                        Toast.warning(res.message);
                    }
                    // _this.courses = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });
            },

            toChapter(course) {
                let _this = this;
                SessionStorage.set(SESSION_KEY_COURSE, course);
                _this.$router.push("/business/chapter");

            },

            initTree(){
                let _this = this;
                let setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            idKey:"id",
                            pIdKey:"parent",
                            rootPId:"00000000",
                            enable: true
                        }
                    }
                };

                let zNodes = _this.categorys;

                // let zNodes =[
                //     { id:1, pId:0, name:"随意勾选 1", open:true},
                //     { id:11, pId:1, name:"随意勾选 1-1", open:true},
                //     { id:111, pId:11, name:"随意勾选 1-1-1"},
                //     { id:112, pId:11, name:"随意勾选 1-1-2"},
                //     { id:12, pId:1, name:"随意勾选 1-2", open:true},
                //     { id:121, pId:12, name:"随意勾选 1-2-1"},
                //     { id:122, pId:12, name:"随意勾选 1-2-2"},
                //     { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
                //     { id:21, pId:2, name:"随意勾选 2-1"},
                //     { id:22, pId:2, name:"随意勾选 2-2", open:true},
                //     { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
                //     { id:222, pId:22, name:"随意勾选 2-2-2"},
                //     { id:23, pId:2, name:"随意勾选 2-3"}
                // ];

                _this.tree = $.fn.zTree.init($("#tree"), setting, zNodes);
                //展开所有节点
                // _this.tree.expandAll(true);
            },

            allTeacher() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/all').then((response) => {
                    Loading.hide();
                    let res = response.data;
                    _this.teachers = res.content;

                });
            },

            allCategory() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response) => {
                    Loading.hide();
                    let res = response.data;
                    _this.categorys = res.content;
                    _this.initTree();
                });
            },

            listCategory(courseId){
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list-category/' + courseId).then((response)=>{
                    Loading.hide();
                    let res = response.data;
                    let categorys = res.content;


                    _this.tree.checkAllNodes(false);

                    //勾选查询到的分类
                    for (let i=0; i<categorys.length; i++){
                        let node = _this.tree.getNodeByParam("id",categorys[i].categoryId);
                        _this.tree.checkNode(node,true);
                    }


                });
            },

            editContent(course){
                let _this = this;
                let id = course.id;
                _this.course = course;

                $("#content").summernote({
                    focus:true,
                    height:300
                });

                //先清空历史文本
                $("#content").summernote('code','');
                Loading.show();

                _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/course/find-content/' + id).then((response)=>{
                    Loading.hide();
                    let res = response.data;

                    if(res.success){
                        $("#course-content-modal").modal({backdrop:'static',keyboard:false});
                        if(res.content){
                            $("#content").summernote('code',res.content.content);
                        }

                        //定时保存
                        let saveContentInterval = setInterval(function () {
                            _this.saveContent();
                        },5000);

                        //关闭内容框时，清空自动保存任务
                        $("#course-content-modal").on('hidden.bs.modal',function (e) {
                            clearInterval(saveContentInterval);
                        });

                    }else{
                        Toast.warning(res.message);
                    }

                });
            },

            saveContent(){
                let _this = this;
                let content = $("#content").summernote("code");
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save-content',{
                    id:_this.course.id,
                    content:content
                }).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if(resp.success){
                        Toast.success("内容保存成功");
                    }else{
                        Toast.warning(resp.message);
                    }
                });
            },

            openSortModal(course){
                let _this = this;
                _this.sort = {
                    id:course.id,
                    oldSort: course.sort,
                    newSort:course.sort
                };

                $("#course-sort-modal").modal("show");
            },

            updateSort(){
                let _this = this;
                if(_this.sort.newSort === _this.sort.oldSort){
                    Toast.warning("排序没有变化");
                    return;
                }
                Loading.show();

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/sort',_this.sort).then((res)=>{
                   let response = res.data;
                   if(response.success){
                       Toast.success("更新排序成功");
                       $("#course-sort-modal").modal("hide");
                       _this.list(1);
                   }else {
                       Toast.warning("更新排序失败");
                   }
                });



            },


            afterUpload(resp) {
                let _this = this;
                let image = resp.content.path;
                _this.course.image = image;
            },


        }


    }
</script>

<!-- scoped  style下的样式只应用于当前组件，防止互相污染 -->

<style scoped>
    .caption h3 {
        font-size: 20px;
    }

    /*根据不同分辨率 显示不同大小的字体*/
    @media (max-width: 1199px) {
        .caption h3{
            font-size: 16px;
        }
    }

</style>


