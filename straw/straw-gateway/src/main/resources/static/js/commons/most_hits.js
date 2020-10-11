let mostHitsApp = new Vue({
    el:'#mostHitsApp',
    data:{
        questions:[]
    },
    methods:{
        loadQuestions:function () {
            $.ajax({
                url:'/api-question/v1/questions/most-hits',
                success:function (json) {
                    let list = json.data;
                    let statusTexts = ['未回复','未解决','已解决'];
                    let statusClasses = ['text-warning','text-info','text-success'];
                    for (let i=0;i<list.length;i++){
                        list[i].statusText = statusTexts[list[i].status];
                        list[i].statusClass = statusClasses[list[i].status];
                    }
                    mostHitsApp.questions = list;
                }
            });
        }
    },
    created:function () {
        this.loadQuestions();
    }
});