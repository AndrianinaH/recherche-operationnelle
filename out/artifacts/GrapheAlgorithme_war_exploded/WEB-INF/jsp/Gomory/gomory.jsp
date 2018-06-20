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
            <form method="POST" action="gomory-result">
                <p>Entrer l'équation de Z :</p>
                <div class='input-field col s12'>
                    <textarea id='z' name="z" class="materialize-textarea"></textarea>
                    <label for='z'>Equation</label>
                </div>
                <br>
                <br>
                <p>Entrer les contraintes (séparer par des points virgules ";"):</p>
                <div class='input-field col s12'>
                    <textarea id='contraintes' name="contraintes" class="materialize-textarea"></textarea>
                    <label for='contraintes'>contraintes</label>
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
        $('#z').trigger('autoresize');
        $('#contraintes').trigger('autoresize');
    });

</script>


