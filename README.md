# Pelumania

Descarga: https://sourceforge.net/projects/hair-head/




REQUERIMIENTOS


|Número|Tipo|Clase|Requerimiento|Detalle|
| :-: | :-: | :-: | :-: | :-: |
|1|RQ FUNCIONAL|Esencial|Como recepcionista quiero realizar Alta de citas en un calendario|Campos dia, horario,cliente, profesional, cliente,estado, servicio, local y precio (ni cliente ni profesional es obligatorio)|
|2|RQ FUNCIONAL|Esencial|Como recepcionista quiero realizar Modificar el estado de mis citas en el calendario|Estados: activo, cancelado, vencido y completado|
|3|RQ FUNCIONAL|Esencial|Como Recepcionista quiero consultar el calendario de citas|Se podrán ver todas las citas programadas, se podrán ver las realizadas, canceladas y activas|
|4|RQ FUNCIONAL|Esencial|Como Recepcionista quiero visualizar la descripción de una cita|Se podrán ver todos los campos|
|5|RQ FUNCIONAL|Esencial|Como Supervisor quiero realizar Alta de Profesional|El profesional tendrá nombre, apellido, servicios que realiza y sucursal de origen|
|6|RQ FUNCIONAL|Esencial|Como Supervisor quiero ver el estado del Profesional|El profesional podrá estar activo, inactivo(de vacaciones o licencia) o desvinculado|
|7|RQ FUNCIONAL|Esencial|Como Supervisor quiero modificar el estado del Profesional|Si se selecciona inactivo, se podra elegir durante cuánto tiempo no pertenece al staff|
|8|RQ FUNCIONAL|Esencial|Como Supervisor quiero crear un servicio|Campos, nombre y precio, debe asignarse mínimamente un profesional para darse de alta.|
|9|RQ FUNCIONAL|Esencial|Como Supervisor quiero modificar un servicio|Se podrá modificar cualquier campo|
|10|RQ FUNCIONAL|Esencial|Como Recepcionista quiero visualizar los servicios|Se podrán ver todos los servicios que brinda la sucursal y los profesionales que la realizan|
|11|RQ NO FUNCIONAL|Deseable|Disponibilidad 24/7 de la exportación de la base de datos|VER Requerimiento 22|
|12|RQ FUNCIONAL|Importante|Como recepcionista quiero fiar a un cliente regular|Se le generara una deuda al cliente, se pondrá una fecha limite para que pague|
|13|RQ NO FUNCIONAL|Importante|Permitir la visualización de turnos ocupados y disponibles rápidamente,|VER Requerimiento 3|
|14|RQ FUNCIONAL|Importante|Como administrador quiero asignar una regla de puntos para los servicios|Cada servicio dara una cierta cantidad de puntos por default|
|15|RQ FUNCIONAL|Esencial|Como Usuario quiero seleccionar el idioma del sistema|Al ingresar cuenta y contraseña, si es la primera vez que ingresa le solicitara elegir idioma|
|16|RQ NO FUNCIONAL|Importante|Disponiblidad del sistema en distintos lenguajes (ingles y español)|VER Requerimiento 21|
|17|RQ NO FUNCIONAL|Importante|Generar comprobante de cualquier operacion realizada|Al generar un comprobante, se le dara la opcion al usuario de imprimir un pdf|
|18|RQ FUNCIONAL|Importante|Como Recepcionista quiero consultar cualquier operacion realizada|Cualquier usuario podra visualizar el calendario|
|19|RQ FUNCIONAL|Importante|Como Supervisor quiero visualizar y poder filtrar los reportes de Caja de mi sucursal|El supervisor podra ver la caja y seleccionar generar reportes eligiendo un rango de fechas y sus filtros|
|20|RQ NO FUNCIONAL|Importante|Crear reportes graficos y estadisticos sobre la Caja del sistema|Los reportes seran intuitivos y seran faciles de leer|
|21|RQ FUNCIONAL|Importante|Como recepcionista quiero realizar el ingreso del pago de un servicio|Cuando una cita pase a completado se efecutara el ingreso del medio de pago a la caja|
|22|RQ FUNCIONAL|Importante|Como Usuario quiero visualizar cada promocion, su regla y los puntos asignados|Las promos se visualizaran en modulo aparte en el cual se podra consultar promos vigentes como historicas|
|23|RQ FUNCIONAL|Importante|Como Contador quiero visualizar las cajas de todas las sucursales|Tendra acceso a cada caja y a la generacion de reportes de cualquier tipo|
|24|RQ FUNCIONAL|Esencial|Como Recepcionista quiero realizar Alta de cliente|Campos: nombre, apellido Medio de contacto(mail o telefono), puntos, Dinero que adeuda, estado|
|25|RQ FUNCIONAL|Importante|Como Recepcionista quiero ver el estado del cliente|Estados: BLOQUEADO, VIP, REGULAR, MOROSO|
|26|RQ FUNCIONAL|Esencial|Como Recepcionista quiero modificar el estado del cliente|GENERAL: Se podran ver los clientes de todas las sucursales|
|27|RQ FUNCIONAL|Esencial|Como Supervisor quiero modificar al cliente|Se podrá modificar cualquier dato del cliente|
|28|RQ NO FUNCIONAL|Importante|Sumar puntos correspondientes al cliente que cumpla las condiciones para dar puntos|Al finalizar el mes se le acreditarán todos los puntos que generó|
|29|RQ FUNCIONAL|Esencial|Como administrador quiero realizar ABMC de Usuarios|Se podran crear, modificar estado(activo o inactivo) y modificar nombre y contraseña|
|30|RQ NO FUNCIONAL|Importante|Se debe enviar un comprobante al cliente al realizar dar de alta, modificar o cancelar un turno|Se le informara el estado actual de la cita|
|31|RQ FUNCIONAL|Esencial|Como administrador quiero realizar ABM de Sucursales|Se podra dar de alta y de estado (activo o inactivo) a cualquier sucursal|
|32|RQ FUNCIONAL|Esencial|Como Usuario quiero consultar sucursales|Se podran ver las sucursales con direccion que forman parte de la empresa|
|33|RQ FUNCIONAL|Importante|Como Dueño quiero tener acceso al ranking de ventas por servicio|Se veran las sucursales que mas ventas por monto realizan|
|34|RQ FUNCIONAL|Importante|Como Dueño quiero tener acceso a todas las cajas de cada sucursal (reportes)|Podra ver cuanto dinero maneja cada sucursal, por dia inclusive|
|35|RQ FUNCIONAL|Importante|Como administrador quiero exportar la base de datos de la sucursal|Al exportar la base de datos, sera en un archivo sql|
|36|RQ FUNCIONAL|Importante|Como administrador quiero importar una base de datos a la sucursal|Solo recibira un archivo sql como entrada|
|37|RQ FUNCIONAL|Deseable|Como Recepcionista quiero abrir y cerrar Caja|No se puede cerrar una caja si no hay una abierta, no se puede abrir una caja si no se cierra la abierta.|
|38|RQ FUNCIONAL|Importante|Como Recepcionista quiero realizar ABMC de Producto|Campos id, nombre del producto, descripcion y precio(descripción no es obligatorio)|
|39|RQ FUNCIONAL|Importante|Como recepcionista quiero realizar el ingreso del pago de un Producto|Cuando un producto sea seleccionado te mostrara la opcion de vender|
|40|RQ FUNCIONAL|Importante|Como Recepcionista quiero realizar un egreso de la Caja|Campos, id, monto, observación|
|41|RQ FUNCIONAL|Importante|Como Supervisor quiero crear una promoción|Las promociones son: porcentaje y Puntos|
|42|RQ FUNCIONAL|Importante|Como Supervisor quiero crear una regla y asociar una promo|La regla es la fecha inicio, fecha fin, servicio, puntos que da o porcentaje de descuento, o precio|
|43|RQ FUNCIONAL|Importante|Como Supervisor quiero modificar una promoción y su regla|Se podra modificar cualquier promocion juntos con sus campos asociados, para saber si la promocion esta vacia se debe contemplar que la fecha inicio sea mayor o igual a la actual y la fecha fin menor o igual a la actual|




