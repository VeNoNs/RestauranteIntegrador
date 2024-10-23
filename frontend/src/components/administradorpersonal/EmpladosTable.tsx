import React from 'react';

interface Empleado {
  idEmpleado: number;
  nombreEmpleado: string;
  apellidoEmpleado: string;
  tipoEmpleado: string;
  
}

const EmpleadosTable: React.FC<{ empleados: Empleado[]; onSelectEmpleado: (empleado: Empleado) => void }> = ({ empleados, onSelectEmpleado }) => {
  return (
    <div className="bg-white shadow rounded-lg overflow-hidden">
      <table className="min-w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID Empleado</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre Empleado</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Apellido Empleado</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tipo Empleado</th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Acciones</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {empleados.map((empleado) => (
            <tr key={empleado.idEmpleado} className="hover:bg-purple-100">
              <td className="py-3 px-4 text-sm font-medium text-gray-900">{empleado.idEmpleado}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empleado.nombreEmpleado}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empleado.apellidoEmpleado}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empleado.tipoEmpleado}</td>
              <td className="py-3 px-4">
                <button
                  className="bg-black text-white px-4 py-2 rounded-full hover:bg-gray-800"
                  onClick={() => onSelectEmpleado(empleado)}
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

export default EmpleadosTable;
