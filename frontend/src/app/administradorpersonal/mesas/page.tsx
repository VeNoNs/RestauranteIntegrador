'use client';
import React, { useState } from 'react';
import ModalAgregarMesa from '@/components/administradorpersonal/ModalAgregarMesa';
import ModalEliminarMesa from '@/components/administradorpersonal/ModalEliminarMesa';

const MesasPage: React.FC = () => {
  const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
  const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
  const [mesaSeleccionada, setMesaSeleccionada] = useState<string | null>(null);

  const mesas = [
    { id: '1', numero: 1, asientos: 5, piso: 1 },
    { id: '2', numero: 2, asientos: 6, piso: 2 },
    { id: '3', numero: 3, asientos: 10, piso: 1 },
    { id: '4', numero: 4, asientos: 8, piso: 1 },
  ];

  const handleAgregarClick = () => {
    setIsAgregarModalOpen(true);
  };

  const handleEliminarClick = () => {
    setIsEliminarModalOpen(true);
  };

  const seleccionarMesa = (id: string) => {
    setMesaSeleccionada(id);
  };

  return (
    <div>
      <h1 className="text-3xl font-bold text-black mb-4">Agregar Mesas</h1>

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
              <th className="px-4 py-2 text-left text-black">Número de Asientos</th>
              <th className="px-4 py-2 text-left text-black">Piso</th>
            </tr>
          </thead>
          <tbody>
            {mesas.map((mesa) => (
              <tr
                key={mesa.id}
                className={`hover:bg-gray-200 cursor-pointer transition-colors ${
                  mesaSeleccionada === mesa.id ? 'bg-purple-100' : ''
                }`}
                onClick={() => seleccionarMesa(mesa.id)}
              >
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.numero}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.asientos}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{mesa.piso}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal para Agregar Mesa */}
      {isAgregarModalOpen && (
        <ModalAgregarMesa onClose={() => setIsAgregarModalOpen(false)} />
      )}

      {/* Modal para Eliminar Mesa */}
      {isEliminarModalOpen && (
        <ModalEliminarMesa onClose={() => setIsEliminarModalOpen(false)} />
      )}
    </div>
  );
};

export default MesasPage;