|Plan de Versiones|Requerimientos|
| :-: | :- |
|2|Sección Calendario-Generar Citas|1-2-3-4-13|
||Sección ABMC Profesionales-Servicios|5-6-7-8-9-10|
||Sección ABM Clientes|24-25-26-27|
|3|Egresos-Ingresos de pagos|12-14-21-28-37-40|
||Sección ABMC Promociones-Reglas|22-41-42-43|
||Generar Sucursales|31-32|
|4|Sección ABMC de Usuarios-Permisos|29|
||Generar reportes|19-20-23-33-34|
||Sección de Comprobantes realizados|1-17-18-30|
|5|Disponibilidad del sistema en Inglés-Español|15-16|
||Pantalla de importar/exportar base|11-35-36|
||Manejo de ventas de Productos|38-39|


DICCIONARIO WBS


|id|nombre|Descripción|Tarea|
| :-: | :-: | :-: | :-: |
|"1"|Sistema para empresa de Peluquerías|Proyecto de gestion de Peluquerias.|En resumen, manejo de calendarios, sucursales, citas, creación de reportes|
|"1.1"|Administración de clientes|Producto encargado del modelado del cliente y de todas las operaciones que lo involucran.|Integracion de las subcategorías que lo componen y pruebas integrales.|
|"1.1.1"|Alta cliente|<p>Producto encargado de las</p><p>operaciones de alta de un cliente.</p>|Análisis, Diseño, Desarrollo, Testeo.|
|"1.1.2"|Baja cliente|<p>Producto encargado de las</p><p>operaciones de baja de un cliente.</p>||
|"1.1.3"|Modificación de cliente|Producto encargado de las operaciones de modificación de un cliente.||
|"1.1.4"|Consultar cliente|<p>Producto encargado de las</p><p>operaciones de consulta de un cliente.</p>||
|"1.2"|Administración de usuarios|Producto encargado del modelado del usuario y de todas las operaciones que lo involucran.|Integracion de las subcategorías que lo componen y pruebas integrales.|
|"1.2.1"|Alta usuario|<p>Producto encargado de las</p><p>operaciones de alta de un usuario.</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.2.2"|Baja usuario|<p>Producto encargado de las</p><p>operaciones de baja de un usuario.</p>||
|"1.2.3"|Modificacion usuario|<p>Producto encargado de las</p><p>operaciones de modificacion de un usuario.</p>||
|"1.2.4"|Consultar usuario|<p>Producto encargado de las</p><p>operaciones de consulta de un usuario.</p>||
|"1.2.5"|Asignar permiso al usuario que corresponde|Producto encargado de darle sentido el tener diferentes usuarios||
|"1.3"|Administracion de Sucursales|Producto encargado del modelado de las distintas sucursales y de individualizacion de sus empleados y cajas|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.3.1"|Alta Sucursal|<p>Producto encargado de las</p><p>operaciones de alta de una sucursal</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.3.2"|Baja Sucursal|<p>Producto encargado de las</p><p>operaciones de baja de una sucursal</p>||
|"1.3.3"|Modificacion de Sucursal|<p>Producto encargado de las</p><p>operaciones de modificacion de una sucursal</p>||
|"1.3.4"|Consultar Sucursal|<p>Producto encargado de las</p><p>operaciones de consulta de una sucursal</p>||
|"1.4"|Administracion de Citas|Producto encargado del modelado de las Citas, conectando la cita con cliente - servicio - profesional|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.4.1"|Alta Cita|<p>Producto encargado de las</p><p>operaciones de alta de Citas</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.4.2"|Baja Cita|<p>Producto encargado de las</p><p>operaciones de baja de Citas</p>||
|"1.4.3"|Modificacion de Cita|<p>Producto encargado de las</p><p>operaciones de modificacion de Citas</p>||
|"1.4.4"|Consultar Cita|<p>Producto encargado de las</p><p>operaciones de consulta de Citas</p>||
|"1.4.5"|Generar Comprobante|Producto encargado de generar comprobantes sobre la cita y el pago.||
|"1.4.6"|Manual de Citas|Producto encargado de mostrar el manual de Citas||
|"1.5"|Administracion de Puntos|Producto encargado del modelado del manejo de los puntos y su utilizacion como manera de pago|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.5.1"|Asignar Puntos|<p>Producto encargado de las</p><p>operaciones de asignacion de puntos</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.5.2"|Quitar Puntos|<p>Producto encargado de las</p><p>operaciones de quita de puntos</p>||
|"1.5.3"|Consultar Puntos|<p>Producto encargado de las</p><p>operaciones de consulta de puntos</p>||
|"1.6"|Administracion de Profesionales|Producto encargado del modelado de los profesionales que trabajan en cada sucursal y su calendario de trabajo|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.6.1"|Alta Profesionales|<p>Producto encargado de las</p><p>operaciones de alta de Profesionales</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.6.2"|Baja Profesionales|<p>Producto encargado de las</p><p>operaciones de baja de Profesionales</p>||
|"1.6.3"|Modificacion de Profesionales|<p>Producto encargado de las</p><p>operaciones de modificacion de Profesionales</p>||
|"1.6.4"|Consultar Profesionales|<p>Producto encargado de las</p><p>operaciones de consulta de Profesionales</p>||
|"1.7"|Administracion de Servicios|Producto encargado del modelado de los servicios que realiza cada profesional|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.7.1"|Alta Servicio|<p>Producto encargado de las</p><p>operaciones de alta de Servicios</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.7.2"|Baja Servicio|<p>Producto encargado de las</p><p>operaciones de baja de Servicio</p>||
|"1.7.3"|Modificacion de Servicio|<p>Producto encargado de las</p><p>operaciones de modificacion de Servicios</p>||
|"1.7.4"|Consultar Servicio|<p>Producto encargado de las</p><p>operaciones de consulta de Servicios</p>||
|"1.8"|Administracion de Promociones|Producto encargado del modelado de las promociones que se activan individualmente en cada sucursal|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.8.1"|Alta Promocion|<p>Producto encargado de las</p><p>operaciones de alta de Promociones</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.8.2"|Baja Promocion|<p>Producto encargado de las</p><p>operaciones de baja de Promociones</p>||
|"1.8.3"|Modificacion de Promocion|<p>Producto encargado de las</p><p>operaciones de modificacion de Promociones</p>||
|"1.8.4"|Consultar Promocion|<p>Producto encargado de las</p><p>operaciones de consulta de Promociones</p>||
|"1.9"|Administracion de Reglas|Producto encargado del modelado de las Reglas y la asociacion a una promocion|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.9.1"|Alta Regla|<p>Producto encargado de las</p><p>operaciones de alta de Regla</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.9.2"|Baja Regla|<p>Producto encargado de las</p><p>operaciones de baja de Regla</p>||
|"1.9.3"|Modificacion de Regla|<p>Producto encargado de las</p><p>operaciones de modificacion de Regla</p>||
|"1.9.4"|Consultar Regla|<p>Producto encargado de las</p><p>operaciones de consulta de Regla</p>||
|"1.10"|Reportes|Producto encargado de generar distintos tipo de informes siguiendo distintas metricas o fines.|Dar visibilidad a cierta informacion que es util para la administracion o para el cliente.|
|"1.10.1"|Por local|Producto encargado de generar un reporte de las citas cerradas por local(se puede elegir un rango de fechas)|Analisis, Diseño, Desarrollo, Testeo.|
|"1.10.2"|General|Producto encargado de generar un reporte de las citas cerradas en todas las sucursales(se puede elegir un rango de fechas)||
|"1.10.3"|Por Cliente|Producto encargado de generar un reporte de las citas de un cliente(se puede elegir un rango de fechas)||
|"1.10.4"|Por Servicio|Producto encargado de generar un reporte de las citas por servicio(se puede elegir un rango de fechas)||
|"1.10.5"|Por Empleado|Producto encargado de generar un reporte de las citas por empleado(se puede elegir un rango de fechas)||
|"1.10.6"|Ranking de Ventas|Producto encargado de generar un reporte de los ranking de Ventas en un rango de fechas||
|"1.11"|Base de datos|Producto encargado de generar persistencia y disponibilidad en informacion valiosa para el proyecto.|Integracion de subcategorias que lo componen, teniendo un proceso de analisis,diseño, desarrollo, testing e Implementacion.|
|"1.11.1"|Analisis de Modelo de datos|Producto que analisa la mejor manera de crear una base de datos para moldear la porcion de realidad que se desea.|Modelo DER, analisis y diseño.|
|"1.11.2"|Back up de datos|Producto que se encarga de realizar una copia de seguridad de la base de datos.|Disponibilidad de extraer datos persistidos a un archivo portable por el usuario. No solo exportando los datos, sino tambien las tablas.|
|"1.11.3"|Importe de base|Producto encargado de recibir un archivo de seguridad de la base y crear una copia identica en la BDD del sistema.|Aceptacion de un archivo de seguridad que comparta los mismos protocolos para poder importar datos persistidos por un usuario. Importando no solo los datos, sino tambien las tablas.|
|"1.11.4"|Coneccion a bdd|Producto encargado de conectarse a una bdd online|Analisis de la tecnologia que se va utilizando, teniendo en cuenta la facilidad con respecto a todas las tecnologias que utilizamos|
|"1.12"|Administracion de Cajas|Producto encargado del modelado de las Cajas y su respectiva visualizacion|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.12.1"|Abrir Caja|<p>Producto encargado de las</p><p>operaciones de alta de Cajas</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.12.2"|Cerrar Caja|<p>Producto encargado de las</p><p>operaciones de baja de Cajas</p>||
|"1.12.3"|Consultar Caja|<p>Producto encargado de las</p><p>operaciones de consulta de Cajas</p>||
|"1.12.4"|Realizar Egreso|<p>Producto encargado de las</p><p>operaciones de egresos de Caja</p>||
|"1.13"|Administracion de Pagos|Producto encargado de modelar todo lo relacionado a formas de pago|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.13.1"|Generar Ticket|Producto encargado de generar comprobantes sobre la cita y el pago.|Analisis, Diseño, Desarrollo, Testeo.|
|"1.13.2"|Fiar servicio|Producto encargado de generar una deuda al cliente con la empresa||
|"1.13.3"|Pagar con Puntos|Producto encargado de modelar el medio de pago por puntos||
|"1.13.4"|Pagar en dinero|Producto encargado de modelar el medio de pago con dinero||
|"1.13.5"|Seleccionar moneda - Lenguaje|Producto encargado de seleccionar la moneda con la que se va a cobrar el sistema (tambien el lenguaje)||
|"1.14"|Administracion de Proyecto|Producto encargado de llevar un analisis y registro de todo lo que rodea al analisis, diseño y testing.|Integracion de subcategorias que lo componen, generando una unidad completa de analisis.|
|"1.14.1"|Gestion de riesgos|Producto encargado de analizar los riesgos del proyecto.|Preveer problemas que pueden ocurrir, etapas mas riesgosas, ver de que manera actuar y solucionar.|
|"1.14.2"|Gestion de cambios|Producto encargado de analizar los cambios del proyecto.|Preveer cambios que puedan llegar a ocurrir, como actuar en esos casos para que no sean criticos.|
|"1.14.3"|Gestion de comunicaciones|Producto encargado de analizar la comunicacion del proyecto.|Preveer medios y formas para comunicarse con los Stakeholders.|
|"1.14.4"|Gestion de versiones|Producto encargado del analisis del manejo de las versiones del sistema.|Analisis sobre como tratar las distintas versiones de produccion, beta, alfa,etc.y donde almacenarlas.|
|"1.14.5"|Seleccion de Tecnologia|Producto encargado del analisis de tecnologias que se van a utilizar a lo largo del proyecto|Analisis sobre que tecnologias van acordes al proyecto, por la experiencia del equipo y facilidad para trabajar|
|1.15|Integracion|Producto encargado de testear y validad la calidad del producto final|Integracion de subcategorias que lo componen, generando una unidad completa de analisis.|
|"1.15.1"|Pruebas de Integracion|Producto encargado de ver que la integracion de modulos se realice|Analisis, Diseño, Desarrollo, Testeo.|
|"1.15.2"|Pruebas de Aceptacion del cliente|Producto encargado de validar la aceptacion del sistema||
|"1.15.3"|Pruebas de Validacion|Producto encargado de testar el codigo y que realice lo que debe realizar||
|"1.15.4"|Instalador|Producto que tiene como finalidad el instalador de la aplicación|Instalar el programa y sus dependencias|
|1.16|GUI's|Producto encargado de crear las interfaces graficas de la aplicacion|Integracion de subcategorias que lo componen, generando una unidad completa de todo lo relacionado a interfacez graficas|
|"1.16.1"|Analisis de interfaz|Producto encargado de buscar que el sistema sea lo mas facil posible|Analisis, Diseño, Desarrollo, Testeo.|
|"1.16.2"|Diseño|Producto encargado de pensar la parte visual de la interfaz||
|1.17|Tareas Automaticas|Producto encargado de realizar tareas sin que nadie las ejecute|Integracion de tareas automaticas que se activaran al cumplirse una determina condicion particular, disparando una reaccion automatica|
|"1.17.1"|Enviar un comprobante al mail del cliente|Producto encargado de enviar comprobante al cliente.|Analisis, Diseño, Desarrollo, Testeo.|
|"1.17.2"|Cambiar a estado moroso al cliente|Producto encargado de cambiar el estado a moroso, si no efectua el pago de un servicio que se le fio.||
|"1.17.3"|Enviar un mail 48hs de la cita como recordatorio|Producto encargado de recordarle al cliente la cita que programo||
|1.18|Administracion de Productos|Producto encargado del modelado de las Productos y su respectiva visualizacion|Integracion de las subcategorias que lo componen y pruebas integrales.|
|"1.18.1"|Alta de Producto|<p>Producto encargado de las</p><p>operaciones de alta de Productos</p>|Analisis, Diseño, Desarrollo, Testeo.|
|"1.18.2"|Baja de Producto|<p>Producto encargado de las</p><p>operaciones de baja de Productos</p>||
|"1.18.3"|Modificación de Producto|<p>Producto encargado de las</p><p>operaciones de modificación de Productos</p>||
|"1.18.4"|Consultar Producto|<p>Producto encargado de las</p><p>operaciones de consulta de Productos</p>||






