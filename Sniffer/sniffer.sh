#!/bin/bash

#variables
 ejecutable=".\Sniffer-IV.jar"
 libs1=".\libs\net.sourceforge.jpcap-0.01.16.jar"
 libs2=".\libs\jdom.jar"
 libs3=".\libs\AbsoluteLayout.jar"
 libs4=".\libs\jfreechart-0.9.21.jar"
 libs5=".\libs\jcommon-0.9.6.jar"
 libs6=".\libs\jnetpcap.jar"
 libs="$libs1;$libs2;$libs3;$libs4;$libs5;$libs6"
 mvm=""
 javaPath="/usr/java/jdk1.8.0_321:$PATH"
 #PATH="$mvm;$PATH"
 #PATH="$PATH:$HOME/bin:$JAVA_HOME/bin"
echo $PATH

#funcion main
function main(){
    clear
    echo .  
    echo           "///////////////////////////////////////////////////////////"
    echo           "/                                                         /"
    echo           "/                Proyecto Fin de Carrera                  /"
    echo           "/                   SNIFFER IV - V 1.0                    /"
    echo           "/                                                         /"
    echo           "///////////////////////////////////////////////////////////"
    echo .
    echo                    "/////////////////////////////////////////"
    echo                    "/                                       /"
    echo                    "/   1.- Modo Grafico                    /"
    echo                    "/                                       /"
    echo                    "/   2.- Modo Comando                    /"
    echo                    "/                                       /"
    echo                    "/   3.- Configurar MVJava               /"
    echo                    "/                                       /"
    echo                    "/   0.- Salir                           /"
    echo                    "/                                       /"
    echo                    "/////////////////////////////////////////"
    echo .

    echo "Select option:"
    read choice

    case $choice in
    1)
	    graphic
	    ;;
    2)
	    line
	    ;;
    3)	
	    mvj
	    ;;
    0)
	    exit
	    ;;	 
    *)
        echo .
        echo "$choice is not valid please try again"
	echo "Pulse una tecla para continuar"
        read -n 1 -s
        echo .
        main
    esac
} #main
#Bucle
function graphic(){
    clear
	if [ $mvm != '' ]
    then
        java="java -Xmx$mvm"
    fi
	pause #Pausar hasta pulsar tecla
	`$java -jar $ejecutable 1`
	#pause #Pausar hasta pulsar tecla
	read -n 1 -s
	`java -jar $ejecutable`
	exit
}
function line(){
	clear
	echo .
	`java -jar $ejecutable -help`
	#'java -jar $ejecutable -help'
	#%java% -jar "%ejecutable%" -help	
	echo "Pulse una tecla para continuar!!!"
	read -n 1 -s
	exit
}
function mvj(){
	clear
	echo .
    echo           "///////////////////////////////////////////////////////////"
    echo           "/                                                         /"
    echo           "/                Proyecto Fin de Carrera                  /"
    echo           "/                   SNIFFER IV - V 1.0                    /"
    echo           "/                                                         /"
    echo           "///////////////////////////////////////////////////////////"
	echo .
	echo                    "/////////////////////////////////////////"
	echo                    "/                                       /"
	echo                    "/            Config MVJ                 /"
	echo                    "/            ----------                 /"
	echo                    "/                                       /"
	echo                    "/   1.- Memoria virtual maxima          /"
	echo                    "/                                       /"
	echo                    "/   0.- Main Menu                       /"
	echo                    "/					 /"
	echo                    "/////////////////////////////////////////"
	echo .
    read choice

    case $choice in
    1)
	    virtualmemory
	    ;;
    0)
	    main
	    ;;	 
    *)
        echo .
        echo "$choice is not valid please try again"
        echo "Pulse una tecla para continuar"
        read -n 1 -s
        main
    esac
}
function virtualmemory(){
	echo .
	echo "Selecciona memoria virtual maxima (Megas):" 
    read mvm
	main
}
function ERROR3(){
    echo "Algo ha fallado en el modo GRAFICO" 
}
function ERROR2(){
    echo "Algo ha fallado en el modo COMANDO" 
}
function ERROR1(){
    echo "Algo ha fallado en el modo MEMORIA VIRTUAL" 
}

#Ejecución
main
echo "Max Virtual Memory $mvm"
echo "Pulse una tecla para continuar"
read -n 1 -s
clear

