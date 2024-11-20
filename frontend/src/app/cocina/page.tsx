'use client';
import "@/styles/globals.css";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation';

interface Pedido {
  idOrden: number;
  comida: {
    nombreComida: string;
  };
  cantidad: number;
  estado: string;
}

const CocinaPage: React.FC = () => {
  const router = useRouter();
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  const [pedidosEnProceso, setPedidosEnProceso] = useState<Pedido[]>([]);

  useEffect(() => {
    const fetchPedidos = async () => {
      try {
        const response = await axios.get('http://localhost:8080/orden/api/verordenes');
        const ordenes = response.data;
        
        // Filtrar pedidos en estado "pendiente" para la primera tabla
        setPedidos(ordenes.filter((pedido: Pedido) => pedido.estado === "pendiente"));
        
        // Filtrar pedidos en estado "en proceso" para la segunda tabla
        setPedidosEnProceso(ordenes.filter((pedido: Pedido) => pedido.estado === "en proceso"));
      } catch (error) {
        console.error('Error al cargar los pedidos:', error);
      }
    };

    fetchPedidos(); // Llamar a la API cuando se carga la página
  }, []);

  const handleTomarPedido = async (pedidoId: number) => {
    try {
      // Envía el estado como un objeto JSON
      await axios.put(`http://localhost:8080/orden/api/editar/${pedidoId}`, { estado: "en proceso" });
  
      // Actualiza el estado en el frontend
      const pedidoTomado = pedidos.find(p => p.idOrden === pedidoId);
      if (pedidoTomado) {
        setPedidos(pedidos.filter(p => p.idOrden !== pedidoId));
        setPedidosEnProceso([...pedidosEnProceso, { ...pedidoTomado, estado: "en proceso" }]);
      }
    } catch (error) {
      console.error('Error al actualizar el estado del pedido a "en proceso":', error);
    }
  };
  

  // Cambiar estado de "En Proceso" a "Terminado" y actualizar en la base de datos
  const handleTerminarPedido = async (pedidoId: number) => {
    try {
      // Enviar solo el estado actualizado
      await axios.put(`http://localhost:8080/orden/api/editar/${pedidoId}`, { estado: "terminado" });

      // Actualizar el estado en el frontend
      setPedidosEnProceso(pedidosEnProceso.filter(p => p.idOrden !== pedidoId)); // Remover de "Pedidos en Proceso"
    } catch (error) {
      console.error('Error al actualizar el estado del pedido a "terminado":', error);
    }
  };

  const handleCerrarSesion = () => {
    router.push('/');
  };

  const nombreCocinero = 'Juan Pérez';

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
              <th className="px-4 py-2 text-left text-black border-b">Cantidad</th>
              <th className="px-4 py-2 text-left text-black border-b">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pedidos.map((pedido) => (
              <tr key={pedido.idOrden} className="hover:bg-gray-200">
                <td className="px-4 py-2 text-black border-b">{pedido.idOrden}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.comida.nombreComida}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.cantidad}</td>
                <td className="px-4 py-2 border-b">
                  <button
                    onClick={() => handleTomarPedido(pedido.idOrden)}
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
              <th className="px-4 py-2 text-left text-black border-b">Cantidad</th>
              <th className="px-4 py-2 text-left text-black border-b">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pedidosEnProceso.map((pedido) => (
              <tr key={pedido.idOrden} className="hover:bg-gray-200">
                <td className="px-4 py-2 text-black border-b">{pedido.idOrden}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.comida.nombreComida}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.cantidad}</td>
                <td className="px-4 py-2 border-b">
                  <button
                    onClick={() => handleTerminarPedido(pedido.idOrden)}
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
