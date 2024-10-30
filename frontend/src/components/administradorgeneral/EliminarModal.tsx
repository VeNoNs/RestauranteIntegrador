'use client';
import React from 'react';

interface ModalEliminarProps {
  onClose: () => void;
  empresaId: number;
  onEliminar: (empresaId: number) => void;
}

const EliminarModal: React.FC<ModalEliminarProps> = ({ onClose, empresaId, onEliminar }) => {
  const handleEliminar = () => {
      onEliminar(empresaId);
      onClose();
  };

  return (
      <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-8 rounded shadow-lg relative">
              <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">✖</button>
              <h2 className="text-2xl mb-4 font-bold text-black">Eliminar Empresa</h2>
              <p>¿Estás seguro de que quieres eliminar esta empresa?</p>
              <div className="flex justify-end space-x-4 mt-4">
                  <button onClick={handleEliminar} className="bg-red-500 text-white py-2 px-4 rounded">Eliminar</button>
                  <button onClick={onClose} className="bg-black text-white py-2 px-4 rounded">Cancelar</button>
              </div>
          </div>
      </div>
  );
};

export default EliminarModal;