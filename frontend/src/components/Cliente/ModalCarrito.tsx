'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface ProductoCarrito {
  idComida: string;
  nombreComida: string;
  cantidad: number;
  precio: number;
}

interface Mesa {
  idMesa: number;
  nroMesa: number;
}

interface ModalCarritoProps {
  carrito: ProductoCarrito[];
  onClose: () => void;
  setCarrito: React.Dispatch<React.SetStateAction<ProductoCarrito[]>>;
}

const ModalCarrito: React.FC<ModalCarritoProps> = ({ carrito, onClose, setCarrito }) => {
  const [pasoActual, setPasoActual] = useState(1);
  const [mesas, setMesas] = useState<Mesa[]>([]);
  const [mesaSeleccionada, setMesaSeleccionada] = useState<number | null>(null);

  // Obtener las mesas disponibles
  useEffect(() => {
    const obtenerMesas = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/mesas/ver');
        setMesas(response.data);
      } catch (error) {
        console.error('Error al obtener las mesas:', error);
      }
    };

    obtenerMesas();
  }, []);

  // Calcular el total
  const total = carrito.reduce((acc, producto) => acc + producto.precio * producto.cantidad, 0);

  // Controlar el avance de los pasos
  const avanzarPaso = () => {
    if (pasoActual < 3) {
      setPasoActual(pasoActual + 1);
    }
  };

  const retrocederPaso = () => {
    if (pasoActual > 1) {
      setPasoActual(pasoActual - 1);
    }
  };

  // Manejar la compra
  const handleCompra = async () => {
    if (!mesaSeleccionada) {
      alert('Debes seleccionar una mesa.');
      return;
    }

    try {
      for (const producto of carrito) {
        // Crear la orden para cada producto en el carrito
        const nuevaOrden = {
          cantidad: producto.cantidad,
          subTotal: producto.precio * producto.cantidad, // Calcula el subtotal
          comida: { idComida: producto.idComida }, // Envía solo el ID de la comida
          mesa: { idMesa: mesaSeleccionada }, // Usa la mesa seleccionada
        };

        // Enviar la solicitud para crear una nueva orden
        const respuestaOrden = await axios.post('http://localhost:8080/orden/api/nueva', nuevaOrden);
        const ordenCreada = respuestaOrden.data;

        // Crear el pago asociado a la orden
        const nuevoPago = {
          fechaPago: new Date().toISOString().split('T')[0], // Formato de fecha
          monto: producto.precio * producto.cantidad, // Monto total para ese producto
          orden: { idOrden: ordenCreada.idOrden }, // Relaciona el pago con la orden creada
        };

        await axios.post('http://localhost:8080/pago/api/nuevo', nuevoPago);

        // Crear el comprobante asociado al pago
        const nuevoComprobante = {
          fechaComprobante: new Date().toISOString().split('T')[0],
          pago: { idPago: nuevoPago.idPago },
        };

        await axios.post('http://localhost:8080/api/comprobante/crear', nuevoComprobante);
      }

      // Mostrar mensaje de éxito
      alert('Compra realizada con éxito');

      // Reiniciar el carrito y cerrar el modal
      setCarrito([]);
      onClose();
    } catch (error: any) {
      console.error('Error al realizar la compra:', error.response ? error.response.data : error.message);
      alert('Hubo un error al procesar la compra. Inténtelo de nuevo.');
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded-lg w-full max-w-3xl relative">
        {/* Botón de cerrar */}
        <button onClick={onClose} className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-3 hover:bg-red-600">
          &times;
        </button>

        {/* Barra de Progreso */}
        <div className="flex justify-between items-center mb-4">
          <div className={`w-1/3 border-b-2 ${pasoActual >= 1 ? 'border-red-500' : 'border-gray-300'}`} />
          <div className={`w-1/3 border-b-2 ${pasoActual >= 2 ? 'border-red-500' : 'border-gray-300'}`} />
          <div className={`w-1/3 border-b-2 ${pasoActual === 3 ? 'border-red-500' : 'border-gray-300'}`} />
        </div>

        {/* Pasos */}
        {pasoActual === 1 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Selecciona tus platos</h2>
            <table className="min-w-full table-auto text-black">
              <thead>
                <tr>
                  <th className="px-4 py-2 text-left">Plato</th>
                  <th className="px-4 py-2 text-left">Cantidad</th>
                  <th className="px-4 py-2 text-left">Precio</th>
                </tr>
              </thead>
              <tbody>
                {carrito.map((producto) => (
                  <tr key={producto.idComida} className="hover:bg-gray-100 text-black">
                    <td className="px-4 py-2 text-black">{producto.nombreComida}</td>
                    <td className="px-4 py-2 text-black">{producto.cantidad}</td>
                    <td className="px-4 py-2 text-black">S/. {producto.precio.toFixed(2)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {pasoActual === 2 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Seleccionar Mesa</h2>
            <div className="mb-4">
              <select
                className="w-full border px-4 py-2 rounded-lg"
                onChange={(e) => setMesaSeleccionada(Number(e.target.value))}
                value={mesaSeleccionada ?? ''}
              >
                <option value="">Selecciona una mesa</option>
                {mesas.map((mesa) => (
                  <option key={mesa.idMesa} value={mesa.idMesa}>
                    Mesa {mesa.nroMesa}
                  </option>
                ))}
              </select>
            </div>
          </div>
        )}

        {pasoActual === 3 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Resumen de la Compra</h2>
            <p className="mb-4 text-black">Gracias por tu compra. Aquí está el resumen de tu pedido:</p>
            <ul className="mb-4 text-black">
              {carrito.map((producto) => (
                <li key={producto.idComida}>
                  {producto.cantidad} x {producto.nombreComida} - S/. {producto.precio.toFixed(2)}
                </li>
              ))}
            </ul>
            <p className="font-bold text-black">Total: S/. {total.toFixed(2)}</p>
          </div>
        )}

        {/* Botones */}
        <div className="flex justify-between mt-6">
          {pasoActual > 1 && (
            <button onClick={retrocederPaso} className="bg-gray-500 text-white py-2 px-4 rounded-lg">
              Anterior
            </button>
          )}
          {pasoActual < 3 ? (
            <button onClick={avanzarPaso} className="bg-red-500 text-white py-2 px-4 rounded-lg">
              Siguiente
            </button>
          ) : (
            <button onClick={handleCompra} className="bg-green-500 text-white py-2 px-4 rounded-lg">
              Comprar
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default ModalCarrito;
