<%@ page import="com.qst.bean.User" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@include file="../app.jsp" %>
<% User user = (User) request.getSession().getAttribute("SESSION_USER"); %>
<% request.getSession().setAttribute("head", 0); %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>宠物乐园</title>
    <link rel="stylesheet" href="${appPath}/css/mainPage.css">
    <script>
    </script>
</head>
<body>
<div class="wrap">

    <!--头部（含搜索框）-->
    <div class="header" id="header">
        <div class="toolbar">
            <div class="toolbar_auto">
                <ul class="toolbar_left">
                    <li class="toolbarColor"><a href="${appPath}/applicantion/mainPage.jsp">宠物乐园</a></li>
                    <li class=""><a href="${appPath}/ThingServlet">宠物商场</a></li>
                    <li class=""><a href="#">宠物百科</a></li>
                    <li class=""><a href="${appPath}/ShoppingCartServlet?action=show">购物车</a></li>
                    <li class=""><a href="${appPath}/OrderServlet">我的订单</a></li>
                    <li><p style="display: inline-block">同时在线人数${count}</p></li>
                </ul>
                <ul class="toolbar_right">
                    <% if (user != null) { %>
                    <li><%= user.getemail() %>
                    </li>
                    <% } %>
                    <% if (user == null) { %>
                    <li><a href="signIn.jsp">登录</a></li>
                    <li class="left"><a href="register.jsp">注册</a></li>
                    <% } else { %>
                    <li><a onclick="cc()" href="${appPath}/LoginServlet?method=delete">注销</a></li>
                    <% }%>
                    <li class="left"><a href="#">微博</a></li>
                    <li class="left"><a href="#">微信</a></li>
                </ul>
            </div>
        </div>
        <div class="search">
            <form class="form_search" action="">
                <div class="search_left">
                    <input name="name" class="search_box" type="text" placeholder="请输入关键字">
                </div>
                <div class="search_right">
                    <input onmouseover="onmouse()" class="search_btn" type="submit" value="搜索">
                </div>
            </form>
            <div class="search_bottom">
                热门搜索：
                <a href="">宠物用品</a>
                <a href="">狗粮</a>
                <a href="">比熊</a>
                <a href="">金毛</a>
                <a href="">贵兵犬</a>
                <a href="">哈士奇</a>
                <a href="">雪纳瑞</a>
                <a href="">更多>></a>
            </div>
        </div>
    </div>

    <!--导航-->
    <div class="nav" id="nav">
        <div class="nav_auto">
            <ul>
                <li style="background-color: #ee5544"><a>首页</a></li>
                <li><a>狗狗</a>
                    <!--下拉-->
                    <div class="nav_list clear">
                        <div class="nav_list_body ">
                            <dl class="">
                                <dt><a href="">狗狗商城>></a></dt>
                                <dd class="clear"><a href="">宠物狗粮</a>
                                    <a href="">狗狗零食</a>
                                    <a href="">罐头湿粮</a>
                                    <a href="">狗狗保健品</a>
                                    <a href="">狗狗医疗</a>
                                    <a href="">狗狗出行</a>
                                    <a href="">狗狗日用品</a>
                                    <a href="">狗狗美容</a>
                                    <a href="">狗狗玩具</a>
                                    <a href="">狗狗住所</a></dd>
                            </dl>
                            <dl class="">
                                <dt><a href="">狗狗百科>></a></dt>
                                <dd><a href="">牛头梗</a>
                                    <a href="">中华田园犬</a>
                                    <a href="">比特犬</a>
                                    <a href="">比熊犬</a>
                                    <a href="">博美犬</a>
                                    <a href="">柯基</a>
                                    <a href="">边境牧羊犬</a>
                                    <a href="">金毛</a>
                                    <a href="">狗狗大全</a>
                                    <a href="">狗狗饲养</a>
                                    <a href="">狗狗训练</a>
                                    <a href="">狗狗医疗</a>
                                    <a href="">问答专区</a>
                                    <a href="">哈士奇</a>
                                    <a href="">哈士奇</a>
                                </dd>
                            </dl>
                        </div>
                        <div class="dog">
                            <a href=""><img src="../images/img57721417751190.jpg" alt="">
                                <span>佩玛思特进口犬粮</span></a>
                        </div>
                    </div>
                </li>
                <li><a>猫咪</a>
                    <!--下拉-->
                    <div class="nav_list clear">
                        <div class="nav_list_body ">
                            <dl class="">
                                <dt><a href="">猫咪商城>></a></dt>
                                <dd class="clear"><a href="">宠物猫粮</a>
                                    <a href="">猫罐头/妙鲜包</a>
                                    <a href="">猫猫零食</a>
                                    <a href="">猫猫保健品</a>
                                    <a href="">猫猫医疗</a>
                                    <a href="">猫猫香波</a>
                                    <a href="">日常用品</a>
                                    <a href="">猫咪住所</a>
                                    <a href="">猫砂猫厕</a>
                                    <a href="">猫猫玩具</a></dd>
                            </dl>
                            <dl class="">
                                <dt><a href="">猫咪百科>></a></dt>
                                <dd><a href="">西伯利亚</a>
                                    <a href="">异国短毛猫</a>
                                    <a href="">布偶</a>
                                    <a href="">豹猫</a>
                                    <a href="">苏格兰折耳猫</a>
                                    <a href="">波斯猫</a>
                                    <a href="">中华田园猫</a>
                                    <a href="">暹罗猫</a>
                                    <a href="">布偶猫</a>
                                    <a href="">猫咪大全</a>
                                    <a href="">猫咪饲养</a>
                                    <a href="">猫咪训练</a>
                                    <a href="">猫咪医疗</a>
                                    <a href="">问答专区</a>
                                </dd>
                            </dl>
                        </div>
                        <div class="dog">
                            <a href=""><img src="../images/img69501417751239.jpg" alt="">
                                <span>佩玛思特进口猫粮</span></a>
                        </div>
                    </div>
                </li>
                <li><a>小宠</a>
                    <!--下拉-->
                    <div class="nav_list clear">
                        <div class="nav_list_body ">
                            <dl class="">
                                <dt><a href="">狗狗商城>></a></dt>
                                <dd class="clear"><a href="">宠物狗粮</a>
                                    <a href="">兔兔用品</a>
                                    <a href="">仓鼠用品</a>
                                    <a href="">龙猫用品</a>
                                    <a href="">天竺鼠用品</a>
                            </dl>
                            <dl class="">
                                <dt><a href="">小宠百科>></a></dt>
                                <dd><a href="">亚历山大</a>
                                    <a href="">松鼠</a>
                                    <a href="">鹦鹉</a>
                                    <a href="">变色龙</a>
                                    <a href="">八哥</a>
                                    <a href="">狐狸</a>
                                    <a href="">小宠大全</a>
                                    <a href="">小宠饲养</a>
                                    <a href="">小宠训练</a>
                                    <a href="">小宠医疗</a>
                                    <a href="">问答专区</a>
                                </dd>
                            </dl>
                        </div>
                        <div class="dog">
                            <a href=""><img src="../images/img90881417139933.jpg" alt="">
                                <span>MA29玛莎仓鼠粮食500g</span></a>
                        </div>
                    </div>
                </li>
                <li><a>水族</a>
                    <!--下拉-->
                    <div class="nav_list clear">
                        <div class="nav_list_body ">
                            <dl class="">
                                <dt><a href="">水族商城>></a></dt>
                                <dd class="clear"><a href="">鱼缸/水族箱</a>
                                    <a href="">鱼饲料</a>
                                    <a href="">过滤器</a>
                                    <a href="">过滤材料</a>
                                    <a href="">潜水泵</a>
                                    <a href="">氧气泵</a>
                                    <a href="">增氧配件</a>
                                    <a href="">温控设备</a>
                                    <a href="">清洁用具</a>
                                    <a href="">药水系列</a>
                                    <a href="">造景装饰</a>
                                    <a href="">水草肥料</a>
                                    <a href="">杀菌灯</a>
                                    <a href="">喂食器</a></dd>
                            </dl>
                            <dl class="">
                                <dt><a href="">水族百科>></a></dt>
                                <dd><a href="">锦鲤</a>
                                    <a href="">金鱼</a>
                                    <a href="">宝莲灯</a>
                                    <a href="">清道夫</a>
                                    <a href="">水族大全</a>
                                    <a href="">水族饲养</a>
                                    <a href="">水族医疗</a>
                                    <a href="">问答专区</a>
                                </dd>
                            </dl>
                        </div>
                        <div class="dog">
                            <a href=""><img src="../images/img86981417140603.jpg" alt="">
                                <span>三元锦鲤鱼增色饲料300g</span></a>
                        </div>
                    </div>
                </li>
                <li><a>爬虫</a>
                    <!--下拉-->
                    <div class="nav_list clear">
                        <div class="nav_list_body ">
                            <dl class="">
                                <dt><a href="">爬虫商城 >></a></dt>
                                <dd class="clear"><a href="">龟粮</a>
                                    <a href="">龟箱</a>
                                    <a href="">龟缸</a>
                                    <a href="">灯具</a>
                                    <a href="">医疗保健用品</a>
                                    <a href="">造景</a>
                                    <a href="">食具</a>
                                    <a href="">躲避台</a>
                                    <a href="">温控</a>
                                    <a href="">垫材</a></dd>
                            </dl>
                            <dl class="">
                                <dt><a href="">柯基犬>></a></dt>
                                <dd><a href="">牛头梗</a>
                                    <a href="">中华田园犬</a>
                                    <a href="">比特犬</a>
                                    <a href="">比熊犬</a>
                                    <a href="">博美犬</a>
                                    <a href="">柯基</a>
                                    <a href="">边境牧羊犬</a>
                                    <a href="">金毛</a>
                                    <a href="">秋田犬</a>
                                    <a href="">柴犬</a>
                                    <a href="">哈士奇</a>
                                </dd>
                            </dl>
                        </div>
                        <div class="dog">
                            <a href=""><img src="../images/img57721417751190.jpg" alt="">
                                <span>佩玛思特进口犬粮</span></a>
                        </div>
                    </div>
                </li>
                <li><a href="${appPath}/ThingServlet">商城</a>

                </li>
            </ul>
            <div></div>
        </div>
    </div>

    <!--轮播-->
    <div class="banner">
        <div class="banner_body">
            <ul>
                <li><img src="../images/img5837591bfbb3dd49a (1).jpg" alt=""></li>
                <li><img src="../images/img9380591bfb8f73c86 (1).jpg" alt=""></li>
                <li><img src="../images/img31935ef876e549dca (1).jpg" alt=""></li>
            </ul>
            <ol></ol>
        </div>
    </div>

    <!--商品内容-->
    <div class="content">
        <!--狗-->
        <div class="dog">
            <div class="dog_title">
                <em class="dog_img"></em>
                <h2>狗狗</h2>
            </div>
            <div class="body">
                <div class="body_left">
                    <div class="content_banner">
                        <ul>
                            <li><a href=""><img src="../images/img91405b8f86335f8da.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img53465b8f8648c0b2f.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img34715b8f863ff137e.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img91405b8f86335f8da.jpg" alt=""></a></li>
                        </ul>
                        <ol>
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                        </ol>
                    </div>
                </div>
                <div class="body_right">
                    <div class="body_right_t">
                        <div class="t">
                            <a href="">狗狗用品>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img46145c6e4ffe9a7d7.jpg" alt=""></dt>
                                    <dd class="name">Go! 狗粮</dd>
                                    <dd>无谷天然 含肉量高</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img27385c6e4382f2eea.jpg" alt=""></dt>
                                    <dd class="name">皇家狗粮</dd>
                                    <dd>精准营养 量身定制</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img58115c6e503d6f628.jpg" alt=""></dt>
                                    <dd class="name">伯纳天纯</dd>
                                    <dd>无谷天然 含肉量高</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                    <div class="body_right_b">
                        <div class="t">
                            <a href="">狗狗用品分类>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img1880567a038801227.jpg" alt=""></dt>
                                    <dd class="name">Go! 狗粮</dd>
                                    <dd>进口粮、国产粮、处方粮、冻干粮</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img5490567a038fd02a2.jpg" alt=""></dt>
                                    <dd class="name">皇家狗粮</dd>
                                    <dd>罐头湿粮、肉类零食、磨牙洁齿、奶酪饼干</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img469567a039597235.jpg" alt=""></dt>
                                    <dd class="name">伯纳天纯</dd>
                                    <dd>皮肤治疗、综合护理、内外驱虫、常备药品</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--猫-->
        <div class="cat">
            <div class="dog_title">
                <em class="cat_img"></em>
                <h2>猫咪</h2>
            </div>
            <div class="body">
                <div class="body_left">
                    <div class="content_banner">
                        <ul>
                            <li><a href=""><img src="../images/img22075b8f866787dfc.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img7505b8f8673220b2.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img95515b8f873ac7865.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img22075b8f866787dfc.jpg" alt=""></a></li>
                        </ul>
                        <ol>
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                        </ol>
                    </div>
                </div>
                <div class="body_right">
                    <div class="body_right_t">
                        <div class="t">
                            <a href="">猫咪用品>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img91365c6e498c0d2f7.jpg" alt=""></dt>
                                    <dd class="name">有鱼</dd>
                                    <dd>太平洋区域深海鱼加工</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img63435c6e4a4e9e51f.jpg" alt=""></dt>
                                    <dd class="name">比瑞吉</dd>
                                    <dd>天然调理 相伴更久</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img23045c6e46b47e48d.jpg" alt=""></dt>
                                    <dd class="name">加拿大NOW</dd>
                                    <dd>鲜肉无谷天然粮</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                    <div class="body_right_b">
                        <div class="t">
                            <a href="">猫咪用品分类>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img7166567a04b16967e.jpg" alt=""></dt>
                                    <dd class="name">猫咪主粮</dd>
                                    <dd>进口粮、国产粮、处方粮、冻干粮</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img68535682418eed7a3.jpg" alt=""></dt>
                                    <dd class="name">猫咪零食</dd>
                                    <dd>罐头湿粮、肉类零食、磨牙洁齿、奶酪饼干</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img313356824196eec7f.jpg" alt=""></dt>
                                    <dd class="name">伯纳天纯</dd>
                                    <dd>皮肤治疗、综合护理、内外驱虫、常备药品</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--小宠-->
        <div class="small_tit">
            <div class="dog_title">
                <em class="small_tit_img"></em>
                <h2>小宠</h2>
            </div>
            <div class="body">
                <div class="body_left">
                    <div class="content_banner">
                        <ul>
                            <li><a href=""><img src="../images/img19845b8f874a82f5c.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img47895b8f8751dae7b.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img35915b8f875905e99.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img19845b8f874a82f5c.jpg" alt=""></a></li>
                        </ul>
                        <ol>
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                        </ol>
                    </div>
                </div>
                <div class="body_right">

                    <div class="body_right_t">
                        <div class="t">
                            <a href="">小宠热门用品>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img2591586cabdacead9.jpg" alt=""></dt>
                                    <dd class="name">迷你秀</dd>
                                    <dd>仓鼠专家</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img121257bfdc2f570f1.jpg" alt=""></dt>
                                    <dd class="name">宠波尔boer仓鼠窝</dd>
                                    <dd>鼠鼠梦想的家</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img464358411ff822a03.jpg" alt=""></dt>
                                    <dd class="name">磨牙石</dd>
                                    <dd>磨牙又营养</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                    <div class="body_right_b">
                        <div class="t">
                            <a href="">小宠用品分类>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img10451417145167.jpg" alt=""></dt>
                                    <dd class="name">仓鼠用品</dd>
                                    <dd>主粮、磨牙、水壶食盆、浴室厕所、玩具、笼窝</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img20901417145430.jpg" alt=""></dt>
                                    <dd class="name">龙猫用品</dd>
                                    <dd>主粮、磨牙、水壶食盆、浴室厕所、玩具、笼子</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img30141417145465.jpg" alt=""></dt>
                                    <dd class="name">兔子用品</dd>
                                    <dd>主粮、磨牙、水壶食盆、浴室厕所、玩具、笼子</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--水族-->
        <div class="water">
            <div class="dog_title">
                <em class="water_img"></em>
                <h2>水族</h2>
            </div>
            <div class="body">
                <div class="body_left">
                    <div class="content_banner">
                        <ul>
                            <li><a href=""><img src="../images/img94655b8f8764c8840.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img54365b8f876e2e113.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img35515b8f8775402ee.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img94655b8f8764c8840.jpg" alt=""></a></li>
                        </ul>
                        <ol>
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                        </ol>
                    </div>
                </div>
                <div class="body_right">
                    <div class="body_right_t">
                        <div class="t">
                            <a href="">狗狗用品>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img21861417145514.jpg" alt=""></dt>
                                    <dd class="name">聚宝源鱼缸</dd>
                                    <dd>绿色 环保 生态</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img3686582ee2d2ab94d.jpg" alt=""></dt>
                                    <dd class="name">海豚水族用品</dd>
                                    <dd>精更专业 更全面</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img42471417145619.jpg" alt=""></dt>
                                    <dd class="name">松宝水族系列</dd>
                                    <dd>专业打造水族器械</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                    <div class="body_right_b">
                        <div class="t">
                            <a href="">水族用品分类>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img18931417162729.jpg" alt=""></dt>
                                    <dd class="name">水族设备</dd>
                                    <dd>鱼缸、迷你水族箱、鱼缸配件、鱼缸底柜</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img78031417162763.jpg" alt=""></dt>
                                    <dd class="name">水族饲料</dd>
                                    <dd>颗粒型、虫干虾干、活体饲料、薄片、贴片</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img6301417162789.jpg" alt=""></dt>
                                    <dd class="name">水族药剂</dd>
                                    <dd>疾病治疗、净水、杀菌、营养品 硝化细菌</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--爬虫-->
        <div class="reptile">
            <div class="dog_title">
                <em class="reptile_img"></em>
                <h2>爬虫</h2>
            </div>
            <div class="body">
                <div class="body_left">
                    <div class="content_banner">
                        <ul>
                            <li><a href=""><img src="../images/img34565b8f87871db35.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img28535b8f878dcc02c.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img38745b8f879411ba3.jpg" alt=""></a></li>
                            <li><a href=""><img src="../images/img34565b8f87871db35.jpg" alt=""></a></li>
                        </ul>
                        <ol>
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                        </ol>
                    </div>
                </div>
                <div class="body_right">
                    <div class="body_right_t">
                        <div class="t">
                            <a href="">爬虫热门用品>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img1288586c682cee3df.jpg" alt=""></dt>
                                    <dd class="name">龟龟灯具</dd>
                                    <dd>取暖照明更出色</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img97305955f75bf0f99.jpg" alt=""></dt>
                                    <dd class="name">守宫取暖灯</dd>
                                    <dd>发热效果更佳</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img86675903195393f93.jpg" alt=""></dt>
                                    <dd class="name">自动给水器</dd>
                                    <dd>水是生命之源</dd>
                                </dl>
                            </a>
                        </div>
                    </div>
                    <div class="body_right_b">
                        <div class="t">
                            <a href="">爬虫用品分类>></a>
                        </div>
                        <div class="c">
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img21841428053295.jpg" alt=""></dt>
                                    <dd class="name">陆龟用品</dd>
                                    <dd>龟箱 龟粮 灯具 加热灯 医疗保健品 造景 食具</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img48691428053320.jpg" alt=""></dt>
                                    <dd class="name">水龟用品</dd>
                                    <dd>龟缸 灯具 龟粮 龟医疗 造景 食具 躲避台 配件</dd>
                                </dl>
                            </a>
                            <a href="">
                                <dl>
                                    <dt><img src="../images/img21051428054015.jpg" alt=""></dt>
                                    <dd class="name">蜥蜴蛇用品</dd>
                                    <dd>保健品系列 加热取暖设备</dd>
                                </dl>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>


    <!--悬浮栏-->
    <div class="right_bottom">
        <a href="" class="r_dog"></a>
        <a href="" class="r_cat"></a>
        <a href="" class="r_small"></a>
        <a href="" class="r_water"></a>
        <a href="" class="r_reptile"></a>
        <a href="" class="goTop" style="display: none;"></a>
    </div>

    <!--七天无忧退货-->
    <div class="bottom"></div>
</div>
</body>
</html>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
    function onmouse() {
        if (document.getElementsByClassName("search_box")[0].value !== null) {
            document.getElementsByClassName("form_search")[0].action = "${appPath}/ThingServlet?method=select";
        }
    }
</script>
<script language="javascript" type="text/javascript" src="${appPath}/js/mainPage.js"></script>
