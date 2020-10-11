let myQuestionsApp = new Vue({
    el: '#myQuestionsApp',
    data: {
        questions: [
            {
                statusText: '未回复',
                statusClass: 'badge-warning',
                title: '测试问题1',
                content: '<i>测试</i> <span style="color:red">正文1</span>',
                tags: [
                    { id: 1, name: 'Java基础' },
                    { id: 2, name: 'JavaSE' },
                    { id: 3, name: 'JavaOOP' },
                ],
                userNickName: '蜡笔小新',
                hits: 365,
                gmtCreateText: '3分钟前'
            },
            {
                statusText: '未解决',
                statusClass: 'badge-info',
                title: '测试问题2',
                content: '<i>测试</i> <span style="color:blue">正文2</span>',
                tags: [
                    { id: 8, name: 'Servlet' },
                    { id: 6, name: 'Web' },
                    { id: 7, name: 'JDBC' },
                ],
                userNickName: '高达',
                hits: 921,
                gmtCreateText: '47分钟前'
            },
            {
                statusText: '已解决',
                statusClass: 'badge-success',
                title: '测试问题3',
                content: '<i>测试</i> <span style="color:green">正文3</span>',
                tags: [
                    { id: 9, name: 'Spring' },
                    { id: 10, name: 'SpringMVC' },
                    { id: 11, name: 'Mybatis' },
                ],
                userNickName: '林南',
                hits: 586,
                gmtCreateText: '2小时前'
            }
        ],
        navigatepageNums: [1,2,3,4,5,6],
        currentPageNum: 2,
        prePageNum: 1,
        nextPageNum: 3
    },
    methods: {
        loadQuestions: function (page) {
            // alert('准备加载问题列表……');
            // isNaN > is Not a Number
            if (page == "" || isNaN(page) || page < 1) {
                page = 1;
            }
            $.ajax({
                url: '/api-question/v1/questions/my',
                data: 'page=' + page,
                success: function (json) {
                    let questions = json.data.list;
                    let statusTexts = ['未回复', '未解决', '已解决'];
                    let statusClasses = ['badge-warning', 'badge-info', 'badge-success'];
                    for (let i = 0; i < questions.length; i++) {
                        questions[i].statusText = statusTexts[questions[i].status];
                        questions[i].statusClass = statusClasses[questions[i].status];
                        questions[i].gmtCreateText = getTimeText(questions[i].gmtCreate);
                    }
                    myQuestionsApp.questions = questions;
                    myQuestionsApp.navigatepageNums = json.data.navigatepageNums;
                    myQuestionsApp.currentPageNum = json.data.pageNum;
                    myQuestionsApp.prePageNum = json.data.hasPreviousPage == true ? json.data.pageNum - 1 : 1;
                    myQuestionsApp.nextPageNum= json.data.hasNextPage == true ? json.data.pageNum + 1 : json.data.pages;
                }
            });
        }
    },
    created: function () {
        this.loadQuestions(1);
    }
});