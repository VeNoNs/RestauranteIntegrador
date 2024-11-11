import React, { useEffect, useState } from 'react';
import axios from 'axios';

interface Empresa {
  id: number;
  nombre: string;
  telefono: string;
  correo: string;
  administrador?: {
    nombreAdmin: string;
    correoAdmin: string;
  };
}

const EmpresasTable: React.FC<{ onSelectEmpresa: (empresa: Empresa) => void }> = ({ onSelectEmpresa }) => {
  const [empresas, setEmpresas] = useState<Empresa[]>([]);

  useEffect(() => {
    const fetchEmpresas = async () => {
      try {
        // Llamada para obtener las empresas
        const empresasResponse = await axios.get('http://localhost:8080/empresa/api/verempresas');
        const empresasData = empresasResponse.data;

        // Llamada para obtener los administradores de las empresas
        const administradoresResponse = await axios.get('http://localhost:8080/administrador-restaurante/api/ver-administradores');
        const administradoresData = administradoresResponse.data;

        // Mapeamos las empresas para incluir los datos del administrador
        const empresasConAdmin = empresasData.map((empresa: any) => {
          const admin = administradoresData.find((admin: any) => admin.empresa.id === empresa.idEmpresa);

          return {
            id: empresa.idEmpresa,
            nombre: empresa.nombreEmpresa,
            telefono: empresa.telefono,
            correo: empresa.usuario,
            administrador: admin
              ? { nombreAdmin: admin.nombreAdmin, correoAdmin: admin.correoAdmin }
              : null, // Si no hay administrador, lo dejamos como null
          };
        });

        setEmpresas(empresasConAdmin);
      } catch (error) {
        console.error('Error fetching empresas or administradores:', error);
      }
    };

    fetchEmpresas();
  }, []);

  return (
    <div className="bg-white shadow rounded-lg overflow-hidden">
      <table className="min-w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID Empresa</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre Empresa</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Teléfono</th>
            
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Administrador</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Correo Administrador</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Acciones</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {empresas.map((empresa) => (
            <tr key={empresa.id} className="hover:bg-purple-100">
              <td className="py-3 px-4 text-sm font-medium text-gray-900">{empresa.id}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.nombre}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.telefono}</td>
            
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.administrador?.nombreAdmin || 'Sin asignar'}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.administrador?.correoAdmin || 'Sin asignar'}</td>
              <td className="py-3 px-4">
                <button
                  className="bg-black text-white px-4 py-2 rounded-full hover:bg-gray-800"
                  onClick={() => onSelectEmpresa(empresa)}
                >
                  Editar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmpresasTable;
