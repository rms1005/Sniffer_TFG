
<h1 align="center">
  <br>
  Sniffer IV
  <br>
</h1>

---
## Descripción

Este proyecto consiste en la mejora de la aplicación Sniffer, una
herramienta para la gestión de redes, que permite capturar y enviar
paquetes con determinados protocolos, así como definir nuevos
paquetes creados por el usuario, entre otras funcionalidades.

El primer objetivo del proyecto es la investigación de las diferentes
posibles bibliotecas sobre las que pueda basarse la aplicación, dado
que la actual ya no se encuentra en desarrollo. Además, se comprueba
el funcionamiento multiplataforma de la aplicación.

El segundo objetivo es la implementación de mejoras en la aplicación,
con el fin de potenciar la usabilidad que la caracteriza. Están
orientadas, sobre todo, a la forma en que se muestran los datos que
reconoce, y a rebajar la cantidad de recursos que acapara en algunas
de sus funcionalidades.

Por último, relacionado con el primer objetivo, tenemos la implementación
de mejoras en la biblioteca utilizada actualmente. En
concreto, se añaden 3 nuevos protocolos, siendo estos DNS, ICMPv6
e IGMP.

---
## Estructura de directorios

/ -> Raíz del proyecto. Se encuentran ahí este fichero y otros directorios.

/Herramientas_Instalacion_Sniffer/ -> Se encuentran los diferentes archivos 
cuya instalación es necesaria para el funcionamiento de la aplicación. En el 
apéndice E. Documentación de usuario, se explica cuando se necesita 
cada uno de ellos.

/jnetpcap-original/ -> En este directorio se encuentra la última versión 
oficial de la biblioteca jNetPcap. Es decir, no se trata de la última versión 
utilizada en la aplicación. No consiste solo en los paquetes y las clases, sino 
que contiene mucha información adicional, como los ficheros escritos en lenguaje 
C que conforman el DLL, o las versiones previas de la biblioteca.

/Memoria/ -> Este directorio almacena los documentos relativos a la memoria, 
siendo estos los PDFs de los anexos y la propia memoria.

/Sniffer/ -> Directorio del proyecto sobre el que se ha trabajado, usando la 
aplicación Eclipse. Contiene aquellos directorios que usa la aplicación desarrollada.

/jNetPcap/ -> Directorio del proyecto de la biblioteca que ha sido mejorada, usando la 
aplicación Eclipse. Contiene aquellos directorios que conforman la biblioteca.

---
> GitHub [@rms1005](https://github.com/rms1005)