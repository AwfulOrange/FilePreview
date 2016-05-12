<%-- 
    Document   : upload
    Created on : Nov 3, 2012, 12:31:16 PM
    Author     : Amila
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Force latest IE rendering engine or ChromeFrame if installed -->
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<meta charset="utf-8">
<title>jQuery File Upload Demo</title>
<meta name="description"
	content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar and preview images for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width">
<!-- Bootstrap CSS Toolkit styles -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<!-- Generic page styles -->
<link rel="stylesheet" href="FileUpload/css/style.css">
<!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
<!--<link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-responsive.min.css">-->
<!-- Bootstrap CSS fixes for IE6 -->
<!--[if lt IE 7]><link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-ie6.min.css"><![endif]-->
<!-- Bootstrap Image Gallery styles -->
<link rel="stylesheet"
	href="http://blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="FileUpload/css/jquery.fileupload-ui.css">
<!-- Shim to make HTML5 elements usable in older Internet Explorer versions -->
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel='shortcut icon' type='image/x-icon' href='../favicon.ico' />

<title>Template :: Metro UI CSS - The front-end framework for
	developing projects on the web in Windows Metro Style</title>

<link href="metro/css/metro.css" rel="stylesheet">
<link href="metro/css/metro-icons.css" rel="stylesheet">
<link href="metro/css/metro-responsive.css" rel="stylesheet">
<script src="metro/js/jquery.min.js"></script>
<script src="metro/js/metro.js"></script>

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
		<div class="cell auto-size padding20 bg-white" id="cell-content">
			<div class="page-header">
				<h1>jQuery File Upload Demo</h1>
			</div>
			<br>
			<!-- The file upload form used as target for the file upload widget -->
			<form id="fileupload" action="UploadServlet" method="POST"
				enctype="multipart/form-data">
				<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
				<div class="row fileupload-buttonbar">
					<div class="span7">
						<!-- The fileinput-button span is used to style the file input field as button -->
						<span class="btn btn-success fileinput-button"> <i
							class="icon-plus icon-white"></i> <span>Add files...</span> <input
							type="file" name="files[]" multiple>
						</span>
						<button type="submit" class="btn btn-primary start">
							<i class="icon-upload icon-white"></i> <span>Start upload</span>
						</button>
						<button type="reset" class="btn btn-warning cancel">
							<i class="icon-ban-circle icon-white"></i> <span>Cancel
								upload</span>
						</button>
						<button type="button" class="btn btn-danger delete">
							<i class="icon-trash icon-white"></i> <span>Delete</span>
						</button>
						<input type="checkbox" class="toggle">
					</div>
					<!-- The global progress information -->
					<div class="span5 fileupload-progress fade">
						<!-- The global progress bar -->
						<div class="progress progress-success progress-striped active"
							role="progressbar" aria-valuemin="0" aria-valuemax="100">
							<div class="bar" style="width: 0%;"></div>
						</div>
						<!-- The extended global progress information -->
						<div class="progress-extended">&nbsp;</div>
					</div>
				</div>
				<!-- The loading indicator is shown during file processing -->
				<div class="fileupload-loading"></div>
				<br>
				<!-- The table listing the files available for upload/download -->
				<table role="presentation" class="table table-striped">
					<tbody class="files" data-toggle="modal-gallery"
						data-target="#modal-gallery"></tbody>
				</table>
			</form>
			<br>
		</div>
	</div>
	<!-- modal-gallery is the modal dialog used for the image gallery -->
	<div id="modal-gallery" class="modal modal-gallery hide fade"
		data-filter=":odd">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">&times;</a>
			<h3 class="modal-title"></h3>
		</div>
		<div class="modal-body">
			<div class="modal-image"></div>
		</div>
		<div class="modal-footer">
			<a class="btn modal-download" target="_blank"> <i
				class="icon-download"></i> <span>Download</span>
			</a> <a class="btn btn-success modal-play modal-slideshow"
				data-slideshow="5000"> <i class="icon-play icon-white"></i> <span>Slideshow</span>
			</a> <a class="btn btn-info modal-prev"> <i
				class="icon-arrow-left icon-white"></i> <span>Previous</span>
			</a> <a class="btn btn-primary modal-next"> <span>Next</span> <i
				class="icon-arrow-right icon-white"></i>
			</a>
		</div>
	</div>

	<!-- The template to display files available for upload -->
	<script id="template-upload" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="preview"><span class="fade"></span></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
            {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>Start</span>
                </button>
                {% } %}</td>
            {% } else { %}
            <td colspan="2"></td>
            {% } %}
            <td class="cancel">{% if (!i) { %}
                <button class="btn btn-warning">
                    <i class="icon-ban-circle icon-white"></i>
                    <span>Cancel</span>
                </button>
                {% } %}</td>
        </tr>
        {% } %}
    </script>
	<!-- The template to display files available for download -->
	<script id="template-download" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
            {% } else { %}
            <td class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
            {% } %}
            <td class="delete">
                <button class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}"{% if (file.delete_with_credentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="icon-trash icon-white"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1">
            </td>
        </tr>
        {% } %}
    </script>

	<script src="FileUpload/js/jquery-1.8.2.min.js"></script>
	<script src="FileUpload/js/vendor/jquery.ui.widget.js"></script>
	<script src="FileUpload/js/tmpl.min.js"></script>
	<script src="FileUpload/js/load-image.min.js"></script>
	<script src="FileUpload/js/canvas-to-blob.min.js"></script>
	<script src="FileUpload/js/bootstrap.min.js"></script>
	<script src="FileUpload/js/bootstrap-image-gallery.min.js"></script>
	<script src="FileUpload/js/jquery.iframe-transport.js"></script>
	<script src="FileUpload/js/jquery.fileupload.js"></script>
	<script src="FileUpload/js/jquery.fileupload-fp.js"></script>
	<script src="FileUpload/js/jquery.fileupload-ui.js"></script>
	<script src="FileUpload/js/locale.js"></script>
	<script src="FileUpload/js/main.js"></script>
</body>
</html>