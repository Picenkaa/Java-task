
<%@page import="ds.buildings"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="ds.registry,java.util.*"%>  

<html>
<body>
<table>
    <table border="1">
    <tr>
        <th>Address</th>
        <th>Owner</th>
        <th>Size</th>
        <th>Market value</th>
        <th>Property type</th>
    </tr>
    <%
        registry a= new registry();
         ArrayList<buildings> b = a.gautiSarasa();
for (buildings c: b)
{


%>
    <tr>
       <td> <%=c.getAddress()%></td> 
       <td> <%=c.getOwner()%></td> 
       <td> <%=c.getSize()%></td> 
       <td> <%=c.getMarket_value()%></td> 
       <td> <%=c.getProperty_type()%></td> 
    </tr>
    <%       
}
%>
		
	</table>

</body>
</html>

 

   
