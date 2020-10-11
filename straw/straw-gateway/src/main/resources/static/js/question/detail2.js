let answerApp = new Vue({
    el:'#answerApp',
    data:{
        answers:[
            {
                userNickName:'范老师',
                gmtCreateText:'30分钟前',
                content:'<span style="color: #00f">这个问题有点难啊</span>'
            }
        ]
    },
    methods:{
        postAnswer:function () {
            let questionId = location.search.substring(1);
            let content = $('#summernote').val();
            let data = {
                questionId:questionId,
                content:content
            };
            $.ajax({
                url:'/api-question/v1/answers/post',
                data:data,
                type:'POST',
                success:function (json) {
                    if(json.state == 2000){
                        //alert('发布答案成功!');
                        $('#summernote').summernote('reset');
                        answerApp.loadAnswers();
                        location.href = '#answers';
                    } else {
                        alert('[错误]'+json.message);
                    }
                }
            });
        },
        loadAnswers:function () {
            let questionId = location.search.substring(1);
            $.ajax({
                url:'/api-question/v1/answers',
                data:'questionId='+questionId,
                success:function (json) {
                    let answers = json.data;
                    for (let i=0;i<answers.length;i++){
                        answers[i].gmtCreateText = getTimeText(answers[i].gmtCreate);
                    }
                    answerApp.answers = answers;
                }
            });
        },
        postComment:function (answerId) {
            let content = $("#postCommentContent"+answerId).val();
            //alert('即将发表评论,正文:' +content);
            $.ajax({
                url:'/api-question/v1/comments/post',
                data:{
                    answerId:answerId,
                    content:content
                },
                type:'POST',
                success:function (json) {
                    if(json.state == 2000){
                        //alert('发表评论成功!');
                        answerApp.loadanswers
                    } else {
                        alert('[失败]'+json.message);
                    }
                }
            });
        }
    },
    created:function () {
        this.loadAnswers();
    }
});