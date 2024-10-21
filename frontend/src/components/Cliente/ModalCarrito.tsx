'use client';
import React, { useState } from 'react';

const ModalCarrito: React.FC = () => {
  const [pasoActual, setPasoActual] = useState(1);

  const [productosSeleccionados, setProductosSeleccionados] = useState([
    { id: '1', nombre: 'Ceviche', cantidad: 1, precio: 50.00 },
    { id: '2', nombre: 'Pollo a la brasa', cantidad: 2, precio: 40.00 },
    { id: '3', nombre: 'Parrilla', cantidad: 2, precio: 50.00 },
    { id: '4', nombre: 'Cerveza', cantidad: 2, precio: 21.00 },
  ]);

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
                {productosSeleccionados.map((producto) => (
                  <tr key={producto.id} className="hover:bg-gray-100 text-black">
                    <td className="px-4 py-2 text-black">{producto.nombre}</td>
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
            <h2 className="text-xl font-bold mb-4 text-black" >Método de Pago</h2>
            <form>
              <div className="mb-4">
                <label className="block mb-2 text-black">Nombre en la tarjeta</label>
                <input type="text" className="w-full border px-4 py-2 rounded-lg" />
              </div>
              <div className="mb-4">
                <label className="block mb-2 text-black">Número de la tarjeta</label>
                <input type="text" className="w-full border px-4 py-2 rounded-lg" />
              </div>
              <div className="mb-4">
                <label className="block mb-2 text-black">Fecha de expiración</label>
                <input type="text" className="w-full border px-4 py-2 rounded-lg" />
              </div>
              <div className="mb-4">
                <label className="block mb-2 text-black">Código CVV</label>
                <input type="text" className="w-full border px-4 py-2 rounded-lg" />
              </div>
            </form>
          </div>
        )}

        {pasoActual === 3 && (
          <div>
            <h2 className="text-xl font-bold mb-4 text-black">Resumen de la Compra</h2>
            <p className="mb-4 text-black">Gracias por tu compra. Aquí está el resumen de tu pedido:</p>
            <ul className="mb-4 text-black">
              {productosSeleccionados.map((producto) => (
                <li key={producto.id}>
                  {producto.cantidad} x {producto.nombre} - S/. {producto.precio.toFixed(2)}
                </li>
              ))}
            </ul>
            <p className="font-bold text-black">Total: S/. {productosSeleccionados.reduce((acc, producto) => acc + (producto.precio * producto.cantidad), 0).toFixed(2)}</p>
          </div>
        )}

        {/* Botones */}
        <div className="flex justify-between mt-6">
          {pasoActual > 1 && (
            <button
              onClick={retrocederPaso}
              className="bg-gray-500 text-white py-2 px-4 rounded-lg"
            >
              Anterior
            </button>
          )}
          {pasoActual < 3 ? (
            <button
              onClick={avanzarPaso}
              className="bg-red-500 text-white py-2 px-4 rounded-lg"
            >
              Siguiente
            </button>
          ) : (
            <button
              onClick={() => alert('Compra realizada con éxito')}
              className="bg-green-500 text-white py-2 px-4 rounded-lg"
            >
              Comprar
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default ModalCarrito;
