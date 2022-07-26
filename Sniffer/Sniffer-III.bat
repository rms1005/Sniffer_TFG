@echo off
rem *
rem *


set ejecutable=.\sniffer-III.jar
set libs1=.\libs\net.sourceforge.jpcap-0.01.16.jar
set libs2=.\libs\jdom.jar
set libs3=.\libs\AbsoluteLayout.jar
set libs4=.\libs\jfreechart-0.9.21.jar
set libs5=.\libs\jcommon-0.9.6.jar
set libs6=.\libs\jnetpcap.jar
set libs=%libs1%;%libs2%;%libs3%;%libs4%;%libs5%;%libs6%
set mvm=
Rem set javaPath= C:\Java\jdk-18;%PATH%
set PATH=.;%PATH%
echo %PATH%


:main
cls
echo.  
echo           ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo           ³                                                         ³
echo           ³                Proyecto Fin de Carrera                  ³
echo           ³                   SNIFFER III - V 1.0                   ³
echo           ³                                                         ³
echo           ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
echo.
echo                    ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo                    ³                                       ³
echo                    ³   1.- Modo Grafico                    ³
echo                    ³                                       ³
echo                    ³   2.- Modo Comando                    ³
echo                    ³                                       ³
echo                    ³   3.- Configurar MVJava               ³
echo                    ³                                       ³
echo                    ³   0.- Salir                           ³ 
echo                    ³                                       ³
echo                    ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
echo.
set choice=
set /p choice=                    Select option: 
rem if not '%choice%'=='' set choice=%choice:~0,1% solo coge un caracter
if '%choice%'=='3' goto mvj
if '%choice%'=='2' goto line
if '%choice%'=='1' goto graphic
if '%choice%'=='0' goto exit
IF ERRORLEVEL 3 GO TO ERROR1
IF ERRORLEVEL 2 GO TO ERROR2
IF ERRORLEVEL 1 GO TO ERROR1
echo.
ECHO "%choice%" is not valid please try again
pause
ECHO.
goto main
:graphic
  cls
	set java=java
	if not '%mvm%' == '' set java=java -Xmx%mvm%m
	pause
	rem echo "%java% -jar %ejecutable% 1" 
	rem pause
	%java% -jar "%ejecutable%"
	goto exit
:line
	cls
	set java=java
	echo %java% -jar "%ejecutable%" -help
	%java% -jar "%ejecutable%" -help	
	echo Pulse una tecla para continuar!!!
	echo .
	pause
	goto exit
:mvj
	cls
	echo.
echo           ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo           ³                                                         ³
echo           ³                Proyecto Fin de Carrera                  ³
echo           ³                   SNIFFER III - V 1.0                   ³
echo           ³                                                         ³
echo           ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
	echo.
	echo                    ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
	echo                    ³                                       ³
	echo                    ³            Config MVJ                 ³
	echo                    ³            ----------                 ³
	echo                    ³                                       ³
	echo                    ³   1.- Memoria virtual maxima          ³
	echo                    ³                                       ³
	echo                    ³   0.- Main Menu                       ³ 
	echo                    ³					³
	echo                    ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ
	echo.
	set choice=
	set /p choice=                    Select option:  
	if '%choice%'=='0' goto main
	if '%choice%'=='1' goto virtualmemory
	echo.
	ECHO "%choice%" is not valid please try again
	pause
	ECHO.
	goto mvj
	:virtualmemory
	echo.
	set /p mvm=                    Selecciona memoria virtual maxima (Megas): 
	goto main
:ERROR3
    echo "Algo ha fallado en el modo GRAFICO" 
:ERROR2
    echo "Algo ha fallado en el modo COMANDO" 
:ERROR1
    echo "Algo ha fallado en el modo MEMORIA VIRTUAL" 
:exit

echo "Max Virtual Memory %mvm%"
pause
cmd
