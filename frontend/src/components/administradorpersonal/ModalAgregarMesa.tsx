'use client';
import React, { useState } from 'react';

interface ModalAgregarMesaProps {
  onClose: () => void;
}

const ModalAgregarMesa: React.FC<ModalAgregarMesaProps> = ({ onClose }) => {
  const [numeroMesa, setNumeroMesa] = useState('');
  const [numeroAsientos, setNumeroAsientos] = useState('');
  const [piso, setPiso] = useState('');

  const handleAgregarMesa = () => {
    // Lógica para agregar la mesa (puedes integrar con tu backend o lógica de estado)
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Agregar Mesa</h2>

        <input
          type="number"
          placeholder="Número de Mesa"
          value={numeroMesa}
          onChange={(e) => setNumeroMesa(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />
        <input
          type="number"
          placeholder="Número de Asientos"
          value={numeroAsientos}
          onChange={(e) => setNumeroAsientos(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />
        <input
          type="number"
          placeholder="Piso"
          value={piso}
          onChange={(e) => setPiso(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />

        <div className="flex justify-end space-x-4">
          <button onClick={handleAgregarMesa} className="bg-green-400 text-white py-2 px-4 rounded">
            Agregar
          </button>
          <button onClick={onClose} className="bg-red-400 text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalAgregarMesa;
