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
        <div class="centreo">
            <h5 class="${color}-text text-darken-3">Résultat de la prédiction</h5>
            <p> Valeurs d'entrée:</p>
            <table class="striped centered">
                <tr>
                    <s:forEach var="data" items="${entrerPredits}">
                        <td>${data}</td>
                    </s:forEach>
                </tr>
            </table>
            <br>
            <br>
            <p> Valeurs de sortie:</p>
            <table class="striped centered">
                <tr>
                    <s:forEach var="data" items="${sortiePredits}">
                        <td>${data[0]}</td>
                    </s:forEach>
                </tr>
            </table>
        </div>

    </div>

</main>

</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/hammer.min.js"></script>
<script type="text/javascript" src="assets/materialize.min.js"></script>


