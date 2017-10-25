# productManager-SpringRestApi-Angular

Este proyecto contiene dos componente:
1. Un servicio web que actuá como una API Rest. 
2. Una aplicación que exponé una pagina web la cual permite consultar la lista de ordenes asociadas a un cliente.

## API Rest
La API Rest esta implementada en Spring y Hibernate. Exponen las siguientes funcionalidades:

1. Consultar el listado de clientes.
2. Permite crear una órden para un cliente con hasta máximo 5 productos. Teniendo en cuenta que sólo algunos productos están permitidos para un cliente.
3. Permite listar las órdenes de un cliente por un rango de fechas.

NOTPA: Ver documento en PDF que descrite la mensajerica de cada metodo del API.

## Pagina web
La pagina web esta implementada en html5 y usan Angular.

1. Permite seleccionar un cliente y presente las órdenes del último mes usando el método listar órdenes del servicio web.
