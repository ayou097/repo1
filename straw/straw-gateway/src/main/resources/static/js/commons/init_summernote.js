$(document).ready(function () {
    $('#summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        placeholder: '请输入问题的详细描述...',
        callbacks:{
            onImageUpload:function (files) {
                //alert('上传回调...')
                if(files.length>1){
                    alert('一次最多只能选择1张图片!')
                    return;
                }
                let file = files[0];
                let data = new FormData();
                data.append('file',file);
                $.ajax({
                    url:'/api-question/v1/questions/image/upload',
                    data:data,
                    contentType:false,
                    processData:false,
                    type:'POST',
                    success:function (json) {
                        if(json.state == 2000){
                            //alert('上传成功!');
                            let img = new Image();//得到<img>标签对象
                            img.src = json.data;//设置<img src="xxx">
                            $('#summernote').summernote('insertNode',img);
                        }else {
                            alert(json.message);
                        }
                    }
                });
            }
        }
    });
});