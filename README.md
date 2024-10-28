Nombre del Proyecto: Sistema de Gestión de un restaurante

DESCRIPCIÓN
El Sistema de Gestión de un Restaurante es una plataforma para facilitar las operaciones 
diarias del restaurante. Permite a los administradores gestionar su menú, tablas y pedidos 
de manera eficiente, mientras que los clientes pueden reservar mesas y realizar pedidos de 
platos en línea.

CASOS DE USO 

![casosuso](https://github.com/user-attachments/assets/75004744-324b-4659-b5a7-4a4eda967433)


   CU01: Configurar Sistema 
   
   CU02: Reservar Mesa  
   
   CU03: Seleccionar Comida 
   
   CU04: Pagar Orden 
   
   CU05: Asignar Pedido en Cocina 
   
   CU06: Confirmar Preparación del Plato 
   
   CU07: Recoger y Entregar Comida 
   
   CU08: Realizar Pedido Adicional 
   
   CU09: Liberar Mesa 


CONFIGURACIÓN DE LA BASE DE DATOS
   CREATE DATABASE restauranteintegrador;
Actualiza el archivo application.properties con las credenciales de tu base de datos
   spring.datasource.url=jdbc:mysql://localhost:3306/restauranteintegrador
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
   spring.jpa.hibernate.ddl-auto=update
