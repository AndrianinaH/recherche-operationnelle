<%@include file="../menu.jsp" %>
<!----------------------- index ------------------------>
<main>
    <div class="container">
        <h5 class="my-titre {{color}}-text text-darken-3">
            <b>${titre}</b>
        </h5>
        <br>
        <br>
        <div class="centreo">
            <p>Tableau de valeur :</p>
            <table class="striped centered">
                <s:forEach var="tab" items="${tableauInitial}">
                    <tr>
                        <s:forEach var="data" items="${tab}">
                            <td>${data.getValue()}</td>
                        </s:forEach>
                    </tr>
                </s:forEach>
            </table>
        </div>
        <div class="centreo">
            <br>
            <br>
            <p class="my-titre {{color}}-text text-darken-3">
                La valeur minimal de l'affectation est : <b>${minimal}</b>
            </p>
            <br>
            <p class="my-titre {{color}}-text text-darken-3">
                La valeur maximal de l'affectation est : <b>${maximal}</b>
            </p>
        </div>

    </div>

</main>

</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/hammer.min.js"></script>
<script type="text/javascript" src="assets/materialize.min.js"></script>


