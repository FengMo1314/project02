const bto = document.getElementById("button");
let is = false;
if (is == false) {
    no()
}

function clicked() {//监控按钮
    const names = document.getElementById("names").value;
    if (names ==null) {//空值判断
        no();
    } else {
        yes(names);
        // is=true;
    }
}

function yes(names) {
    var html = "";
    $.ajax({
            url: './SomeGoodsServlet',//请求地址
            type: 'post',//请求方式
            headers: {"Content-type": "application/json;charset=UTF-8"},
            async:false,//是否异步
            data: JSON.stringify({"names": names}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {//请求成功回调函数
                $.each(res, function (k, v) {
                    // console.log(k, v);
                    // $('.under-under')[0].innerHTML=(
                    html = html + `
                     <div class="shops">
                     <div class="img">
                     <img src="http://localhost:8080/img${v.goods_carousel}">
                    </div>
                    <div class="shops-mesg">
                     <div class="goods_name">${v.goods_name}</div>
                     <a href="#">${v.goods_intro}</a>
                     <div class="goods_detail_content">${v.goods_detail_content}</div>
                     <h6>原价${v.original_price}</h6>
                     <h5>售价：${v.selling_price}</h5>
                     <h4>库存：${v.stock_num}</h4>
                     </div>
                      <el-button type="info" icon="el-icon-message" circle></el-button>
                     </div>
                     `
                })
                $('.under-under').html(html);
            },
            error(err) {//请求失败回调函数
                console.log("dd");
                console.log(err);
            }
        }
    )

}

function no() {
    is = false;
    var html = "";
    $.ajax({
        url: '/AllGoodsServlet',//请求地址
        type: 'post',//请求方式
        async: true,//是否异步
        data: {},//向服务器发送的数据
        success: function (res) {//请求成功回调函数
            console.log("no", res);
            // alert("1"+$('.under-under')[1])
            // $('.under-under').remove();//移除其他
            $.each(res, function (k, v) {
                html = html + `
                  <div class="shops">
                     <div class="img">
                     <img src="http://localhost:8080/img${v.goods_carousel}">
                    </div>
                    <div class="shops-mesg">
                     <div class="goods_name">${v.goods_name}</div>
                     <a href="#">${v.goods_intro}</a>
                     <div class="goods_detail_content">${v.goods_detail_content}</div>
                     <h6>原价${v.original_price}</h6>
                     <h5>售价：${v.selling_price}</h5>
                     <h4>库存：${v.stock_num}</h4>
                     </div>
                      <el-button type="info" icon="el-icon-message" circle></el-button>
                     </div>
                           `;
            })
            $('.under-under').html(html);
        },
        error(err) {//请求失败回调函数
            console.log("dd")
            console.log(err);
        }
    })
}
