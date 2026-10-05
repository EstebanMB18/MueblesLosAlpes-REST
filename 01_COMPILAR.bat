@echo off
setlocal
cd /d "%~dp0"
echo ==========================================================
echo MUEBLES LOS ALPES - COMPILACION

echo ==========================================================
where mvn >nul 2>nul
if errorlevel 1 (
  echo [ERROR] Maven no esta disponible en PATH.
  echo Usa NetBeans 8.2: Clean and Build backend, luego servicios.
  echo O instala Maven y vuelve a ejecutar este archivo.
  pause
  exit /b 1
)
java -version
call mvn -version

echo.
echo [1/2] Compilando e instalando backend...
pushd mueblesdelosalpes.backend
call mvn clean install
if errorlevel 1 (
  echo [ERROR] Fallo backend.
  popd
  pause
  exit /b 1
)
popd

echo.
echo [2/2] Compilando servicios...
pushd mueblesdelosalpes.servicios
call mvn clean package
if errorlevel 1 (
  echo [ERROR] Fallo servicios.
  popd
  pause
  exit /b 1
)
popd

echo.
echo ==========================================================
echo OK - WAR generado en:
echo mueblesdelosalpes.servicios\target\mueblesdelosalpes.servicios-1.0-SNAPSHOT.war
echo ==========================================================
pause
