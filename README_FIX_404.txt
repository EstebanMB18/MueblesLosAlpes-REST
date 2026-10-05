CORRECCION V3 - ERROR 404
=========================

Causa encontrada:
La compilacion V2 mostraba este warning:
  "selected war files include a WEB-INF/web.xml which will be ignored"
Por eso el WAR se desplegaba, pero no quedaba registrado el servlet REST /webresources/* y toda URL REST respondia 404.

Cambios V3:
1. maven-war-plugin actualizado para incluir WEB-INF/web.xml.
2. web.xml actualizado a Jersey 2, que es el Jersey integrado en GlassFish 4.1.
3. API Java EE 7 como provided; se evita empaquetar el viejo jsr311-api dentro del WAR.
4. WAR con nombre fijo: mueblesdelosalpes.servicios.war.
5. Script de despliegue hace undeploy de la version anterior y despliega el WAR nuevo con context root fijo.

PASOS:
1) Abrir PowerShell nueva y configurar Java/Maven:
   $env:MAVEN_HOME="C:\Maven\apache-maven-3.9.16"
   $env:JAVA_HOME="C:\Program Files\Zulu\zulu-8"
   $env:Path="$env:MAVEN_HOME\bin;$env:JAVA_HOME\bin;$env:Path"

2) Ejecutar:
   .\01_COMPILAR.bat

3) Confirmar BUILD SUCCESS y despues:
   .\02_DESPLEGAR_GLASSFISH.bat

4) Probar:
   .\03_PROBAR_REST.bat

URL:
http://localhost:8080/mueblesdelosalpes.servicios/webresources/Catalogo/muebles/
