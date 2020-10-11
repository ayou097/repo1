let registerApp = new Vue({
    el:'#registerApp',
    methods:{
        register:function () {
            //alert('准备提交注册！')
            $.ajax({
               url:'/api-user/v1/users/reg',
               data:$('#form-reg').serialize(),
               type:'POST',
               success:function (json) {
                   if(json.state ==2000){
                       alert('注册成功！');
                   } else {
                       alert(json.message);
                   }
               }
            });
        }
    }
});