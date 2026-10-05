@echo off
set "URL=http://localhost:8080/mueblesdelosalpes.servicios/webresources/Catalogo/muebles/"
echo Probando %URL%
powershell -NoProfile -Command "try { $r=Invoke-WebRequest -UseBasicParsing -Uri '%URL%'; Write-Host ('HTTP ' + [int]$r.StatusCode); $r.Content } catch { Write-Host $_.Exception.Message; if ($_.Exception.Response) { Write-Host ('HTTP ' + [int]$_.Exception.Response.StatusCode) }; exit 1 }"
pause
