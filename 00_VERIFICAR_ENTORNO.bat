@echo off
echo ===== VERIFICACION DE ENTORNO =====
echo.
echo JAVA:
java -version 2>&1
if errorlevel 1 echo [FALTA] Java no esta en PATH.
echo.
echo MAVEN:
where mvn 2>nul
if errorlevel 1 (
  echo [AVISO] Maven no esta en PATH. Puedes usar el Maven integrado de NetBeans 8.2.
) else (
  call mvn -version
)
echo.
echo RECOMENDADO PARA ESTE LABORATORIO: JDK 8 + NetBeans 8.2 + GlassFish 4.1.
pause
