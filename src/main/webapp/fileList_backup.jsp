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
<meta name="description"
	content="Metro UI CSS 是一套用来创建类似于Windows 8 Metro UI风格网站的样式. 这组风格被开发成一个独立的基于Bootstrap的解决方案。">
<meta name="author" content="Bootstrap中文网">
<meta name="keywords"
	content="windows 8, modern style, Bootstrap,Metro UI, style, modern, css, framework">

<link href="http://www.bootcss.com/p/metro-ui-css/css/modern.css"
	rel="stylesheet">
<link
	href="http://www.bootcss.com/p/metro-ui-css/css/modern-responsive.css"
	rel="stylesheet">
<link href="http://www.bootcss.com/p/metro-ui-css/css/site.css"
	rel="stylesheet" type="text/css">
<link href="http://cdn.bootcss.com/prettify/r224/prettify.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery-mousewheel/3.0.6/jquery.mousewheel.min.js"></script>

<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/dropdown.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/accordion.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/buttonset.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/carousel.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/input-control.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/pagecontrol.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/rating.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/slider.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/tile-slider.js"></script>
<script type="text/javascript"
	src="http://www.bootcss.com/p/metro-ui-css/js/modern/tile-drag.js"></script>

<title>列表视图 · Bootstrap Metro UI CSS 中文版</title>
</head>
<body class="modern-ui" onload="prettyPrint()">


	<div class="page secondary">

		<div class="page-region">
			<div class="page-region-content">
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
									href ="#";

								}
						%>
						<li class="fg-color-white">
							<div class="icon">
								<img src=<%=src%>>
							</div>

							<div class="data">
								<h4><%=fp.get(i).getSid()+fp.get(i).getFilepath()%></h4>
								<div class="static-rating small">
									<div style="width: 75%" class="rating-value"></div>
								</div>

								<p>1 RUB</p>
							</div>
							<div class="button" onclick="javascript:window.location.href='<%=href%>'">Convert</div>
							<div class="button"
								onclick="javascript:window.location.href='<%=href%>';">Preview</div>
						</li>
						<%
							}
						%>

					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="page">
		<div class="nav-bar">
			<div class="nav-bar-inner padding10">
				<span class="element"> 2012, Metro UI CSS &copy; </span>
			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="http://cdn.bootcss.com/prettify/r224/prettify.js"></script>

	<script src="/p/projects.js"></script>
</body>
</html>