<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Movements List</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Type</th>
					<th>Amount</th>
					<th>Name</th>
					<th>Date</th>						
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${moves}" var="move">
					<tr>
						<td>${move.type}</td>
<%-- 						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td> --%>
						<td>${move.desc}</td>
						<td >$ ${move.amount}</td>
						<td>${move.name}</td>
						<td>${move.date}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="btn btn-success" href="/">Home</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>