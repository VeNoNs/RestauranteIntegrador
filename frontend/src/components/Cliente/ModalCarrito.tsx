'use client';
import React, { useState } from 'react';

interface Producto {
  id: number;
  nombre: string;
  precio: number;
}

interface ModalCarritoProps {
  carrito: { producto: Producto; cantidad: number }[];
  onClose: () => void;
}

const ModalCarrito: React.FC<ModalCarritoProps> = ({ carrito, onClose }) => {
  const [pasoActual, setPasoActual] = useState(1);
  const [nombreTarjeta, setNombreTarjeta] = useState('');
  const [numeroTarjeta, setNumeroTarjeta] = useState('');
  const [fechaExpiracion, setFechaExpiracion] = useState('');
  const [codigoCVV, setCodigoCVV] = useState('');

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

  const total = carrito.reduce((acc, item) => acc + item.producto.precio * item.cantidad, 0);

  const confirmarOrden = async () => {
    const ordenes = carrito.map(item => ({
      cantidad: item.cantidad,
      subTotal: item.producto.precio * item.cantidad,
      comida: { idComida: item.producto.id }, // Suponiendo que la entidad Comida tiene un idComida
      local: { idEmpresa: 1 } // Cambia 1 por el ID correspondiente de tu local
    }));

    try {
      const response = await fetch('/orden/api/nueva', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(ordenes),
      });

      if (response.ok) {
        // Manejar la respuesta si es necesario
        console.log('Órdenes guardadas con éxito');
        onClose(); // Cerrar el modal después de confirmar
      } else {
        console.error('Error al guardar la orden');
      }
    } catch (error) {
      console.error('Error en la solicitud:', error);
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded-lg w-full max-w-3xl">
        {/* Barra de Progreso */}
        <div className="flex justify-between items-center mb-4">
          <div className={`w-1/3 border-b-2 ${pasoActual >= 1 ? 'border-red-500' : 'border-gray-300'}`} />
          <div className={`w-1/3 border-b-2 ${pasoActual >= 2 ? 'border-red-500' : 'border-gray-300'}`} />
          <div className={`w-1/3 border-b-2 ${pasoActual === 3 ? 'border-red-500' : 'border-gray-300'}`} />
        </div>

        {/* Pasos */}
        {pasoActual === 1 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Carrito de compras</h2>
            <table className="min-w-full table-auto text-black">
              <thead>
                <tr>
                  <th className="px-4 py-2 text-left">Plato</th>
                  <th className="px-4 py-2 text-left">Cantidad</th>
                  <th className="px-4 py-2 text-left">Precio</th>
                </tr>
              </thead>
              <tbody>
                {carrito.map((item) => (
                  <tr key={item.producto.id} className="hover:bg-gray-100 text-black">
                    <td className="px-4 py-2 text-black">{item.producto.nombre}</td>
                    <td className="px-4 py-2 text-black">{item.cantidad}</td>
                    <td className="px-4 py-2 text-black">S/. {(item.producto.precio * item.cantidad).toFixed(2)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {pasoActual === 2 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Método de Pago</h2>
            <form>
              <div className="mb-4">
                <label className="block mb-2 text-black">Nombre en la tarjeta</label>
                <input
                  type="text"
                  className="w-full border px-4 py-2 rounded-lg"
                  value={nombreTarjeta}
                  onChange={(e) => setNombreTarjeta(e.target.value)}
                />
              </div>
              <div className="mb-4">
                <label className="block mb-2 text-black">Número de la tarjeta</label>
                <input
                  type="text"
                  className="w-full border px-4 py-2 rounded-lg"
                  value={numeroTarjeta}
                  onChange={(e) => setNumeroTarjeta(e.target.value)}
                />
              </div>
              <div className="mb-4">
                <label className="block mb-2 text-black">Fecha de expiración</label>
                <input
                  type="text"
                  className="w-full border px-4 py-2 rounded-lg"
                  value={fechaExpiracion}
                  onChange={(e) => setFechaExpiracion(e.target.value)}
                />
              </div>
              <div className="mb-4">
                <label className="block mb-2 text-black">Código CVV</label>
                <input
                  type="text"
                  className="w-full border px-4 py-2 rounded-lg"
                  value={codigoCVV}
                  onChange={(e) => setCodigoCVV(e.target.value)}
                />
              </div>
            </form>
          </div>
        )}

        {pasoActual === 3 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Resumen de la Compra</h2>
            <p className="mb-4 text-black">Aquí está el resumen de tu pedido:</p>
            <ul className="mb-4 text-black">
              {carrito.map((item) => (
                <li key={item.producto.id}>
                  {item.cantidad} x {item.producto.nombre} - S/. {(item.producto.precio * item.cantidad).toFixed(2)}
                </li>
              ))}
            </ul>
            <p className="font-bold text-black">Total a pagar: S/. {total.toFixed(2)}</p>
          </div>
        )}

        {/* Botones */}
        <div className="flex justify-between mt-6">
          {pasoActual > 1 && (
            <button onClick={retrocederPaso} className="bg-gray-500 text-white py-2 px-4 rounded-lg">
              Anterior
            </button>
          )}
          {pasoActual < 3 && (
            <button onClick={avanzarPaso} className="bg-red-500 text-white py-2 px-4 rounded-lg">
              Siguiente
            </button>
          )}
          {pasoActual === 3 && (
            <button onClick={confirmarOrden} className="bg-green-500 text-white py-2 px-4 rounded-lg">
              Confirmar
            </button>
          )}
          <button onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded-lg">
            Cerrar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalCarrito;
