Vue.component('v-select', VueSelect.VueSelect)
let postQuestionApp = new Vue({
    el: '#postQuestionApp',
    data: {
        title:null,
        tags: [],
        selectedTagIds: [],
        teachers: [],
        selectedTeacherIds: []
    },
    methods: {
        loadTags: function () {
            $.ajax({
                url: '/api-question/v1/tags',
                success: function (json) {
                    let list = json.data;
                    let tags = [];
                    for (let i = 0; i < list.length; i++) {
                        let op = {};
                        op.label = list[i].name;
                        op.value = list[i].id;
                        tags[i] = op;
                    }
                    postQuestionApp.tags = tags;
                }
            });
        },
        loadTeachers:function () {
            $.ajax({
                url: '/api-user/v1/users/teachers/select-option',
                success:function (json) {
                    let list = json.data;
                    let teachers = [];
                    for (let i=0;i<list.length;i++){
                        let op = {};
                        op.label = list[i].nickname;
                        op.value = list[i].id;
                        teachers[i]=op;
                    }
                    postQuestionApp.teachers = teachers;
                }
            });
        },
        postQuestion:function () {
            let data = {
                title:postQuestionApp.title,
                content:$('#summernote').val(),
                tagIds:postQuestionApp.selectedTagIds,
                teacherIds:postQuestionApp.selectedTeacherIds
            }
            console.log("即将提交到服务器的数据如下:");
            console.log(data);

            $.ajax({
                url:'/api-question/v1/questions/post',
                data:data,
                type:'POST',
                traditional: true,
                success:function (json) {
                    if(json.state == 2000){
                        alert('发布问题成功!')
                    } else {
                        alert('[错误]'+json.message);
                    }
                }
            });
        }
    },
    created: function () {
        this.loadTags();
        this.loadTeachers();
    }
});