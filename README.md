![casosuso](https://github.com/user-attachments/assets/75004744-324b-4659-b5a7-4a4eda967433)
CU01: Configurar Sistema 
     Identificador: CU01 
     Actores: Administrador 
     Tipo: Primario 
     Referencias: Configuración del sistema, CU01 en el diagrama de casos de uso. 
     Precondición: El sistema debe estar instalado y operativo. 
     Postcondición: El restaurante queda configurado en el sistema según las 
    necesidades especificadas. 
     Descripción: El administrador configura el sistema para adaptarlo a las 
    necesidades específicas del restaurante, estableciendo el número de mesas, los 
    menús disponibles, el personal de cocina, y otras configuraciones. 
     Resumen: El administrador accede al sistema y realiza la configuración necesaria 
    para el restaurante. 
     Curso Normal: 
    o Paso 1: El administrador accede al sistema. 
    o Paso 2: Selecciona la opción de configuración. 
    o Paso 3: Ingresa los datos del restaurante como número de mesas, menús y 
    personal de cocina. 
    o Paso 4: El sistema guarda la configuración y actualiza los datos. 
     Resultado: El restaurante queda configurado en el sistema. 
CU02: Reservar Mesa 
     Identificador: CU02 
     Actor: Cliente 
     Tipo: Primario 
     Referencias: Requerimientos funcionales relacionados con la reserva de mesas. 
     Precondición: El sistema debe estar configurado con las mesas disponibles. 
    15 
     Postcondición: La mesa queda reservada en el sistema y se envía una 
    confirmación al cliente. 
     Descripción: El cliente realiza una reserva de mesa a través del sistema. 
     Resumen: El cliente accede al sistema, selecciona una mesa y confirma su 
    reserva. 
    Curso Normal: 
    1. Cliente: Accede al sistema (app o web). 
    2. Cliente: Selecciona la fecha, hora y número de personas para la reserva. 
    3. Sistema: Muestra las mesas disponibles. 
    4. Cliente: Selecciona una mesa y confirma la reserva. 
    5. Sistema: Registra la reserva y envía confirmación. 
    Curso Alterno 1(No hay mesas disponibles): 
    1. El Cliente selecciona fecha, hora y número de personas. 
    2. El Sistema informa que no hay mesas disponibles para la selección. 
    3. El Cliente recibe una notificación y se le da la opción de cambiar la fecha/hora, o 
    cancelar la solicitud. 
    4. Si el Cliente cambia la fecha/hora, se repite el paso 3 del curso normal. 
    5. Si el Cliente cancela, el sistema finaliza el proceso sin realizar la reserva. 
    Curso Alterno 2 (Problema con la confirmación de la reserva): 
    1. El Cliente selecciona fecha, hora y mesa. 
    2. El Sistema intenta registrar la reserva. 
    3. El Sistema detecta un error o falla en el registro de la reserva. 
    4. El Sistema notifica al Cliente que la reserva no pudo completarse y sugiere 
    reintentar o contactar al soporte técnico. 
    
CU03: Seleccionar Comida 
     Identificador: CU03 
     Actor: Cliente 
     Tipo: Primario 
     Referencias: Requerimientos funcionales relacionados con la selección de 
    comida. 
     Precondición: El cliente debe haber reservado una mesa o estar en una mesa 
    asignada. 
     Postcondición: El pedido se registra y se envía al personal de cocina. 
     Descripción: El cliente selecciona los platos y bebidas que desea desde su 
    mesa. 
     Resumen: El cliente elige su comida y la envía a la cocina. 
    Curso Normal: 
    1. Cliente: Accede al menú desde la mesa reservada. 
    2. Cliente: Navega por el menú y selecciona platos y bebidas. 
    3. Cliente: Confirma la selección de alimentos. 
    4. Sistema: Registra el pedido y lo envía a la cocina. 
    Curso Alterno (Plato no disponible): 
    1. El Cliente selecciona platos y bebidas. 
    2. El Sistema detecta que uno o más de los platos seleccionados no están 
    disponibles (por ejemplo, porque se agotaron). 
    3. El Sistema informa al Cliente que ciertos platos no están disponibles y sugiere 
    reemplazos o la opción de seleccionar otros. 
    4. El Cliente selecciona platos alternativos o elimina los no disponibles. 
    5. El Cliente confirma el pedido actualizado. 
    6. El Sistema registra el pedido y lo envía a la cocina. 
    
    
CU04: Pagar Orden 
     Identificador: CU04 
     Actor: Cliente 
     Tipo: Primario 
     Referencias: Requerimientos funcionales relacionados con el pago. 
     Precondición: El cliente debe haber seleccionado su comida. 
     Postcondición: El pago se registra y el sistema actualiza el estado de la 
    transacción. 
     Descripción: El cliente realiza el pago de la orden. 
     Resumen: El cliente selecciona su método de pago y el sistema procesa la 
    transacción. 
    Curso Normal: 
    1. Cliente: Selecciona la opción de pago en el sistema. 
    2. Sistema: Muestra el resumen de la orden y el total. 
    3. Cliente: Selecciona el método de pago (tarjeta, efectivo, etc.). 
    4. Cliente: Confirma el pago. 
    5. Sistema: Procesa el pago y emite un recibo. 
    Curso Alterno 1 (Método de pago rechazado): 
    1. El Cliente selecciona el método de pago (tarjeta, efectivo, etc.). 
    2. El Sistema procesa el pago, pero la transacción es rechazada (por ejemplo, por 
    fondos insuficientes o problemas con el procesador de pagos). 
    3. El Sistema notifica al Cliente que el pago fue rechazado. 
    4. El Cliente selecciona un nuevo método de pago o elige cancelar la transacción. 
    5. Si se selecciona un nuevo método de pago, se repite el paso 3 del curso normal. 
    6. Si se cancela, la orden no se procesa y el Cliente es notificado. 
    Curso Alterno 2 (Error en la conexión con el procesador de pagos): 
    1. El Cliente selecciona el método de pago. 
    2. El Sistema intenta conectarse al procesador de pagos, pero falla la conexión. 
    3. El Sistema informa al Cliente que el procesamiento de pagos no está disponible 
    temporalmente. 
    4. El Cliente puede intentar de nuevo más tarde o seleccionar otro método de pago 
    (si es aplicable). 
    
CU05: Asignar Pedido en Cocina 
     Identificador: CU05 
     Actor: Personal de Cocina 
     Tipo: Primario 
     Referencias: Requerimientos funcionales relacionados con la asignación de 
    pedidos. 
     Precondición: Deben existir pedidos pendientes en el sistema. 
     Postcondición: El pedido es asignado a un miembro del personal de cocina. 
     Descripción: El personal de cocina selecciona o recibe la asignación automática 
    de un plato para preparar. 
     Resumen: El sistema asigna pedidos a los cocineros o ellos mismos seleccionan 
    qué preparar. 
    Curso Normal: 
    1. Personal de Cocina: Consulta la lista de pedidos pendientes. 
    2. Sistema: Asigna automáticamente el pedido o el personal selecciona uno. 
    3. Sistema: Marca el pedido como "En preparación". 
    
CU06: Confirmar Preparación del Plato 
     Identificador: CU06 
     Actor: Personal de Cocina 
     Tipo: Primario 
     Referencias: Requerimientos funcionales relacionados con la preparación y 
    confirmación de platos. 
     Precondición: El pedido debe estar asignado y en estado "En preparación". 
     Postcondición: El pedido está listo para ser recogido y el mozo es notificado. 
     Descripción: El personal de cocina confirma que el plato está listo. 
     Resumen: El personal de cocina completa el plato y lo marca como listo en el 
    sistema. 
    Curso Normal: 
    1. Personal de Cocina: Prepara el plato. 
    2. Personal de Cocina: Marca el pedido como "Listo" en el sistema. 
    3. Sistema: Notifica al mozo. 
    
CU07: Recoger y Entregar Comida 
     Identificador: CU07 
     Actor: Mozo 
     Tipo: Primario 
     Referencias: Requerimientos funcionales relacionados con la entrega de comida. 
     Precondición: El pedido debe estar marcado como "Listo". 
     Postcondición: La comida se entrega al cliente en la mesa reservada. 
     Descripción: El mozo recoge la comida preparada y la entrega en la mesa. 
     Resumen: El mozo verifica la identidad del cliente y entrega el pedido. 
    Curso Normal: 
    1. Mozo: Recibe la notificación de que el pedido está listo. 
    2. Mozo: Recoge la comida de la cocina. 
    3. Mozo: Se dirige a la mesa reservada. 
    4. Mozo: Escanea el código QR del cliente. 
    5. Mozo: Entrega la comida. 
    Curso Alterno (Pedido entregado incorrectamente): 
    1. El Mozo recoge la comida en la cocina. 
    2. El Mozo se dirige a la mesa equivocada o el Cliente indica que no es su pedido. 
    3. El Mozo revisa el pedido, detecta el error y lo reporta al sistema. 
    4. El Mozo devuelve el pedido incorrecto a la cocina y busca el correcto. 
    5. El Sistema actualiza la situación, y el pedido correcto es entregado. 
    Curso Alterno 2 (Cliente no presente en la mesa): 
    1. El Mozo recibe la notificación de que el pedido está listo. 
    2. El Mozo se dirige a la mesa, pero el Cliente no está presente. 
    3. El Mozo notifica al Sistema que el Cliente no está en la mesa. 
    4. El Sistema registra la situación, y el pedido queda en espera hasta que el Cliente 
    regrese. 
    5. El Mozo reintenta la entrega cuando el Cliente está disponible. 
    
CU08: Realizar Pedido Adicional 
     Identificador: CU08 
     Actor: Mozo 
     Tipo: Secundario 
     Referencias: Requerimientos funcionales relacionados con pedidos adicionales. 
     Precondición: La mesa debe estar ocupada y con un pedido inicial registrado. 
     Postcondición: El pedido adicional se registra y se envía a la cocina. 
    21 
     Descripción: El mozo toma un pedido adicional para un cliente adicional en la 
    mesa. 
     Resumen: El mozo ingresa un nuevo pedido en el sistema para su preparación. 
    Curso Normal: 
    1. Mozo: Selecciona la opción de pedido adicional en el sistema. 
    2. Mozo: Ingresa los nuevos platos y bebidas. 
    3. Sistema: Actualiza el pedido y lo envía a la cocina. 
    
CU09: Liberar Mesa 
     Identificador: CU09 
     Actor: Mozo 
     Tipo: Secundario 
     Referencias: Requerimientos funcionales relacionados con la liberación de 
    mesas. 
     Precondición: La mesa debe haber sido ocupada y limpiada. 
     Postcondición: La mesa queda disponible para futuras reservas o asignaciones. 
     Descripción: El mozo libera una mesa en el sistema después de la salida del 
    cliente. 
     Resumen: El mozo marca la mesa como "Disponible" en el sistema. 
    Curso Normal: 
    1. Mozo: Selecciona la opción de liberación de mesa. 
    2. Mozo: Selecciona la mesa que ha sido limpiada. 
    3. Sistema: Actualiza el estado de la mesa a "Disponible". 
