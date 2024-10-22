import React from 'react';

interface Empresa {
  id: number;
  nombre: string;
  empresa: string;
  telefono: string;
  correo: string;
}

const EmpresasTable: React.FC<{ empresas: Empresa[]; onSelectEmpresa: (empresa: Empresa) => void }> = ({ empresas, onSelectEmpresa }) => {
  return (
    <div className="bg-white shadow rounded-lg overflow-hidden">
      <table className="min-w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID Empresa</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre Empresa</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Teléfono</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Correo</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Acciones</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {empresas.map((empresa) => (
            <tr key={empresa.id} className="hover:bg-purple-100">
              <td className="py-3 px-4 text-sm font-medium text-gray-900">{empresa.id}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.nombre}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.telefono}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.correo}</td>
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
