<%@taglib uri="http://www.springframework.org/tags" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>
<!doctype html>
<html>
<head>
    <base href="/">
    <title>Recherche Opérationnelle</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="assets/css/materialize.min.css" type="text/css"/>
    <link rel="stylesheet" href="assets/css/style.css" type="text/css"/>
    <meta name="viewport" content="width=device-width">
</head>
<body>
<!----------------------- Menu Principal  ------------------------>
<header>
    <div class="navbar">
        <nav class="nav-extended ${color} darken-3">
            <div class="nav-wrapper">
                <a class="brand-logo center white-text" href="/">
                    <strong>${title}</strong>
                </a>
            </div>
            <!-- fin nav wrapper -->
            <div class="nav-content">
                <ul class="tabs ${color} darken-1 tabs-fixed-width tabs-transparent">
                    <li class="tab"><a target="_self" class="${hongroiseLink}" href="/">Methode Hongroise</a></li>
                    <li class="tab"><a target="_self" class="${noLink}" href="/nord-ouest">Coin Nord-Ouest</a></li>
                    <li class="tab"><a target="_self" class="${simplexLink}" href="/">Simplex pln</a></li>
                    <li class="tab"><a target="_self" class="${gomoryLink}" href="/">Simplex Coupe de Gomory</a></li>



                </ul>
            </div>
            <!-- fin nav-content -->
        </nav>
    </div>
</header>
