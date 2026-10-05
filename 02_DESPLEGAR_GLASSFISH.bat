@echo off
setlocal
set "GF=C:\glassfish4\glassfish\bin\asadmin.bat"
set "WAR=%~dp0mueblesdelosalpes.servicios\target\mueblesdelosalpes.servicios.war"

echo ==========================================================
echo MUEBLES LOS ALPES - DESPLIEGUE GLASSFISH 4.1 (V3)
echo ==========================================================

if not exist "%GF%" (
  echo [ERROR] No se encontro GlassFish 4.1 en C:\glassfish4
  pause
  exit /b 1
)
if not exist "%WAR%" (
  echo [ERROR] No existe el WAR: %WAR%
  echo Ejecuta primero 01_COMPILAR.bat
  pause
  exit /b 1
)

call "%GF%" start-domain domain1 >nul 2>&1
call "%GF%" set server.ejb-container.property.disable-nonportable-jndi-names=true

rem Quitar version anterior si existe para evitar contextos viejos.
call "%GF%" undeploy mueblesdelosalpes.servicios >nul 2>&1

call "%GF%" deploy --force=true --contextroot mueblesdelosalpes.servicios "%WAR%"
if errorlevel 1 (
  echo.
  echo [ERROR] Fallo el despliegue. Ultimas lineas del log:
  powershell -NoProfile -Command "Get-Content 'C:\glassfish4\glassfish\domains\domain1\logs\server.log' -Tail 80"
  pause
  exit /b 1
)

echo.
echo [OK] Aplicacion desplegada.
call "%GF%" list-applications

echo.
echo URL de prueba:
echo http://localhost:8080/mueblesdelosalpes.servicios/webresources/Catalogo/muebles/
echo.
pause
