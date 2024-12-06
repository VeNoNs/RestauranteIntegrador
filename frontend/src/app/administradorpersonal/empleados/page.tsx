'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ModalAgregarEmpleado from '@/components/administradorpersonal/ModalAgregarEmpleado';
import ModalEliminarEmpleado from '@/components/administradorpersonal/ModalEliminarEmpleado';

interface Empleado {
  idEmpleado: number;
  nombreEmpleado: string;
  apellidoEmpleado: string;
  tipoEmpleado: string;
  correo: string;
  local: {
    idLocal: number;
    ubicacion: string;
  };
}

const EmpleadosPage: React.FC = () => {
  const [empleados, setEmpleados] = useState<Empleado[]>([]);
  const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
  const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
  const [empleadoSeleccionado, setEmpleadoSeleccionado] = useState<number | null>(null);

  // Función para obtener los empleados
  const fetchEmpleados = async () => {
    try {
      const response = await axios.get('http://localhost:8080/empleado/api/verempleados');
      setEmpleados(response.data);
    } catch (error) {
      console.error('Error al obtener los empleados:', error);
    }
  };

  // Fetch de empleados al montar el componente
  useEffect(() => {
    fetchEmpleados();
  }, []);

  const handleAgregarClick = () => {
    setIsAgregarModalOpen(true);
  };

  const handleEliminarClick = () => {
    setIsEliminarModalOpen(true);
  };

  const seleccionarEmpleado = (idEmpleado: number) => {
    setEmpleadoSeleccionado(idEmpleado);
  };

  // Actualizar la tabla al cerrar los modales de agregar o eliminar
  const handleCloseAgregarModal = () => {
    setIsAgregarModalOpen(false);
    fetchEmpleados(); // Vuelve a cargar los empleados
  };

  const handleCloseEliminarModal = () => {
    setIsEliminarModalOpen(false);
    fetchEmpleados(); // Vuelve a cargar los empleados
  };

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold text-black mb-6">Empleados</h1>

      {/* Botones superiores */}
      <div className="flex justify-end space-x-4 mb-6">
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

      {/* Tabla de empleados */}
      <div className="overflow-x-auto shadow-md rounded-lg">
        <table className="min-w-full bg-white">
          <thead className="bg-gray-100 text-left">
            <tr>
              <th className="px-6 py-3 text-black font-bold">Nombre</th>
              <th className="px-6 py-3 text-black font-bold">Apellido</th>
              <th className="px-6 py-3 text-black font-bold">Correo</th>
              <th className="px-6 py-3 text-black font-bold">Rol</th>
              <th className="px-6 py-3 text-black font-bold">Local</th>
            </tr>
          </thead>
          <tbody>
            {empleados.map((empleado) => (
              <tr
                key={empleado.idEmpleado}
                className={`border-b hover:bg-gray-200 cursor-pointer ${
                  empleadoSeleccionado === empleado.idEmpleado ? 'bg-purple-100' : ''
                }`}
                onClick={() => seleccionarEmpleado(empleado.idEmpleado)}
              >
                <td className="px-6 py-4 text-black">{empleado.nombreEmpleado}</td>
                <td className="px-6 py-4 text-black">{empleado.apellidoEmpleado}</td>
                <td className="px-6 py-4 text-black">{empleado.correo}</td>
                <td className="px-6 py-4 text-black">{empleado.tipoEmpleado}</td>
                <td className="px-6 py-4 text-black">{empleado.local.ubicacion}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal para Agregar Empleado */}
      {isAgregarModalOpen && (
        <ModalAgregarEmpleado onClose={handleCloseAgregarModal} />
      )}

      {/* Modal para Eliminar Empleado */}
      {isEliminarModalOpen && (
        <ModalEliminarEmpleado onClose={handleCloseEliminarModal} />
      )}
    </div>
  );
};

export default EmpleadosPage;
