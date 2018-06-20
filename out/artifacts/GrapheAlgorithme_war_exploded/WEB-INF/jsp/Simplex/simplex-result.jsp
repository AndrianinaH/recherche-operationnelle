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
            <p>L'équation de Z :</p>
            <div class='input-field col s12'>
                <textarea disabled id='z' name="z" class="materialize-textarea">${z}</textarea>
                <label for='z'>Equation</label>
            </div>
            <br>
            <br>
            <p>Les contraintes (séparer par des points virgules ";"):</p>
            <div class='input-field col s12'>
                <textarea disabled  id='contraintes' name="contraintes" class="materialize-textarea">${contraintes}</textarea>
                <label for='contraintes'>contraintes</label>
            </div>
            <br>
            <br>
            <p>Resultats :</p>
            <table class="striped centered">
                <thead>
                    <tr>
                        <s:forEach var="val" items="${variables}">
                            <th>${val}</th>
                        </s:forEach>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <s:forEach var="val" items="${valeurs}">
                            <td>${val}</td>
                        </s:forEach>
                    </tr>
                </tbody>
            </table>
            <br>
            <p class="my-titre {{color}}-text text-darken-3">
                La valeur maximal de Z est : <b>${result}</b>
            </p>
        </div>
    </div>
</main>

</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/hammer.min.js"></script>
<script type="text/javascript" src="assets/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        $('#z').trigger('autoresize');
        $('#contraintes').trigger('autoresize');
    });

</script>


