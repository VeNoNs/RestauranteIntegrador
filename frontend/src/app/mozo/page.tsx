'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Pedido {
  idOrden: number;
  comida: {
    nombreComida: string;
  };
  cantidad: number;
  estado: string;
  mesa: {
    nroMesa: number;
  };
}

const PedidosListos: React.FC = () => {
  const [pedidos, setPedidos] = useState<Pedido[]>([]);

  useEffect(() => {
    const fetchPedidos = async () => {
      try {
        const response = await axios.get('http://localhost:8080/orden/api/verordenes');
        // Filtrar solo los pedidos con estado "terminado"
        const pedidosTerminados = response.data.filter((pedido: Pedido) => pedido.estado === 'terminado');
        setPedidos(pedidosTerminados);
      } catch (error) {
        console.error('Error al cargar los pedidos:', error);
      }
    };

    fetchPedidos();
  }, []);

  const handleEntregarPedido = async (pedidoId: number) => {
    try {
      // Cambiar el estado de "terminado" a "entregado"
      await axios.put(`http://localhost:8080/orden/api/editar/${pedidoId}`, { estado: 'entregado' });
      
      // Actualizar el estado del frontend, removiendo el pedido de la lista
      setPedidos(pedidos.filter(pedido => pedido.idOrden !== pedidoId));
    } catch (error) {
      console.error('Error al actualizar el estado del pedido a "entregado":', error);
    }
  };

  return (
    <div className="p-8 bg-white min-h-screen">
      <h2 className="text-2xl font-bold text-black mb-4">Pedidos Listos para Entregar</h2>
      <div className="overflow-x-auto">
        <table className="min-w-full table-auto bg-white shadow-md rounded-lg border">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left text-black border-b">ID Pedido</th>
              <th className="px-4 py-2 text-left text-black border-b">Número de Mesa</th>
              <th className="px-4 py-2 text-left text-black border-b">Nombre del Plato</th>
              <th className="px-4 py-2 text-left text-black border-b">Cantidad</th>
              <th className="px-4 py-2 text-left text-black border-b">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pedidos.map((pedido) => (
              <tr key={pedido.idOrden} className="hover:bg-gray-200">
                <td className="px-4 py-2 text-black border-b">{pedido.idOrden}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.mesa.nroMesa}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.comida.nombreComida}</td>
                <td className="px-4 py-2 text-black border-b">{pedido.cantidad}</td>
                <td className="px-4 py-2 border-b">
                  <button
                    onClick={() => handleEntregarPedido(pedido.idOrden)}
                    className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
                  >
                    Entregado
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

export default PedidosListos;
