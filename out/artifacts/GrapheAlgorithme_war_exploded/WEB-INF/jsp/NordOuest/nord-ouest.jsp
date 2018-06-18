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
        <div class="login-box">
            <form method="POST" action="nord-ouest-init">
                <p>Entrer le nombre de ligne du tableau :</p>
                <div class='input-field col s6'>
                    <input type='number' id='ligne' name="ligne">
                    <label for='ligne'>Ligne</label>
                </div>
                <p>Entrer le nombre de colonne du tableau :</p>
                <div class='input-field col s6'>
                    <input type='number' id='colonne' name="colonne">
                    <label for='colonne'>Colonne</label>
                </div>

                <div class="my-divider"></div>
                <br>
                <div class="centreo">
                    <div class='row'>
                        <button type='submit' class='col s12 btn btn-large waves-effect ${color} darken-3'>Commencer</button>
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
<script>
    $(document).ready(function(){

    });

</script>