TRAZABILIDAD


|id Req|Tipo Req|Requerimientos|Id WBS|Nombre padre-hijo WBS|Esfuerzo Requerido|
| :-: | :-: | :-: | :-: | :-: | :-: |
|1|Esencial|Como recepcionista quiero realizar Alta de citas en un calendario|"1.3.1"|Adm de Sucursales-Alta Sucursal|5|
||||"1.4.1"|Adm.de Citas-Alta citas||
||||"1.6.1"|Adm de Prof-Alta Profesional||
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
||||"1.17.3"|Tareas Automaticas- Enviar un mail 48hs de la cita como recordatorio||
||||"1.4.5"|Adm de Citas -Generar Comprobante||
||||"1.17.1"|Tareas Automaticas -Enviar un comprobante al mail del cliente||
||||"1.4.6"|Adm de Citas-Manual de Citas||
||||"1.11.1"|Base de datos-Analisis de Modelo de datos||
|2|Esencial|Como recepcionista quiero realizar Modificar el estado de mis citas en el calendario|"1.3.1"|Adm de Sucursales-Alta Sucursal|1|
||||"1.4.3"|Adm.de Citas-Modificacion citas||
||||"1.4.2"|Adm.de Citas-Baja cita||
||||"1.6.3"|Adm de Prof-Modif Profesional||
||||"1.7.3"|Adm de Servicios-Modif Servicios||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|3|Esencial|Como Recepcionista quiero consultar el calendario de citas|"1.4.4"|Adm.de Citas-Consultar citas|1|
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|4|Esencial|Como Recepcionista quiero visualizar la descripcion de una cita|"1.4.4"|Adm.de Citas-Consultar citas|1|
||||"1.6.4"|Adm de Prof-Consultar Profesional||
||||"1.1.4"|Adm de Cliente-Consultar Cliente||
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
|5|Esencial|Como Supervisor quiero realizar Alta de Profesional|"1.6.1"|Adm de Prof-Alta Profesional|2|
||||"1.17.1"|GUI's - Analisis||
||||"1.17.2"|GUI'S - Diseño||
|6|Esencial|Como Supervisor quiero ver el estado del Profesional|"1.6.4"|Adm de Prof-Consultar Profesional|1|
||||"1.17.1"|GUI's - Analisis||
||||"1.17.2"|GUI'S - Diseño||
|7|Esencial|Como Supervisor quiero modificar el estado del Profesional|"1.6.4"|Adm de Prof-Consultar Profesional|1|
||||"1.6.2"|Adm de Prof-Baja Profesional||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|8|Esencial|Como Supervisor quiero crear un servicio|"1.7.1"|Adm de Servicios-Alta Servicios|2|
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|9|Esencial|Como Supervisor quiero modificar un servicio|"1.7.1"|Adm de Servicios-Alta Servicios|2|
||||"1.7.3"|Adm de Servicios-Modif Servicios||
||||"1.7.2"|Adm de Servicios - Baja Servicio||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|10|Esencial|Como Recepcionista quiero visualizar los servicios|"1.7.1"|Adm de Servicios-Alta Servicios|1|
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|11|Deseable|Disponibilidad 24/7 de la exportacion de la base de datos|"1.11.4"|BDD-Coneccion a bdd|1|
|12|Importante|Como recepcionista quiero fiar a un cliente regular|"1.4.1"|Adm.de Citas-Alta citas|1|
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.13.2"|Adm de pagos- fiar Cliente||
|13|Deseable|Permitir la visualizacion de turnos ocupados y disponibles rápidamente|||1|
|14|Importante|Como Supervisor quiero asignar una regla de puntos para los servicios|"1.9.1"|Adm de Reglas-Alta regla|1|
||||"1.7.1"|Adm de Servicios-Alta Servicios||
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
||||"1.7.3"|Adm de Servicios-Modif Servicios||
|15|Esencial|Como Usuario quiero seleccionar el idioma del sistema|"1.13.5"|Adm de pagos-Seleccionar moneda- Idioma|3|
||||"1.2.1"|Adm.de Usuario-Alta usuario||
||||"1.16.2"|GUI's - Diseño||
|16|Importante|Disponiblidad del sistema en distintos lenguajes (ingles y español)|||3|
|17|Importante|Generar comprobante de cualquier operacion realizada|"1.4.5"|Adm de Citas -Generar Comprobante|3|
||||"1.17.1"|Tareas automaticas-Enviar un comprobante al mail del cliente||
||||"1.17.2"|Tareas Automaticas-Cambiar a estado moroso al cliente||
|18|Importante|Como Recepcionista quiero consultar cualquier operacion realizada|"1.4.4"|Adm.de Citas-Consultar citas|3|
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
||||"1.6.4"|Adm de Prof-Consultar Profesional||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|19|Importante|Como Supervisor quiero visualizar y poder filtrar los reportes de Caja de mi sucursal|"1.12.3"|Adm de caja-Consultar Caja|5|
||||"1.10.1"|Reportes - Por local||
||||"1.10.2"|Reportes - Por cliente||
||||"1.10.3"|Reportes - General||
||||"1.10.4"|Reportes - Por servicio||
||||"1.10.5"|Reportes - Por empleado||
||||"1.3.1"|Adm de Sucursales-Alta Sucursal||
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|20|Deseable|Crear reportes graficos y estadisticos sobre la Caja del sistema|||4|
|21|Importante|Como recepcionista quiero realizar el ingreso del pago|"1.13.3"|Admin de Pagos- Pagar con Puntos|4|
||||"1.13.1"|Admin de Pagos- Generar Ticket||
||||"1.5.2"|Administracion de Puntos - Quitar Puntos||
||||"1.13.1"|Admin de Pagos - Imprimir Ticket||
||||"1.13.4"|Admin de Pagos- Pagar con Dinero||
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|22|Importante|Como Usuario quiero visualizar cada promocion, su regla y los puntos asignados|"1.8.1"|Adm. de Promociones-Alta Promociones|1|
||||"1.9.1"|Adm de Reglas-Alta regla||
||||"1.8.4"|Adm. de Promociones-Consultar Promociones||
||||"1.9.4"|Adm de Reglas-Consultar regla||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|23|Importante|Como Contador quiero visualizar las cajas de todas las sucursales|"1.3.4"|Adm de Sucursales-Consultar Sucursal|4|
||||"1.12.3"|Adm de caja-Consultar Caja||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|24|Esencial|Como Recepcionista quiero realizar Alta de cliente|"1.1.1"|Adm de Cliente-Alta Cliente|2|
||||"1.17.1"|GUI'S - Analisis||
||||"1.17.2"|GUI'S - Diseño||
|25|Esencial|Como Recepcionista quiero ver el estado del cliente|"1.1.4"|Adm de Cliente-Consultar Cliente|1|
||||"1.5.3"|Administracion de Puntos - Consultar Puntos||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|26|Esencial|Como Recepcionista quiero modificar el estado del cliente|"1.1.3"|Adm de Cliente-Modificar Cliente|1|
||||"1.1.4"|Adm de Cliente-Consultar Cliente||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|27|Esencial|Como Supervisor quiero modificar al cliente|"1.1.3"|Adm de Cliente-Modificar Cliente|2|
||||"1.1.4"|Adm de Cliente-Consultar Cliente||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
||||"1.1.2"|Adm de Cliente-Baja de Cliente||
|28|Importante|Sumar puntos correspondientes al cliente que cumpla las condiciones para dar puntos|"1.5.1"|Administracion de Puntos - Asignar Puntos|2|
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.5.3"|Administracion de Puntos - Consultar Puntos||
|29|Esencial|Como administrador quiero realizar ABMC de Usuarios|"1.2.1"|Adm.de Usuario-Alta usuario|5|
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
||||"1.2.2"|Adm.de Usuario-Baja usuario||
||||"1.2.3"|Adm.de Usuario-Modificar usuario||
||||"1.2.4"|Adm.de Usuario-Consultar usuario||
||||"1.2.5"|Adm de usuario-Asignar permisos||
|30|Importante|Se debe enviar un comprobante al cliente al realizar dar de alta, modificar o cancelar un turno|"1.17.1"|TareasAutomaticas- Enviar un comprobante al mail del cliente|3|
||||"1.4.2"|Adm.de Citas-Baja cita||
||||"1.4.3"|Adm.de Citas-Modif cita||
|31|Esencial|Como administrador quiero realizar ABM de Sucursales|"1.4.1"|Adm.de Citas-Alta citas|5|
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.3.1"|Adm de Sucursales-Alta Sucursal||
||||"1.16.1"|GUI'S - Analisis||
||||"1.3.3"|Adm de Sucursales-Modificar Sucursal||
||||"1.3.3"|Adm de Sucursales-Consultar Sucursal||
||||"1.16.2"|GUI'S - Diseño||
||||"1.3.2"|Adm de Sucursales-Baja Sucursal||
|32|Esencial|Como Usuario quiero consultar sucursales|"1.3.1"|Adm de Sucursales-Alta Sucursal|2|
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
|33|Importante|Como Dueño quiero tener acceso al ranking de ventas por servicio|"1.3.3"|Adm de Sucursales-Consultar Sucursal|3|
||||"1.12.3"|Adm de caja-Consultar Caja||
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
|34|Importante|Como Dueño quiero tener acceso a todas las cajas de cada sucursal (reportes)|"1.3.1"|Adm de Sucursales-Alta Sucursal|1|
||||"1.10.6"|Reportes- Ranking de Ventas||
||||"1.3.1"|Adm de Sucursales-Alta Sucursal||
|35|Importante|Como administrador quiero exportar la base de datos de la sucursal|"1.12.3"|Adm de caja-Consultar Caja|4|
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
||||"1.11.1"|Base de Datos- Analisis de Modelo de datos||
||||"1.11.4"|BDD-Coneccion a bdd||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|36|Importante|Como administrador quiero importar una base de datos a la sucursal|"1.11.2"|Base de datos- Back up de datos|4|
||||"1.11.4"|Base de Datos- Seleccion de la tecnologia||
||||"1.11.1"|Base de Datos- Analisis de Modelo de datos||
||||"1.11.4"|BDD-Coneccion a bdd||
||||"1.11.3"|Base de datos- Importe de Base||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|37|Deseable|Como Recepcionista quiero abrir y cerrar Caja|"1.12.1"|Administracion de Cajas-Abrir Caja|3|
||||"1.12.2"|Administracion de Cajas-Cerrar Caja||
|38|Importante|Como Recepcionista quiero realizar ABMC de Producto|"1.18.1"|Admin de Productos- Alta de Producto|4|
||||"1.18.2"|Admin de Productos- Baja de Producto||
||||"1.18.3"|Admin de Productos- Modificacion de Producto||
||||"1.18.4"|Admin de Productos- Consulta de Producto||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|39|Importante|Como recepcionista quiero realizar el ingreso del pago de un Producto|"1.13.3"|Admin de Pagos- Pagar con Puntos|4|
||||"1.5.2"|Administracion de Puntos - Quitar Puntos||
||||"1.13.1"|Admin de Pagos - Imprimir Ticket||
||||"1.13.4"|Admin de Pagos- Pagar con Dinero||
||||"1.18.1"|Admin de Productos- Alta de Producto||
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|40|Importante|Como Recepcionista quiero realizar un egreso de la Caja|"1.12.4"|Administracion de Cajas- Realizar Egreso|3|
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.12.3"|Adm de caja-Consultar Caja||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|41|Esencial|Como Supervisor quiero crear una promocion|"1.8.1"|Adm. de Promociones-Alta Promociones|2|
||||"1.2.1"|Adm.de Usuario-Alta usuario||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|42|Importante|Como Supervisor quiero crear una regla y asociarle una promo|"1.9.1"|Adm de Reglas-Alta regla|2|
||||"1.8.4"|Adm. de Promociones-Consultar Promociones||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|43|Importante|Como Supervisor quiero modificar una Promoción y su regla|"1.9.1"|Adm de Reglas-Alta regla|2|
||||"1.8.2"|Adm de Promociones - Modificar Promocion||
||||"1.8.3"|Adm de Promociones - Baja Promoción||
||||"1.9.3"|Adm de Reglas-Modificar Regla||
||||"1.9.2"|Adm de Reglas- Baja de Regla||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||


