$(".toolbar_left li").click(function () {
    $(".toolbar_left li").removeClass("toolbarColor");
    $(this).addClass("toolbarColor");
});

$(".nav_auto li").hover(function () {
    $(this).children(".nav_list").addClass("show");
}, function () {
    $(this).children(".nav_list").removeClass("show");
});

/*
document.addEventListener("scroll", function (evt) {
    if (document.documentElement.scrollTop !== 0) {
        document.getElementsByClassName("goTop").style.display = "block";
    }
});
*/


var banner = document.getElementsByClassName("banner")[0];
var banner_body = banner.children[0];
var banner_body_ul = banner_body.children[0];
var banner_body_ul_li = banner_body_ul.children;
var banner_body_ol = banner_body.children[1];
var banner_body_ol_li = banner_body_ol.children;

var page = 0, page1 = 0, page2 = 0, page3 = 0, page4 = 0, page5 = 0;


banner_body_ul.appendChild(banner_body_ul_li[0].cloneNode(true));

//生成ol中的li
for (var i = 0; i < banner_body_ul_li.length - 1; i++) {
    var li = document.createElement("li");
    li.innerHTML = i + 1;
    banner_body_ol.appendChild(li);
}

var timer = null;
timer = setInterval(move, 6000);
var timer1, timer2, timer3, timer4, timer5;
timer1 = setInterval(dog, 6000);
/*timer2 = setInterval(move2, 6000);
timer3 = setInterval(move3, 6000);
timer4 = setInterval(move4, 6000);
timer5 = setInterval(move5, 6000);*/


banner.style.backgroundColor = "#dfecf2";
banner_body_ol.children[0].className = "selected";


//鼠标放到方块上，轮播图片
for (i = 0; i < banner_body_ol_li.length; i++) {
    banner_body_ol_li[i].onmouseover = function () {
        clearInterval(timer);
        clearInterval(banner_body_ul.timer);
        for (var j = 0; j <= 2; j++) {
            banner_body_ol_li[j].className = "";
        }
        var key = this.innerHTML - 1;
        banner_body_ol_li[key].className = "selected";
        var speed = 10 * (key - page);
        var result = Math.abs(key - page);
        if (result !== 0) {
            if (key === 0) {
                banner.style.backgroundColor = "#dfecf2";
            } else if (key === 1) {
                banner.style.backgroundColor = "#ffffff";
            } else if (key === 2) {
                banner.style.backgroundColor = "#fecef8";
            }
            banner_body_ul.timer = setInterval(function () {
                banner_body_ul.style.left = banner_body_ul.offsetLeft - speed + "px";
                if (Math.abs(banner_body_ul.offsetLeft + key * 1190) < Math.abs(speed)
                    || Math.abs(banner_body_ul.offsetLeft) === result * 1190) {
                    banner_body_ul.style.left = -key * 1190 + "px";
                    page = key;
                    clearInterval(banner_body_ul.timer);
                }
            }, 0.5);
            timer = setInterval(move, 6000);
        }
    }
}


//图片移动
function move() {
    var speed = 10;
    var time = setInterval(function () {
        /*
                console.log("dudu " + banner_body_ul.style.left + " " + banner_body_ul.offsetLeft);
        */
        banner_body_ul.style.left = banner_body_ul.offsetLeft - speed + "px";
        if (banner_body_ul.offsetLeft % 1190 === 0 && banner_body_ul.offsetLeft / 1190 !== 0) {
            page++;
            if (page === 3) {
                banner_body_ul.style.left = 0 + "px";
            }
            page = page % 3;
            if (page === 0) {
                banner.style.backgroundColor = "#dfecf2";
            } else if (page === 1) {
                banner.style.backgroundColor = "#ffffff";
            } else if (page === 2) {
                banner.style.backgroundColor = "#fecef8";
            }
            for (var j = 0; j <= 2; j++) {
                banner_body_ol_li[j].className = "";
            }
            banner_body_ol_li[page].className = "selected";
            clearInterval(time);
        }
    }, 0.5);
}


/*动物轮播图*/

/*狗*/

function dog() {
    move1(document.getElementsByClassName("content_banner")[0], page1);
    move1(document.getElementsByClassName("content_banner")[1], page2);
    move1(document.getElementsByClassName("content_banner")[2], page3);
    move1(document.getElementsByClassName("content_banner")[3], page4);
    move1(document.getElementsByClassName("content_banner")[4], page5);
    page1++;
    page1 %= 3;
    page2++;
    page2 %= 3;
    page3++;
    page3 %= 3;
    page4++;
    page4 %= 3;
    page5++;
    page5 %= 3;
}

function move1(a, pa) {
    var content_banner_ul = a.children[0];
    var content_banner_ul_li = content_banner_ul.children;
    var content_banner_ol = a.children[1];
    var content_banner_ol_li = content_banner_ol.children;
    var speedd = 5;

    var timee = setInterval(function () {
        content_banner_ul.style.left = content_banner_ul.offsetLeft - speedd + "px";
        if (content_banner_ul.offsetLeft % 360 === 0 && content_banner_ul.offsetLeft / 360 !== 0) {

            pa++;
            if (pa === 3) {
                content_banner_ul.style.left = 0 + "px";
            }
            pa = pa % 3;
            /*            for (var j = 0; j <= 2; j++) {
                            content_banner_ol_li[j].className = "";
                        }
                        content_banner_ol_li[pa].className = "selected";*/
            clearInterval(timee);
        }
    }, 0.5);
}
