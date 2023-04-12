<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

   function getDeptName(vDeptno) {
	   $.ajax({
           url : "<%=context%>/getDeptName"
         , data : {deptno : vDeptno}
         , dataType : 'text'
         , success:function(data) {
             console.log(data);
             $('#deptName').val(data);
             $('#msg').html(data);
         }
     });
   }

   // RestController
   // Deptno -> deptName 조건 은 객체로 가져옴
   function getDept(vDeptno) {
		//alert("vDeptno -> " + vDeptno);
		$.ajax({
			 url : "<%=context%>/getDeptName"
		         , data : {deptno : vDeptno}
		         , dataType : 'text'
		         , success:function(data) {
		             console.log(data);
		             $('#RestDept').val(data);
		         }
		})
   } 




</script>
</head>
<body>
<h2>회원 정보</h2>
<table>
	<tr><th>사번</th><th>이름</th><th>업무</th><th>부서</th><th>근무지</th></tr>
	<c:forEach var="emp" items="${listEmp}">
		<tr><td>${emp.empno }</td><td>${emp.ename }</td>
			<td>${emp.job }</td>
			<td>${emp.deptno} 
			    <input type="button" id="btn_idCheck" value="부서명" onmouseover="getDeptName(${emp.deptno})">
			</td>
			<td>${empDept.loc }</td>
		</tr>
	</c:forEach>
</table>

	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>
    
    RestController sendVO2: <input type="text" id="RestDept"    readonly="readonly"><p>
	RestController sendVO2: sendVO2<input type="button" id="btn_Dept"  value="부서명"  
	                                      onclick="getDept(10)"><p>
    
	 
</body>
</html>