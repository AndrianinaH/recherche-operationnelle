<%@include file="../menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!----------------------- index ------------------------>
<main>
    <div class="container">
        <h5 class="my-titre {{color}}-text text-darken-3">
            <b>${titre}</b>
        </h5>
        <br>
        <br>
        <div class="tableau-box">
            <form method="POST" action="hongroise-tableau">
                <p>Entrer les valeurs dans le tableau :</p>

                <s:forEach var="i" begin="0" end="${init-1}">
                    <div class="row">
                        <s:forEach var="j" begin="0" end="${init-1}">
                            <div class='input-field col s2'>
                                <input type='number' id='tab${i}${j}' name="tab${i}${j}" required>
                                <label for='tab${i}${j}'>tab${i}${j}</label>
                            </div>
                        </s:forEach>
                    </div>
                </s:forEach>
                <input type="hidden" value="${init}" name="init" required>


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


