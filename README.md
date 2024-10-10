# Emuladores
Clases que simulan servicios, sockets, productores JMS y otros recursos.

## AMBIENTAR ACTIVE MQ - PASOS PARA CORRER LA PRUEBA DESDE CERO:

### DESCARGAR LA HERRAMIENTA DE LA PAGINA:
	https://activemq.apache.org/components/artemis/download/

Ya descargado, se descomprime, creamos el broker:

	$ ${ARTEMIS_HOME}/bin/artemis create mybroker


Ahora ejecutamos el Broker:

	mybroker/bin/artemis run

Consola:

	http://localhost:8161/console
	User: admin
	Pass: admin

Crear una QUEUE:
	queue create --name pruebas_queue --auto-create-address --anycast
	
CON ESO YA TENEMOS LA QUEUE Y LO NECESARIO PARA LA PRUEBA.

Detener el broker:

	mybroker/bin/artemis stop
		**Tambien se puede usar CTRL+C
