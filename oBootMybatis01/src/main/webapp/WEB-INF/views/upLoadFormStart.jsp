<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
 
    UpLoad Image : <img id="chooseFile" alt="UpLoad Image" src="${pageContext.request.contextPath}/upload/${savedName}">
	<script type="text/javascript">
	document.getElementByName('file1').addEventListener('change', function() {
		var reader = new FileReader();
	    reader.onload = function(e) {	
	      	document.getElementById('chooseFile').src = e.target.result;
	    }
	    reader.readAsDataURL(this.files[0]);
	});
	</script>
     <form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" >
		<input type="file" name="file1"> <p>
		<input type="text" name="title"> <p>
		<input type="submit">
	</form>
	<a href="uploadFileDelete">upLoad삭제Test</a>





</body>
</html>

