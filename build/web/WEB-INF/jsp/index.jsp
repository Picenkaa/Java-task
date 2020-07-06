
<%@page import="ctrl.MainControl"%>
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
        MainControl mc= new MainControl();
         ArrayList<buildings> b = a.gautiSarasa();
for (buildings c: b)
{


%>
    <tr>
       <td> <%=c.getAddress()%></td> 
       <td> <%=c.getOwner()%></td> 
       <td> <%=c.getSize()+" m<sup>2</sup>" %></td> 
       <td> <%=c.getMarket_value()+ " &#8364"%></td> 
       <td> <%=c.getProperty_type()%></td> 
    </tr>
    <%       
}
%>
		
	</table>
<p></p>


<table style="width:100%">
    <table border="1">
    <tr>
        <th>Owner</th>
         <th>Tax per year</th>
    </tr>
    <%
       
         ArrayList<buildings> d = a.gautiSarasa();
       HashSet hs = new HashSet();
       hs.addAll(d);
d.clear();
d.addAll(hs);
for (buildings c: d)
{

%>
    <tr>
       <td> <%=c.getOwner()%></td> 
        <td> <%=mc.getThat(c.getOwner())%></td> 
    </tr>
    <%       
}
%>
		
	</table>

</body>
</html>

 

   
