<%@include file="../menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!----------------------- index ------------------------>
<main>
    <div class="container">
        <h5 class="my-titre ${color}-text text-darken-3">
            <b>${titre}</b>
        </h5>
        <div class="centreo">
            <br>
            <br>
            <p class="my-titre {{color}}-text text-darken-3">
                La valeur Z est : <b>${result}</b>
            </p>
        </div>
        <br>
        <br>
        <div class="centreo">
            <p>Tableau initial :</p>
            <table class="striped centered">
                <s:forEach var="tab" items="${tableauInitial}">
                    <tr>
                        <s:forEach var="data" items="${tab}">
                            <td>${data}</td>
                        </s:forEach>
                    </tr>
                </s:forEach>
            </table>
            <br>
            <br>
            <p><b>Liste des offres</b></p>
            <table class="striped centered">
                <tr>
                    <s:forEach var="offre" items="${offres}">
                        <td>${offre}</td>
                    </s:forEach>
                </tr>
            </table>
            <br>
            <br>
            <p><b> Liste des demandes </b></p>
            <table class="striped centered">
                <tr>
                    <s:forEach var="demande" items="${demandes}">
                        <td>${demande}</td>
                    </s:forEach>
                </tr>
            </table>
            <br>
            <br>
            <p>Tableau de solution :</p>
            <table class="striped centered">
                <s:forEach var="solution" items="${solutions}">
                    <tr>
                        <s:forEach var="data" items="${solution}">
                            <td>${data}</td>
                        </s:forEach>
                    </tr>
                </s:forEach>
            </table>
        </div>


    </div>

</main>

</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/hammer.min.js"></script>
<script type="text/javascript" src="assets/materialize.min.js"></script>


