<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Voiture" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Immatriculation</th><th>Marque</th><th>Modèle</th><th>État</th><th>Actions</th>
    </tr>
    <%
        List<Voiture> voitures = (List<Voiture>) request.getAttribute("voitures");
        for(Voiture v : voitures){
    %>
    <tr>
        <td><%= v.getImmatriculation() %></td>
        <td><%= v.getMarque() %></td>
        <td><%= v.getModele() %></td>
        <td><%= v.getEtat() %></td>
        <td>
            <a href="VoitureServlet?action=modifier&immatriculation=<%= v.getImmatriculation() %>">Modifier</a> | 
            <a href="VoitureServlet?action=supprimer&immatriculation=<%= v.getImmatriculation() %>" 
               onclick="return confirm('Confirmer la suppression de cette voiture ?');">Supprimer</a>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>