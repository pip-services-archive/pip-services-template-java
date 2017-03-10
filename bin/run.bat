@ECHO OFF

SETLOCAL enabledelayedexpansion

TITLE Microservice Template

SET OLDDIR=%CD%
SET CURRDIR=%~dp0\..

CD /d %CURRDIR%

mvn exec:java -Dexec.mainClass="org.pipservices.dummy.run.DummyProcessRunner" %1

CD /d %OLDDIR%

ENDLOCAL