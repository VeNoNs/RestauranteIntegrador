'use client';
import React, { useState } from 'react';

interface ModalAgregarEmpleadoProps {
  onClose: () => void;
}

const ModalAgregarEmpleado: React.FC<ModalAgregarEmpleadoProps> = ({ onClose }) => {
  const [nombreEmpleado, setNombreEmpleado] = useState('');
  const [rolEmpleado, setRolEmpleado] = useState('');

  const handleAgregarEmpleado = () => {
    // Lógica para agregar el empleado (puedes integrar con tu backend o lógica de estado)
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Agregar Empleado</h2>

        <input
          type="text"
          placeholder="Nombre del Empleado"
          value={nombreEmpleado}
          onChange={(e) => setNombreEmpleado(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />

        <select
          value={rolEmpleado}
          onChange={(e) => setRolEmpleado(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        >
          <option value="">Seleccionar Rol</option>
          <option value="Cocina">Cocina</option>
          <option value="Mozo">Mozo</option>
          <option value="Administrador">Administrador</option>
        </select>

        <div className="flex justify-end space-x-4">
          <button onClick={handleAgregarEmpleado} className="bg-green-500 text-white py-2 px-4 rounded">
            Agregar
          </button>
          <button onClick={onClose} className="bg-red-500 text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalAgregarEmpleado;
