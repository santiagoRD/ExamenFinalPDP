<%-- 
    Document   : index
    Created on : 13/06/2018, 06:04:38 PM
    Author     : SANTIAGO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.servletContext.contextPath}/css/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
      <form class="form-signin" action="${pageContext.servletContext.contextPath}/ingresoServlet" method="post">
      <h1 class="h3 mb-3 font-weight-normal">Curso</h1>
      <label for="inputcurso" class="sr-only">Id Materia</label>
      <input name="txtidmateria" type="text" id="inputcurso" class="form-control" placeholder="Id Materia" required autofocus>
      
      <label for="inputnombre" class="sr-only">Nombre</label>
      <input name="txtnombre" type="text" id="inputnombre" class="form-control" placeholder="Nombre" required>
      
      <label for="inputnumcred" class="sr-only">Numero Creditos</label>
      <input name="txtnumcred" type="text" id="inputnumcred" class="form-control" placeholder="Numero Creditos" required>
      
      <label for="inputcupomax" class="sr-only">Cupo Maximo</label>
      <input name="txtcupomax" type="text" id="inputnumcred" class="form-control" placeholder="Cupo Maximo" required>
      
      <label for="inputcupomin" class="sr-only">Cupo Minimo</label>
      <input name="txtcupomin" type="text" id="inputcupomin" class="form-control" placeholder="Cupo Minimo" required>
      
      
      <button name="accion" value="registrar"class="btn btn-lg btn-primary btn-block" type="submit">Registrar</button>
      
      
    </form>
      <form class="form-signin" action="${pageContext.servletContext.contextPath}/ingresoServlet" method="post">
      <h1 class="h3 mb-3 font-weight-normal">Inscripciones</h1>
      <label for="inputinscripciones" class="sr-only">Id Inscripcion</label>
      <input name="txtidinscrip" type="text" id="inputcursos" class="form-control" placeholder="Id inscripcion" required autofocus>
      
      <label for="inputdoc" class="sr-only">Documento</label>
      <input name="txtdoc" type="text" id="inputdoc" class="form-control" placeholder="Documento" required>
      
      <label for="inputcur" class="sr-only">Curso</label>
      <input name="txtcurso" type="text" id="inputcur" class="form-control" placeholder="Curso" required>
      
      <label for="inputest" class="sr-only">Estrato</label>
      <input name="txtestrato" type="text" id="inputest" class="form-control" placeholder="Estrato" required>
      
      <button name="accion" value="inscribir"class="btn btn-lg btn-primary btn-block" type="submit">Inscribir</button>
      
    </form>
      <footer>
          <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
      </footer>
  </body>
</html>