|id Req|Tipo Req|Requerimientos|Id WBS|Nombre padre-hijo WBS|Esfuerzo Requerido|
| :-: | :-: | :-: | :-: | :-: | :-: |
|1|Esencial|Como recepcionista quiero realizar Alta de citas en un calendario|"1.3.1"|Adm de Sucursales-Alta Sucursal|5|
||||"1.4.1"|Adm.de Citas-Alta citas||
||||"1.6.1"|Adm de Prof-Alta Profesional||
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
||||"1.17.3"|Tareas Automaticas- Enviar un mail 48hs de la cita como recordatorio||
||||"1.4.5"|Adm de Citas -Generar Comprobante||
||||"1.17.1"|Tareas Automáticas -Enviar un comprobante al mail del cliente||
||||"1.4.6"|Adm de Citas-Manual de Citas||
||||"1.11.1"|Base de datos-Análisis de Modelo de datos||
|2|Esencial|Como recepcionista quiero realizar Modificar el estado de mis citas en el calendario|"1.3.1"|Adm de Sucursales-Alta Sucursal|1|
||||"1.4.3"|Adm.de Citas-Modificacion citas||
||||"1.4.2"|Adm.de Citas-Baja cita||
||||"1.6.3"|Adm de Prof-Modif Profesional||
||||"1.7.3"|Adm de Servicios-Modif Servicios||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|3|Esencial|Como Recepcionista quiero consultar el calendario de citas|"1.4.4"|Adm.de Citas-Consultar citas|1|
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|4|Esencial|Como Recepcionista quiero visualizar la descripcion de una cita|"1.4.4"|Adm.de Citas-Consultar citas|1|
||||"1.6.4"|Adm de Prof-Consultar Profesional||
||||"1.1.4"|Adm de Cliente-Consultar Cliente||
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
|5|Esencial|Como Supervisor quiero realizar Alta de Profesional|"1.6.1"|Adm de Prof-Alta Profesional|2|
||||"1.17.1"|GUI's - Analisis||
||||"1.17.2"|GUI'S - Diseño||
|6|Esencial|Como Supervisor quiero ver el estado del Profesional|"1.6.4"|Adm de Prof-Consultar Profesional|1|
||||"1.17.1"|GUI's - Analisis||
||||"1.17.2"|GUI'S - Diseño||
|7|Esencial|Como Supervisor quiero modificar el estado del Profesional|"1.6.4"|Adm de Prof-Consultar Profesional|1|
||||"1.6.2"|Adm de Prof-Baja Profesional||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|8|Esencial|Como Supervisor quiero crear un servicio|"1.7.1"|Adm de Servicios-Alta Servicios|2|
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|9|Esencial|Como Supervisor quiero modificar un servicio|"1.7.1"|Adm de Servicios-Alta Servicios|2|
||||"1.7.3"|Adm de Servicios-Modif Servicios||
||||"1.7.2"|Adm de Servicios - Baja Servicio||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|10|Esencial|Como Recepcionista quiero visualizar los servicios|"1.7.1"|Adm de Servicios-Alta Servicios|1|
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|11|Deseable|Disponibilidad 24/7 de la exportación de la base de datos|"1.11.4"|BDD-Coneccion a bdd|1|
|12|Importante|Como recepcionista quiero fiar a un cliente regular|"1.4.1"|Adm.de Citas-Alta citas|1|
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.13.2"|Adm de pagos- fiar Cliente||
|13|Deseable|Permitir la visualización de turnos ocupados y disponibles rápidamente|||1|
|14|Importante|Como Supervisor quiero asignar una regla de puntos para los servicios|"1.9.1"|Adm de Reglas-Alta regla|1|
||||"1.7.1"|Adm de Servicios-Alta Servicios||
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
||||"1.7.3"|Adm de Servicios-Modif Servicios||
|15|Esencial|Como Usuario quiero seleccionar el idioma del sistema|"1.13.5"|Adm de pagos-Seleccionar moneda- Idioma|3|
||||"1.2.1"|Adm.de Usuario-Alta usuario||
||||"1.16.2"|GUI's - Diseño||
|16|Importante|Disponibilidad del sistema en distintos lenguajes (inglés y español)|||3|
|17|Importante|Generar comprobante de cualquier operación realizada|"1.4.5"|Adm de Citas -Generar Comprobante|3|
||||"1.17.1"|Tareas automáticas-Enviar un comprobante al mail del cliente||
||||"1.17.2"|Tareas Automáticas-Cambiar a estado moroso al cliente||
|18|Importante|Como Recepcionista quiero consultar cualquier operación realizada|"1.4.4"|Adm.de Citas-Consultar citas|3|
||||"1.7.4"|Adm de Servicios-Consultar Servicios||
||||"1.6.4"|Adm de Prof-Consultar Profesional||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|19|Importante|Como Supervisor quiero visualizar y poder filtrar los reportes de Caja de mi sucursal|"1.12.3"|Adm de caja-Consultar Caja|5|
||||"1.10.1"|Reportes - Por local||
||||"1.10.2"|Reportes - Por cliente||
||||"1.10.3"|Reportes - General||
||||"1.10.4"|Reportes - Por servicio||
||||"1.10.5"|Reportes - Por empleado||
||||"1.3.1"|Adm de Sucursales-Alta Sucursal||
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|20|Deseable|Crear reportes gráficos y estadísticos sobre la Caja del sistema|||4|
|21|Importante|Como recepcionista quiero realizar el ingreso del pago|"1.13.3"|Admin de Pagos- Pagar con Puntos|4|
||||"1.13.1"|Admin de Pagos- Generar Ticket||
||||"1.5.2"|Administración de Puntos - Quitar Puntos||
||||"1.13.1"|Admin de Pagos - Imprimir Ticket||
||||"1.13.4"|Admin de Pagos- Pagar con Dinero||
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|22|Importante|Como Usuario quiero visualizar cada promoción, su regla y los puntos asignados|"1.8.1"|Adm. de Promociones-Alta Promociones|1|
||||"1.9.1"|Adm de Reglas-Alta regla||
||||"1.8.4"|Adm. de Promociones-Consultar Promociones||
||||"1.9.4"|Adm de Reglas-Consultar regla||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|23|Importante|Como Contador quiero visualizar las cajas de todas las sucursales|"1.3.4"|Adm de Sucursales-Consultar Sucursal|4|
||||"1.12.3"|Adm de caja-Consultar Caja||
||||"1.16.1"|GUI's - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|24|Esencial|Como Recepcionista quiero realizar Alta de cliente|"1.1.1"|Adm de Cliente-Alta Cliente|2|
||||"1.17.1"|GUI'S - Análisis||
||||"1.17.2"|GUI'S - Diseño||
|25|Esencial|Como Recepcionista quiero ver el estado del cliente|"1.1.4"|Adm de Cliente-Consultar Cliente|1|
||||"1.5.3"|Administración de Puntos - Consultar Puntos||
||||"1.16.1"|GUI'S - Análisis||
||||"1.16.2"|GUI'S - Diseño||
|26|Esencial|Como Recepcionista quiero modificar el estado del cliente|"1.1.3"|Adm de Cliente-Modificar Cliente|1|
||||"1.1.4"|Adm de Cliente-Consultar Cliente||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|27|Esencial|Como Supervisor quiero modificar al cliente|"1.1.3"|Adm de Cliente-Modificar Cliente|2|
||||"1.1.4"|Adm de Cliente-Consultar Cliente||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
||||"1.1.2"|Adm de Cliente-Baja de Cliente||
|28|Importante|Sumar puntos correspondientes al cliente que cumpla las condiciones para dar puntos|"1.5.1"|Administracion de Puntos - Asignar Puntos|2|
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.5.3"|Administracion de Puntos - Consultar Puntos||
|29|Esencial|Como administrador quiero realizar ABMC de Usuarios|"1.2.1"|Adm.de Usuario-Alta usuario|5|
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
||||"1.2.2"|Adm.de Usuario-Baja usuario||
||||"1.2.3"|Adm.de Usuario-Modificar usuario||
||||"1.2.4"|Adm.de Usuario-Consultar usuario||
||||"1.2.5"|Adm de usuario-Asignar permisos||
|30|Importante|Se debe enviar un comprobante al cliente al realizar dar de alta, modificar o cancelar un turno|"1.17.1"|TareasAutomaticas- Enviar un comprobante al mail del cliente|3|
||||"1.4.2"|Adm.de Citas-Baja cita||
||||"1.4.3"|Adm.de Citas-Modif cita||
|31|Esencial|Como administrador quiero realizar ABM de Sucursales|"1.4.1"|Adm.de Citas-Alta citas|5|
||||"1.1.1"|Adm de Cliente-Alta Cliente||
||||"1.3.1"|Adm de Sucursales-Alta Sucursal||
||||"1.16.1"|GUI'S - Analisis||
||||"1.3.3"|Adm de Sucursales-Modificar Sucursal||
||||"1.3.3"|Adm de Sucursales-Consultar Sucursal||
||||"1.16.2"|GUI'S - Diseño||
||||"1.3.2"|Adm de Sucursales-Baja Sucursal||
|32|Esencial|Como Usuario quiero consultar sucursales|"1.3.1"|Adm de Sucursales-Alta Sucursal|2|
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
|33|Importante|Como Dueño quiero tener acceso al ranking de ventas por servicio|"1.3.3"|Adm de Sucursales-Consultar Sucursal|3|
||||"1.12.3"|Adm de caja-Consultar Caja||
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
|34|Importante|Como Dueño quiero tener acceso a todas las cajas de cada sucursal (reportes)|"1.3.1"|Adm de Sucursales-Alta Sucursal|1|
||||"1.10.6"|Reportes- Ranking de Ventas||
||||"1.3.1"|Adm de Sucursales-Alta Sucursal||
|35|Importante|Como administrador quiero exportar la base de datos de la sucursal|"1.12.3"|Adm de caja-Consultar Caja|4|
||||"1.3.4"|Adm de Sucursales-Consultar Sucursal||
||||"1.11.1"|Base de Datos- Analisis de Modelo de datos||
||||"1.11.4"|BDD-Coneccion a bdd||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|36|Importante|Como administrador quiero importar una base de datos a la sucursal|"1.11.2"|Base de datos- Back up de datos|4|
||||"1.11.4"|Base de Datos- Seleccion de la tecnologia||
||||"1.11.1"|Base de Datos- Analisis de Modelo de datos||
||||"1.11.4"|BDD-Coneccion a bdd||
||||"1.11.3"|Base de datos- Importe de Base||
||||"1.16.1"|GUI'S - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|37|Deseable|Como Recepcionista quiero abrir y cerrar Caja|"1.12.1"|Administracion de Cajas-Abrir Caja|3|
||||"1.12.2"|Administracion de Cajas-Cerrar Caja||
|38|Importante|Como Recepcionista quiero realizar ABMC de Producto|"1.18.1"|Admin de Productos- Alta de Producto|4|
||||"1.18.2"|Admin de Productos- Baja de Producto||
||||"1.18.3"|Admin de Productos- Modificacion de Producto||
||||"1.18.4"|Admin de Productos- Consulta de Producto||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|39|Importante|Como recepcionista quiero realizar el ingreso del pago de un Producto|"1.13.3"|Admin de Pagos- Pagar con Puntos|4|
||||"1.5.2"|Administracion de Puntos - Quitar Puntos||
||||"1.13.1"|Admin de Pagos - Imprimir Ticket||
||||"1.13.4"|Admin de Pagos- Pagar con Dinero||
||||"1.18.1"|Admin de Productos- Alta de Producto||
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|40|Importante|Como Recepcionista quiero realizar un egreso de la Caja|"1.12.4"|Administracion de Cajas- Realizar Egreso|3|
||||"1.12.1"|Adm de caja-Abrir Caja||
||||"1.12.3"|Adm de caja-Consultar Caja||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|41|Esencial|Como Supervisor quiero crear una promocion|"1.8.1"|Adm. de Promociones-Alta Promociones|2|
||||"1.2.1"|Adm.de Usuario-Alta usuario||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|42|Importante|Como Supervisor quiero crear una regla y asociarle una promo|"1.9.1"|Adm de Reglas-Alta regla|2|
||||"1.8.4"|Adm. de Promociones-Consultar Promociones||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||
|43|Importante|Como Supervisor quiero modificar una Promoción y su regla|"1.9.1"|Adm de Reglas-Alta regla|2|
||||"1.8.2"|Adm de Promociones - Modificar Promocion||
||||"1.8.3"|Adm de Promociones - Baja Promoción||
||||"1.9.3"|Adm de Reglas-Modificar Regla||
||||"1.9.2"|Adm de Reglas- Baja de Regla||
||||"1.16.1"|GUI's - Analisis||
||||"1.16.2"|GUI'S - Diseño||

