// 获取指定名称的cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');//拆分cookie字符串
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}

// 打印所有cookie
function printCookies() {
    var strcookie = document.cookie;//获取cookie字符串
    var arrcookie = strcookie.split(";");//分割
    var cookies = "";
    //遍历匹配
    for (var i = 0; i < arrcookie.length; i++) {
        var arr = arrcookie[i].split("=");
        cookies += "<h1>" + arr[0] + "</h1>" + "<h2>" + arr[1] + "</h2>";
    }
    return cookies;
}

function freshCaptchaById(id, newsrc) {
    var time = new Date();
    document.getElementById(id).src = newsrc + "?" + time;
}

function getUser(k, v) {
    var html = `
      <tr>
      <td>
      <input type="checkbox" id="${v.id}">
      </td>
      <td>${v.id}</td>
      <td>${v.username}</td>
      <td>${v.password}</td>
      <td >
       <button type="button">
       <label for="${v.id}">选择 </label>
       </button>
       </td>
       <td>
       <button type="button">
       <label for="${v.id}">删除</label>
       </button>
      </td>
       <td>
       <button type="button">
       <label for="${v.id}">修改</label>
       </button>
      </td>
      </tr>
     `
    return html;
}
