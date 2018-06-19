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
            <form method="POST" action="neurone-tableau" enctype="multipart/form-data">
                <h5 class="${color}-text text-darken-3">Apprentissage</h5>
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
                <p>Entrer les valeurs des entrées par défaut :</p>
                <s:forEach var="i" begin="0" end="${entrer-1}">
                    <div class="row">
                        <s:forEach var="j" begin="0" end="${dataParEntrer-1}">
                            <div class='input-field col s2'>
                                <input type='text' id='entrer${i}${j}' name="entrer${i}${j}" required>
                                <label for='entrer${i}${j}'>entrée${i}${j}</label>
                            </div>
                        </s:forEach>
                    </div>
                </s:forEach>
                <br>
                <br>
                <p>Entrer les valeurs des sorties attendues:</p>
                <s:forEach var="i" begin="0" end="${sortie-1}">
                    <div class="row">
                        <s:forEach var="j" begin="0" end="${dataParSortie-1}">
                            <div class='input-field col s2'>
                                <input type='text' id='sortie${i}${j}' name="sortie${i}${j}" required>
                                <label for='sortie${i}${j}'>sortie${i}${j}</label>
                            </div>
                        </s:forEach>
                    </div>
                </s:forEach>
                <input type="hidden" value="${entrer}" name="entrer" required>
                <input type="hidden" value="${dataParEntrer}" name="dataParEntrer" required>
                <input type="hidden" value="${sortie}" name="sortie" required>
                <input type="hidden" value="${dataParSortie}" name="dataParSortie" required>
                <input type="hidden" value="${alpha}" name="alpha" required>
                <input type="hidden" value="${seuil}" name="seuil" required>
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


