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
            <form method="POST" action="nord-ouest-tableau">
                <p>Entrer les valeurs de départ dans le tableau :</p>
                <s:forEach var="i" begin="0" end="${ligne-1}">
                    <div class="row">
                        <s:forEach var="j" begin="0" end="${colonne}">
                            <s:choose>
                                <s:when test="${j != colonne}">
                                    <div class='input-field col s2'>
                                        <input type='number' id='tab${i}${j}' name="tab${i}${j}" required>
                                        <label for='tab${i}${j}'>tab${i}${j}</label>
                                    </div>
                                </s:when>
                                <%-- offres --%>
                                <s:otherwise>
                                    <div class='input-field col s2'>
                                        <input type='number' id='offre${i}' name="offre${i}" required>
                                        <label for='offre${i}'>offre${i}</label>
                                    </div>
                                </s:otherwise>
                            </s:choose>

                        </s:forEach>
                    </div>
                </s:forEach>
                <%-- demandes --%>
                <div class="row">
                    <s:forEach var="i" begin="0" end="${colonne-1}">
                        <div class='input-field col s2'>
                            <input type='number' id='demande${i}' name="demande${i}" required>
                            <label for='demande${i}'>demande${i}</label>
                        </div>
                    </s:forEach>
                </div>

                <input type="hidden" value="${ligne}" name="ligne" required>
                <input type="hidden" value="${colonne}" name="colonne" required>



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


