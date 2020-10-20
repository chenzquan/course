<template>
    <!--  <div id="app">-->
    <!--    <img alt="Vue logo" src="./assets/logo.png">-->
    <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <!--  </div>-->
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h1>
                                <i class="ace-icon fa fa-leaf green"></i>
                                <span class="black">控制台登录</span>
                                <!--                <span class="white" id="id-text2">Application</span>-->
                            </h1>
                            <h4 class="blue" id="id-company-text">&copy; Company Name</h4>
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="ace-icon fa fa-coffee green"></i>
                                            请输入用户名和密码
                                        </h4>

                                        <div class="space-6"></div>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.loginName" type="text" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.password" type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <div class="space"></div>

                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <input v-model="remember" type="checkbox" class="ace" />
                                                        <span class="lbl"> 记住我</span>
                                                    </label>

                                                    <button type="button" class="width-35 pull-right btn btn-sm btn-primary" @click="login()">
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110">登录</span>
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>

                                        <!--                    <div class="social-or-login center">-->
                                        <!--                      <span class="bigger-110">Or Login Using</span>-->
                                        <!--                    </div>-->

                                        <!--                    <div class="space-6"></div>-->

                                        <!--                    <div class="social-login center">-->
                                        <!--                      <a class="btn btn-primary">-->
                                        <!--                        <i class="ace-icon fa fa-facebook"></i>-->
                                        <!--                      </a>-->

                                        <!--                      <a class="btn btn-info">-->
                                        <!--                        <i class="ace-icon fa fa-twitter"></i>-->
                                        <!--                      </a>-->

                                        <!--                      <a class="btn btn-danger">-->
                                        <!--                        <i class="ace-icon fa fa-google-plus"></i>-->
                                        <!--                      </a>-->
                                        <!--                    </div>-->
                                    </div><!-- /.widget-main -->

                                    <!--                  <div class="toolbar clearfix">-->
                                    <!--                    <div>-->
                                    <!--                      <a href="#" data-target="#forgot-box" class="forgot-password-link">-->
                                    <!--                        <i class="ace-icon fa fa-arrow-left"></i>-->
                                    <!--                        I forgot my password-->
                                    <!--                      </a>-->
                                    <!--                    </div>-->

                                    <!--                    <div>-->
                                    <!--                      <a href="#" data-target="#signup-box" class="user-signup-link">-->
                                    <!--                        I want to register-->
                                    <!--                        <i class="ace-icon fa fa-arrow-right"></i>-->
                                    <!--                      </a>-->
                                    <!--                    </div>-->
                                    <!--                  </div>-->
                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->

                            <div id="forgot-box" class="forgot-box widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header red lighter bigger">
                                            <i class="ace-icon fa fa-key"></i>
                                            Retrieve Password
                                        </h4>

                                        <div class="space-6"></div>
                                        <p>
                                            Enter your email and to receive instructions
                                        </p>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
                                                </label>

                                                <div class="clearfix">
                                                    <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                        <i class="ace-icon fa fa-lightbulb-o"></i>
                                                        <span class="bigger-110">Send Me!</span>
                                                    </button>
                                                </div>
                                            </fieldset>
                                        </form>
                                    </div><!-- /.widget-main -->

                                    <div class="toolbar center">
                                        <a href="#" data-target="#login-box" class="back-to-login-link">
                                            Back to login
                                            <i class="ace-icon fa fa-arrow-right"></i>
                                        </a>
                                    </div>
                                </div><!-- /.widget-body -->
                            </div><!-- /.forgot-box -->

                            <div id="signup-box" class="signup-box widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header green lighter bigger">
                                            <i class="ace-icon fa fa-users blue"></i>
                                            New User Registration
                                        </h4>

                                        <div class="space-6"></div>
                                        <p> Enter your details to begin: </p>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repeat password" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
                                                </label>

                                                <label class="block">
                                                    <input type="checkbox" class="ace" />
                                                    <span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
                                                </label>

                                                <div class="space-24"></div>

                                                <div class="clearfix">
                                                    <button type="reset" class="width-30 pull-left btn btn-sm">
                                                        <i class="ace-icon fa fa-refresh"></i>
                                                        <span class="bigger-110">Reset</span>
                                                    </button>

                                                    <button type="button" class="width-65 pull-right btn btn-sm btn-success">
                                                        <span class="bigger-110">Register</span>

                                                        <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                                    </button>
                                                </div>
                                            </fieldset>
                                        </form>
                                    </div>

                                    <div class="toolbar center">
                                        <a href="#" data-target="#login-box" class="back-to-login-link">
                                            <i class="ace-icon fa fa-arrow-left"></i>
                                            Back to login
                                        </a>
                                    </div>
                                </div><!-- /.widget-body -->
                            </div><!-- /.signup-box -->
                        </div><!-- /.position-relative -->

                        <div class="navbar-fixed-top align-right">
                            <br />
                            &nbsp;
                            <a id="btn-login-dark" href="#">Dark</a>
                            &nbsp;
                            <span class="blue">/</span>
                            &nbsp;
                            <a id="btn-login-blur" href="#">Blur</a>
                            &nbsp;
                            <span class="blue">/</span>
                            &nbsp;
                            <a id="btn-login-light" href="#">Light</a>
                            &nbsp; &nbsp; &nbsp;
                        </div>
                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</template>

<script>
    // import HelloWorld from './components/HelloWorld.vue'
    // import 'bootstrap/dist/css/bootstrap.min.css'
    // import 'jquery/dist/jquery.min.js'
    // import 'bootstrap/dist/js/bootstrap.min.js'

    export default {
        name: 'App',
        // components: {
        //   HelloWorld
        // }


        data:function(){
          return {
              user:{},
              remember:true
          }
        },


        methods:{
            login(){
                let _this = this;
                let passwordShow = _this.user.password;
                _this.user.password = hex_md5(_this.user.password + KEY);
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login',
                    _this.user
                ).then((response) => {
                    Loading.hide();
                    let res = response.data;
                    // console.log("jieguo add", response);
                    if(res.success){
                        console.log("response.content",res.content);
                        // SessionStorage.set("USER",res.content);
                        let loginUser = res.content;
                        Tool.setLoginUser(res.content);
                        if(_this.remember){
                            LocalStorage.set(LOCAL_KEY_REMEMBER_USER,{
                                loginName:loginUser.loginName,
                                password:passwordShow
                            });
                        }else{
                            LocalStorage.set(LOCAL_KEY_REMEMBER_USER,null);
                        }

                        _this.$router.push("/welcome");
                    }else{
                        Toast.warning(res.message);
                    }
                    // _this.users = response.data.list;
                    // _this.$refs.pagination.render(page, response.data.total);

                });


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



        },
        mounted() {
            let _this = this;
            $('body').removeClass('no-skin');
            $('body').attr('class', 'login-layout light-login');

            let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER);
            if(rememberUser){
                _this.user = rememberUser;
            }
        }


    }
</script>

<style>

</style>