CALENDARIO SEMANAL


|**Responsable**|**Semana 1**|**Horas**|**Semana 2**|**Horas**|**Semana 3**|**Horas**|**Semana 4**|**Horas**|
| :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
|Tomi|"1.6.1" Adm de Prof-Alta Profesional|4|"1.7.1" Adm de Servicios-Alta Servicios|4|<p>"1.12.1"</p><p>Administracion de Cajas</p><p>Abrir Caja</p>|4|"1.3.1" Adm de Sucursales-Alta Sucursal|5|
||"1.6.2" Adm de Prof-Baja Profesional|4|"1.7.2" Adm de Servicios - Baja Servicio|4|<p>"1.12.2"</p><p>Administracion de Cajas</p><p>Cerrar Caja</p>|4|"1.3.4" Adm de Sucursales-Consultar Sucursal|5|
||"1.6.3" Adm de Prof-Modif Profesional|4|"1.7.3" Adm de Servicios-Modif Servicios|4|<p>"1.12.3"</p><p>Adm de caja</p><p>Consultar Caja</p>|4|<p>1.13.2</p><p>Administracion de Sucursales</p><p>Baja Sucursal</p>|5|
||"1.6.4" Adm de Prof-Consultar Profesional|4|"1.7.4" Adm de Servicios-Consultar Servicios|4|<p>"1.16.1"</p><p>Analisis GUI</p>|3|<p>1.13.2</p><p>Administracion de Sucursales</p><p>Modificar Sucursal</p>|5|
||<p>"1.16.1"</p><p>Analisis GUI</p>|3|<p>"1.16.2"</p><p>Diseño GUI</p>|6|"1.16.2" GUI'S - Diseño|3|"1.16.2" GUI'S - Diseño|3|
||||<p>"1.15.4"</p><p>Instalador</p>|2|||<p>"1.15.4"</p><p>Instalador</p>|2|
||Horas por semana:|19|Horas por semana:|24|Horas por semana:|18|Horas por semana:|25|
||||||||||
|Nico|<p>"1.1.1"</p><p>Administracion de Clientes</p><p>Alta de Cliente</p>|4|<p>"1.1.4"</p><p>Administracion de Clientes</p><p>Consulta de Cliente</p>|4|"1.8.1" Adm. de Promociones-Alta Promociones|4|"1.9.1"	Adm de Reglas-Alta regla|4|
||<p>"1.1.2"</p><p>Administracion de Clientes</p><p>Baja de Cliente</p>|4|<p>"1.16.1"</p><p>GUI</p><p>Analisis</p>|3|"1.8.2" Adm de Promociones - Modificar Promocion|4|"1.9.2" Adm de Reglas- Baja de Regla|4|
||<p>"1.1.3"</p><p>Administracion de Clientes</p><p>Modificacion de Cliente</p>|4|<p>"1.16.2"</p><p>GUI</p><p>Diseño</p>|6|"1.8.3" Adm de Promociones - Baja Promoción|4|"1.9.3" Adm de Reglas-Modificar Regla|4|
||"1.11.1" Base de Datos- Analisis de Modelo de datos|5|||"1.8.4" Adm. de Promociones-Consultar Promociones|4|"1.9.4" Adm de Reglas-Consultar regla|4|
||||||||||
||Horas por semana:|17|Horas por semana:|13|Horas por semana:|16|Horas por semana:|16|
|Mati|<p>"1.4.1" Adm de Citas-</p><p>Alta de Citas</p>|5|<p>"1.4.4" Adm de Citas-</p><p>Consultar Citas</p>|5|<p>"1.5.1"</p><p>Asignar Puntos</p>|4|"1.13.1"	Admin de Pagos - Imprimir Ticket|5|
||<p>"1.4.3" Adm de Citas-</p><p>Modificacion de Citas</p>|5|<p>"1.4.5" Adm de Citas-</p><p>Generar Comprobante</p>|7|<p>"1.5.2"</p><p>Quitar Puntos</p>|4|"1.13.3" Admin de Pagos- Pagar con Puntos|3|
||<p>"1.4.2" Adm de Citas-</p><p>Baja de Citas</p>|5|<p>"1.16.2"</p><p>Diseño GUI</p>|6|<p>"1.5.3"</p><p>Consultar Puntos</p>|4|"1.5.2" Administracion de Puntos - Quitar Puntos|3|
||<p>"1.16.1"</p><p>Analisis GUI</p>|3|||"1.13.4" Admin de Pagos- Pagar con dinero|2|<p>"1.13.2" Admin de pagos</p><p>Fiar cliente</p>|4|
||||||<p>"1.16.1"</p><p>Analisis GUI</p>|3|<p>"1.16.2"</p><p>GUI</p><p>Diseño</p>|6|
||||||||||
||Horas por semana:|18|Horas por semana:|18|Horas por semana:|17|Horas por semana:|21|
||||||||||
|**Responsable**|**Semana 5**|**Horas**|**Semana 6**|**Horas**|**Semana 7**|**Horas**|**Semana 8**|**Horas**|
|Tomas|<p></p><p>"1.2.1" Adm.de Usuario-Alta usuario</p>|4|"1.2.5" Adm de usuario-Asignar permisos|8|"1.13.5" Adm de pagos-Seleccionar moneda- Idioma|5|"1.13.5" Adm de pagos-Seleccionar moneda- Idioma|5|
||"1.2.2" Adm.de Usuario-Baja usuario|4|"1.16.1" GUI'S - Analisis|3|||<p>"1.15.4"</p><p>Instalador</p>|2|
||"1.2.3" Adm.de Usuario-Modificar usuario|4|"1.16.2"	GUI'S - Diseño|6|||||
||"1.2.4" Adm.de Usuario-Consultar usuario|4|<p>"1.15.4"</p><p>Instalador</p>|2|||||
||Horas por semana:|16|Horas por semana:|19|Horas por semana:|5|Horas por semana:|7|
||||||||||
||||||||||
|Nico|<p>"1.10"</p><p>Reportes</p><p>Elaborar Reportes</p>|5|<p>"1.10.4"</p><p>Reportes</p><p>Filtrar reportes por Servicio</p>|5|<p>1.18.1</p><p>Administración de Productos</p><p>Alta de Producto</p>|4|<p>1.18.4</p><p>Administración de Productos</p><p>Consulta de Producto</p>|4|
||<p>"1.10.2"</p><p>Reportes</p><p>Filtrar reportes por Sucursal</p>|5|<p>"1.10.5"</p><p>Reportes</p><p>Filtrar reportes por Empleado</p>|5|<p>1.18.2</p><p>Administración de Productos</p><p>Baja de Producto</p>|4|<p>1.13.1</p><p>Administración de Pagos</p><p>Pagar con Dinero</p>|2|
||<p>"1.10.3"</p><p>Reportes</p><p>Filtrar reportes por Cliente</p>|5|<p>"1.10.6"</p><p>Reportes</p><p>Ranking de Ventas</p>|5|<p>1.18.3</p><p>Administración de Productos</p><p>Modificación de Producto</p>|4|<p>"1.16.2"</p><p>GUI</p><p>Diseño</p>|6|
||<p>"1.16.1"</p><p>GUI</p><p>Análisis</p>|3|<p>"1.16.2"</p><p>GUI</p><p>Diseño</p>|6|<p>"1.16.1"</p><p>GUI</p><p>Análisis</p>|3|||
||||||||||
||Horas por semana:|18|Horas por semana:|21|Horas por semana:|15|Horas por semana:|12|
||||||||||
||||||||||
|Mati|"1.17.1" Tareas automáticas-Enviar un comprobante al mail del cliente|6|"1.17.2" Tareas Automáticas-Cambiar a estado moroso al cliente|6|"1.11.2"	Base de datos- Back up de datos|5|"1.11.3"	Base de datos- Importe de Base|5|
||"1.17.3" Tareas Automáticas- Enviar un mail 48hs de la cita como recordatorio|3|"1.17.3" Tareas Automáticas- Enviar un mail 48hs de la cita como recordatorio|3|"1.16.1"	GUI'S - Análisis|3|"1.16.2"	GUI'S - Diseño|6|
||||||"1.11.4"	BDD-Coneccion a bdd|3|||
||Horas por semana:|9|Horas por semana:|9|Horas por semana:|11|Horas por semana:|11|


