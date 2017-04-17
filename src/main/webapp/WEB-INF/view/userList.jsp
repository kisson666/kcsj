<%@ page import="java.util.List" %>
<%@ page import="com.wang.model.User" %><%--
  Created by IntelliJ IDEA.
  User: hppc
  Date: 2017/4/14
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body{ text-align:center}
        .div{ margin:0 auto; width:400px; height:100px; border:1px solid #F00}
        /* css注释：为了观察效果设置宽度 边框 高度等样式 */
    </style>
    <title>用户列表</title>
</head>
<body>
    <div id="userList_kisson">
        <%List<User> users = (List<User>) request.getAttribute("users");%>
        <%for (User user : users) {%>
            <font color="red">用户邮箱:</font><%out.println(user.getEmail()+"  ");%>
            <font color="red">用户姓名:</font><%out.println(user.getRealName()+"  ");%>
            <button class="button_userList_kisson" value=<%=user.getId()%>>点击查看此用户详细资料</button>
            <br><br>

        <%}%>
    </div>

    <div id="userInfo_kisson" style="display: none">
        <div id="ico_kisson">头像:<img id="img_ico_kisson" style="height: 100px;width: 100px"></div>
        <div id="realname_kisson"></div>
        <div id="age_kisson"></div>
        <div id="email_kisson"></div>
        <div id="phone_kisson"></div>
        <div id="hometown_kisson"></div>
        <div id="about_kisson"></div>
        <div>

            <button id="userInfo_button_kisson">关闭</button>
        </div>
    </div>



    <script  type="text/javascript"  src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.button_userList_kisson').click(function () {
                var id = $(this).val();
                $.ajax({
                    url:'/userInfo/id/'+id,
                    type:'get',
                    dataType:'json',
                    success:function (response) {
                        if (response.status == 0) {
                            $('#userList_kisson').hide();
                            $('#img_ico_kisson').attr("src",response.body.user.ico);
                            $('#realname_kisson').text("姓名:"+response.body.user.realName);
                            $('#age_kisson').text("年龄:"+response.body.user.age);
                            $('#email_kisson').text("邮箱:"+response.body.user.email);
                            $('#phone_kisson').text("电话:"+response.body.user.phoneNumber);
                            $('#hometown_kisson').text("籍贯:"+response.body.user.pro+response.body.user.city);
                            $('#about_kisson').text("自我介绍:"+response.body.user.about);
                            $('#userInfo_kisson').show();
                        } else {
                            alert(response.errmsg);
                        }
                    },
                    error:function () {
                        alert('发送请求失败');
                    }
                })
            })
        })
    </script>

    <script type="text/javascript">
        $(function () {
            $('#userInfo_button_kisson').click(function () {
                $('#userInfo_kisson').hide();
                $('#userList_kisson').show();
            })
        })
    </script>
</body>
</html>
