'use client';
import React, { useState } from 'react';

interface ModalEliminarMesaProps {
  onClose: () => void;
}

const ModalEliminarMesa: React.FC<ModalEliminarMesaProps> = ({ onClose }) => {
  const [mesaSeleccionada, setMesaSeleccionada] = useState('');

  const mesas = [
    { id: '1', numero: 1 },
    { id: '2', numero: 2 },
    { id: '3', numero: 3 },
    { id: '4', numero: 4 },
  ];

  const handleEliminarMesa = () => {
    // Lógica para eliminar la mesa (integrar con backend o lógica de estado)
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Eliminar Mesa</h2>

        <select
          value={mesaSeleccionada}
          onChange={(e) => setMesaSeleccionada(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        >
          <option value="">Selecciona una mesa</option>
          {mesas.map((mesa) => (
            <option key={mesa.id} value={mesa.id}>
              Mesa {mesa.numero}
            </option>
          ))}
        </select>

        <div className="flex justify-end space-x-4">
          <button onClick={handleEliminarMesa} className="bg-red-500 text-white py-2 px-4 rounded">
            Eliminar
          </button>
          <button onClick={onClose} className="bg-black text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalEliminarMesa;