CALENDARIO FINAL


|***Fecha***|***RF***|***ID REQ.***|***REQUERIMIENTO***||
| :-: | :-: | :-: | :-: | :- |
|10/7/2019|2|1|Como recepcionista quiero realizar Alta de citas en un calendario|4|
|||2|Como recepcionista quiero realizar Modificar el estado de mis citas en el calendario|1|
|||3|Como Recepcionista quiero consultar el calendario de citas|1|
|||4|Como Recepcionista quiero visualizar la descripción de una cita|1|
|||5|Como Supervisor quiero realizar Alta de Profesional|2|
|||6|Como Supervisor quiero ver el estado del Profesional|1|
|||7|Como Supervisor quiero modificar el estado del Profesional|1|
|||8|Como Supervisor quiero crear un servicio|2|
|||9|Como Supervisor quiero modificar un servicio|2|
|||10|Como Recepcionista quiero visualizar los servicios|1|
|||13|Permitir la visualización de turnos ocupados y disponibles rápidamente,|1|
|||24|Como Recepcionista quiero realizar Alta de cliente|1|
|||25|Como Recepcionista quiero ver el estado del cliente|1|
|||26|Como Recepcionista quiero modificar el estado del cliente|1|
|||27|Como Supervisor quiero modificar al cliente|3|
|10/21/2019|3|37|Como Recepcionista quiero abrir y cerrar Caja|3|
|||12|Como recepcionista quiero fiar a un cliente regular|3|
|||41|Como Supervisor quiero crear una promoción|3|
|||42|Como Supervisor quiero crear una regla y asociar una promo|5|
|||43|Como Supervisor quiero modificar una promoción y su regla|4|
|||22|Como Usuario quiero visualizar cada promoción, su regla y los puntos asignados|4|
|||14|Como Supervisor quiero asignar una regla de puntos para los servicios|1|
|||21|Como recepcionista quiero realizar el ingreso del pago|4|
|||28|Sumar puntos correspondientes al cliente que cumpla las condiciones para dar puntos|2|
|||31|Como administrador quiero realizar ABM de Sucursales|1|
|||32|Como Usuario quiero consultar sucursales|1|
|||40|Como Recepcionista quiero realizar un egreso de la Caja|2|
|11/4/2019|4|17|Generar comprobante de cualquier operación realizada|2|
|||18|Como Recepcionista quiero consultar cualquier operación realizada|5|
|||19|Como Supervisor quiero visualizar y poder filtrar los reportes de Caja de mi sucursal|3|
|||20|Crear reportes gráficos y estadísticos sobre la Caja del sistema|5|
|||23|Como Contador quiero visualizar las cajas de todas las sucursales|2|
|||29|Como administrador quiero realizar ABMC de Usuarios|3|
|||30|Se debe enviar un comprobante al cliente al realizar dar de alta, modificar o cancelar un turno|1|
|||33|Como Dueño quiero tener acceso al ranking de ventas por servicio|4|
|||34|Como Dueño quiero tener acceso a todas las cajas de cada sucursal (reportes)|4|
|11/18/2019|5|11|Disponibilidad 24/7 de la exportación de la base de datos|3|
|||15|Como Usuario quiero seleccionar el idioma del sistema|4|
|||16|Disponibilidad del sistema en distintos lenguajes (inglés y español)|4|
|||35|Como administrador quiero exportar la base de datos de la sucursal|3|
|||36|Como administrador quiero importar una base de datos a la sucursal|2|
|||38|Como Recepcionista quiero realizar ABMC de Producto|2|
|||39|Como recepcionista quiero realizar el ingreso del pago de un Producto|2|



