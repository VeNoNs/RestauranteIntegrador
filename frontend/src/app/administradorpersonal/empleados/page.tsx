'use client';
import React, { useEffect, useState } from 'react';
import ModalAgregarEmpleado from '@/components/administradorpersonal/ModalAgregarEmpleado';
import ModalEliminarEmpleado from '@/components/administradorpersonal/ModalEliminarEmpleado';
import EmpleadosTable from '@/components/administradorpersonal/EmpladosTable';

interface Empleado {
  idEmpleado: string;
  nombreEmpleado: string;
  apellidoEmpleado: string;
  tipoEmpleado: string;
  
}

const EmpleadosPage: React.FC = () => {
    const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
    const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
    const [empleadoSeleccionado, setEmpleadoSeleccionado] = useState<string | null>(null);
    const [empleados, setEmpleados] = useState<Empleado[]>([]);
  
   
  
    const handleAgregarClick = () => {
      setIsAgregarModalOpen(true);
    };
  
    const handleEliminarClick = () => {
      setIsEliminarModalOpen(true);
    };
  
    const seleccionarEmpleado = (id: string) => {
      setEmpleadoSeleccionado(id);
    };

    useEffect(() => {
      const fetchEmpleados = async () => {
        try {
          const response = await fetch('http://localhost:8080/empleado/api/verempleados');
          if (!response.ok) {
            throw new Error('Error al obtener empleados');
          }
          const data = await response.json();
          
          // Mapeo para alinear las propiedades del backend con las del frontend
          const empleadosMapped = data.map((empleado: any) => ({
            idEmpleado: empleado.idEmpleado,
            nombreEmpleado: empleado.nombreEmpleado,
            apellidoEmpleado: empleado.apellidoEmpleado,
            tipoEmpleado: empleado.tipoEmpleado,
          }));
  
          setEmpleados(empleadosMapped);
        } catch (error) {
          console.error('Error fetching empleados:', error);
        }
      };
  
      fetchEmpleados();
    }, []);
  
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
  
        {/* Tabla de empleados con diseño de Tailwind */}
        <div className="overflow-x-auto shadow-md rounded-lg">
          <table className="min-w-full bg-white">
            <thead className="bg-gray-100 text-left">
              <tr>
              <th className="px-6 py-3 text-black font-bold">ID</th>
                <th className="px-6 py-3 text-black font-bold">Nombre</th>
                <th className="px-6 py-3 text-black font-bold">Apellido</th>
                <th className="px-6 py-3 text-black font-bold">Tipo</th>
              </tr>
            </thead>
            <tbody>
            {empleados.map((empleado) => (
              <tr
                key={empleado.idEmpleado}
                className={`border-b hover:bg-gray-200 ${
                  empleadoSeleccionado === empleado.idEmpleado ? 'bg-purple-100' : ''
                }`}
                onClick={() => seleccionarEmpleado(empleado.idEmpleado)}
              > 
                <td className="px-6 py-4 text-black">{empleado.idEmpleado}</td>
                <td className="px-6 py-4 text-black">{empleado.nombreEmpleado}</td>
                <td className="px-6 py-4 text-black">{empleado.apellidoEmpleado}</td>
                <td className="px-6 py-4 text-black">{empleado.tipoEmpleado}</td>
              </tr>
            ))}
            </tbody>
          </table>
          {/*<EmpleadosTable empleados={empleados} onSelectEmpleado={handleE}*/}
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