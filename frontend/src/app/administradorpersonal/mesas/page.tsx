'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ModalAgregarMesa from '@/components/administradorpersonal/ModalAgregarMesa';
import ModalEliminarMesa from '@/components/administradorpersonal/ModalEliminarMesa';

interface Mesa {
  idMesa: number;
  nroMesa: number;
  cantidadSillas: number;
  estado: string;
  local: {
    idLocal: number;
    ubicacion: string;
  };
}

const MesasPage: React.FC = () => {
  const [mesas, setMesas] = useState<Mesa[]>([]);
  const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
  const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
  const [mesaSeleccionada, setMesaSeleccionada] = useState<number | null>(null);

  // Función para obtener las mesas desde la API
  const fetchMesas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/mesas/ver');
      setMesas(response.data);
    } catch (error) {
      console.error('Error al obtener las mesas:', error);
    }
  };

  // Llamar a fetchMesas cuando el componente se monta
  useEffect(() => {
    fetchMesas();
  }, []);

  // Al cerrar el modal, volvemos a hacer fetch de las mesas para actualizar la tabla
  const handleModalClose = () => {
    setIsAgregarModalOpen(false);
    setIsEliminarModalOpen(false);
    fetchMesas(); // Volvemos a obtener las mesas para actualizar la tabla
  };

  const handleAgregarClick = () => {
    setIsAgregarModalOpen(true);
  };

  const handleEliminarClick = () => {
    setIsEliminarModalOpen(true);
  };

  const seleccionarMesa = (idMesa: number) => {
    setMesaSeleccionada(idMesa);
  };

  return (
    <div>
      <h1 className="text-3xl font-bold text-black mb-4">Administrar Mesas</h1>

      {/* Botones superiores */}
      <div className="flex justify-end space-x-4 mb-4">
        <button
          onClick={handleAgregarClick}
          className="bg-green-500 text-white py-2 px-4 rounded-md shadow-md hover:bg-green-700"
        >
          Agregar
        </button>

        <button
          onClick={handleEliminarClick}
          className="bg-red-500 text-white py-2 px-4 rounded-md shadow-md hover:bg-red-700"
        >
          Eliminar
        </button>
      </div>

      {/* Tabla de mesas con estilo ajustado */}
      <div className="overflow-x-auto">
        <table className="min-w-full table-auto bg-white shadow-lg rounded-lg overflow-hidden">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left text-black">N° Mesa</th>
              <th className="px-4 py-2 text-left text-black">Cantidad de Sillas</th>
              <th className="px-4 py-2 text-left text-black">Estado</th>
              <th className="px-4 py-2 text-left text-black">Local</th>
              <th className="px-4 py-2 text-left text-black">Dirección del Local</th>
            </tr>
          </thead>
          <tbody>
            {mesas.map((mesa) => (
              <tr
                key={mesa.idMesa}
                className={`hover:bg-gray-200 cursor-pointer transition-colors ${
                  mesaSeleccionada === mesa.idMesa ? 'bg-purple-100' : ''
                }`}
                onClick={() => seleccionarMesa(mesa.idMesa)}
              >
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.nroMesa}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.cantidadSillas}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.estado}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.local.idLocal}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.local.ubicacion}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal para Agregar Mesa */}
      {isAgregarModalOpen && (
        <ModalAgregarMesa onClose={handleModalClose} />
      )}

      {/* Modal para Eliminar Mesa */}
      {isEliminarModalOpen && (
        <ModalEliminarMesa onClose={handleModalClose} />
      )}
    </div>
  );
};

export default MesasPage;
