<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html>
<body>
<h1>Ajouter une voiture</h1>
<form action="VoitureServlet" method="post">
    <label>Immatriculation :</label><br/>
    <input type="text" name="immatriculation" required/><br/><br/>
    
    <label>Marque :</label><br/>
    <input type="text" name="marque" required/><br/><br/>
    
    <label>Modèle :</label><br/>
    <input type="text" name="modele" required/><br/><br/>
    
    <label>État :</label><br/>
    <select name="etat" required>
        <option value="pris en charge">Pris en charge</option>
        <option value="en cours">En cours</option>
        <option value="terminé">Terminé</option>
    </select><br/><br/>
    
    <input type="submit" value="Ajouter"/>
</form>
<br/>
<a href="VoitureServlet">Retour à la liste</a>
</body>
</html>




