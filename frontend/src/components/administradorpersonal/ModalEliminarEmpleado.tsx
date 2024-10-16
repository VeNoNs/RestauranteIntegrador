'use client';
import React, { useState } from 'react';

interface ModalEliminarEmpleadoProps {
  onClose: () => void;
}

const ModalEliminarEmpleado: React.FC<ModalEliminarEmpleadoProps> = ({ onClose }) => {
  const [empleadoSeleccionado, setEmpleadoSeleccionado] = useState('');

  const empleados = [
    { id: '1', nombre: 'Carlos Ruiz' },
    { id: '2', nombre: 'Ana Fernández' },
    { id: '3', nombre: 'Pedro Sánchez' },
  ];

  const handleEliminarEmpleado = () => {
    // Lógica para eliminar el empleado (integrar con backend o lógica de estado)
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Eliminar Empleado</h2>

        <select
          value={empleadoSeleccionado}
          onChange={(e) => setEmpleadoSeleccionado(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        >
          <option value="">Seleccionar Empleado</option>
          {empleados.map((empleado) => (
            <option key={empleado.id} value={empleado.id}>
              {empleado.nombre}
            </option>
          ))}
        </select>

        <div className="flex justify-end space-x-4">
          <button onClick={handleEliminarEmpleado} className="bg-red-500 text-white py-2 px-4 rounded">
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

export default ModalEliminarEmpleado;
