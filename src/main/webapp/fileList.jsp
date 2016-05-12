<%@ page language="java" import="cn.edu.zucc.chenxg.ws.*"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="cn.edu.zucc.chenxg.model.*"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="target-densitydpi=device-dpi, width=device-width, initial-scale=1.0, maximum-scale=1">

<link href="metro/css/metro_list.css" rel="stylesheet">
<link
	href="http://www.bootcss.com/p/metro-ui-css/css/modern-responsive.css"
	rel="stylesheet">

<link href="metro/css/metro.css" rel="stylesheet">
<link href="metro/css/metro-icons.css" rel="stylesheet">
<link href="metro/css/metro-responsive.css" rel="stylesheet">

<script src="metro/js/jquery.min.js"></script>
<script src="metro/js/jquery.dataTables.min.js"></script>
<script src="metro/js/metro.js"></script>



<title>列表视图 · Bootstrap Metro UI CSS 中文版</title>
</head>
<body>



	<div class="app-bar fixed-top darcula" data-role="appbar">
		<a class="app-bar-element branding">FilePreview</a> <span
			class="app-bar-divider"></span>
		<ul class="app-bar-menu">
			<li><a href="upload.jsp">UploadFile</a></li>
			<li><a href="fileList.jsp">FileList</a></li>
			<li><a href="">System</a></li>
		</ul>

		<div class="app-bar-element place-right">
			<span class="dropdown-toggle"><span class="mif-cog"></span>
				Hi, <%=session.getAttribute("username")%></span>
			<div
				class="app-bar-drop-container padding10 place-right no-margin-top block-shadow fg-dark"
				data-role="dropdown" data-no-close="true" style="width: 220px">
				<h2 class="text-light">Quick settings</h2>
				<ul class="unstyled-list fg-dark">
					<li><a href="" class="fg-white1 fg-hover-yellow">Profile</a></li>
					<li><a href="" class="fg-white2 fg-hover-yellow">Security</a></li>
					<li><a href="" class="fg-white3 fg-hover-yellow">Exit</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="page-content">

		<div class="flex-grid no-responsive-future" style="height: 100%;">
			<div class="flex-grid no-responsive-future" style="height: 100%;">
				<div class="span10">
					<h2>列表视图</h2>

					<ul class="listview">
						<%
							List<Filepreview> fp = new WebServiceClient().getUserDefaultFile();
							String src = null;
							String fileName = null;
							String href = null;
							for (int i = 0; i < fp.size(); i++) {
								href = "http://localhost:8080/FilePreview/web/viewer.html?file=../files/";
								if (fp.get(i).getFilepath().endsWith(".ppt") || fp.get(i).getFilepath().endsWith(".pptx")) {
									src = "images/ppt2013icon.jpeg";
								} else if (fp.get(i).getFilepath().endsWith(".doc") || fp.get(i).getFilepath().endsWith(".docx")) {
									src = "images/word2013icon.png";
								} else if (fp.get(i).getFilepath().endsWith(".xls") || fp.get(i).getFilepath().endsWith(".xlsx")) {
									src = "images/excel2013icon.png";
								}
								if (fp.get(i).getPreviewfilepath() != null) {
									href = href + fp.get(i).getPreviewfilepath();
								} else {
									href = "#";

								}
						%>
						<li class="fg-color-darken">
							<div class="icon">
								<img src=<%=src%>>
							</div>

							<div class="data">
								<h4><%=fp.get(i).getSid() + fp.get(i).getFilepath()%></h4>

								<br></br>
							</div>
							<div class="button primary"
								onclick="javascript:window.location.href='<%=href%>'">Convert</div>
							<div class="button success"
								onclick="javascript:window.location.href='<%=href%>';">Preview</div>
							<div class="button warning"
								onclick="javascript:window.location.href='<%=href%>';">Share</div>
						</li>
						<%
							}
						%>

					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="-mob-share-ui-button -mob-share-open">分享</div>
<div class="-mob-share-ui" style="display: none">
    <ul class="-mob-share-list">
        <li class="-mob-share-weibo"><p>新浪微博</p></li>
        <li class="-mob-share-tencentweibo"><p>腾讯微博</p></li>
        <li class="-mob-share-qzone"><p>QQ空间</p></li>
        <li class="-mob-share-qq"><p>QQ好友</p></li>
        <li class="-mob-share-renren"><p>人人网</p></li>
        <li class="-mob-share-kaixin"><p>开心网</p></li>
        <li class="-mob-share-douban"><p>豆瓣</p></li>
        <li class="-mob-share-facebook"><p>Facebook</p></li>
        <li class="-mob-share-twitter"><p>Twitter</p></li>
    </ul>
    <div class="-mob-share-close">取消</div>
</div>
<div class="-mob-share-ui-bg"></div>
<script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=8c8b979f3b1e"></script>
<!--MOB SHARE END-->



</body>
</html>