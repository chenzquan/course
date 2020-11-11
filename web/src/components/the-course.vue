<template>
    <div class="card mb-4 shadow-sm">
        <img class="img-fluid" :src="course.image">
        <!--                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>-->
        <div class="card-body">
            <h4>{{course.name}}</h4>
            <p class="card-text">{{course.summary}}</p>
            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <router-link :to="'/detail?id=' + course.id" type="button" class="btn btn-sm btn-outline-secondary">课程详情</router-link>
<!--                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>-->
                </div>
                <div class="text-muted">
                    <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i>&nbsp;{{course.price}}</span>&nbsp;
                    <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i> &nbsp;{{course.enroll}}</span>&nbsp;
                    <span class="badge badge-info">{{COURSE_LEVEL | optionKV(course.level)}}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "the-course",
        props:{
            course:{}
        },
        data:function () {
            return{
                COURSE_LEVEL:COURSE_LEVEL
            }
        }
    }
</script>

<style scoped>
    .course h4{
        font-size:1.25rem;
        margin:15px 0;
    }

    .course .text-muted .badge{
        font-size: 1rem;
    }


</style>
