<%@include file="../menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!----------------------- index ------------------------>
<main>
    <div class="container">
        <h5 class="my-titre ${color}-text text-darken-3">
            <b>${titre}</b>
        </h5>
        <br>
        <br>
        <div class="tableau-box">
            <form method="POST" action="neurone-prediction" enctype="multipart/form-data">
                <p>Sélectionner le fichier csv d'initialisation :</p>
                <div class="file-field input-field">
                    <div class="btn ${color}">
                        <span>File</span>
                        <input type="file" name="file" required>
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="Fichier csv d'initialisation">
                    </div>
                </div>
                <input type="hidden" value="${dataParEntrer}" name="dataParEntrer" required>
                <br>
                <h5 class="${color}-text text-darken-3">Prédiction</h5>
                <p>Les entrées à prédire :</p>
                <div class="row">
                    <s:forEach var="i" begin="0" end="${dataParEntrer-1}">
                        <div class='input-field col s2'>
                            <input type='text' id='entrerPredit${i}' name="entrerPredit${i}" required>
                            <label for='entrerPredit${i}'>entrer à prédire ${i}</label>
                        </div>
                    </s:forEach>
                </div>
                <div class="my-divider"></div>
                <br>
                <div class="centreo">
                    <div class='row'>
                        <button type='submit' class='col s12 btn btn-large waves-effect ${color} darken-3'>Calculer</button>
                    </div>
                </div>
            </form>
        </div>

    </div>

</main>

</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/hammer.min.js"></script>
<script type="text/javascript" src="assets/materialize.min.js"></script>


