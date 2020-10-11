let questionDetailApp = new Vue({
    el:'#questionDetailApp',
    data:{
        question:{
            tags:[
                {id:1,name:'Java基础'},
                {id:2,name:'JavaEE'},
            ],
            hits:123,
            title:'测试标题',
            content:'<span style="color:#f00">测试</span>正文',
            userNickName:'苍老师',
            gmtCreateText:'2天前'
        }
    },
    methods:{
        loadQuestionDetail:function () {
            //alert('准备加载问题详情...');

            let id = location.search.substring(1);
            if(id =="" || isNaN(id) || id<1){
                alert('参数错误!');
                location.href = '/index.html';
                return;
            }
            $.ajax({
                url:'/api-question/v1/questions/'+id,
                success:function (json) {
                    if(json.state == 2000){
                        let question = json.data;
                        question.gmtCreateText = getTimeText(question.gmtCreate);
                        questionDetailApp.question = question;
                    } else {
                        alert(json.message);
                        location.href = '/index.html';
                    }

                }
            });
        }
    },
    created:function () {
        this.loadQuestionDetail();
    }
});