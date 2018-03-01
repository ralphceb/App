<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
		<table class="table table-striped">
			<caption>Deposit Funds </caption>
			<thead>
				<tr>
					<th>1 INITIAL INFORMATION</th>
					<th>2 TRANSFER DETAILS</th>
					<th style="text-decoration:underline;font-size: larger;">3 CONFIRMATION</th>
				</tr>
			</thead>		
		</table>
		
		<form:form method="post" commandName="depositC">
			<h1><caption style="font-size: larger">DEPOSIT SUCCESS!!!</caption></h1>
			<br>
			<caption style="font-size: larger">Your balance after deposit $${acount.founds}</caption>
			<br>
			<br>
			<br>
		</fieldset>

		

		<button type="submit" class="btn btn-success">Finish</button>
	</form:form>

</div>

<%@ include file="common/footer.jspf" %>