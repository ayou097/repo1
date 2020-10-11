let tagsApp = new Vue({
    el:'#tagsApp',
    data:{
        tags:[
            {id:1,name:'Spring'},
            {id:2,name:'Spring MVC'},
            {id:3,name:'Mybatis'},
            {id:4,name:'Spring Boot'},
            {id:5,name:'Spring Cloud'},
        ]
    },
    methods:{
        loadTags:function () {
            //alert("准备加载标签列表.....")
            $.ajax({
                url:'/api-question/v1/tags',
                success:function (json) {
                    tagsApp.tags = json.data;
                }
            });
        }
    },
    created:function () {
        this.loadTags();
    }
});