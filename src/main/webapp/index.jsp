<html>
<head><title>Restful Web Services with Jersey</title></head>
<body>
	<h2>Restful Web Services with Jersey</h2>
	<a href=<%=request.getRequestURL()%>/index.html>API Docs Via Swagger. </a> <br>
	
	<div style="padding-left: 2em;">Note! After the page is
	loaded, paste <font color='green'><i><%=request.getRequestURL()%>webapi/swagger.json</i></font>
	in the textbox and click explore to see the full list of API
	end-points.</div>

	<h3>Sample Calls</h3>
	<ul>
		<li>Get all orders in JSON: <a href="webapi/orders">/orders</a></li>
		<li>Get all orders belonging to a customer as XML: <a
			href="webapi/orders/VINET">/orders/VINET</a></li>
		<li>Get an order for a customer with an order id: <a
			href="webapi/orders/VINET/10248">/orders/VINET/10248</a></li>
	</ul>
	<br/><br/>
	<p> Visit <a href="http://jersey.java.net">Project Jersey website</a> for
		more information on Jersey!</p>
</body>
</html>
