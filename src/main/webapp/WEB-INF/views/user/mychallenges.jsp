<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 //EN">

<c:import url="/WEB-INF/views/common/layout_login.jsp"
	charEncoding="UTF-8">
	<c:param name="title" value="Help" />
	<c:param name="body">
		<style>
body {
	position: relative;
}

.divIndent {
	padding-left: 4em;
	text-indent: -4em;
}
</style>

		<title>Wall</title>
		
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<!-- Fonts -->
<link href="${pageContext.request.contextPath}/css/animate.css"
	rel="stylesheet" />
<!-- Squad theme CSS -->
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/default.css"
	rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" />
<link
	href="${pageContext.request.contextPath}/fonts/glyphicons-halflings-regular.woff"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/fonts/glyphicons-halflings-regular.ttf"
	rel="stylesheet" />
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="css/register.css" />
		
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-responsive.css">
		</head>
		<body  >
			
		<div class="container">
			<div class="navbar-header page-scroll">
				
				
				<img src="${pageContext.request.contextPath}/img/ICYLogo.png" 
									alt="Image could not be displayed" height='30px' width='50px'/>
			</div>
			<div
				class="cellregister" align='right'>
				<a href='${pageContext.request.contextPath}/user/challenge' id="challenge" class="btn btn-primary" >Create Challenge</a>
				<a href='${pageContext.request.contextPath}/user/wall' id="challenge" class="btn btn-primary" >Wall</a>
				<a href='${pageContext.request.contextPath}/user/mychallenges' id="challenge" class="btn btn-primary:active" >My Challenges</a>
				<a href='${pageContext.request.contextPath}/login' id="challenge" class="btn btn-primary" >Logout</a>
					
				
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	
	
	<div class="sticky-wrap">
	<table class="sticky-enabled" style="margin: 0px; width: 100%; font-size: 20px">
	<thead>
		<tr>
			<th>Challenge</th>
			<th>Category</th>
			<th>Description</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${my_challenges}" var="challenge">
			<tr>
				<td>
					<c:out value="${challenge.challengename}" />
					<br />
					
				</td>
				<td>
					<c:out value="${challenge.challengecategory}" />
					<br />
					
				</td>
				<td>
					<strong>
						
							<c:out value="${challenge.challengedesc}" />
					
					</strong>
					<br />
					${challenge.challengeadditionalinfo}
				</td>
				<td>
					<input type="submit" class="btn btn-skin pull-right"
											id="btnAccept" value="Done">
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
		</body>

	</c:param>
</c:import>
</html>