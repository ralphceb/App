<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Welcome back ${acount.user}</caption>
			<br>
			
			<thead>
				<tr>
					<th>Account</th>					
					<th>Balance</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>${acount.acount}</td>						
						<td>${acount.founds}</td>
					</tr>
			</tbody>
		</table>
		
	</div>
<%@ include file="common/footer.jspf" %>