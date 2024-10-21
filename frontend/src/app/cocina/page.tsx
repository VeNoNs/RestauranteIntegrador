'use client';
import "@/styles/globals.css";
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
interface Pedido {
  id: string;
  nombre: string;
  estado: string;
}

const CocinaPage: React.FC = () => {
  const router = useRouter();
  const [pedidos, setPedidos] = useState<Pedido[]>([
    { id: '1', nombre: 'Pizza Margherita', estado: 'Pendiente' },
    { id: '2', nombre: 'Ensalada César', estado: 'Pendiente' },
    { id: '3', nombre: 'Hamburguesa con Queso', estado: 'Pendiente' },
  ]);
  
  const [pedidosEnProceso, setPedidosEnProceso] = useState<Pedido[]>([]);

  const handleTomarPedido = (pedidoId: string) => {
    const pedidoTomado = pedidos.find(p => p.id === pedidoId);
    if (pedidoTomado) {
      pedidoTomado.estado = 'En Proceso';
      setPedidos(pedidos.filter(p => p.id !== pedidoId));
      setPedidosEnProceso([...pedidosEnProceso, pedidoTomado]);
    }
  };

  const handleTerminarPedido = (pedidoId: string) => {
    setPedidosEnProceso(pedidosEnProceso.filter(p => p.id !== pedidoId));
  };

  const handleCerrarSesion = () => {
    
    router.push('/');
    console.log('Cerrando sesión');
  };

  const nombreCocinero = 'Juan Pérez'; // Aquí puedes colocar el nombre dinámico del usuario logueado.

  return (
    <div className="p-8 bg-white min-h-screen">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-black">Cocina</h1>
        <div className="flex items-center space-x-4">
          <div className="text-xl font-bold text-black">Cocinero: {nombreCocinero}</div>
          <button
            onClick={handleCerrarSesion}
            className="bg-red-500 text-white py-2 px-4 rounded hover:bg-red-600 flex items-center"
          >
            <span className="mr-2">Cerrar Sesión</span>
            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round">
              <path d="M10 19l-7-7 7-7" />
              <path d="M3 12h18" />
            </svg>
          </button>
        </div>
      </div>

      {/* Tabla de Pedidos Pendientes */}
      <h2 className="text-2xl font-bold text-black mb-4">Pedidos Pendientes</h2>
      <div className="overflow-x-auto">
        <table className="min-w-full table-auto bg-white shadow-md rounded-lg border">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left text-black border-b">ID Pedido</th>
              <th className="px-4 py-2 text-left text-black border-b">Nombre del Plato</th>
              <th className="px-4 py-2 text-left text-black border-b">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pedidos.map((pedido) => (
              <tr
                key={pedido.id}
                className="hover:bg-gray-200"
              >
                <td className="px-4 py-2 text-black border-b">{pedido.id}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.nombre}</td>
                <td className="px-4 py-2 border-b">
                  <button
                    onClick={() => handleTomarPedido(pedido.id)}
                    className="bg-black text-white py-2 px-4 rounded hover:bg-gray-800"
                  >
                    Tomar Pedido
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Tabla de Pedidos en Proceso */}
      <h2 className="text-2xl font-bold text-black mb-4 mt-8">Pedidos en Proceso</h2>
      <div className="overflow-x-auto">
        <table className="min-w-full table-auto bg-white shadow-md rounded-lg border">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left text-black border-b">ID Pedido</th>
              <th className="px-4 py-2 text-left text-black border-b">Nombre del Plato</th>
              <th className="px-4 py-2 text-left text-black border-b">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pedidosEnProceso.map((pedido) => (
              <tr key={pedido.id} className="hover:bg-gray-200">
                <td className="px-4 py-2 text-black border-b">{pedido.id}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.nombre}</td>
                <td className="px-4 py-2 border-b">
                  <button
                    onClick={() => handleTerminarPedido(pedido.id)}
                    className="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600"
                  >
                    Terminar Pedido
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default CocinaPage;
