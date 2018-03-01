<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<script type="text/javascript">
  function handleClick()
  {
     if(document.getElementById('select1').value==0){
         alert("Select a Deposit Form");
     return false;
  }
     else
         return true;
  }
</script>

<div class="container">
		<table class="table table-striped">
			<caption>Deposit Funds </caption>
			<thead>
				<tr>
					<th style="text-decoration:underline;font-size: larger;">1 INITIAL INFORMATION</th>
					<th>2 TRANSFER DETAILS</th>
					<th>3 CONFIRMATION</th>
				</tr>
			</thead>		
		</table>
		
		<form:form method="post" commandName="depositT" onsubmit="return handleClick();">
		<fieldset class="form-group">
			<form:label path="desc">Deposit Form</form:label>	
			<form:select id="select1" path="desc" class="form-control"
				required="required">
				<form:option value="0" label="Select an Option" />
    			<form:options items="${cards}"/>
			</form:select>
			<form:errors path="desc" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="card">Deposit To:</form:label>
			<form:label path="card" style="text-decoration:underline;font-size: larger;"> ${acount.acount}</form:label>
			<form:errors path="card" cssClass="text-warning" />
		</fieldset>

		<button id="buton1" type="submit" class="btn btn-success">Next</button>
	</form:form>

</div>

<%@ include file="common/footer.jspf" %>