PESO

|Peso RF2|23|
| :-: | :-: |
|Peso RF3|33|
|Peso RF4|29|
|Peso RF5|20|
|Peso Total|105|


![Gráfico](Plan%20de%20Proyecto.001.png)

PLANEADO

|**Entrega**|**Semana**|**BaseLine**|
| :-: | :-: | :-: |
|RF2|24 Septiembre a 1 Octubre|10.95%|
||1 Octubre a 7 Octubre|21.90%|
|RF3|7 Octubre a 14 Octubre|37.70%|
||14 Octubre a 21 Octubre|53.30%|
|RF4|21 Octubre a 28 Octubre|67.12%|
||28 Octubre a 4 Noviembre|80.95%|
|RF5|4 Noviembre a 11 Noviembre|90.47%|
||11 Noviembre a 18 Noviembre|99%|

ACTUAL


|**Entrega**|**Semana**|**BaseLine**|
| :-: | :-: | :-: |
|2|24 Septiembre a 1 Octubre|7.61%|
||1 Octubre a 7 Octubre|15.23%|
|3|7 Octubre a 14 Octubre|43.63%|
||14 Octubre a 21 Octubre|52.29%|
|4|21 Octubre a 28 Octubre|65.45%|
||28 Octubre a 4 Noviembre|73.25%|
|5|4 Noviembre a 11 Noviembre|83.47%|
||11 Noviembre a 18 Noviembre|94.85%|
|1ra semana atraso|18 Noviembre a 26 Noviembre|98.05%|

