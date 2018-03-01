<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<script type="text/javascript">
var expirationDate = new Date();
var tokens;
  function handleClick()
  {
     if(document.getElementById('amountInput').value <= 0){
         alert("Insert a valid amount to deposit");
     return false;
  }
     else{
    	 var date = new Date();
    	 var tokenGenerated=document.getElementById('tokenInput');
    	 var tokenCopied=document.getElementById('tokenAccess');
         if(expirationDate > date){
             if(tokenGenerated.value==tokenCopied.value){
            	 return true;
                 }else{
                     alert("The token doesn't match");
             		 return false;	
         			}
             }else{
			alert("Token has expired, please generate a new one")
			return false;
                 }
         
     }
  };

  function token(){

		   //alert( Math.floor(10000000 + Math.random() * 90000000));
		   tokens=Math.floor(10000000 + Math.random() * 90000000);
		   var date = new Date();
		   date.setSeconds(date.getSeconds() + 30)
		   expirationDate=date;
		   document.getElementById('tokenInput').value=tokens;
		   document.getElementById('tokenInput').style.visibility = "visible";
  		};

	
</script>

<div class="container">
	<table class="table table-striped">
		<caption>Deposit Funds</caption>
		<thead>
			<tr>
				<th>1 INITIAL INFORMATION</th>
				<th style="text-decoration: underline; font-size: larger;">2
					TRANSFER DETAILS</th>
				<th>3 CONFIRMATION</th>
			</tr>
		</thead>
	</table>

	<form:form method="post" commandName="depositD"
		onsubmit="return handleClick();">
		<fieldset class="form-group">
			<form:label path="acount">Destination Acount:</form:label>
			<form:label path="acount"
				style="text-decoration:underline;font-size: larger;"> ${acount.acount}</form:label>
			<form:errors path="acount" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="founds">Amount:</form:label>
			<form:input id="amountInput" path="founds" type="number"
				class="form-control" required="required" />
			<form:errors path="founds" cssClass="text-warning" />
		</fieldset>
		<br>
		<label>Enter your validation token:</label>
		<input id="tokenAccess" type="number" class="form-control"
			required="required" />
		<br>
		<br>
		<input id="tokenInput" type="number" style="visibility: hidden;" class="form-control" />
		<br>
		<br>
		<button type="button" class="btn btn-success" onclick="token();">Generate
			Token</button>
		<button type="submit" class="btn btn-success">Next</button>
	</form:form>

</div>

<%@ include file="common/footer.jspf"%>