<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/common/layout_login.jsp"
	charEncoding="UTF-8">
	<c:param name="title" value="HOME" />
	<c:param name="body">
		<html>
<head>
<title>Login Page</title>


<div id="fb-root"></div>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>

<c:set var="status" value="" scope="session" />

<style type="text/css">
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
	background-image:'';
	background-repeat: no-repeat;
}

#login {
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, .2), 0
		3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0 #fff, 0 7px 0
		rgba(0, 0, 0, .2);
}

#login:before {
	content: '';
	position: absolute;
	z-index: -1;
	border: 1px dashed #ccc;
	top: 5px;
	bottom: 5px;
	left: 5px;
	right: 5px;
	-moz-box-shadow: 0 0 0 1px #fff;
	-webkit-box-shadow: 0 0 0 1px #fff;
	box-shadow: 0 0 0 1px #fff;
}


h1
{
    text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5);
    text-transform: uppercase;
    text-align: center;
    color: #666;
    margin: 0 0 30px 0;
    letter-spacing: 4px;
    font: normal 26px/1 Verdana, Helvetica;
    position: relative;
}

h1:after, h1:before
{
    background-color: #777;
    content: "";
    height: 1px;
    position: absolute;
    top: 15px;
    width: 50px;   
}

h1:after
{ 
    background-image: -webkit-gradient(linear, left top, right top, from(#777), to(#fff));
    background-image: -webkit-linear-gradient(left, #777, #fff);
    background-image: -moz-linear-gradient(left, #777, #fff);
    background-image: -ms-linear-gradient(left, #777, #fff);
    background-image: -o-linear-gradient(left, #777, #fff);
    background-image: linear-gradient(left, #777, #fff);      
    right: 0;
}

h1:before
{
    background-image: -webkit-gradient(linear, right top, left top, from(#777), to(#fff));
    background-image: -webkit-linear-gradient(right, #777, #fff);
    background-image: -moz-linear-gradient(right, #777, #fff);
    background-image: -ms-linear-gradient(right, #777, #fff);
    background-image: -o-linear-gradient(right, #777, #fff);
    background-image: linear-gradient(right, #777, #fff);
    left: 0;
}

.table-row {
	display: table-row;
}

.cell1,.cell2,.cell3 {
	display: table-cell;
}

.cell2 {
	width: 700px;
}

.cell1 {
	width: 400px;
}

.cell3 {
	width: 100px;
}
</style>
</head>
<body onload='document.f.j_username.focus();' background="${pageContext.request.contextPath}/img/cner.jpg" style="width:100%;height:100%;"
>
 
	<div class=container>

		<c:if test="${not empty param.login_error}">
			<div class="alert alert-error">
				<b> ERROR: </b> Invalid username or password.
				
			</div>
		</c:if>



		<div class='table-row'>
			<div class="cell1"></div>
			<div class="cell2">
				<br /> <br /> <br /> <br /> <br />

				<form class="form-signin" name='f'
					action="<c:url value='/resources/j_spring_security_check' />"
					method='POST' id="login">
					<h1>Login in</h1>

					<input type="text" name='j_username' class="input-block-level"
						placeholder="Nume utilizator" required> <input
						type="password" name='j_password' class="input-block-level"
						placeholder="Parola" required>
					<button class="btn btn-large btn-primary" type="submit"
						name="submit" value="Submit">Connect</button>
					<a href='${pageContext.request.contextPath}/user/sendMail'>
						Forgot password?? </a>
				</form>
			</div>
	


	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	

</body>
		</html>
	</c:param>
</c:import>