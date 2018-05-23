'use strict';

function getQueryString(name)
{
    //var vars = window.location.search.substring(1).split("&");
    var search = window.location.href.split("?")[1] || "";
    var vars = search.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if(pair[0] == name) {
            return pair[1];
        }
    }
    return null;
}

function getFormParas(selector) {
    var paras = $(selector).serializeArray();

    var result = {};
    paras.forEach(function(item) {
        result[item.name] = item.value;
    });
    return result;
}

function gotoUrl(url) {
    window.location.href = url;
}

function numberRound(num, bit) {
    var n = Math.pow(10, bit);
    return Math.round(num * n) / n;
}

function sprintf(format) {
    var i = 1;
    var items = arguments;
    return format.replace(/\%([0-9]*)[ds]/g, function (substring, num) {
        var item = String(items[i]);
        if (num.length > 0) {
            var length = Number(num);
            if (item.length < length) {
                var remain = length - item.length;
                while (remain--) {
                    var prefix = num[0] === "0" ? "0" : " ";
                    item = prefix + item;
                }
            }
        }
        i++;
        return item;
    });
}

function formatDateTime(timestamp) {
    timestamp = Number(timestamp);
    var date = new Date(timestamp);
    return sprintf("%s-%02s-%02s %02s:%02s:%02s",
        date.getFullYear(),
        date.getMonth() + 1,
        date.getDate(),
        date.getHours(),
        date.getMinutes(),
        date.getSeconds());
}

function formatDate(timestamp) {
    timestamp = Number(timestamp);
    var date = new Date(timestamp);
    return sprintf("%s-%02s-%02s",
        date.getFullYear(),
        date.getMonth() + 1,
        date.getDate());
}

function getExploreName(){
// https://segmentfault.com/a/1190000007640795
    var userAgent = navigator.userAgent;
    if(userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1){
        return 'Opera';
    } else if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1){
        return 'IE';
    } else if (userAgent.indexOf("Edge") > -1){
        return 'Edge';
    } else if (userAgent.indexOf("Firefox") > -1){
        return 'Firefox';
    } else if (userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1){
        return 'Safari';
    } else if (userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1){
        return 'Chrome';
    } else if (!!window.ActiveXObject || "ActiveXObject" in window){
        return 'IE>=11';
    } else {
        return 'Unkonwn';
    }
}
