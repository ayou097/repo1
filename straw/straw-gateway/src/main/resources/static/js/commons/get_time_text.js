function getTimeText(time) {
    let t1 = new Date(time).getTime();
    let t2 = new Date().getTime();
    let t = (t2-t1)/1000;
    let gmtCreateText;
    if(t<60){
        gmtCreateText = "刚刚";
    }else if(t<60*60){
        gmtCreateText = parseInt(t/60)+"分钟前"
    } else if (t < 24 * 60 * 60) {
        gmtCreateText = parseInt(t / 60 / 60) + "小时前";
    } else {
        gmtCreateText = parseInt(t / 60 / 60 / 24) + "天前";
    }
    return gmtCreateText;
}