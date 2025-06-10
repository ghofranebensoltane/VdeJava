<%@ page import="model.Voiture" %>
<%
    Voiture voiture = (Voiture) request.getAttribute("voiture");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<h2>Modifier une voiture</h2>
<form method="post" action="VoitureServlet">
    <input type="hidden" name="action" value="modifier" />
    <label>Immatriculation:</label>
    <input type="text" name="immatriculation" value="<%= voiture.getImmatriculation() %>" readonly /><br/>

    <label>Marque:</label>
    <input type="text" name="marque" value="<%= voiture.getMarque() %>" /><br/>

    <label>Modèle:</label>
    <input type="text" name="modele" value="<%= voiture.getModele() %>" /><br/>

    <label>État:</label>
    <input type="text" name="etat" value="<%= voiture.getEtat() %>" /><br/>

    <button type="submit">Modifier</button>
</form>
<a href="VoitureServlet">Retour à la liste</a>




<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>