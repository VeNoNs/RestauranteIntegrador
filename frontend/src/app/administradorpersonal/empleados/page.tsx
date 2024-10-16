'use client';
import React, { useState } from 'react';
import ModalAgregarEmpleado from '@/components/administradorpersonal/ModalAgregarEmpleado';
import ModalEliminarEmpleado from '@/components/administradorpersonal/ModalEliminarEmpleado';



const EmpleadosPage: React.FC = () => {
    const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
    const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
    const [empleadoSeleccionado, setEmpleadoSeleccionado] = useState<string | null>(null);
  
    const empleados = [
      { id: '1', nombre: 'Carlos Ruiz', rol: 'Cocina' },
      { id: '2', nombre: 'Ana Fernández', rol: 'Mozo' },
      { id: '3', nombre: 'Pedro Sánchez', rol: 'Administrador' },
    ];
  
    const handleAgregarClick = () => {
      setIsAgregarModalOpen(true);
    };
  
    const handleEliminarClick = () => {
      setIsEliminarModalOpen(true);
    };
  
    const seleccionarEmpleado = (id: string) => {
      setEmpleadoSeleccionado(id);
    };
  
    return (
      <div className="p-6">
        <h1 className="text-3xl font-bold text-black mb-6">Empleados</h1>
  
        {/* Botones superiores */}
        <div className="flex justify-end space-x-4 mb-6">
          <button
            onClick={handleAgregarClick}
            className="bg-black text-white py-2 px-4 rounded-md shadow-md hover:bg-gray-900"
          >
            Agregar
          </button>
  
          <button
            onClick={handleEliminarClick}
            className="bg-black text-white py-2 px-4 rounded-md shadow-md hover:bg-gray-900"
          >
            Eliminar
          </button>
        </div>
  
        {/* Tabla de empleados con diseño de Tailwind */}
        <div className="overflow-x-auto shadow-md rounded-lg">
          <table className="min-w-full bg-white">
            <thead className="bg-gray-100 text-left">
              <tr>
                <th className="px-6 py-3 text-black font-bold">Nombre</th>
                <th className="px-6 py-3 text-black font-bold">Rol</th>
              </tr>
            </thead>
            <tbody>
              {empleados.map((empleado) => (
                <tr
                  key={empleado.id}
                  className={`border-b hover:bg-gray-200 ${
                    empleadoSeleccionado === empleado.id ? 'bg-purple-100' : ''
                  }`}
                  onClick={() => seleccionarEmpleado(empleado.id)}
                >
                  <td className="px-6 py-4 text-black">{empleado.nombre}</td>
                  <td className="px-6 py-4 text-black">{empleado.rol}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
  
        {/* Modal para Agregar Empleado */}
        {isAgregarModalOpen && (
          <ModalAgregarEmpleado onClose={() => setIsAgregarModalOpen(false)} />
        )}
  
        {/* Modal para Eliminar Empleado */}
        {isEliminarModalOpen && (
          <ModalEliminarEmpleado onClose={() => setIsEliminarModalOpen(false)} />
        )}
      </div>
    );
  };
  
  export default EmpleadosPage;