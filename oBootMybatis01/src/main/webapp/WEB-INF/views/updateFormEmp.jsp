<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function inData(){
		alert('inData Start');
		today = new Date();
		var yyyy = today.getFullYear();
		currentFirst = yyyy + '-01-01';
		hiredate = document.getElementById("hiredate");
		hiredate.value = currentFirst;
	}	
</script>
</head>
<body>
	<h1>직원 정보</h1>
	<form action="updateEmp" method="post">
	<input type="hidden" name="empno" value="${emp.empno }">
		<table>
			<tr><th>사번</th><td>${emp.empno}</td></tr>
			<tr><th>이름</th>
				<td><input type="text" name="ename" required="required" value="${emp.ename}"></td></tr>
			<tr><th>업무</th>
				<td><input type="text" name="job" required="required" value="${emp.job}"></td></tr>
			<tr><th>급여</th>
				<td><input type="text" name="sal" required="required" value="${emp.sal}"></td></tr>
			<tr><th>입사일</th>
				<td><input type="date" id="hiredate" name="hiredate" required="required" value="${emp.hiredate}">
					<input type="button" value="기본일자 확인" onclick="inData()"></td></tr>
			<tr><th>보너스</th>
				<td><input type="text" name="comm" required="required" value="${emp.comm}"></td></tr>
			<tr><th>관리자번호</th>
				<td><input type="text" name="mgr" required="required" value="${emp.mgr}"></td></tr>
			<tr><th>부서코드</th>
				<td><input type="text" name="deptno" required="required" value="${emp.deptno}"></td></tr>
			<tr>
				<td colspan="2"><input type="submit" value="확인"></td></tr>
		</table>
	</form>
</body>
</html>