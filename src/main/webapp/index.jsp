<%@ page import="java.util.List" %>
<%@ page import="model.Voiture" %>
<html>
<body>
<h1>Liste des voitures</h1>

<p><a href="VoitureServlet?action=ajouter">Ajouter une voiture</a></p>

<table border="1">
    <tr>
        <th>Immatriculation</th>
        <th>Marque</th>
        <th>Modèle</th>
        <th>État</th>
        <th>Actions</th> 
    </tr>
    <%
        List<Voiture> voitures = (List<Voiture>) request.getAttribute("voitures");
        if (voitures != null) {
            for (Voiture v : voitures) {
    %>
    <tr>
        <td><%= v.getImmatriculation() %></td>
        <td><%= v.getMarque() %></td>
        <td><%= v.getModele() %></td>
        <td><%= v.getEtat() %></td>
        <td>
            <a href="VoitureServlet?action=modifier&immatriculation=<%= v.getImmatriculation() %>">Modifier</a>
            <a href="VoitureServlet?action=supprimer&immatriculation=<%= v.getImmatriculation() %>" 
       onclick="return confirm('Confirmer la suppression de cette voiture ?');">Supprimer</a>
            
        </td>
    </tr>
    <% 
            }
        } else { 
    %>
    <tr><td colspan="5">Aucune voiture trouvée</td></tr>
    <% } %>
</table>
</body>
</html>
