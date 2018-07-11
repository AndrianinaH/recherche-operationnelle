<%@include file="../menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!----------------------- index ------------------------>
<main>
    <div class="container">
        <h5 class="my-titre ${color}-text text-darken-3">
            <b>${titre}</b>
        </h5>
        <br>
        <div class="centreo">
            <a class="s12 btn btn-large waves-effect amber darken-1 goPrediction">Aller directement à la phase de prédiction</a>
        </div>
        <br>
        <br>
        <div class="tableau-box-box row">
            <form method="POST" action="neurone-init">
                <div class="col s12"><p class="center-align">Initialisation des entrées par défaut:</p></div>
                <div class='input-field col s6'>
                    <input type='number' id='entrer' name="entrer" required>
                    <label for='entrer'>Entrer le nombre d'entrée </label>
                </div>
                <div class='input-field col s6'>
                    <input type='number' id='dataParEntrer' name="dataParEntrer" required>
                    <label for='dataParEntrer'>Entrer le nombre de données par entrée </label>
                </div>
                <div class="col s12"><br><p class="center-align">Initialisation des sorties attendues:</p></div>
                <div class='input-field col s6'>
                    <input type='number' id='sortie' name="sortie" required>
                    <label for='sortie'>Entrer le nombre de sortie </label>
                </div>
                <div class='input-field col s6'>
                    <input type='number' id='dataParSortie' name="dataParSortie" required>
                    <label for='dataParSortie'>Entrer le nombre de donnée par sortie </label>
                </div>
                <div class="col s12"><br><p class="center-align">Paramètres de calcul </p></div>
                <div class='input-field col s6'>
                    <input type='text' id='seuil' name="seuil" required>
                    <label for='seuil'>Entrer le seuil d'erreur </label>
                </div>
                <div class='input-field col s6'>
                    <input type='text' id='alpha' name="alpha" required>
                    <label for='alpha'>Entrer alpha </label>
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

<div id="initPrediction" class="modal">
    <form method="POST" action="neurone-prediction-init">
    <div class="modal-content">
        <h5 class="my-titre ${color}-text text-darken-3">
            <b>Initialiser le nombre de données d'entrée</b>
        </h5>
        <div class='input-field col s6'>
            <input type='number' id='dataParEntrers' name="dataParEntrer" required>
            <label for='dataParEntrers'>Nombre de données d'entrée</label>
        </div>
    </div>
    <div class="modal-footer">
        <a class="modal-action modal-close waves-effect btn black">Annuler</a>
        <button type='submit' class='btn waves-effect amber darken-1'>Prédire</button>
    </div>
    </form>

</div>



</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/hammer.min.js"></script>
<script type="text/javascript" src="assets/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        $('.modal').modal();

        $('.goPrediction').on("click",function(){
            $('#initPrediction').modal('open');
        });

    });
</script>


