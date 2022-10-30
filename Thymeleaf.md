Jak tahat proměnné z modelu v thymeleaf template:

prvně nad hlavičkou html definovat:
<html lang="cs" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">

  
  
potom taháme z modelu zapomocí th:XYZ
  např. th:href - pro dynamicky v šabloně měnící se odkaz (např pro dyn. měnící se styly: th:href="@{'/css/' + ${name} + '.css'}" ve style tagu)
        th:text - nastavení textu v kombinaci s proměnnou v modelu
        th:each - foreach cyklus
        th:field a th:action - pro získávání dat z formulářů
        th:if a th:unless - if else 
