//日期格式化
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

var Eu = {
    // 格式化时间
    formatDateTime: function (value, row, index) {
        var now = new Date(value);
        return now.format("yyyy-MM-dd hh:mm:ss");
    },
    //显示消息
    showMsg: function (msg) {
        top.window.$.messager.show({
            title: '提示',
            msg: '<div class="light-info"><div class="light-tip icon-tip"></div><div>' + msg || "消息内容！" + '</div></div>',
            timeout: 3000,
            showType: 'slide'
        });
    }
}